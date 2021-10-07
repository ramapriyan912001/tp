package seedu.awe.model.group;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import seedu.awe.model.expense.Expense;
import seedu.awe.model.person.Person;
import seedu.awe.model.tag.Tag;

public class Group {
    //TODO: WRITE MESSAGE CONSTRAINTS MESSAGE
    private final GroupName groupName;
    private final ArrayList<Person> members = new ArrayList<>();
    private final Set<Tag> tags = new HashSet<>();
    private final ArrayList<Expense> expenses = new ArrayList<>();

    /**
     * Creates new Group object.
     *
     * @param groupName String object representing name of the group.
     * @param members ArrayList of Person objects representing list of members.
     */
    public Group(GroupName groupName, ArrayList<Person> members) {
        this.groupName = groupName;
        for (Person member : members) {
            this.addMember(member);
        }
    }

    /**
     * Creates new Group object with tags.
     *
     * @param groupName String object representing name of the group.
     * @param members ArrayList of Person objects representing list of members.
     * @param tags Set of Tag objects to describe group.
     */
    public Group(GroupName groupName, ArrayList<Person> members, Set<Tag> tags) {
        this.groupName = groupName;
        for (Person member : members) {
            this.addMember(member);
        }
        this.tags.addAll(tags);
    }

    /**
     * Creates new Group object with tags.
     *
     * @param groupName String object representing name of the group.
     * @param members ArrayList of Person objects representing list of members.
     * @param tags Set of Tag objects to describe group.
     * @param expenses List of expenses in the group.
     */
    public Group(GroupName groupName, ArrayList<Person> members, Set<Tag> tags, ArrayList<Expense> expenses) {
        this.groupName = groupName;
        for (Person member : members) {
            this.addMember(member);
        }
        this.tags.addAll(tags);
        for (Expense expense : expenses) {
            this.expenses.add(expense);
        }
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

    public GroupName getGroupName() {
        return groupName;
    }

    public ArrayList<Person> getMembers() {
        return members;
    }

    /**
     * Checks if a person is part of the group.
     *
     * @param person to check if is part of the group.
     * @return Whether the person is part of the group.
     */
    public boolean isPartOfGroup(Person person) {
        for (Person member : members) {
            if (member.equals(person)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public ArrayList<Expense> getExpenses() {
        return expenses;
    }

    /**
     * Checks for group equality
     * @param otherGroup group to be compared
     * @return Boolean value for equality
     */
    public boolean isSameGroup(Group otherGroup) {
        if (this == otherGroup) {
            return true;
        }
        return otherGroup != null
               && this.groupName.equals(otherGroup.getGroupName());
    }


    @Override
    public boolean equals (Object otherGroup) {
        if (this == otherGroup) {
            return true;
        } else if (otherGroup == null) {
            return false;
        } else if (otherGroup instanceof Group) {
            return this.isSameGroup((Group) otherGroup);
        } else {
            return false;
        }
    }

    /**
     * Adds an expense into the group.
     *
     * @param expense to be added to the group.
     * @return A new group with the expense added to it.
     */
    public Group addExpense(Expense expense) {
        ArrayList<Expense> newExpenses = new ArrayList<>(expenses);
        newExpenses.add(expense);
        return new Group(groupName, members, tags, newExpenses);
    }

    @Override
    public String toString() {
        //TODO: TO BE IMPROVED TO POSSIBLY LIST ALL MEMBERS NAMES
        return String.format("Group %s with %d members", this.groupName, this.members.size());
    }
}
