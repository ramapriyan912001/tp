package seedu.awe.testutil;

import java.util.ArrayList;

import seedu.awe.model.expense.Cost;
import seedu.awe.model.expense.Description;
import seedu.awe.model.expense.Expense;
import seedu.awe.model.person.Person;

public class ExpenseBuilder {

    public static final String DEFAULT_COST = "50";
    public static final String DEFAULT_DESCRIPTION = "holiday";
    public static final Person DEFAULT_PERSON = new PersonBuilder().build();

    private Person payer;
    private Cost cost;
    private Description description;
    private final ArrayList<Person> included = new ArrayList<>();


    /**
     * Creates an {@code ExpenseBuilder} with the default details.
     */
    public ExpenseBuilder() {
        payer = DEFAULT_PERSON;
        cost = new Cost(DEFAULT_COST);
        description = new Description(DEFAULT_DESCRIPTION);
    }

    /**
     * Initializes the ExpenseBuilder with the data of {@code expenseToCopy}.
     */
    public ExpenseBuilder(Expense expenseToCopy) {
        payer = expenseToCopy.getPayer();
        cost = expenseToCopy.getCost();
        description = expenseToCopy.getDescription();
    }

    /**
     * Sets the {@code Description} of the {@code Expense} that we are building.
     *
     * @param description The description of the expense.
     * @return An expensebuilder.
     */
    public ExpenseBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code payer} of the {@code Expense} that we are building.
     */
    public ExpenseBuilder withPayer(Person payer) {
        this.payer = payer;
        return this;
    }

    /**
     * Sets the {@code Cost} of the {@code Expense} that we are building.
     */
    public ExpenseBuilder withCost(String cost) {
        this.cost = new Cost(cost);
        return this;
    }

    /**
     * Sets the {@code included} of the {@code Expense} that we are building.
     */
    public ExpenseBuilder withIncluded(Person... persons) {
        for (Person person : persons) {
            this.included.add(new PersonBuilder(person).build());
        }
        return this;
    }

    /**
     * Builds an {@code Expense} class with the relevant information.
     *
     * @return The built Expense class.
     */
    public Expense build() {
        return new Expense(payer, cost, description, included);
    }
}
