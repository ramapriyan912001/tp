package seedu.awe.model.transactionsummary;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.awe.testutil.Assert.assertThrows;
import static seedu.awe.testutil.TypicalGroups.BALI_WITH_EXPENSES;
import static seedu.awe.testutil.TypicalGroups.BALI_WITH_TRANSACTION_SUMMARIES;
import static seedu.awe.testutil.TypicalPersons.ALICE;
import static seedu.awe.testutil.TypicalPersons.AMY;
import static seedu.awe.testutil.TypicalPersons.BOB;
import static seedu.awe.testutil.TypicalTransactionSummary.ALICE_TRANSACTION_SUMMARY;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.awe.model.expense.Cost;


public class TransactionSummaryListTest {
    private final TransactionSummaryList emptyTransactionSummaryList = new TransactionSummaryList();
    private final TransactionSummaryList transactionSummaryList = new TransactionSummaryList(BALI_WITH_EXPENSES);

    {
        transactionSummaryList.set(BALI_WITH_TRANSACTION_SUMMARIES);
    }

    @Test
    public void setGroup_success() {
        emptyTransactionSummaryList.setGroup(BALI_WITH_EXPENSES);
        assertEquals(Optional.ofNullable(BALI_WITH_EXPENSES), emptyTransactionSummaryList.getGroup());
    }

    @Test
    public void getGroup_groupAbsent_returnsEmptyOptional() {
        assertEquals(Optional.empty(), emptyTransactionSummaryList.getGroup());
    }

    @Test
    public void contains_nullTransactionSummary_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> emptyTransactionSummaryList.contains(null));
    }

    @Test
    public void contains_transactionSummaryNotInList_returnsFalse() {
        assertFalse(emptyTransactionSummaryList.contains(ALICE_TRANSACTION_SUMMARY));
    }

    @Test
    public void contains_paymentInList_returnsTrue() {
        assertTrue(transactionSummaryList.contains(new TransactionSummary(ALICE, new Cost(400))));
    }

    @Test
    public void clearTest() {
        transactionSummaryList.clear();
        assertEquals(emptyTransactionSummaryList, transactionSummaryList);
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                emptyTransactionSummaryList.asUnmodifiableObservableList().remove(0));
    }
    @Test
    public void iterator() {
        Iterator<TransactionSummary> transactionSummaryIterator = transactionSummaryList.iterator();

        assertTrue(transactionSummaryIterator instanceof Iterator);

        assertEquals(new TransactionSummary(ALICE, new Cost(400)), transactionSummaryIterator.next());
        assertEquals(new TransactionSummary(BOB, new Cost(400)), transactionSummaryIterator.next());
        assertEquals(new TransactionSummary(AMY, new Cost(400)), transactionSummaryIterator.next());

        assertFalse(transactionSummaryIterator.hasNext());
    }

    @Test
    public void equals() {
        TransactionSummaryList transactionSummaryList1 = new TransactionSummaryList();
        transactionSummaryList1.set(Arrays.asList(
                new TransactionSummary(ALICE, new Cost(400)),
                new TransactionSummary(BOB, new Cost(400)),
                new TransactionSummary(AMY, new Cost(400))
        ));

        // Same object -> true
        assertTrue(transactionSummaryList1.equals(transactionSummaryList1));

        // null object -> false
        assertFalse(transactionSummaryList1.equals(null));

        // other objects passed in -> false
        assertFalse(transactionSummaryList1.equals(new TransactionSummary(ALICE, new Cost(400))));

        // Another instance passed in -> true
        assertTrue(transactionSummaryList1.equals(transactionSummaryList));

        // Different transaction in list -> false
        assertFalse(transactionSummaryList1.equals(emptyTransactionSummaryList));
    }

    @Test
    public void hashcode() {
        // same instance -> equals
        assertEquals(transactionSummaryList.hashCode(), transactionSummaryList.hashCode());
        assertEquals(emptyTransactionSummaryList.hashCode(), emptyTransactionSummaryList.hashCode());

        // different -> not equals
        assertNotEquals(transactionSummaryList, emptyTransactionSummaryList);
    }

}
