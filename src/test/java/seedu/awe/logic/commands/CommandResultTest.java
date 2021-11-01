package seedu.awe.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CommandResultTest {
    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback")));
        assertTrue(commandResult.equals(new CommandResult("feedback", false,
                false, false, false, false, false, false)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different")));
    }

    @Test
    public void equals_withUiTest_failure() {
        CommandResult commandResult = new CommandResult("feedback");

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", true, false, false, false, false,
                false, false)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, true, false, false,
                false, false, false)));

        // different showGroup value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, false, true, false, false,
                false, false)));

        // different showContacts value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, false, false, true, false,
                false, false)));

        // different showExpenses value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, false, false, false, true,
                false, false)));

        // different showTransactionSummary value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, false, false, false, false,
                true, false)));

        // different showPayments value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, false, false, false, false,
                false, true)));
    }

    @Test
    public void equals_isShowHelp_success() {
        CommandResult commandResult = new CommandResult("feedback", true, false, false, false, false,
                false, false);

        // same showHelp value -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback", true, false, false, false, false,
                false, false)));

        // isShowHelp -> return true
        assertTrue(commandResult.isShowHelp());

        // Everything else -> return false
        assertFalse(commandResult.isExit());
        assertFalse(commandResult.isShowContacts());
        assertFalse(commandResult.isShowExpenses());
        assertFalse(commandResult.isShowGroups());
        assertFalse(commandResult.isShowPaymentsCommand());
        assertFalse(commandResult.isShowTransactionSummary());
    }

    @Test
    public void equals_isExit_success() {
        CommandResult commandResult = new CommandResult("feedback", false, true, false, false, false,
                false, false);


        // different exit value -> returns false
        assertTrue(commandResult.equals(new CommandResult("feedback", false, true, false, false,
                false, false, false)));

        // isExit -> return true
        assertTrue(commandResult.isExit());

        // Everything else -> return false
        assertFalse(commandResult.isShowHelp());
        assertFalse(commandResult.isShowContacts());
        assertFalse(commandResult.isShowExpenses());
        assertFalse(commandResult.isShowGroups());
        assertFalse(commandResult.isShowPaymentsCommand());
        assertFalse(commandResult.isShowTransactionSummary());
    }

    @Test
    public void equals_isShowGroup_success() {
        CommandResult commandResult = new CommandResult("feedback", false, false, true, false, false,
                false, false);


        // different showGroup value -> returns false
        assertTrue(commandResult.equals(new CommandResult("feedback", false, false, true, false, false,
                false, false)));

        // isShowGroup -> return true
        assertTrue(commandResult.isShowGroups());

        // Everything else -> return false
        assertFalse(commandResult.isShowHelp());
        assertFalse(commandResult.isShowContacts());
        assertFalse(commandResult.isShowExpenses());
        assertFalse(commandResult.isExit());
        assertFalse(commandResult.isShowPaymentsCommand());
        assertFalse(commandResult.isShowTransactionSummary());
    }

    @Test
    public void equals_isShowContact_success() {
        CommandResult commandResult = new CommandResult("feedback", false, false, false, true, false,
                false, false);


        // different showContacts value -> returns false
        assertTrue(commandResult.equals(new CommandResult("feedback", false, false, false, true, false,
                false, false)));

        // isShowContact -> return true
        assertTrue(commandResult.isShowContacts());

        // Everything else -> return false
        assertFalse(commandResult.isShowHelp());
        assertFalse(commandResult.isExit());
        assertFalse(commandResult.isShowExpenses());
        assertFalse(commandResult.isShowGroups());
        assertFalse(commandResult.isShowPaymentsCommand());
        assertFalse(commandResult.isShowTransactionSummary());
    }

    @Test
    public void equals_isShowExpense_success() {
        CommandResult commandResult = new CommandResult("feedback", false, false, false, false, true,
                false, false);


        // different showExpenses value -> returns false
        assertTrue(commandResult.equals(new CommandResult("feedback", false, false, false, false, true,
                false, false)));

        // isShowExpense -> return true
        assertTrue(commandResult.isShowExpenses());

        // Everything else -> return false
        assertFalse(commandResult.isShowHelp());
        assertFalse(commandResult.isShowContacts());
        assertFalse(commandResult.isExit());
        assertFalse(commandResult.isShowGroups());
        assertFalse(commandResult.isShowPaymentsCommand());
        assertFalse(commandResult.isShowTransactionSummary());
    }

    @Test
    public void equals_isShowTransaction_success() {
        CommandResult commandResult = new CommandResult("feedback", false, false, false, false, false,
                true, false);


        // different showTransactionSummary value -> returns false
        assertTrue(commandResult.equals(new CommandResult("feedback", false, false, false, false, false,
                true, false)));

        // isShowTransaction -> return true
        assertTrue(commandResult.isShowTransactionSummary());

        // Everything else -> return false
        assertFalse(commandResult.isShowHelp());
        assertFalse(commandResult.isShowContacts());
        assertFalse(commandResult.isShowExpenses());
        assertFalse(commandResult.isShowGroups());
        assertFalse(commandResult.isShowPaymentsCommand());
        assertFalse(commandResult.isExit());
    }

    @Test
    public void equals_isShowPayments_success() {
        CommandResult commandResult = new CommandResult("feedback", false, false, false, false, false,
                false, true);


        // different showPayments value -> returns false
        assertTrue(commandResult.equals(new CommandResult("feedback", false, false, false, false, false,
                false, true)));

        // isShowPayement -> return true
        assertTrue(commandResult.isShowPaymentsCommand());

        // Everything else -> return false
        assertFalse(commandResult.isShowHelp());
        assertFalse(commandResult.isShowContacts());
        assertFalse(commandResult.isShowExpenses());
        assertFalse(commandResult.isShowGroups());
        assertFalse(commandResult.isExit());
        assertFalse(commandResult.isShowTransactionSummary());
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", true, false,
                false, false, false, false, false).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, true,
                false, false, false, false, false).hashCode());

        // different showGroup value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, false,
                true, false, false, false, false).hashCode());

        // different showContacts value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, false,
                false, true, false, false, false).hashCode());

        // different showExpenses value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, false,
                false, false, true, false, false).hashCode());

        // different showTransactionSummary value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, false,
            false, false, false, true, false).hashCode());

        // different showPayments value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, false,
                false, false, false, false, true).hashCode());
    }
}
