package seedu.awe.model.transactionsummary;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.awe.testutil.Assert.assertThrows;
import static seedu.awe.testutil.TypicalPersons.ALICE;
import static seedu.awe.testutil.TypicalPersons.AMY;
import static seedu.awe.testutil.TypicalPersons.BOB;
import static seedu.awe.testutil.TypicalTransactionSummary.ALICE_COST;
import static seedu.awe.testutil.TypicalTransactionSummary.ALICE_TRANSACTION_SUMMARY;
import static seedu.awe.testutil.TypicalTransactionSummary.AMY_COST;
import static seedu.awe.testutil.TypicalTransactionSummary.AMY_TRANSACTION_SUMMARY;
import static seedu.awe.testutil.TypicalTransactionSummary.CARL_TRANSACTION_SUMMARY;
import static seedu.awe.testutil.TypicalTransactionSummary.DANIEL_COST;
import static seedu.awe.testutil.TypicalTransactionSummary.DANIEL_TRANSACTION_SUMMARY;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.awe.model.expense.Cost;

public class TransactionSummaryTest {
    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TransactionSummary(null, ALICE_COST));
    }

    @Test
    public void constructor_nullCost_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TransactionSummary(ALICE, null));
    }

    @Test
    public void getPersonTest() {
        assertEquals(AMY, AMY_TRANSACTION_SUMMARY.getPerson());
        assertEquals(ALICE, ALICE_TRANSACTION_SUMMARY.getPerson());
    }

    @Test
    public void getCostTest() {
        assertEquals(DANIEL_COST, DANIEL_TRANSACTION_SUMMARY.getCost());
        assertEquals(AMY_COST, AMY_TRANSACTION_SUMMARY.getCost());
    }

    @Test
    public void equals() {
        TransactionSummary alicePayment = new TransactionSummary(ALICE, new Cost(20.0));

        // Same object -> true
        assertTrue(alicePayment.equals(alicePayment));

        // null object -> false
        assertFalse(alicePayment.equals(null));

        // other objects passed in -> false
        assertFalse(alicePayment.equals(20.0));

        // Another instance passed in -> true
        assertTrue(alicePayment.equals(ALICE_TRANSACTION_SUMMARY));

        // Different cost -> false
        assertFalse(alicePayment.equals(new TransactionSummary(ALICE, new Cost(10.0))));

        // Different person -> false
        assertFalse(alicePayment.equals(new TransactionSummary(BOB, new Cost(20.0))));
    }

    @Test
    public void hashcode() {
        TransactionSummary alicePayment = new TransactionSummary(ALICE, new Cost(20.0));

        // Same object -> equal
        assertEquals(alicePayment.hashCode(), alicePayment.hashCode());

        // Different object -> not equal
        assertNotEquals(alicePayment.hashCode(), new Cost(10.0).hashCode());

        // Another instance passed in -> equal
        assertEquals(ALICE_TRANSACTION_SUMMARY.hashCode(), alicePayment.hashCode());

        // Different cost -> not equal
        assertNotEquals(alicePayment, new TransactionSummary(ALICE, new Cost(10.0)));

        // Different person -> not equal
        assertNotEquals(alicePayment, new TransactionSummary(BOB, new Cost(20.0)));
    }

    @Test
    public void comparatorTest() {
        List<TransactionSummary> transactionSummaries = new ArrayList<>();
        transactionSummaries.add(ALICE_TRANSACTION_SUMMARY);
        transactionSummaries.add(AMY_TRANSACTION_SUMMARY);
        transactionSummaries.add(CARL_TRANSACTION_SUMMARY);
        transactionSummaries.add(DANIEL_TRANSACTION_SUMMARY);
        transactionSummaries.sort(new Comparator<TransactionSummary>() {
            @Override
            public int compare(TransactionSummary t1, TransactionSummary t2) {
                return t1.compareTo(t2);
            }
        });

        List<TransactionSummary> expectedTransactionSummaries = new ArrayList<>();
        expectedTransactionSummaries.add(ALICE_TRANSACTION_SUMMARY);
        expectedTransactionSummaries.add(AMY_TRANSACTION_SUMMARY);
        expectedTransactionSummaries.add(CARL_TRANSACTION_SUMMARY);
        expectedTransactionSummaries.add(DANIEL_TRANSACTION_SUMMARY);


        int i = 0;
        while (i < 4) {
            assertEquals(transactionSummaries.get(i), expectedTransactionSummaries.get(i));
            i++;
        }
    }

    @Test
    public void toStringTest() {
        String expectedString = "Alice Pauline spent $20.00.";
        assertEquals(expectedString, ALICE_TRANSACTION_SUMMARY.toString());
    }
}
