package seedu.address.model.group;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

public class Group {
    private String name;
    private final ArrayList<Person> members = new ArrayList<>();
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Creates new Group object.
     *
     * @param name String object representing name of the group.
     * @param members ArrayList of Person objects representing list of members.
     */
    public Group(String name, ArrayList<Person> members) {
        this.name = name;
        for (Person member : members) {
            this.addMember(member);
        }
    }

    /**
     * Creates new Group object with tags.
     *
     * @param name String object representing name of the group.
     * @param members ArrayList of Person objects representing list of members.
     * @param tags Set of Tag objects to describe group.
     */
    public Group(String name, ArrayList<Person> members, Set<Tag> tags) {
        this.name = name;
        for (Person member : members) {
            this.addMember(member);
        }
        this.tags.addAll(tags);
    }

    /**
     * Adds member to Group.
     *
     * @param member Person object representing member to be added to group.
     */
    public void addMember(Person member) {
        this.members.add(member);
    }

    /**
     * Removes member from Group.
     *
     * @param member Person object representing member to be removed from group.
     */
    public void removeMember(Person member) {
        this.members.remove(member);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Person> getMembers() {
        return members;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setName(String name) {
        this.name = name;
    }
}
