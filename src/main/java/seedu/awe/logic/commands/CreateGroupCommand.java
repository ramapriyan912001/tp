package seedu.awe.logic.commands;
import static java.util.Objects.requireNonNull;
import static seedu.awe.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Set;

import seedu.awe.logic.commands.exceptions.CommandException;
import seedu.awe.model.Model;
import seedu.awe.model.group.Group;
import seedu.awe.model.group.GroupName;
import seedu.awe.model.person.Person;
import seedu.awe.model.tag.Tag;

public class CreateGroupCommand extends Command {
    public static final String COMMAND_WORD = "creategroup";
    public static final String MESSAGE_SUCCESS = "New group created";
    public static final String MESSAGE_ERROR = "Group not created. Be sure to use the exact names of group members";
    public static final String MESSAGE_DUPLICATE_GROUP = "This group already exists";
    public static final String MESSAGE_USAGE = "creategroup gn/[GROUPNAME] n/[NAME1] n/[OPTIONAL NAME2]...";
    public static final String MESSAGE_EMPTY_GROUP = "Group requires at least 1 member. \n%1$s\n%s";
    public static final String MESSAGE_INVALID_NAMES = "None of the names are in your contact book.";

    private final ArrayList<Person> members;
    private final GroupName groupName;
    private final boolean isValidCommand;
    private final Set<Tag> tags;

    /**
     * Creates a CreateGroupCommand to create the specified {@code Group}
     */
    public CreateGroupCommand(GroupName groupName, ArrayList<Person> members, boolean isValidCommand, Set<Tag> tags) {
        requireAllNonNull(groupName, members, isValidCommand, tags);
        this.groupName = groupName;
        this.members = members;
        this.isValidCommand = isValidCommand;
        this.tags = tags;
    }

    public ArrayList<Person> getMembers() {
        return members;
    }

    public GroupName getGroupName() {
        return groupName;
    }

    public boolean getValidCommand() {
        return isValidCommand;
    }

    /**
     * Returns int object tracking the number of non matching members between this.members and otherMembers.
     *
     * @param numberOfNonMatchingMembers int value to track the number of members in otherMembers
     *                                   that are not present in this.members.
     * @param member Person object that is being searched for in otherMembers.
     * @param otherMembers List of Person objects from another instance of CreateGroupCommand.
     * @return int object to track the number of members in otherMembers that are not present in this.members.
     */
    public int checkForMember(int numberOfNonMatchingMembers, Person member, ArrayList<Person> otherMembers) {
        for (Person otherMember : otherMembers) {
            if (member.equals(otherMember)) {
                numberOfNonMatchingMembers--;
                break;
            }
        }
        return numberOfNonMatchingMembers;
    }

    /**
     * Returns a boolean object representing if this.members contains the same Person objects as otherMembers.
     *
     * @param otherMembers List of Person objects from another instance of CreateGroupCommand.
     * @return boolean object representing if this.members contains the same Person objects as otherMembers.
     */
    public boolean checkSameMembers(ArrayList<Person> otherMembers) {
        int numberOfNonMatchingMembers = otherMembers.size();
        for (Person member : this.members) {
            numberOfNonMatchingMembers = checkForMember(numberOfNonMatchingMembers, member, otherMembers);
        }
        return numberOfNonMatchingMembers == 0;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!isValidCommand) {
            return new CommandResult(MESSAGE_ERROR);
        }

        Group group = new Group(groupName, members, tags);
        if (model.hasGroup(group)) {
            throw new CommandException(MESSAGE_DUPLICATE_GROUP);
        }
        model.addGroup(group);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof CreateGroupCommand)) {
            return false;
        }
        CreateGroupCommand otherCommand = (CreateGroupCommand) other;
        if (this.isValidCommand == (otherCommand.getValidCommand())
                && checkSameMembers(otherCommand.getMembers())
                && this.groupName.equals(otherCommand.getGroupName())) {
            return true;
        }
        return false;
    }
}
