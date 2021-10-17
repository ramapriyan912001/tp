package seedu.awe.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;
import java.util.PriorityQueue;

import seedu.awe.logic.commands.exceptions.CommandException;
import seedu.awe.model.Model;
import seedu.awe.model.group.Group;
import seedu.awe.model.payment.Payment;
import seedu.awe.model.person.Person;

public class CalculatePaymentsCommand extends Command {
    public static final String MESSAGE_USAGE = "calculatepayments gn/GROUP_NAME";
    public static final String MESSAGE_GROUP_DOES_NOT_EXIST = "This group does not exist in the awe book";
    private final Group group;

    public CalculatePaymentsCommand(Group group) {
        this.group = group;
    }

    private class Pair implements Comparable<Pair> {

        private double surplus;
        private Person person;

        Pair(double surplus, Person person) {
            this.surplus = surplus;
            this.person = person;
        }

        public double getSurplus() {
            return surplus;
        }

        public Person getPerson() {
            return person;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            if (!(other instanceof Pair)) { // instanceof handles nulls
                return false;
            }

            Pair otherPayment = (Pair) other;

            boolean isSamePerson = person.equals(otherPayment.getPerson());
            boolean isSameSurplus = surplus == otherPayment.getSurplus();

            return isSamePerson && isSameSurplus;
        }

        @Override
        public int hashCode() {
            return Objects.hash(surplus, person);
        }

        @Override
        public int compareTo(Pair p2) {
            if (this.getSurplus() < p2.getSurplus()) {
                return -1;
            } else if (this.getSurplus() > p2.getSurplus()) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(group);

        if (!model.hasGroup(group)) {
            throw new CommandException(MESSAGE_GROUP_DOES_NOT_EXIST);
        }

        Group group = model.getAddressBook().getGroupByName(this.group.getGroupName());

        // TODO: CALCULATE PAYMENTS HERE, EMPTY METHODS BELOW.

        return null;
    }

    /**
     * Calculates payments to be made between Persons in the group.
     * @param group group for which payments are to be calculated
     * @return List of payments to make.
     */
    public PriorityQueue<Payment> calculatePayments(Group group) {
        // TODO: CALCULATES PAYMENTS

        PriorityQueue<Payment> paymentsQueue = new PriorityQueue<>();

        return new PriorityQueue<>();
    }

    /**
     * Converts the set of payments into user-friendly text.
     * @param payments List of payments
     * @return Readable string
     */
    public String makePaymentsString(PriorityQueue<Payment> payments) {
        // TODO: PAYMENTS STRING MAKER
        return null;
    }
}
