package seedu.awe.model.payment;

import static java.util.Objects.requireNonNull;
import static seedu.awe.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.awe.model.group.Group;
import seedu.awe.model.payment.exceptions.PaymentNotFoundException;
import seedu.awe.model.person.exceptions.DuplicatePersonException;

public class PaymentList implements Iterable<Payment> {

    private final ObservableList<Payment> internalList = FXCollections.observableArrayList();
    private final ObservableList<Payment> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);
    private Optional<Group> group;

    public PaymentList() {
        group = Optional.empty();
    }
    public PaymentList(Group group) {
        this.group = Optional.of(group);
    }

    public void setGroup(Group group) {
        this.group = Optional.of(group);
    }

    public Optional<Group> getGroup() {
        return group;
    }

    /**
     * Returns true if the list contains an equivalent payment as the given argument.
     */
    public boolean contains(Payment toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Clears the entire list of payments.
     */
    public void clear() {
        internalList.clear();
    }

    /**
     * Adds all payments from the given list to the list of payments.
     *
     * @param payments List of payments to add.
     */
    public void addAll(List<Payment> payments) {
        internalList.addAll(payments);
    }

    /**
     * Adds an payment to the list.
     */
    public void add(Payment toAdd) {
        requireNonNull(toAdd);
        internalList.add(toAdd);
    }

    /**
     * Replaces the payment {@code target} in the list with {@code editedPayment}.
     * {@code target} must exist in the list.
     */
    public void setPayment(Payment target, Payment editedPayment) {
        requireAllNonNull(target, editedPayment);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PaymentNotFoundException();
        }

        if (!target.equals(editedPayment) && contains(editedPayment)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedPayment);
    }

    /**
     * Removes the equivalent payment from the list.
     * The person must exist in the list.
     */
    public void remove(Payment toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PaymentNotFoundException();
        }
    }

    public void setPayments(PaymentList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code payments}.
     */
    public void setPayments(List<Payment> payments) {
        requireAllNonNull(payments);
        internalList.setAll(payments);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Payment> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Payment> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof PaymentList)) { // instanceof handles nulls
            return false;
        }

        return internalList.equals(((PaymentList) other).internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }
}
