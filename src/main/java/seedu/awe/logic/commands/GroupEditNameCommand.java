package seedu.awe.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.awe.commons.core.Messages.MESSAGE_GROUPEDITNAMECOMMAND_ERROR;
import static seedu.awe.commons.core.Messages.MESSAGE_GROUPEDITNAMECOMMAND_EXISTING_GROUP;
import static seedu.awe.commons.core.Messages.MESSAGE_GROUPEDITNAMECOMMAND_SUCCESS;
import static seedu.awe.commons.core.Messages.MESSAGE_GROUPEDITNAMECOMMAND_USAGE;
import static seedu.awe.commons.core.Messages.MESSAGE_NONEXISTENT_GROUP;
import static seedu.awe.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import javafx.collections.ObservableList;
import seedu.awe.logic.commands.exceptions.CommandException;
import seedu.awe.model.Model;
import seedu.awe.model.group.Group;
import seedu.awe.model.group.GroupName;

public class GroupEditNameCommand extends Command {
    public static final String COMMAND_WORD = "groupeditname";

    private final GroupName oldGroupName;
    private final GroupName newGroupName;
    private final boolean isValidCommand;

    /**
     * Creates a GroupEditNameCommand to edit the specified {@code GroupName}
     */
    public GroupEditNameCommand(GroupName oldGroupName, GroupName newGroupName, boolean isValidCommand) {
        requireAllNonNull(oldGroupName, newGroupName, isValidCommand);
        this.oldGroupName = oldGroupName;
        this.newGroupName = newGroupName;
        this.isValidCommand = isValidCommand;
    }

    public GroupName getOldGroupName() {
        return oldGroupName;
    }

    public GroupName getNewGroupName() {
        return newGroupName;
    }

    public boolean getValidCommand() {
        return isValidCommand;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!isValidCommand) {
            throw new CommandException(MESSAGE_GROUPEDITNAMECOMMAND_ERROR + MESSAGE_GROUPEDITNAMECOMMAND_USAGE);
        }
        Group oldGroup = model.getGroupByName(oldGroupName);
        if (Objects.isNull(oldGroup)) {
            throw new CommandException(String.format(MESSAGE_NONEXISTENT_GROUP, oldGroupName));
        }
        if (findExistingGroupName(newGroupName, model.getAddressBook().getGroupList())) {
            throw new CommandException(
                    String.format(MESSAGE_GROUPEDITNAMECOMMAND_EXISTING_GROUP, newGroupName.getName()));
        }
        Group newGroup = oldGroup.editName(newGroupName);
        model.setGroup(oldGroup, newGroup);
        model.setAllMembersOfGroup(newGroup);
        return new CommandResult(String.format(MESSAGE_GROUPEDITNAMECOMMAND_SUCCESS, newGroupName));
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof GroupEditNameCommand)) {
            return false;
        }
        GroupEditNameCommand otherCommand = (GroupEditNameCommand) other;
        if (this.isValidCommand == otherCommand.getValidCommand()
                && this.oldGroupName.equals(otherCommand.getOldGroupName())
                && this.newGroupName.equals(otherCommand.getNewGroupName())) {
            return true;
        }
        return false;
    }

    /**
     * Returns boolean object representing whether a group exists within AWE with the same name as newGroupName.
     * @param newGroupName GroupName object that will be set as the new group name.
     * @param groupObservableList ObservableList of groups currently in AWE.
     * @return boolean object representing whether a group exists within AWE with the same name as newGroupName.
     */
    private boolean findExistingGroupName(GroupName newGroupName, ObservableList<Group> groupObservableList) {
        for (Group group : groupObservableList) {
            if (group.getGroupName().equals(newGroupName)) {
                return true;
            }
        }
        return false;
    }
}
