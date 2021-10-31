package seedu.awe.model.payment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.awe.testutil.Assert.assertThrows;
import static seedu.awe.testutil.TypicalGroups.BALI_WITH_EXPENSES;
import static seedu.awe.testutil.TypicalGroups.BALI_WITH_EXPENSES_PAYMENTS;
import static seedu.awe.testutil.TypicalPayments.ALICE_BOB_COST;
import static seedu.awe.testutil.TypicalPayments.ALICE_BOB_PAYMENT;
import static seedu.awe.testutil.TypicalPersons.ALICE;
import static seedu.awe.testutil.TypicalPersons.BOB;

import java.util.Iterator;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.awe.testutil.PaymentBuilder;

public class PaymentListTest {

    private final PaymentList emptyPaymentList = new PaymentList();
    private final PaymentList paymentList = new PaymentList(BALI_WITH_EXPENSES);

    public void addPayments() {
        paymentList.addAll(BALI_WITH_EXPENSES_PAYMENTS);
    }


    @Test
    public void contains_nullPayment_throwsNullPointerException() {
        addPayments();
        assertThrows(NullPointerException.class, () -> emptyPaymentList.contains(null));
    }

    @Test
    public void constructor_nullGroup_throwsNullPointerException() {
        addPayments();
        assertThrows(NullPointerException.class, () -> new PaymentList(null));
    }

    @Test
    public void contains_paymentNotInList_returnsFalse() {
        addPayments();
        assertFalse(emptyPaymentList.contains(ALICE_BOB_PAYMENT));
    }

    @Test
    public void contains_paymentInList_returnsTrue() {
        addPayments();
        emptyPaymentList.add(ALICE_BOB_PAYMENT);
        assertTrue(emptyPaymentList.contains(ALICE_BOB_PAYMENT));
    }

    @Test
    public void contains_paymentWithSameIdentityFieldsInList_returnsTrue() {
        addPayments();
        emptyPaymentList.add(ALICE_BOB_PAYMENT);
        Payment editedPayment = new PaymentBuilder().withPayer(ALICE).withPayee(BOB).withCost(ALICE_BOB_COST).build();
        assertTrue(emptyPaymentList.contains(editedPayment));
    }

    @Test
    public void add_nullPayment_throwsNullPointerException() {
        addPayments();
        assertThrows(NullPointerException.class, () -> emptyPaymentList.add(null));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        addPayments();
        assertThrows(UnsupportedOperationException.class, () ->
                emptyPaymentList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void equals() {
        addPayments();
        assertEquals(paymentList, paymentList);
        assertNotEquals(paymentList, emptyPaymentList);
        assertFalse(paymentList.equals(30));
    }

    @Test
    public void hashcode() {
        addPayments();
        assertEquals(paymentList.hashCode(), paymentList.hashCode());
        assertEquals(emptyPaymentList.hashCode(), emptyPaymentList.hashCode());
        assertNotEquals(paymentList, emptyPaymentList);
    }

    @Test
    public void getGroup_groupPresent_returnsOptionalOfGroup() {
        addPayments();
        assertEquals(Optional.ofNullable(BALI_WITH_EXPENSES), paymentList.getGroup());
    }

    @Test
    public void getGroup_groupAbsent_returnsEmptyOptional() {
        addPayments();
        assertEquals(Optional.empty(), emptyPaymentList.getGroup());
    }

    @Test
    public void setGroup_sucess() {
        addPayments();
        emptyPaymentList.setGroup(BALI_WITH_EXPENSES);
        assertEquals(Optional.ofNullable(BALI_WITH_EXPENSES), emptyPaymentList.getGroup());
    }

    @Test
    public void iterator() {
        addPayments();
        assertTrue(paymentList.iterator() instanceof Iterator);
    }
}
