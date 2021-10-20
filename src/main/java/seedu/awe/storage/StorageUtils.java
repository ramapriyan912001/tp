package seedu.awe.storage;

import seedu.awe.commons.exceptions.IllegalValueException;
import seedu.awe.model.expense.Cost;
import seedu.awe.model.expense.IndividualAmount;
import seedu.awe.model.person.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StorageUtils {

    public static Map<JsonAdaptedPerson, String> jsonAdaptIndividualExpensesMap(Map<Person, Cost> individualExpenses) {
        Map<JsonAdaptedPerson, String> adaptedIndividualExpenses = new HashMap<>();
        for (Person person : individualExpenses.keySet()) {
            JsonAdaptedPerson jsonAdaptedPerson = new JsonAdaptedPerson(person);
            String individualExpense = String.valueOf(individualExpenses.get(person).getCost());
            adaptedIndividualExpenses.put(jsonAdaptedPerson, individualExpense);
        }
        return adaptedIndividualExpenses;
    }

    public static Map<Person, Cost> convertJsonAdaptedExpensesMapToModelType(
            Map<JsonAdaptedPerson, String> adaptedIndividualExpenses) throws IllegalValueException {
        Map<Person, Cost> individualExpenses = new HashMap<>();
        for (JsonAdaptedPerson jsonAdaptedPerson : adaptedIndividualExpenses.keySet()) {
            Person person = jsonAdaptedPerson.toModelType();
            Cost individualExpense = new Cost(adaptedIndividualExpenses.get(jsonAdaptedPerson));
            individualExpenses.put(person, individualExpense);
        }
        return individualExpenses;
    }

    public static List<IndividualAmount> convertExpenseMapToListOfIndividualAmounts(Map<Person, Cost> expenseMap) {
        List<IndividualAmount> individualAmounts = new ArrayList<>();
        for (Person person : expenseMap.keySet()) {
            IndividualAmount individualAmount = new IndividualAmount(person,
                    expenseMap.get(person).getCost());
            individualAmounts.add(individualAmount);
        }
        return individualAmounts;
    }

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
}
