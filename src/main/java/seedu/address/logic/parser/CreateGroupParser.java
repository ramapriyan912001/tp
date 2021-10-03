package seedu.address.logic.parser;

import java.util.ArrayList;
import java.util.Optional;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.CreateGroupCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.group.GroupName;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

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
        GroupName groupName = new GroupName(findGroupName(args));
        Optional<ArrayList<Person>> members = findGroupMembers(args);
        ArrayList<Person> listOfMembers = new ArrayList<>();
        boolean validCommand = true;
        if (groupName.equals(BAD_FORMATTING) || members.isEmpty()) {
            validCommand = false;
        }
        if (!members.isEmpty()) {
            listOfMembers = members.get();
        }
        return new CreateGroupCommand(groupName, listOfMembers, validCommand);
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
    private Optional<ArrayList<Person>> findGroupMembers(String args) {
        try {
            int startIndex = args.indexOf(':') + 2;
            String teamMembers = args.substring(startIndex);

            int nextColon = teamMembers.indexOf(':');
            while (nextColon != -1) {
                Name memberName = new Name(teamMembers.substring(0, nextColon - 1));
                Optional<ArrayList<Person>> updatedMembers = addMemberIfExist(memberName);
                if (updatedMembers.isEmpty()) {
                    return Optional.empty();
                }
                teamMembers = teamMembers.substring(nextColon + 2);
                nextColon = teamMembers.indexOf(':');
            }
            Optional<ArrayList<Person>> updatedMembers = addMemberIfExist(new Name(teamMembers));
            if (updatedMembers.isEmpty()) {
                return Optional.empty();
            }
            if (toBeAddedToGroup.size() == 0) {
                return Optional.empty();
            } else {
                return Optional.of(toBeAddedToGroup);
            }
        } catch (IndexOutOfBoundsException e) {
            return Optional.empty();
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
}
