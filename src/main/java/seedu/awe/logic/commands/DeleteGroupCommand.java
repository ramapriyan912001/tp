package seedu.awe.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.awe.logic.parser.CliSyntax.PREFIX_GROUP_NAME;
import static seedu.awe.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.awe.logic.commands.exceptions.CommandException;
import seedu.awe.model.Model;
import seedu.awe.model.group.Group;

public class DeleteGroupCommand extends Command {
    public static final String COMMAND_WORD = "deletegroup";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": deletes a group from the awe book. "
            + "Parameters: "
            + PREFIX_GROUP_NAME + "GROUP_NAME\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_GROUP_NAME + "Bali";
    public static final String MESSAGE_SUCCESS = "Group %s with %d member(s) deleted";
    public static final String MESSAGE_GROUP_DOES_NOT_EXIST = "This group does not exist in the awe book";

    private Group group;

    /**
     * Delete group command, delete based on name
     *
     * @param group group object to be deleted
     */
    public DeleteGroupCommand(Group group) {
        this.group = group;
    }

    /**
     * Get group field
     *
     * @return Group object
     */
    public Group getGroup() {
        return group;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasGroup(group)) {
            throw new CommandException(MESSAGE_GROUP_DOES_NOT_EXIST);
        }

        Group groupFromInternalList = model.getGroupByName(group.getGroupName());
        model.deleteGroup(groupFromInternalList);
        model.setAllMembersOfGroup(groupFromInternalList);
        int numberOfMembers = groupFromInternalList.getMembers().size();
        String groupName = groupFromInternalList.getGroupName().getName();
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, groupName, numberOfMembers));
    }

    @Override
    public boolean equals (Object otherCommand) {
        if (this == otherCommand) {
            return true;
        }
        if (!(otherCommand instanceof DeleteGroupCommand)) {
            return false;
        }
        DeleteGroupCommand otherDeleteGroupCommand = (DeleteGroupCommand) otherCommand;
        return this.getGroup().equals(otherDeleteGroupCommand.getGroup());
    }
}
