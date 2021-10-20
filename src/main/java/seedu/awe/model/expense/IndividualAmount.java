package seedu.awe.model.expense;

import static seedu.awe.commons.util.CollectionUtil.requireAllNonNull;

import seedu.awe.model.person.Person;

public class IndividualAmount {

    private Person person;
    private Double expenditure;

    /**
     * Intermediate class to store expense maps in json format
     * @param person person to whom expenditure is attached.
     * @param expenditure expenditure attached to person.
     */
    public IndividualAmount(Person person, Double expenditure) {
        requireAllNonNull(person, expenditure);
        this.person = person;
        this.expenditure = expenditure;
    }

    public Person getPerson() {
        return person;
    }

    public Double getExpenditure() {
        return expenditure;
    }

}
