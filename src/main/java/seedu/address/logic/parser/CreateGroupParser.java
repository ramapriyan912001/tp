package seedu.address.logic.parser;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Stream;
import java.util.Optional;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.CreateGroupCommand;
import seedu.address.logic.parser.exceptions.EmptyGroupException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.group.GroupName;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CreateGroupCommand.MESSAGE_EMPTY_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

public class CreateGroupParser implements Parser<CreateGroupCommand> {
    private static final String BAD_FORMATTING = "\"creategroup command\" is not properly formatted";
    private ObservableList<Person> allMembers;
    private final ArrayList<Person> toBeAddedToGroup;

    /**
     * Creates new CreateGroupParser object.
     *
     * @param model Model object passed into constructor to provide list of contacts.
     */
    public CreateGroupParser(Model model) {
        ReadOnlyAddressBook addressBook = model.getAddressBook();
        this.allMembers = addressBook.getPersonList();
        this.toBeAddedToGroup = new ArrayList<>();
    }

    /**
     * Returns CreateGroupCommand based on user input.
     *
     * @param args User input into addressbook.
     * @return CreateGroupCommand object to represent command to be executed.
     * @throws ParseException If user input is incorrectly formatted.
     */
    public CreateGroupCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenizeStringWithRepeatedPrefixes(args, PREFIX_NAME, PREFIX_GROUP_NAME);

        if (!arePrefixesPresent(argMultimap, PREFIX_GROUP_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateGroupCommand.MESSAGE_USAGE));
        }

        ArrayList<Person> members = findGroupMembers(args);

        GroupName groupName = ParserUtil.parseGroupName(argMultimap.getValue(PREFIX_GROUP_NAME).get());

        boolean validCommand = true;
        if (groupName.equals(BAD_FORMATTING) || members.isEmpty()) {
            validCommand = false;
        }
        return new CreateGroupCommand(groupName, members, validCommand);
    }

    /**
     * Returns group name from user input.
     * If user input invalid, BAD_FORMATTING is returned.
     *
     * @param args String object representing user input into the addressbook.
     * @return String object representing group name.
     */
    private String findGroupName(String args) {
        //assuming it is already a valid command which will be checked beforehand
        try {
            int startIndex = 1;
            int endIndex = args.indexOf(':') - 1;
            return args.substring(startIndex, endIndex);
        } catch (IndexOutOfBoundsException e) {
            return BAD_FORMATTING;
        }
    }

    /**
     * Returns Optional object containing list of members in the group.
     * If user input not valid, empty Optional object is returned.
     * Use Optional object here to avoid multiple conditionals dealing with null returns.
     *
     * @param args String object representing user input into the addressbook.
     * @return Optional object containing ArrayList of Person objects representing members to be added to the group.
     */
    private ArrayList<Person> findGroupMembers(String args) throws ParseException {
        try {
            int startIndex = args.indexOf(" n/") + 3;

            if (startIndex == 1) {
                throw new ParseException(MESSAGE_INVALID_COMMAND_FORMAT);
            }
            String teamMembers = args.substring(startIndex);
            int nextPrefix = teamMembers.indexOf(" n/");
            while (nextPrefix != -1) {
                Name memberName = new Name(teamMembers.substring(0, nextPrefix - 1));
                if (Objects.isNull(addMemberIfExist(memberName))) {
                    return null;
                }
                teamMembers = teamMembers.substring(nextPrefix + 3);
                nextPrefix = teamMembers.indexOf("n/");
            }
            Optional<ArrayList<Person>> updatedMembers = addMemberIfExist(new Name(teamMembers));
            if (updatedMembers.isEmpty()) {
                return Optional.empty();
            }
            if (toBeAddedToGroup.size() == 0) {
                throw new EmptyGroupException(MESSAGE_EMPTY_GROUP);
            } else {
                return Optional.of(toBeAddedToGroup);
            }
        } catch (IndexOutOfBoundsException e) {
            return null;
        } catch (EmptyGroupException err) {
            throw new EmptyGroupException(String.format(MESSAGE_EMPTY_GROUP, CreateGroupCommand.MESSAGE_USAGE));
        } catch (ParseException err) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateGroupCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Returns Optional object containing list of members in the group.
     * If user input not valid, empty Optional is returned.
     * Use Optional object here to avoid multiple conditionals dealing with null returns.
     *
     * @param memberName Name object representing name of member to be added.
     * @return Optional object containing ArrayList of Person objects representing members to be added to the group.
     */
    private Optional<ArrayList<Person>> addMemberIfExist(Name memberName) {
        Optional<Person> memberFound = findMember(memberName, this.allMembers);
        if (!memberFound.isEmpty()) {
            toBeAddedToGroup.add(memberFound.get());
        } else {
            return Optional.empty();
        }
        return Optional.of(this.toBeAddedToGroup);
    }

    /**
     * Returns Optional object containing person that matches a given Name object if in addressbook.
     * If person not in addressbook, empty Optional is returned.
     * Use Optional object here to avoid multiple conditionals dealing with null returns.
     *
     * @param memberName Name object representing person that is being searched for in the addressbook.
     * @param members ObservableList of Person objects representing all contacts in the addressbook.
     * @return Optional object containing Person object if name matches that of a person from the addressbook.
     */
    private Optional<Person> findMember(Name memberName, ObservableList<Person> members) {
        Optional<Person> foundPerson;
        for (Person member : members) {
            if (member.getName().equals(memberName)) {
                foundPerson = Optional.of(member);
                return foundPerson;
            }
        }
        return Optional.empty();
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
