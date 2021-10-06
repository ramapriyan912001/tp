package seedu.awe.model.expense;

import static java.util.Objects.requireNonNull;
import static seedu.awe.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.awe.model.group.exceptions.GroupNotFoundException;

/**
 * A list of expenses that enforces uniqueness between its elements and does not allow nulls.
 * An expense is considered unique by comparing using {@code Expense#isSameExpense(Expense)}. As such, adding and updating of
 * groups uses Expense#isSameExpense(Expense) for equality so as to ensure that the expense being added or updated is
 * unique in terms of identity in the UniqueExpenseList. However, the removal of an expense uses
 * Expense#equals(Object) so as to ensure that the expense with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 *
 */
public class UniqueExpenseList implements Iterable<Expense> {

    private final ObservableList<Expense> internalList = FXCollections.observableArrayList();
    private final ObservableList<Expense> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent group as the given argument.
     */
    public boolean contains(Expense toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a group to the list.
     * The group must not already exist in the list.
     */
    public void add(Expense toAdd) {
        requireNonNull(toAdd);
        internalList.add(toAdd);
    }

    /**
     * Replaces the expense {@code target} in the list with {@code editedExpense}.
     * {@code target} must exist in the list.
     * The expense identity of {@code editedExpense} must not be the same as another existing expense in the list.
     */
    public void setExpense(Expense target, Expense editedExpense) {
        requireAllNonNull(target, editedExpense);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new GroupNotFoundException();
        }
        internalList.set(index, editedExpense);
    }

    public void setExpense(UniqueExpenseList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code expenses}.
     * {@code expenses} must not contain duplicate expenses.
     */
    public void setExpense(List<Expense> expense) {
        requireAllNonNull(expense);
        internalList.setAll(expense);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Expense> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Expense> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof UniqueExpenseList)) { // instanceof handles nulls
            return false;
        }

        return internalList.equals(((UniqueExpenseList) other).internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }
}
