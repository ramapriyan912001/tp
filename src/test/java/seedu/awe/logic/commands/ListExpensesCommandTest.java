package seedu.awe.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.awe.commons.core.Messages.MESSAGE_LISTEXPENSESCOMMAND_GROUP_NOT_FOUND;
import static seedu.awe.commons.core.Messages.MESSAGE_LISTEXPENSESCOMMAND_SUCCESS;
import static seedu.awe.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.awe.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.awe.testutil.Assert.assertThrows;
import static seedu.awe.testutil.TypicalGroups.BALI;
import static seedu.awe.testutil.TypicalGroups.OSLO;

import org.junit.jupiter.api.Test;

import seedu.awe.model.Model;
import seedu.awe.model.group.GroupName;
import seedu.awe.testutil.ModelBuilder;


public class ListExpensesCommandTest {
    private Model model = new ModelBuilder().build();
    private Model expectedModel = new ModelBuilder().build();

    @Test
    public void constructor_nullGroupName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ListExpensesCommand(null));
    }

    @Test
    public void execute_invalidGroupName_throwsCommandException() {
        GroupName test = new GroupName("test");
        ListExpensesCommand listExpensesCommand = new ListExpensesCommand(test);
        assertCommandFailure(listExpensesCommand, model, MESSAGE_LISTEXPENSESCOMMAND_GROUP_NOT_FOUND);
    }

    @Test
    public void execute_showExpenses_success() {
        ListExpensesCommand listExpensesCommand = new ListExpensesCommand(BALI.getGroupName());
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_LISTEXPENSESCOMMAND_SUCCESS,
                false, false, false, false,
                true, false, false);
        assertCommandSuccess(listExpensesCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void equals() {
        ListExpensesCommand listExpensesBaliCommand = new ListExpensesCommand(BALI.getGroupName());
        ListExpensesCommand listExpensesOsloCommand = new ListExpensesCommand(OSLO.getGroupName());

        // same object -> returns true
        assertTrue(listExpensesBaliCommand.equals(listExpensesBaliCommand));
        assertTrue(listExpensesOsloCommand.equals(listExpensesOsloCommand));

        // different types -> returns false
        assertFalse(listExpensesBaliCommand.equals(1));

        // null -> returns false
        assertFalse(listExpensesBaliCommand.equals(null));

        // different group name -> returns false
        assertFalse(listExpensesBaliCommand.equals(listExpensesOsloCommand));
    }

}
