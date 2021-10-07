package seedu.awe.model;

import javafx.collections.ObservableList;
import seedu.awe.model.expense.Expense;
import seedu.awe.model.group.Group;
import seedu.awe.model.group.GroupName;
import seedu.awe.model.person.Person;

/**
 * Unmodifiable view of an awe book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    ObservableList<Group> getGroupList();

    /**
     * Returns an unmodifiable view of the expenses list.
     */
    ObservableList<Expense> getExpenseList();

    Group getGroupByName(GroupName groupName);
}
