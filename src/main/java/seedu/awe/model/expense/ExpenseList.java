package seedu.awe.model.expense;

import static java.util.Objects.requireNonNull;
import static seedu.awe.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.awe.model.expense.exceptions.ExpenseNotFoundException;
import seedu.awe.model.group.Group;
import seedu.awe.model.person.exceptions.DuplicatePersonException;

public class ExpenseList implements Iterable<Expense> {

    private final ObservableList<Expense> internalList = FXCollections.observableArrayList();
    private final ObservableList<Expense> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);
    private Optional<Group> group;

    public ExpenseList() {
        group = Optional.empty();
    }
    public ExpenseList(Group group) {
        this.group = Optional.of(group);
    }

    public void setGroup(Group group) {
        this.group = Optional.of(group);
    }

    public Optional<Group> getGroup() {
        return group;
    }

    /**
     * Returns true if the list contains an equivalent expense as the given argument.
     */
    public boolean contains(Expense toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Clears the entire list of expenses.
     */
    public void clear() {
        internalList.clear();
    }

    /**
     * Adds all expenses from the given list to the list of expenses.
     *
     * @param expenses List of expenses to add.
     */
    public void addAll(List<Expense> expenses) {
        internalList.addAll(expenses);
    }

    /**
     * Adds an expense to the list.
     */
    public void add(Expense toAdd) {
        requireNonNull(toAdd);
        internalList.add(toAdd);
    }

    /**
     * Replaces the expense {@code target} in the list with {@code editedExpense}.
     * {@code target} must exist in the list.
     */
    public void setExpense(Expense target, Expense editedExpense) {
        requireAllNonNull(target, editedExpense);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ExpenseNotFoundException();
        }

        if (!target.equals(editedExpense) && contains(editedExpense)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedExpense);
    }

    /**
     * Removes the equivalent expense from the list.
     * The person must exist in the list.
     */
    public void remove(Expense toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ExpenseNotFoundException();
        }
    }

    public void setExpenses(ExpenseList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code expenses}.
     */
    public void setExpenses(List<Expense> expenses) {
        requireAllNonNull(expenses);
        internalList.setAll(expenses);
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

        if (!(other instanceof ExpenseList)) { // instanceof handles nulls
            return false;
        }

        return internalList.equals(((ExpenseList) other).internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }
}
