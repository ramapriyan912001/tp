package seedu.awe.testutil;

import static seedu.awe.logic.commands.CommandTestUtil.VALID_COST_BUFFET;
import static seedu.awe.logic.commands.CommandTestUtil.VALID_COST_SOUVENIRS;
import static seedu.awe.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BUFFET;
import static seedu.awe.logic.commands.CommandTestUtil.VALID_DESCRIPTION_SOUVENIRS;
import static seedu.awe.testutil.TypicalGroups.BALI;
import static seedu.awe.testutil.TypicalGroups.HELSINKI;
import static seedu.awe.testutil.TypicalGroups.LONDON;
import static seedu.awe.testutil.TypicalGroups.OSLO;
import static seedu.awe.testutil.TypicalPersons.ALICE;
import static seedu.awe.testutil.TypicalPersons.AMY;
import static seedu.awe.testutil.TypicalPersons.BENSON;
import static seedu.awe.testutil.TypicalPersons.BOB;
import static seedu.awe.testutil.TypicalPersons.CARL;
import static seedu.awe.testutil.TypicalPersons.DANIEL;
import static seedu.awe.testutil.TypicalPersons.ELLE;
import static seedu.awe.testutil.TypicalPersons.FIONA;
import static seedu.awe.testutil.TypicalPersons.GEORGE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.awe.model.Awe;
import seedu.awe.model.expense.Expense;
import seedu.awe.model.group.Group;
import seedu.awe.model.person.Person;


public class TypicalExpenses {

    public static final Expense HOLIDAY = new ExpenseBuilder().withDescription("Holiday")
            .withCost("1000").withPayer(ALICE).build();
    public static final Expense PIZZA = new ExpenseBuilder().withDescription("Pizza")
            .withCost("100").withPayer(BENSON).build();
    public static final Expense PARTY = new ExpenseBuilder().withDescription("Party")
            .withCost("500").withPayer(CARL).build();
    public static final Expense BIRTHDAY_GIFTS = new ExpenseBuilder().withDescription("Birthday Gifts")
            .withCost("60").withPayer(DANIEL).build();
    public static final Expense DINNER = new ExpenseBuilder().withDescription("dinner")
            .withCost("500").withPayer(ELLE).build();
    public static final Expense TRANSPORTATION = new ExpenseBuilder().withDescription("Transportation")
            .withCost("30").withPayer(FIONA).build();
    public static final Expense MOVIE_TICKETS = new ExpenseBuilder().withDescription("Movie Tickets")
            .withCost("70").withPayer(GEORGE).build();

    // Manually added - Expense's details found in {@code CommandTestUtil}
    public static final Expense BUFFET = new ExpenseBuilder().withDescription(VALID_DESCRIPTION_BUFFET)
            .withCost(VALID_COST_BUFFET).withPayer(AMY).build();
    public static final Expense SOUVENIRS = new ExpenseBuilder().withDescription(VALID_DESCRIPTION_SOUVENIRS)
            .withCost(VALID_COST_SOUVENIRS).withPayer(BOB).build();

    public static final String KEYWORD_MATCHING_BUFFET = "Buffet"; // A keyword that matches BUFFET

    private TypicalExpenses() {
    } // prevents instantiation

    /**
     * Returns an {@code Awe} with all the typical expenses.
     */
    public static Awe getTypicalAddressBook() {
        Awe ab = new Awe();

        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }

        for (Group group : getTypicalGroups()) {
            for (Expense expense : getTypicalExpenses(group)) {
                group = group.addExpense(expense);
            }
            ab.addGroup(group);
        }
        return ab;
    }


    public static List<Expense> getTypicalExpenses(Group group) {
        String groupName = group.getGroupName().toString();

        switch (groupName) {

        case "Bali":
            return new ArrayList<>(Arrays.asList(BUFFET, SOUVENIRS, HOLIDAY));

        case "London":
            return new ArrayList<>(Arrays.asList(BUFFET, PIZZA, MOVIE_TICKETS));

        case "Oslo":
            return new ArrayList<>(Arrays.asList(PARTY, DINNER, BIRTHDAY_GIFTS));

        case "Helsinki":
            return new ArrayList<>(Arrays.asList(MOVIE_TICKETS, DINNER, BIRTHDAY_GIFTS));

        default:
            return new ArrayList<>();
        }
    }


    public static List<Group> getTypicalGroups() {
        return new ArrayList<>(Arrays.asList(BALI, LONDON, OSLO, HELSINKI));
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}



