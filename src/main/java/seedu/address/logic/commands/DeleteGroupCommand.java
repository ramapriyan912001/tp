package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP_NAME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;

public class DeleteGroupCommand extends Command {
    public static final String COMMAND_WORD = "deletegroup";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": deletes a group from the address book. "
            + "Parameters: "
            + PREFIX_GROUP_NAME + "GROUP_NAME ";
    public static final String MESSAGE_SUCCESS = "Group deleted: %1$s";
    public static final String MESSAGE_GROUP_DOES_NOT_EXIST = "This group does not exist in the address book";

    private Group group;

    /**
     * Delete group command, delete based on name
     * @param group group object to be deleted
     */
    public DeleteGroupCommand(Group group) {
        this.group = group;
    }

    /**
     * Get group field
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

        model.deleteGroup(group);
        return new CommandResult(String.format(MESSAGE_SUCCESS, group));
    }
}
