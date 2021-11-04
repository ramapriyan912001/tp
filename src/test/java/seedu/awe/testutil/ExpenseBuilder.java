package seedu.awe.testutil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import seedu.awe.model.expense.Cost;
import seedu.awe.model.expense.Description;
import seedu.awe.model.expense.Expense;
import seedu.awe.model.person.Person;

public class ExpenseBuilder {

    public static final String DEFAULT_COST = "50";
    public static final String DEFAULT_DESCRIPTION = "holiday";
    public static final Person DEFAULT_PERSON = new PersonBuilder().build();
    public static final List<Person> DEFAULT_INCLUDED = new ArrayList<>();
    public static final HashMap<Person, Cost> DEFAULT_INDIVIDUAL_EXPENSE = new HashMap<>();
    public static final HashMap<Person, Cost> DEFAULT_SPLIT_EXPENSE = new HashMap<>();

    private Person payer;
    private Cost cost;
    private Description description;
    private final List<Person> included;
    private final Map<Person, Cost> individualExpenses;
    private final Map<Person, Cost> splitExpenses;


    /**
     * Creates an {@code ExpenseBuilder} with the default details.
     */
    public ExpenseBuilder() {
        payer = DEFAULT_PERSON;
        cost = new Cost(DEFAULT_COST);
        description = new Description(DEFAULT_DESCRIPTION);
        included = DEFAULT_INCLUDED;
        individualExpenses = DEFAULT_INDIVIDUAL_EXPENSE;
        splitExpenses = DEFAULT_SPLIT_EXPENSE;
    }

    /**
     * Initializes the ExpenseBuilder with the data of {@code expenseToCopy}.
     */
    public ExpenseBuilder(Expense expenseToCopy) {
        payer = expenseToCopy.getPayer();
        cost = expenseToCopy.getCost();
        description = expenseToCopy.getDescription();
        included = expenseToCopy.getIncluded();
        individualExpenses = expenseToCopy.getIndividualExpenses();
        splitExpenses = expenseToCopy.getSplitExpenses();
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
     * Sets the {@code individualExpenses} of the {@code Expense} that we are building.
     */
    public ExpenseBuilder withIndividualExpenses(Map<Person, Cost> expenses) {
        for (Map.Entry<Person, Cost> e : expenses.entrySet()) {
            this.individualExpenses.merge(e.getKey(), e.getValue(), Cost::add);
        }
        return this;
    }

    /**
     * Sets the {@code splitExpenses} of the {@code Expense} that we are building.
     */
    public ExpenseBuilder withSplitExpenses(Map<Person, Cost> expenses) {
        for (Map.Entry<Person, Cost> e : expenses.entrySet()) {
            this.splitExpenses.merge(e.getKey(), e.getValue(), Cost::add);
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
