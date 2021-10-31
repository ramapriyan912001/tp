package seedu.awe.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.awe.commons.core.Messages.MESSAGE_CALCULATEPAYMENTSCOMMAND_GROUP_NOT_FOUND;
import static seedu.awe.commons.core.Messages.MESSAGE_CALCULATEPAYMENTSCOMMAND_PAYMENTS_EMPTY;
import static seedu.awe.commons.core.Messages.MESSAGE_CALCULATEPAYMENTSCOMMAND_PAYMENT_DISCREPANCY;
import static seedu.awe.commons.core.Messages.MESSAGE_CALCULATEPAYMENTSCOMMAND_SUCCESS;
import static seedu.awe.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.awe.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.awe.testutil.Assert.assertThrows;
import static seedu.awe.testutil.TypicalGroups.AMSTERDAM;
import static seedu.awe.testutil.TypicalGroups.AMSTERDAM_WITH_EXPENSES;
import static seedu.awe.testutil.TypicalGroups.AMSTERDAM_WITH_EXPENSES_PAYMENTS;
import static seedu.awe.testutil.TypicalGroups.BALI;
import static seedu.awe.testutil.TypicalGroups.BALI_WITH_EXPENSES;
import static seedu.awe.testutil.TypicalGroups.BALI_WITH_EXPENSES_PAYMENTS;
import static seedu.awe.testutil.TypicalGroups.CHINA;
import static seedu.awe.testutil.TypicalGroups.COLOMBO_WITH_EXPENSES;
import static seedu.awe.testutil.TypicalGroups.INDIA;
import static seedu.awe.testutil.TypicalGroups.MALIBU;
import static seedu.awe.testutil.TypicalGroups.PERU_WITH_EXPENSES_INVALID;
import static seedu.awe.testutil.TypicalGroups.RIO_WITH_EXPENSES_INVALID;
import static seedu.awe.testutil.TypicalGroups.SANTIAGO_WITH_EXPENSES;

import org.junit.jupiter.api.Test;

import seedu.awe.logic.commands.exceptions.CommandException;
import seedu.awe.model.Model;
import seedu.awe.testutil.ModelBuilder;

public class CalculatePaymentsCommandTest {

    private Model model = new ModelBuilder().build();

    public void resetModel() {
        model = new ModelBuilder().build();
    }

    @Test
    public void constructor_invalidInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CalculatePaymentsCommand(null));
    }

    @Test
    public void equals() {
        assertNotEquals(new CalculatePaymentsCommand(AMSTERDAM), new CalculatePaymentsCommand(BALI));
        assertNotEquals(new CalculatePaymentsCommand(BALI), null);
        assertNotEquals(new CalculatePaymentsCommand(AMSTERDAM_WITH_EXPENSES), new CalculatePaymentsCommand(BALI));

        assertEquals(new CalculatePaymentsCommand(BALI), new CalculatePaymentsCommand(BALI));
        assertEquals(new CalculatePaymentsCommand(BALI_WITH_EXPENSES), new CalculatePaymentsCommand(BALI));
        CalculatePaymentsCommand calculatePaymentsCommand = new CalculatePaymentsCommand(BALI_WITH_EXPENSES);
        assertEquals(calculatePaymentsCommand, calculatePaymentsCommand);
    }

    @Test
    public void execute_groupNotInModel_throwsCommandException() {
        resetModel();
        // COLOMBO_WITH_EXPENSES is not in model
        CalculatePaymentsCommand calculatePaymentsCommand = new CalculatePaymentsCommand(COLOMBO_WITH_EXPENSES);
        assertCommandFailure(calculatePaymentsCommand, model, MESSAGE_CALCULATEPAYMENTSCOMMAND_GROUP_NOT_FOUND);
    }

    @Test
    public void execute_nullModel_throwsCommandException() {
        resetModel();
        // COLOMBO_WITH_EXPENSES is not in model
        CalculatePaymentsCommand calculatePaymentsCommand = new CalculatePaymentsCommand(COLOMBO_WITH_EXPENSES);
        assertThrows(NullPointerException.class, () -> calculatePaymentsCommand.execute(null));
    }

    @Test
    public void execute_validInput_returnsCommandResult() {
        resetModel();
        // More deficit members than surplus members, multiple expenses
        CommandResult expectedCommandResultBali = new CommandResult(MESSAGE_CALCULATEPAYMENTSCOMMAND_SUCCESS, false,
                false, false, false, false, false, true);
        assertCommandSuccess(new CalculatePaymentsCommand(BALI_WITH_EXPENSES), model, expectedCommandResultBali, model);

        // More deficit members than surplus members, one expense.
        CommandResult expectedCommandResultAmsterdam = new CommandResult(MESSAGE_CALCULATEPAYMENTSCOMMAND_SUCCESS,
                false, false, false, false, false, false, true);
        assertCommandSuccess(new CalculatePaymentsCommand(AMSTERDAM_WITH_EXPENSES), model,
                expectedCommandResultAmsterdam, model);

        // More surplus members than deficit members
        CommandResult expectedCommandResultMalibu = new CommandResult(MESSAGE_CALCULATEPAYMENTSCOMMAND_SUCCESS,
                false, false, false, false, false, false, true);
        assertCommandSuccess(new CalculatePaymentsCommand(MALIBU), model,
                expectedCommandResultMalibu, model);

        // Pair of members with equal deficit and surplus
        CommandResult expectedCommandResultSantiago = new CommandResult(MESSAGE_CALCULATEPAYMENTSCOMMAND_SUCCESS,
                false, false, false, false, false, false, true);
        assertCommandSuccess(new CalculatePaymentsCommand(SANTIAGO_WITH_EXPENSES), model,
                expectedCommandResultSantiago, model);
    }

    @Test
    public void getPayments_groupExpenseSumNotZero_throwsException() {
        resetModel();
        assertCommandFailure(new CalculatePaymentsCommand(RIO_WITH_EXPENSES_INVALID), model,
                MESSAGE_CALCULATEPAYMENTSCOMMAND_PAYMENT_DISCREPANCY);


        assertCommandFailure(new CalculatePaymentsCommand(PERU_WITH_EXPENSES_INVALID), model,
                MESSAGE_CALCULATEPAYMENTSCOMMAND_PAYMENT_DISCREPANCY);
    }

    @Test
    public void execute_emptyGroup_returnsCommandResult() throws CommandException {
        resetModel();
        CommandResult expectedCommandResultIndia = new CommandResult(MESSAGE_CALCULATEPAYMENTSCOMMAND_PAYMENTS_EMPTY,
                false, false, false, false, false, false, true);
        assertCommandSuccess(new CalculatePaymentsCommand(INDIA), model, expectedCommandResultIndia, model);

        resetModel();
        CommandResult expectedCommandResultChina = new CommandResult(MESSAGE_CALCULATEPAYMENTSCOMMAND_PAYMENTS_EMPTY,
                false, false, false, false, false, false, true);
        assertCommandSuccess(new CalculatePaymentsCommand(CHINA), model,
                expectedCommandResultChina, model);
    }

    @Test
    public void getPayments_validGroupExpenses_returnsPaymentList() throws CommandException {
        resetModel();
        assertEquals(BALI_WITH_EXPENSES_PAYMENTS, new CalculatePaymentsCommand(BALI_WITH_EXPENSES).getPayments(
                BALI_WITH_EXPENSES
        ));

        assertEquals(AMSTERDAM_WITH_EXPENSES_PAYMENTS, new CalculatePaymentsCommand(AMSTERDAM_WITH_EXPENSES).getPayments
                (AMSTERDAM_WITH_EXPENSES
        ));

        assertNotEquals(BALI_WITH_EXPENSES_PAYMENTS, new CalculatePaymentsCommand(AMSTERDAM_WITH_EXPENSES)
                .getPayments(AMSTERDAM_WITH_EXPENSES));
    }
}
