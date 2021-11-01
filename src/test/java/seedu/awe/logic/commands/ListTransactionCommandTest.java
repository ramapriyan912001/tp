package seedu.awe.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.awe.commons.core.Messages.MESSAGE_LISTTRANSACTIONSUMMARYCOMMAND_GROUP_NOT_FOUND;
import static seedu.awe.commons.core.Messages.MESSAGE_LISTTRANSACTIONSUMMARYCOMMAND_SUCCESS;
import static seedu.awe.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.awe.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.awe.testutil.Assert.assertThrows;
import static seedu.awe.testutil.TypicalGroups.AMSTERDAM;
import static seedu.awe.testutil.TypicalGroups.AMSTERDAM_WITH_EXPENSES;
import static seedu.awe.testutil.TypicalGroups.BALI;
import static seedu.awe.testutil.TypicalGroups.BALI_WITH_EXPENSES;
import static seedu.awe.testutil.TypicalGroups.COLOMBO_WITH_EXPENSES;
import static seedu.awe.testutil.TypicalGroups.MALIBU;
import static seedu.awe.testutil.TypicalGroups.SANTIAGO_WITH_EXPENSES;

import org.junit.jupiter.api.Test;

import seedu.awe.model.Model;
import seedu.awe.testutil.ModelBuilder;

public class ListTransactionCommandTest {

    private Model model = new ModelBuilder().build();

    public void resetModel() {
        model = new ModelBuilder().build();
    }

    @Test
    public void constructor_invalidInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ListTransactionSummaryCommand(null));
    }

    @Test
    public void equals() {
        // Compare with null -> not equal
        assertNotEquals(new ListTransactionSummaryCommand(BALI), null);

        // Compare with other groups -> not equal
        assertNotEquals(new ListTransactionSummaryCommand(AMSTERDAM), new ListTransactionSummaryCommand(BALI));
        assertNotEquals(new ListTransactionSummaryCommand(AMSTERDAM_WITH_EXPENSES),
                new ListTransactionSummaryCommand(BALI));

        // Same group -> equals
        assertEquals(new ListTransactionSummaryCommand(BALI), new ListTransactionSummaryCommand(BALI));
        assertEquals(new ListTransactionSummaryCommand(BALI_WITH_EXPENSES), new ListTransactionSummaryCommand(BALI));

        // Check with itself -> equal
        ListTransactionSummaryCommand listTransactionSummaryCommand =
                new ListTransactionSummaryCommand(BALI_WITH_EXPENSES);
        assertEquals(listTransactionSummaryCommand, listTransactionSummaryCommand);
    }

    @Test
    public void execute_groupNotInModel_throwsCommandException() {
        resetModel();
        // COLOMBO_WITH_EXPENSES is not in model
        ListTransactionSummaryCommand listTransactionSummaryCommand =
                new ListTransactionSummaryCommand(COLOMBO_WITH_EXPENSES);
        assertCommandFailure(listTransactionSummaryCommand, model,
                MESSAGE_LISTTRANSACTIONSUMMARYCOMMAND_GROUP_NOT_FOUND);
    }

    @Test
    public void execute_nullModel_throwsCommandException() {
        resetModel();
        // COLOMBO_WITH_EXPENSES is not in model
        ListTransactionSummaryCommand listTransactionSummaryCommand =
                new ListTransactionSummaryCommand(COLOMBO_WITH_EXPENSES);
        assertThrows(NullPointerException.class, () -> listTransactionSummaryCommand.execute(null));
    }

    @Test
    public void execute_validInput_returnsCommandResult() {
        resetModel();
        // More deficit members than surplus members, multiple expenses
        CommandResult expectedCommandResultBali = new CommandResult(MESSAGE_LISTTRANSACTIONSUMMARYCOMMAND_SUCCESS,
                false, false, false, false, false, true, false);
        assertCommandSuccess(new ListTransactionSummaryCommand(BALI_WITH_EXPENSES), model,
                expectedCommandResultBali, model);

        // More deficit members than surplus members, one expense.
        CommandResult expectedCommandResultAmsterdam = new CommandResult(MESSAGE_LISTTRANSACTIONSUMMARYCOMMAND_SUCCESS,
                false, false, false, false, false, true, false);
        assertCommandSuccess(new ListTransactionSummaryCommand(AMSTERDAM_WITH_EXPENSES), model,
                expectedCommandResultAmsterdam, model);

        // More surplus members than deficit members
        CommandResult expectedCommandResultMalibu = new CommandResult(MESSAGE_LISTTRANSACTIONSUMMARYCOMMAND_SUCCESS,
                false, false, false, false, false, true, false);
        assertCommandSuccess(new ListTransactionSummaryCommand(MALIBU), model,
                expectedCommandResultMalibu, model);

        // Pair of members with equal deficit and surplus
        CommandResult expectedCommandResultSantiago = new CommandResult(MESSAGE_LISTTRANSACTIONSUMMARYCOMMAND_SUCCESS,
                false, false, false, false, false, true, false);
        assertCommandSuccess(new ListTransactionSummaryCommand(SANTIAGO_WITH_EXPENSES), model,
                expectedCommandResultSantiago, model);
    }
}
