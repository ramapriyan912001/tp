package seedu.awe.testutil;

import seedu.awe.model.Awe;
import seedu.awe.model.person.Person;

/**
 * A utility class to help with building Awe objects.
 * Example usage: <br>
 *     {@code Awe ab = new AweBuilder().withPerson("John", "Doe").build();}
 */
public class AweBuilder {

    private Awe awe;

    public AweBuilder() {
        awe = new Awe();
    }

    public AweBuilder(Awe awe) {
        this.awe = awe;
    }

    /**
     * Adds a new {@code Person} to the {@code Awe} that we are building.
     */
    public AweBuilder withPerson(Person person) {
        awe.addPerson(person);
        return this;
    }

    public Awe build() {
        return awe;
    }
}
