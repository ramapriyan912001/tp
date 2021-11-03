package seedu.awe.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.awe.commons.exceptions.IllegalValueException;
import seedu.awe.model.AddressBook;
import seedu.awe.model.ReadOnlyAddressBook;
import seedu.awe.model.expense.Expense;
import seedu.awe.model.group.Group;
import seedu.awe.model.person.Person;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_GROUP = "Groups list contains duplicate group(s).";
    public static final String MESSAGE_INVALID_GROUP_MEMBER = "Group member(s) not found in persons list.";
    public static final String MESSAGE_INVALID_EXPENSE_PAYER = "The payer of the expense is not found in persons list.";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedGroup> groups = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
                                       @JsonProperty("groups") List<JsonAdaptedGroup> groups) {
        this.persons.addAll(persons);
        this.groups.addAll(groups);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        groups.addAll(source.getGroupList().stream().map(JsonAdaptedGroup::new).collect(Collectors.toList()));
    }

    /**
     * Checks if the members in the group are inside list of persons.
     *
     * @param group The group to check if members are valid.
     * @param addressBook The address book containing list of persons.
     */
    public boolean areValidMembers(Group group, AddressBook addressBook) {
        for (Person person : group.getMembers()) {
            if (!addressBook.hasPerson(person)) {
                return false;
            }
        } return true;
    }

    /**
     * Checks if the payers of the expenses are valid persons.
     *
     * @param group The group to check if members are valid.
     * @param addressBook The address book containing the list of persons.
     */
    public boolean areValidExpenses(Group group, AddressBook addressBook) {
        for (Expense expense : group.getExpenses()) {
            Person payer = expense.getPayer();
            if (!addressBook.hasPerson(payer)) {
                return false;
            }
        } return true;
    }

    /**
     * Converts this awe book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (addressBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addPerson(person);
        }
        for (JsonAdaptedGroup jsonAdaptedGroup : groups) {
            Group group = jsonAdaptedGroup.toModelType();
            if (addressBook.hasGroup(group)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_GROUP);
            }
            if (!areValidMembers(group, addressBook)) {
                throw new IllegalValueException(MESSAGE_INVALID_GROUP_MEMBER);
            }
            if (!areValidExpenses(group, addressBook)) {
                throw new IllegalValueException(MESSAGE_INVALID_EXPENSE_PAYER);
            }
            addressBook.addGroup(group);
        }
        return addressBook;
    }

}
