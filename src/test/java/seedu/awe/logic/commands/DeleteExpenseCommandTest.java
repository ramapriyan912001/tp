package seedu.awe.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.awe.model.Model;
import seedu.awe.model.ModelManager;
import seedu.awe.model.UserPrefs;
import seedu.awe.model.expense.Expense;
import seedu.awe.model.group.Group;
import seedu.awe.model.group.GroupName;
import seedu.awe.model.person.Person;

import static seedu.awe.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.awe.testutil.TypicalExpenses.getTypicalAddressBook;
import static seedu.awe.testutil.TypicalIndexes.INDEX_FIRST_EXPENSE;
import static seedu.awe.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

public class DeleteExpenseCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Group group = getTypicalAddressBook().getGroupByName(new GroupName("Bali"));

    @Test
    public void execute_validIndexUnfilteredList_success() {
        model.setExpenses(group);
        Expense expenseToDelete = model.getExpenses().get(INDEX_FIRST_EXPENSE.getZeroBased());
        DeleteExpenseCommand deleteExpenseCommand = new DeleteExpenseCommand(INDEX_FIRST_EXPENSE);

        String expectedMessage = String.format(DeleteExpenseCommand.MESSAGE_SUCCESS,
                expenseToDelete.getDescription().getFullDescription());

        ModelManager expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.setExpenses(group);
        Group newGroup = group.deleteExpense(expenseToDelete);
        expectedModel.setGroup(group, newGroup);
        expectedModel.deleteExpense(expenseToDelete, newGroup);

        assertCommandSuccess(deleteExpenseCommand, model, expectedMessage, expectedModel);
    }



}