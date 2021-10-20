package seedu.awe.model.expense;

import seedu.awe.model.person.Person;

import static seedu.awe.commons.util.CollectionUtil.requireAllNonNull;

public class IndividualAmount {

    private Person person;
    private Double expenditure;

    public IndividualAmount(Person person, Double expenditure) {
        requireAllNonNull(person, expenditure);
        this.person = person;
        this.expenditure = expenditure;
    }

    public IndividualAmount(Person person, String expenditure) {
        requireAllNonNull(person, expenditure);
        this.person = person;
        this.expenditure = Double.parseDouble(expenditure);
    }

    public Person getPerson() {
        return person;
    }

    public Double getExpenditure() {
        return expenditure;
    }

}
