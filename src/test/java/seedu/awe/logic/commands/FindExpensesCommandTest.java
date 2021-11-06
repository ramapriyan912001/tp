package seedu.awe.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.awe.commons.core.Messages.MESSAGE_EXPENSES_LISTED_OVERVIEW;
import static seedu.awe.commons.core.Messages.MESSAGE_FINDEXPENSESCOMMAND_GROUP_NOT_FOUND;
import static seedu.awe.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.awe.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.awe.testutil.TypicalExpenses.BUFFET;
import static seedu.awe.testutil.TypicalExpenses.SOUVENIRS;
import static seedu.awe.testutil.TypicalExpenses.getTypicalAddressBook;
import static seedu.awe.testutil.TypicalGroups.BALI;
import static seedu.awe.testutil.TypicalGroups.VIENNA_NOT_IN_GROUPS;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.awe.model.Model;
import seedu.awe.model.ModelManager;
import seedu.awe.model.UserPrefs;
import seedu.awe.model.expense.DescriptionContainsKeywordsPredicate;
import seedu.awe.model.group.Group;
import seedu.awe.model.group.GroupName;
import seedu.awe.testutil.ModelBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code FindExpensesCommand}.
 */
public class FindExpensesCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        DescriptionContainsKeywordsPredicate firstPredicate =
                new DescriptionContainsKeywordsPredicate(Collections.singletonList("first"));
        DescriptionContainsKeywordsPredicate secondPredicate =
                new DescriptionContainsKeywordsPredicate(Collections.singletonList("second"));

        FindExpensesCommand findFirstCommand = new FindExpensesCommand(BALI.getGroupName(), firstPredicate);
        FindExpensesCommand findSecondCommand = new FindExpensesCommand(BALI.getGroupName(), secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindExpensesCommand findFirstCommandCopy = new FindExpensesCommand(BALI.getGroupName(), firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different expense -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noExpenseFound() {
        String expectedMessage = String.format(MESSAGE_EXPENSES_LISTED_OVERVIEW, 0);
        DescriptionContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindExpensesCommand command = new FindExpensesCommand(BALI.getGroupName(), predicate);
        expectedModel.updateFilteredExpenseList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel, false, false, true, false, false);
        assertEquals(Collections.emptyList(), model.getExpenses());
    }

    @Test
    public void execute_multipleKeywords_multipleExpensesFound() {
        String expectedMessage = String.format(MESSAGE_EXPENSES_LISTED_OVERVIEW, 2);
        DescriptionContainsKeywordsPredicate predicate = preparePredicate("Souvenirs Buffet");
        FindExpensesCommand command = new FindExpensesCommand(new GroupName("Bali"), predicate);
        expectedModel.setExpenses(expectedModel.getGroupByName(new GroupName("Bali")));
        expectedModel.updateFilteredExpenseList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel, false, false, true, false, false);
        assertEquals(Arrays.asList(BUFFET, SOUVENIRS), model.getExpenses());
    }

    @Test
    public void execute_groupNotInModel_throwsCommandException() {
        Model testModel = new ModelBuilder().build();
        Group nonExistentGroup = VIENNA_NOT_IN_GROUPS;
        DescriptionContainsKeywordsPredicate predicate = preparePredicate("Souvenirs Buffet");
        FindExpensesCommand findExpensesCommand = new FindExpensesCommand(nonExistentGroup.getGroupName(), predicate);
        assertCommandFailure(findExpensesCommand, testModel, MESSAGE_FINDEXPENSESCOMMAND_GROUP_NOT_FOUND);
    }

    /**
     * Parses {@code userInput} into a {@code DescriptionContainsKeywordsPredicate}.
     */
    private DescriptionContainsKeywordsPredicate preparePredicate(String userInput) {
        return new DescriptionContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
