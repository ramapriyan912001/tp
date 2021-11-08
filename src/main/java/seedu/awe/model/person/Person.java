package seedu.awe.model.person;

import static seedu.awe.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.awe.model.group.Group;
import seedu.awe.model.tag.Tag;

/**
 * Represents a Person in the awe book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Set<Tag> tags) {
        requireAllNonNull(name, phone, tags);
        this.name = name;
        this.phone = phone;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }


    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns an ArrayList of groups that the instance of Person belongs to.
     *
     * @param groups ObservableList of Group objects that exist in the awe.
     * @return ArrayList of Group objects that the instance of Person belongs to.
     */
    public ArrayList<Group> getPersonGroups(ObservableList<Group> groups) {
        ArrayList<Group> personGroups = new ArrayList<>();
        for (Group group : groups) {
            if (group.getMembers().stream().anyMatch(p -> p.getName().equals(this.getName()))) {
                personGroups.add(group);
            }
        }
        return personGroups;
    }

    /**
     * Returns a String representation of group names that the instance of Person belongs to.
     *
     * @param groups ObservableList of Group objects that exist in the awe.
     * @return String object representing all groups that the instance of Person belongs to.
     */
    public String getGroupsName(ObservableList<Group> groups) {
        String result = "";
        ArrayList<Group> personGroups = getPersonGroups(groups);
        for (int i = 0; i < personGroups.size(); i++) {
            Group group = personGroups.get(i);
            if (i != personGroups.size() - 1) {
                result = result + group.getGroupName() + ", ";
            } else {
                result = result + group.getGroupName();
            }
        }
        return result;
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
