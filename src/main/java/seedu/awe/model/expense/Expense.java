package seedu.awe.model.expense;

import java.util.Objects;

import seedu.awe.model.person.Person;

public class Expense {

    // identity fields
    private final Person payer;

    // data fields
    private final Cost cost;
    private final Description description;

    /**
     * Constructs an {@code Expense}.
     *
     * @param payer of expense.
     * @param cost of expense.
     * @param description of expense.
     */
    public Expense(Person payer, Cost cost, Description description) {
        this.payer = payer;
        this.cost = cost;
        this.description = description;
    }

    public Person getPayer() {
        return payer;
    }

    public Cost getCost() {
        return cost;
    }

    public Description getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Expense)) {
            return false;
        }

        Expense otherExpense = (Expense) other;
        return otherExpense.getPayer().equals(getPayer())
                && otherExpense.getCost().equals(getCost())
                && otherExpense.getDescription().equals(getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(payer, cost, description);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getPayer())
                .append(" paid ")
                .append(getCost())
                .append(" for ")
                .append(getDescription())
                .append(".");
        return builder.toString();
    }
}
