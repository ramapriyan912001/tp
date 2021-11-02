package seedu.awe.logic.commands;

import static seedu.awe.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.awe.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.awe.testutil.Assert.assertThrows;
import static seedu.awe.testutil.TypicalGroups.BALI;
import static seedu.awe.testutil.TypicalGroups.getTypicalAddressBook;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.awe.commons.core.Messages;
import seedu.awe.model.Model;
import seedu.awe.model.ModelManager;
import seedu.awe.model.UserPrefs;
import seedu.awe.model.group.Group;
import seedu.awe.model.group.GroupName;
import seedu.awe.testutil.GroupBuilder;


public class GroupEditNameCommandTest {
    private static final GroupName BALI_GROUP_NAME = new GroupName("Bali");
    private static final GroupName JAPAN_GROUP_NAME = new GroupName("Japan");

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullGroup_throwsNullPointerException() {
        // old group name null
        assertThrows(NullPointerException.class, () ->
                new GroupEditNameCommand(null, new GroupName("Bali"), true));

        // new group name null
        assertThrows(NullPointerException.class, () ->
                new GroupEditNameCommand(new GroupName("Bali"), null, true));
    }

    @Test
    public void getters_validConstructor_success() {
        GroupEditNameCommand groupEditNameCommand = new GroupEditNameCommand(BALI_GROUP_NAME, JAPAN_GROUP_NAME, true);

        // getOldGroupName
        Assertions.assertEquals(groupEditNameCommand.getOldGroupName(), BALI_GROUP_NAME);

        // getNewGroupName
        Assertions.assertEquals(groupEditNameCommand.getNewGroupName(), JAPAN_GROUP_NAME);

        // getValidCommand
        Assertions.assertTrue(groupEditNameCommand.getValidCommand());
    }


    @Test
    public void execute_invalidCommand_failure() {
        GroupEditNameCommand groupEditNameCommand = new GroupEditNameCommand(BALI_GROUP_NAME, JAPAN_GROUP_NAME, false);

        String expectedMessage = Messages.MESSAGE_GROUPEDITNAMECOMMAND_ERROR + "\n"
                + Messages.MESSAGE_GROUPEDITNAMECOMMAND_USAGE;

        assertCommandFailure(groupEditNameCommand, model, expectedMessage);
    }

    @Test
    public void execute_noGroupFound_failure() {
        GroupEditNameCommand groupEditNameCommand = new GroupEditNameCommand(JAPAN_GROUP_NAME, BALI_GROUP_NAME, true);

        String expectedMessage = String.format(Messages.MESSAGE_NONEXISTENT_GROUP, JAPAN_GROUP_NAME);

        assertCommandFailure(groupEditNameCommand, model, expectedMessage);
    }

    @Test
    public void execute_validInputs_success() {
        GroupEditNameCommand groupEditNameCommand = new GroupEditNameCommand(BALI_GROUP_NAME, JAPAN_GROUP_NAME, true);

        CommandResult expectedCommandResult = new CommandResult(
                String.format(Messages.MESSAGE_GROUPEDITNAMECOMMAND_SUCCESS, JAPAN_GROUP_NAME));

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Group updatedGroup = new GroupBuilder(BALI).withGroupName(JAPAN_GROUP_NAME.getName()).build();
        expectedModel.setGroup(BALI, updatedGroup);

        assertCommandSuccess(groupEditNameCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void equals() {
        GroupEditNameCommand groupEditNameCommand = new GroupEditNameCommand(BALI_GROUP_NAME, JAPAN_GROUP_NAME, true);
        // null input -> false
        Assertions.assertFalse(groupEditNameCommand.equals(null));

        // Not same class -> false
        Assertions.assertFalse(groupEditNameCommand.equals(new ListContactsCommand()));

        // Same instance -> true
        Assertions.assertTrue(groupEditNameCommand.equals(groupEditNameCommand));

        // Different instance but same details -> true
        GroupEditNameCommand groupEditNameCommandToCheck =
                new GroupEditNameCommand(BALI_GROUP_NAME, JAPAN_GROUP_NAME, true);
        Assertions.assertTrue(groupEditNameCommand.equals(groupEditNameCommandToCheck));

        // Difference instance with different details -> false
        GroupEditNameCommand groupEditNameCommandDifferentDetails =
                new GroupEditNameCommand(JAPAN_GROUP_NAME, BALI_GROUP_NAME, true);
        Assertions.assertFalse(groupEditNameCommand.equals(groupEditNameCommandDifferentDetails));
    }
}
