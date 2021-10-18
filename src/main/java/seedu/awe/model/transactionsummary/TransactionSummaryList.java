package seedu.awe.model.transactionsummary;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.awe.model.group.Group;

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
     * Set TransactionSummary from the given list to the list of TransactionSummary.
     *
     * @param transactionSummary List of TransactionSummary to add.
     */
    public void set(List<TransactionSummary> transactionSummary) {
        internalList.clear();
        internalList.addAll(transactionSummary);
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
