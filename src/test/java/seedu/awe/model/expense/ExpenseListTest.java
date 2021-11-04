package seedu.awe.model.expense;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.awe.testutil.Assert.assertThrows;
import static seedu.awe.testutil.TypicalExpenses.DINNER;
import static seedu.awe.testutil.TypicalExpenses.HOLIDAY;
import static seedu.awe.testutil.TypicalPersons.BOB;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.awe.model.expense.exceptions.ExpenseNotFoundException;
import seedu.awe.model.person.Person;
import seedu.awe.testutil.ExpenseBuilder;

public class ExpenseListTest {

    private final ExpenseList expenseList = new ExpenseList();

    @Test
    public void contains_nullExpense_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> expenseList.contains(null));
    }

    @Test
    public void contains_expenseNotInList_returnsFalse() {
        assertFalse(expenseList.contains(HOLIDAY));
    }

    @Test
    public void contains_expenseInList_returnsTrue() {
        expenseList.add(HOLIDAY);
        assertTrue(expenseList.contains(HOLIDAY));
    }

    @Test
    public void add_nullExpense_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> expenseList.add(null));
    }

    @Test
    public void setExpense_nullTargetExpense_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> expenseList.setExpense(null, HOLIDAY));
    }

    @Test
    public void setExpense_nullEditedExpense_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> expenseList.setExpense(HOLIDAY, null));
    }

    @Test
    public void setPerson_editedExpenseIsSameExpense_success() {
        expenseList.add(HOLIDAY);
        expenseList.setExpense(HOLIDAY, HOLIDAY);
        ExpenseList expectedExpenseList = new ExpenseList();
        expectedExpenseList.add(HOLIDAY);
        assertEquals(expectedExpenseList, expenseList);
    }

    @Test
    public void setExpense_editedExpenseHasSameIdentity_success() {
        expenseList.add(HOLIDAY);
        Map<Person, Cost> validSplitExpenses = new HashMap<>();
        validSplitExpenses.put(BOB, new Cost(5));
        Expense editedHoliday = new ExpenseBuilder(HOLIDAY).withSplitExpenses(validSplitExpenses)
                .build();
        expenseList.setExpense(HOLIDAY, editedHoliday);
        ExpenseList expectedExpenseList = new ExpenseList();
        expectedExpenseList.add(editedHoliday);
        assertEquals(expectedExpenseList, expenseList);
    }

    @Test
    public void setExpense_editedExpenseHasDifferentIdentity_success() {
        expenseList.add(HOLIDAY);
        expenseList.setExpense(HOLIDAY, DINNER);
        ExpenseList expectedExpenseList = new ExpenseList();
        expectedExpenseList.add(DINNER);
        assertEquals(expectedExpenseList, expenseList);
    }

    @Test
    public void setExpense_expenseDooesNotExist_throwsExpenseNotFoundException() {
        assertThrows(ExpenseNotFoundException.class, () -> expenseList.setExpense(HOLIDAY, DINNER));
    }

    @Test
    public void remove_nullExpense_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> expenseList.remove(null));
    }

    @Test
    public void remove_expenseDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(ExpenseNotFoundException.class, () -> expenseList.remove(HOLIDAY));
    }

    @Test
    public void remove_existingExpense_removesExpense() {
        expenseList.add(HOLIDAY);
        expenseList.remove(HOLIDAY);
        ExpenseList expectedExpenseList = new ExpenseList();
        assertEquals(expectedExpenseList, expenseList);
    }

    @Test
    public void setExpenses_nullExpenseList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> expenseList.setExpenses((ExpenseList) null));
    }

    @Test
    public void setExpenses_expenseList_replacesOwnListWithProvidedExpenseList() {
        expenseList.add(HOLIDAY);
        ExpenseList expectedExpenseList = new ExpenseList();
        expectedExpenseList.add(DINNER);
        expenseList.setExpenses(expectedExpenseList);
        assertEquals(expectedExpenseList, expenseList);
    }

    @Test
    public void setExpenses_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> expenseList.setExpenses((List<Expense>) null));
    }

    @Test
    public void setExpenses_list_replacesOwnListWithProvidedList() {
        expenseList.add(HOLIDAY);
        List<Expense> validExpenseList = Collections.singletonList(DINNER);
        expenseList.setExpenses(validExpenseList);
        ExpenseList expectedExpenseList = new ExpenseList();
        expectedExpenseList.add(DINNER);
        assertEquals(expectedExpenseList, expenseList);
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> expenseList
                .asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void hashcode() {
        assertEquals(expenseList.hashCode(), expenseList.hashCode());
        assertEquals(expenseList.hashCode(), new ExpenseList().hashCode());

        ExpenseList differentExpenseList = new ExpenseList();
        differentExpenseList.add(HOLIDAY);
        assertNotEquals(expenseList, differentExpenseList);
    }

    @Test
    public void iterator() {
        // Empty List
        assertFalse(expenseList.iterator().hasNext());

        // Contains 1 Person
        expenseList.add(HOLIDAY);
        Iterator<Expense> expenseListIterator = expenseList.iterator();

        ObservableList<Expense> singlePersonObservableList = FXCollections.observableArrayList(HOLIDAY);
        Iterator<Expense> singlePersonObservableListIterator = singlePersonObservableList.iterator();

        assertEquals(singlePersonObservableListIterator.next(), expenseListIterator.next());
        assertFalse(expenseListIterator.hasNext());

        // Contains > 1 Person
        expenseList.add(DINNER);
        expenseListIterator = expenseList.iterator();

        ObservableList<Expense> multiplePersonObservableList = FXCollections.observableArrayList(HOLIDAY, DINNER);
        Iterator<Expense> multiplePersonObservableListIterator = multiplePersonObservableList.iterator();

        assertEquals(multiplePersonObservableListIterator.next(), expenseListIterator.next());
        assertEquals(multiplePersonObservableListIterator.next(), expenseListIterator.next());
        assertFalse(expenseListIterator.hasNext());
    }

    @Test
    public void equals() {
        // same instance -> true
        assertTrue(expenseList.equals(expenseList));

        // null -> false
        assertFalse(expenseList.equals(null));

        // list is passed in -> false
        assertFalse(expenseList.equals(new ArrayList<>()));

        // different person -> return false
        ExpenseList containExtraExpense = new ExpenseList();
        containExtraExpense.add(HOLIDAY);

        assertFalse(expenseList.equals(containExtraExpense));

        // contain same amount of person
        expenseList.add(HOLIDAY);

        assertTrue(expenseList.equals(containExtraExpense));
    }
}
