package seedu.address.logic.commands;
import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupName;
import seedu.address.model.person.Person;

public class CreateGroupCommand extends Command {
    public static final String COMMAND_WORD = "creategroup";
    public static final String MESSAGE_SUCCESS = "New group created";
    public static final String MESSAGE_ERROR = "Group not created. Be sure to use the exact names of group members";
    public static final String MESSAGE_DUPLICATE_GROUP = "This group already exists";

    private final ArrayList<Person> members;
    private GroupName groupName;
    private final boolean validCommand;

    /**
     * Creates a CreateGroupCommand to create the specified {@code Group}
     */
    public CreateGroupCommand(GroupName groupName, ArrayList<Person> members, boolean validCommand) {
        this.groupName = groupName;
        this.members = members;
        this.validCommand = validCommand;
    }

    /**
     * Adds a given group object into the groups attribute for each member.
     *
     * @param group Group object that is added to every member.
     */
    public void addGroupForAllMembers(Group group) {
        for (Person member : members) {
            member.addGroup(group);
        }
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!validCommand) {
            return new CommandResult(MESSAGE_ERROR);
        }
        Group group = new Group(groupName, members);
        addGroupForAllMembers(group);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
