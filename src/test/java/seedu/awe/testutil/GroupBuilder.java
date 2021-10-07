package seedu.awe.testutil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import seedu.awe.model.group.Group;
import seedu.awe.model.group.GroupName;
import seedu.awe.model.person.Person;
import seedu.awe.model.tag.Tag;
import seedu.awe.model.util.SampleDataUtil;

/**
 * A utility class to help with building Group objects.
 */
public class GroupBuilder {

    public static final String DEFAULT_NAME = "Bali";

    private GroupName groupName;
    private final ArrayList<Person> members = new ArrayList<>();
    private Set<Tag> tags = new HashSet<>();

    /**
     * Creates a {@code GroupBuilder} with the default details.
     */
    public GroupBuilder() {
        groupName = new GroupName(DEFAULT_NAME);
    }

    /**
     * Initializes the GroupBuilder with the data of {@code groupToCopy}.
     */
    public GroupBuilder(Group groupToCopy) {
        groupName = groupToCopy.getGroupName();
        for (int i = 0; i < groupToCopy.getMembers().size(); i++) {
            Person personToCopy = groupToCopy.getMembers().get(i);
            Person copyOfPerson = new PersonBuilder(personToCopy).build();
            members.add(copyOfPerson);
        }
    }

    /**
     * Sets the {@code GroupName} of the {@code Group} that we are building.
     */
    public GroupBuilder withGroupName(String groupName) {
        this.groupName = new GroupName(groupName);
        return this;
    }

    /**
     * Sets the {@code Person} of the {@code Group} that we are building.
     */
    public GroupBuilder withAnotherMember(Person person) {
        this.members.add(new PersonBuilder(person).build());
        return this;
    }

    /**
     * Sets the {@code List of Person} of the {@code Group} that we are building.
     */
    public GroupBuilder withMembers(Person... persons) {
        for (Person person : persons) {
            this.members.add(new PersonBuilder(person).build());
        }
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Group} that we are building.
     */
    public GroupBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    public Group build() {
        return new Group(this.groupName, this.members, this.tags);
    }

    /**
     * Returns a valid Group object.
     *
     * @return Group object with default name "Bali" and default member "Amy Bee".
     */
    public Group buildValidGroup() {
        GroupName bali = new GroupName("Bali");
        ArrayList<Person> members = new ArrayList<Person>();
        members.add(new PersonBuilder().build());
        return new Group(bali, members);
    }

    public GroupName getValidGroupName() {
        GroupName bali = new GroupName("Bali");
        return bali;
    }

    public ArrayList<Person> getValidMembers() {
        ArrayList<Person> members = new ArrayList<>();
        members.add(new PersonBuilder().build());
        return members;
    }

}
