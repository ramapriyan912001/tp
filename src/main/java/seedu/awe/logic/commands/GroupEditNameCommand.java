package seedu.awe.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.awe.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.awe.commons.core.Messages;
import seedu.awe.logic.commands.exceptions.CommandException;
import seedu.awe.logic.parser.ParserUtil;
import seedu.awe.model.Model;
import seedu.awe.model.group.Group;
import seedu.awe.model.group.GroupName;

public class GroupEditNameCommand extends Command {
    public static final String COMMAND_WORD = "groupeditname";

    private final GroupName oldGroupName;
    private final GroupName newGroupName;

    /**
     * Creates a GroupEditNameCommand to edit the specified {@code GroupName}
     */
    public GroupEditNameCommand(GroupName oldGroupName, GroupName newGroupName) {
        requireAllNonNull(oldGroupName, newGroupName);
        this.oldGroupName = oldGroupName;
        this.newGroupName = newGroupName;
    }

    public GroupName getOldGroupName() {
        return oldGroupName;
    }

    public GroupName getNewGroupName() {
        return newGroupName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Group oldGroup = model.getGroupByName(oldGroupName);
        if (Objects.isNull(oldGroup)) {
            throw new CommandException(String.format(Messages.MESSAGE_NONEXISTENT_GROUP, oldGroupName));
        }
        if (newGroupName.equals(oldGroupName)) {
            throw new CommandException(Messages.MESSAGE_GROUPEDITNAMECOMMAND_SAME_NAME);
        }
        if (ParserUtil.findExistingGroupName(newGroupName, model.getAwe().getGroupList())) {
            throw new CommandException(
                    String.format(Messages.MESSAGE_GROUPEDITNAMECOMMAND_EXISTING_GROUP, newGroupName.getName()));
        }
        Group newGroup = oldGroup.editName(newGroupName);
        model.setGroup(oldGroup, newGroup);
        model.setAllMembersOfGroup(newGroup);
        return new CommandResult(String.format(Messages.MESSAGE_GROUPEDITNAMECOMMAND_SUCCESS, newGroupName));
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof GroupEditNameCommand)) {
            return false;
        }
        GroupEditNameCommand otherCommand = (GroupEditNameCommand) other;
        if (this.oldGroupName.equals(otherCommand.getOldGroupName())
                && this.newGroupName.equals(otherCommand.getNewGroupName())) {
            return true;
        }
        return false;
    }
}
