package seedu.awe.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.awe.commons.core.Messages.MESSAGE_GROUPEDITNAMECOMMAND_SAME_NAME;
import static seedu.awe.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.awe.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.awe.testutil.Assert.assertThrows;
import static seedu.awe.testutil.TypicalGroups.BALI;
import static seedu.awe.testutil.TypicalGroups.getTypicalAddressBook;

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
    private static final GroupName TAIWAN_GROUP_NAME = new GroupName("Taiwan");
    private static final GroupName LONDON_GROUP_NAME = new GroupName("London");

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullGroup_throwsNullPointerException() {
        // old group name null
        assertThrows(NullPointerException.class, () ->
                new GroupEditNameCommand(null, new GroupName("Bali")));

        // new group name null
        assertThrows(NullPointerException.class, () ->
                new GroupEditNameCommand(new GroupName("Bali"), null));
    }

    @Test
    public void getters_validConstructor_success() {
        GroupEditNameCommand groupEditNameCommand = new GroupEditNameCommand(BALI_GROUP_NAME, JAPAN_GROUP_NAME);

        // getOldGroupName
        assertEquals(groupEditNameCommand.getOldGroupName(), BALI_GROUP_NAME);

        // getNewGroupName
        assertEquals(groupEditNameCommand.getNewGroupName(), JAPAN_GROUP_NAME);
    }

    @Test
    public void execute_noGroupFound_failure() {
        GroupEditNameCommand groupEditNameCommand = new GroupEditNameCommand(JAPAN_GROUP_NAME, BALI_GROUP_NAME);

        String expectedMessage = String.format(Messages.MESSAGE_NONEXISTENT_GROUP, JAPAN_GROUP_NAME);

        assertCommandFailure(groupEditNameCommand, model, expectedMessage);
    }

    @Test
    public void execute_changeToSameName_failure() {
        GroupEditNameCommand groupEditNameCommand = new GroupEditNameCommand(BALI_GROUP_NAME, BALI_GROUP_NAME);

        String expectedMessage = String.format(Messages.MESSAGE_GROUPEDITNAMECOMMAND_EXISTING_GROUP, BALI_GROUP_NAME);

        assertCommandFailure(groupEditNameCommand, model, expectedMessage);
    }

    @Test
    public void execute_changeToExistingName_failure() {
        GroupEditNameCommand groupEditNameCommand = new GroupEditNameCommand(BALI_GROUP_NAME, LONDON_GROUP_NAME);

        String expectedMessage = String.format(Messages.MESSAGE_GROUPEDITNAMECOMMAND_EXISTING_GROUP, LONDON_GROUP_NAME);

        assertCommandFailure(groupEditNameCommand, model, expectedMessage);
    }

    @Test
    public void execute_validInputs_success() {
        GroupEditNameCommand groupEditNameCommand = new GroupEditNameCommand(BALI_GROUP_NAME, JAPAN_GROUP_NAME);

        CommandResult expectedCommandResult = new CommandResult(
                String.format(Messages.MESSAGE_GROUPEDITNAMECOMMAND_SUCCESS, JAPAN_GROUP_NAME));

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Group updatedGroup = new GroupBuilder(BALI).withGroupName(JAPAN_GROUP_NAME.getName()).build();
        expectedModel.setGroup(BALI, updatedGroup);

        assertCommandSuccess(groupEditNameCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_sameGroupName_failure() {
        GroupEditNameCommand groupEditNameCommand = new GroupEditNameCommand(BALI_GROUP_NAME, BALI_GROUP_NAME);

        CommandResult expectedCommandResult = new CommandResult(
                String.format(Messages.MESSAGE_GROUPEDITNAMECOMMAND_SUCCESS, JAPAN_GROUP_NAME));


        assertCommandFailure(groupEditNameCommand, model, MESSAGE_GROUPEDITNAMECOMMAND_SAME_NAME);
    }

    @Test
    public void equals() {
        GroupEditNameCommand groupEditNameCommand = new GroupEditNameCommand(BALI_GROUP_NAME, JAPAN_GROUP_NAME);
        // null input -> false
        assertFalse(groupEditNameCommand.equals(null));

        // Not same class -> false
        assertFalse(groupEditNameCommand.equals(new ListContactsCommand()));

        // Same instance -> true
        assertTrue(groupEditNameCommand.equals(groupEditNameCommand));

        // Different instance but same details -> true
        GroupEditNameCommand groupEditNameCommandToCheck =
                new GroupEditNameCommand(BALI_GROUP_NAME, JAPAN_GROUP_NAME);
        assertTrue(groupEditNameCommand.equals(groupEditNameCommandToCheck));

        // Different instance with different oldGroup -> false
        GroupEditNameCommand groupEditNameCommandDifferentOldGroup =
                new GroupEditNameCommand(BALI_GROUP_NAME, TAIWAN_GROUP_NAME);
        assertFalse(groupEditNameCommand.equals(groupEditNameCommandDifferentOldGroup));

        // Different instance with different oldGroup -> false
        GroupEditNameCommand groupEditNameCommandDifferentNewGroup =
                new GroupEditNameCommand(TAIWAN_GROUP_NAME, JAPAN_GROUP_NAME);
        assertFalse(groupEditNameCommand.equals(groupEditNameCommandDifferentNewGroup));
    }
}
