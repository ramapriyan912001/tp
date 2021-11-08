package seedu.awe.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.awe.commons.exceptions.IllegalValueException;
import seedu.awe.model.Awe;
import seedu.awe.model.ReadOnlyAwe;
import seedu.awe.model.group.Group;
import seedu.awe.model.person.Person;

/**
 * An Immutable Awe that is serializable to JSON format.
 */
@JsonRootName(value = "awe")
class JsonSerializableAwe {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_GROUP = "Groups list contains duplicate group(s).";
    public static final String MESSAGE_INVALID_GROUP_MEMBER = "Group member(s) not found in persons list.";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedGroup> groups = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAwe} with the given persons.
     */
    @JsonCreator
    public JsonSerializableAwe(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
                               @JsonProperty("groups") List<JsonAdaptedGroup> groups) {
        this.persons.addAll(persons);
        this.groups.addAll(groups);
    }

    /**
     * Converts a given {@code ReadOnlyAwe} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAwe}.
     */
    public JsonSerializableAwe(ReadOnlyAwe source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        groups.addAll(source.getGroupList().stream().map(JsonAdaptedGroup::new).collect(Collectors.toList()));
    }

    /**
     * Checks if the members in the group are inside list of persons.
     *
     * @param group The group to check if members are valid.
     * @param awe The address book containing list of persons.
     */
    public boolean areValidMembers(Group group, Awe awe) {
        for (Person person : group.getMembers()) {
            if (!awe.hasExactPerson(person)) {
                return false;
            }
        } return true;
    }

    /**
     * Converts this awe book into the model's {@code Awe} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Awe toModelType() throws IllegalValueException {
        Awe awe = new Awe();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (awe.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            awe.addPerson(person);
        }
        for (JsonAdaptedGroup jsonAdaptedGroup : groups) {
            Group group = jsonAdaptedGroup.toModelType();
            if (awe.hasGroup(group)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_GROUP);
            }
            if (!areValidMembers(group, awe)) {
                throw new IllegalValueException(MESSAGE_INVALID_GROUP_MEMBER);
            }
            awe.addGroup(group);
        }
        return awe;
    }

}
