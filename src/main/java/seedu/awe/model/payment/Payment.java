package seedu.awe.model.payment;

import java.util.Objects;

import seedu.awe.model.expense.Cost;
import seedu.awe.model.person.Person;

public class Payment implements Comparable<Payment> {

    private Person payer;
    private Person payee;
    private Cost cost;
    private boolean isPaymentFlow;

    /**
     * Payment object represents transactions to be made to balance expenses.
     * @param payer person to pay amount
     * @param payee person to be paid amount
     * @param cost amount to pay
     */
    public Payment(Person payer, Person payee, Cost cost) {
        this.payer = payer;
        this.payee = payee;
        this.cost = cost;
        isPaymentFlow = true;
    }

    /**
     * Constructor without payer object.
     * @param payee person who paid amount.
     * @param cost amount paid.
     */
    public Payment(Person payee, Cost cost) {
        this.payee = payee;
        this.cost = cost;
        isPaymentFlow = false;
    }

    @Override
    public String toString() {
        if (isPaymentFlow) {
            return String.format("%s pays $%.2f to %s.", payee.getName(), getCost(), payer.getName());
        } else {
            return String.format("%s spent $%0.2f.", payee.getName(), getCost());
        }
    }

    public Person getPayer() {
        return payer;
    }

    public Person getPayee() {
        return payee;
    }

    public Cost getCost() {
        return cost;
    }

    public boolean isPaymentFlow() {
        return isPaymentFlow;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Payment)) {
            return false;
        }

        Payment otherExpense = (Payment) other;
        boolean isSamePayer = otherExpense.getPayer().equals(getPayer());
        boolean isSamePayee = otherExpense.getPayee().equals(getPayee());
        boolean isSameCost = otherExpense.getCost().equals(getCost());
        boolean isSamePaymentFlow = otherExpense.isPaymentFlow() == isPaymentFlow();

        return isSamePayer && isSamePayee && isSameCost && isSamePaymentFlow;
    }

    @Override
    public int hashCode() {
        return Objects.hash(payer, payee, cost, isPaymentFlow);
    }

    /**
     * Sort by payer name then payee name.
     * @param otherPayment Payment to compare
     * @return Integer to mark priority
     */
    @Override
    public int compareTo(Payment otherPayment) {
        String payeeName = this.getPayee().getName().getFullName();
        String otherPayeeName = this.getPayee().getName().getFullName();
        String payerName = this.getPayer().getName().getFullName();
        String otherPayerName = this.getPayer().getName().getFullName();

        if (payerName.compareTo(otherPayerName) == -1) {
            return -1;
        } else if (payerName.compareTo(otherPayerName) == 1) {
            return 1;
        } else {
            if (payeeName.compareTo(otherPayeeName) == -1) {
                return -1;
            } else if (payeeName.compareTo(otherPayeeName) == 1) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}
