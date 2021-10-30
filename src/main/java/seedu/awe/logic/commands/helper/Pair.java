package seedu.awe.logic.commands.helper;

import seedu.awe.model.person.Person;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class Pair implements Comparable<Pair> {

    private double surplus;
    private Person person;

    public Pair(double surplus, Person person) {
        requireNonNull(person);
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
