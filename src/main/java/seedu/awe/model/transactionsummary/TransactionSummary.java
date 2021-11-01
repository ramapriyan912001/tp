package seedu.awe.model.transactionsummary;

import static seedu.awe.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.awe.model.expense.Cost;
import seedu.awe.model.person.Person;

public class TransactionSummary implements Comparable<TransactionSummary> {

    private Person person;
    private Cost cost;

    /**
     * Payment object represents total transaction.
     * @param person person spending the money
     * @param cost amount spent
     */
    public TransactionSummary(Person person, Cost cost) {
        requireAllNonNull(person, cost);
        this.person = person;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return String.format("%s spent $%0.2f.", person.getName(), getCost());
    }

    public Person getPerson() {
        return person;
    }

    public Cost getCost() {
        return cost;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TransactionSummary)) {
            return false;
        }

        TransactionSummary otherExpense = (TransactionSummary) other;
        boolean isSamePayer = otherExpense.getPerson().equals(person);
        boolean isSameCost = otherExpense.getCost().equals(getCost());

        return isSamePayer && isSameCost;
    }

    @Override
    public int hashCode() {
        return Objects.hash(person, cost);
    }

    /**
     * Sort by person name.
     * @param otherTransactionSummary TransactionSummary to compare
     * @return Integer to mark priority
     */
    @Override
    public int compareTo(TransactionSummary otherTransactionSummary) {
        String personName = this.getPerson().getName().getFullName();
        String otherPersonName = this.getPerson().getName().getFullName();

        return personName.compareTo(otherPersonName);
    }
}
