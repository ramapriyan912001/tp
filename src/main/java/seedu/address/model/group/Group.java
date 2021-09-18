package seedu.address.model.group;

import java.util.ArrayList;

import seedu.address.model.person.Person;

public class Group {
    private String name;
    private ArrayList<Person> travelGroup;
    private boolean isActive;

    public Group(String name, ArrayList<Person> members) {
        this.name = name;
        this.travelGroup = new ArrayList<>();
        for (Person member : members) {
            this.addMember(member);
        }
        this.isActive = false;
    }

    public ArrayList<Person> addMember(Person member) {
        this.travelGroup.add(member);
        return this.travelGroup;
    }

    public ArrayList<Person> removeMember(Person member) {
        this.travelGroup.remove(member);
        return this.travelGroup;
    }
}
