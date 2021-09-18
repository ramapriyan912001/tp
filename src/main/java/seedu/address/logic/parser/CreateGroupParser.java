package seedu.address.logic.parser;

import java.util.ArrayList;
import java.util.Objects;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.CreateGroupCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

public class CreateGroupParser implements Parser<CreateGroupCommand> {
    private static final String BAD_FORMATTING = "\"creategroup command\" is not properly formatted";
    private ObservableList<Person> allMembers;
    private final ArrayList<Person> toBeAddedToGroup;

    public CreateGroupParser(Model model) {
        ReadOnlyAddressBook addressBook = model.getAddressBook();
        this.allMembers = addressBook.getPersonList();
        this.toBeAddedToGroup = new ArrayList<>();
    }
    //assume command is of the following form
    //creategroup Berlin : Chrus Chong : Nancy Chua : Tom Hiddleston
    public CreateGroupCommand parse(String args) throws ParseException {
        String groupName = findGroupName(args);
        ArrayList<Person> members = findGroupMembers(args);
        boolean validCommand = true;
        if (groupName.equals(BAD_FORMATTING) || Objects.isNull(members)) {
            validCommand = false;
        }
        return new CreateGroupCommand(groupName, members, validCommand);
    }

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

    private ArrayList<Person> findGroupMembers(String args) {
        try {
            int startIndex = args.indexOf(':') + 2;
            String teamMembers = args.substring(startIndex);

            int nextColon = teamMembers.indexOf(':');
            while (nextColon != -1) {
                Name memberName = new Name(teamMembers.substring(0, nextColon - 1));
                if (Objects.isNull(addMemberIfExist(memberName))) {
                    return null;
                }
                teamMembers = teamMembers.substring(nextColon + 2);
                nextColon = teamMembers.indexOf(':');
            }
            if (Objects.isNull(addMemberIfExist(new Name(teamMembers)))) {
                return null;
            }
            if (toBeAddedToGroup.size() == 0) {
                return null;
            } else {
                return toBeAddedToGroup;
            }
        } catch (IndexOutOfBoundsException e) {
            return null;
        }

    }

    private ArrayList<Person> addMemberIfExist(Name memberName) {
        Person memberFound = findMember(memberName, this.allMembers);
        if (!Objects.isNull(memberFound)) {
            toBeAddedToGroup.add(memberFound);
        } else {
            return null;
        }
        return this.toBeAddedToGroup;
    }

    private Person findMember(Name memberName, ObservableList<Person> members) {
        for (Person member : members) {
            if (member.getName().equals(memberName)) {
                return member;
            }
        }
        return null;
    }
}
