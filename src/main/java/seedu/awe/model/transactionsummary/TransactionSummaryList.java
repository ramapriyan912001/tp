package seedu.awe.model.transactionsummary;

import static java.util.Objects.requireNonNull;
import static seedu.awe.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.awe.model.group.Group;
import seedu.awe.model.person.exceptions.DuplicatePersonException;
import seedu.awe.model.transactionsummary.exceptions.TransactionSummaryNotFoundException;

public class TransactionSummaryList implements Iterable<TransactionSummary> {

    private final ObservableList<TransactionSummary> internalList = FXCollections.observableArrayList();
    private final ObservableList<TransactionSummary> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);
    private Optional<Group> group;

    public TransactionSummaryList() {
        group = Optional.empty();
    }
    public TransactionSummaryList(Group group) {
        this.group = Optional.of(group);
    }

    public void setGroup(Group group) {
        this.group = Optional.of(group);
    }

    public Optional<Group> getGroup() {
        return group;
    }

    /**
     * Returns true if the list contains an equivalent TransactionSummary as the given argument.
     */
    public boolean contains(TransactionSummary toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Clears the entire list of TransactionSummary.
     */
    public void clear() {
        internalList.clear();
    }

    /**
     * Adds all TransactionSummary from the given list to the list of TransactionSummary.
     *
     * @param transactionSummary List of TransactionSummary to add.
     */
    public void addAll(List<TransactionSummary> transactionSummary) {
        internalList.addAll(transactionSummary);
    }

    /**
     * Adds an TransactionSummary to the list.
     */
    public void add(TransactionSummary toAdd) {
        requireNonNull(toAdd);
        internalList.add(toAdd);
    }

    /**
     * Removes the equivalent TransactionSummary from the list.
     * The TransactionSummary must exist in the list.
     */
    public void remove(TransactionSummary toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new TransactionSummaryNotFoundException();
        }
    }

    /**
     * Replaces the TransactionSummary {@code target} in the list with {@code editedTransactionSummary}.
     * {@code target} must exist in the list.
     */
    public void setTransactionSummary(TransactionSummary target, TransactionSummary editedTransactionSummary) {
        requireAllNonNull(target, editedTransactionSummary);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new TransactionSummaryNotFoundException();
        }

        if (!target.equals(editedTransactionSummary) && contains(editedTransactionSummary)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedTransactionSummary);
    }

    public void setTransactionSummary(TransactionSummaryList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code transactionSummary}.
     */
    public void setTransactionSummary(List<TransactionSummary> transactionSummary) {
        requireAllNonNull(transactionSummary);
        internalList.setAll(transactionSummary);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<TransactionSummary> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<TransactionSummary> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TransactionSummaryList)) { // instanceof handles nulls
            return false;
        }

        return internalList.equals(((TransactionSummaryList) other).internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }
}
