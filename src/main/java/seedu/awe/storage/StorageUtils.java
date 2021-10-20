package seedu.awe.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import seedu.awe.commons.exceptions.IllegalValueException;
import seedu.awe.model.expense.Cost;
import seedu.awe.model.expense.Expense;
import seedu.awe.model.expense.IndividualAmount;
import seedu.awe.model.person.Person;

public class StorageUtils {

    /**
     * Convert a map of expenses between people and costs to a list of jsonAdaptedIndividualAmounts
     * @param expenseMap
     * @return
     */
    public static List<IndividualAmount> convertExpenseMapToListOfIndividualAmounts(Map<Person, Cost> expenseMap) {
        List<IndividualAmount> individualAmounts = new ArrayList<>();
        for (Person person : expenseMap.keySet()) {
            IndividualAmount individualAmount = new IndividualAmount(person,
                    expenseMap.get(person).getCost());
            individualAmounts.add(individualAmount);
        }
        return individualAmounts;
    }

    /**
     * Convert list of jsonAdaptedIndividualAmounts to a map of expenses between people and costs.
     * @param jsonAdaptedIndividualAmounts JsonAdaptedIndividualAmount object
     * @return Map of Persons to Costs.
     * @throws IllegalValueException
     */
    public static Map<Person, Cost> convertListOfJsonAdaptedIndividualAmountsToExpenseMap(
            List<JsonAdaptedIndividualAmount> jsonAdaptedIndividualAmounts) throws IllegalValueException {
        Map<Person, Cost> expenseMap = new HashMap<>();
        for (JsonAdaptedIndividualAmount jsonAdaptedIndividualAmount : jsonAdaptedIndividualAmounts) {
            IndividualAmount individualAmount = jsonAdaptedIndividualAmount.toModelType();
            Person person = individualAmount.getPerson();
            Cost cost = new Cost(individualAmount.getExpenditure());
            expenseMap.put(person, cost);
        }
        return expenseMap;
    }

    /**
     * Converts list of adaptedExpenses to expenses
     * @param adaptedExpenses list of jsonAdaptedExpenses
     * @return list of expenses
     * @throws IllegalValueException
     */
    public static List<Expense> convertAdaptedExpensesToExpenses(
            List<JsonAdaptedExpense> adaptedExpenses
    ) throws IllegalValueException {
        List<Expense> expenses = new ArrayList<>();
        for (JsonAdaptedExpense expense : adaptedExpenses) {
            expenses.add(expense.toModelType());
        }
        return expenses;
    }

    /**
     * Construct paidByPayers map for group object from list of expenses.
     * @param expenses List of expenses in group.
     * @param members List of members in group.
     * @return Map between people and the amounts they have paid.
     */
    public static Map<Person, Cost> buildPayerMapFromListOfExpenses(List<Expense> expenses, List<Person> members) {
        Map<Person, Cost> paidByPayers = new HashMap<>();
        for (Person member : members) {
            paidByPayers.put(member, new Cost(0.0));
        }
        for (Expense expense : expenses) {
            Person payer = expense.getPayer();
            if (!paidByPayers.containsKey(payer)) {
                paidByPayers.put(payer, expense.getCost());
            } else {
                paidByPayers.computeIfPresent(payer, (key, val) -> val.add(expense.getCost()));
            }
        }
        return paidByPayers;
    }

    /**
     * Construct paidByPayees map for group object from list of expenses.
     * @param expenses List of expenses in group.
     * @param members List of members in group.
     * @return Map between people and their expenditures.
     */
    public static Map<Person, Cost> buildIndividualExpenditureMapFromListOfExpenses(List<Expense> expenses,
                                                                                    List<Person> members) {
        Map<Person, Cost> paidByPayees = new HashMap<>();
        for (Person member : members) {
            paidByPayees.put(member, new Cost(0.0));
        }
        for (Expense expense : expenses) {
            Map<Person, Cost> individualExpenditures = expense.getIndividualExpenses();
            for (Person payee : individualExpenditures.keySet()) {
                if (!paidByPayees.containsKey(payee)) {
                    paidByPayees.put(payee, individualExpenditures.get(payee));
                } else {
                    paidByPayees.computeIfPresent(payee, (key, val) -> val.add(individualExpenditures.get(payee)));
                }
            }
        }
        return paidByPayees;
    }
}
