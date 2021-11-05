package seedu.awe.testutil;

import seedu.awe.model.Awe;
import seedu.awe.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code Awe ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private Awe awe;

    public AddressBookBuilder() {
        awe = new Awe();
    }

    public AddressBookBuilder(Awe awe) {
        this.awe = awe;
    }

    /**
     * Adds a new {@code Person} to the {@code Awe} that we are building.
     */
    public AddressBookBuilder withPerson(Person person) {
        awe.addPerson(person);
        return this;
    }

    public Awe build() {
        return awe;
    }
}
