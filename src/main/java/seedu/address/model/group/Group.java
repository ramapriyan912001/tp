package seedu.address.model.group;

import java.util.ArrayList;

import seedu.address.model.person.Person;

public class Group {
    private String name;
    private ArrayList<Person> travelGroup;

    /**
     * Creates new Group object.
     *
     * @param name String object representing name of the group.
     * @param members ArrayList of Person objects representing list of members.
     */
    public Group(String name, ArrayList<Person> members) {
        this.name = name;
        this.travelGroup = new ArrayList<>();
        for (Person member : members) {
            this.addMember(member);
        }
    }

    /**
     * Adds member to Group.
     *
     * @param member Person object representing member to be added to group.
     */
    public void addMember(Person member) {
        this.travelGroup.add(member);
    }

    /**
     * Removes member from Group.
     *
     * @param member Person object representing member to be removed from group.
     */
    public void removeMember(Person member) {
        this.travelGroup.remove(member);
    }

    public String getName() {
        return name;
    }
}
