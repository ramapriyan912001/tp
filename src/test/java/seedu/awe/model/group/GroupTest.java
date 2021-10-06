package seedu.awe.model.group;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.awe.testutil.Assert.assertThrows;
import static seedu.awe.testutil.TypicalGroups.AMSTERDAM;
import static seedu.awe.testutil.TypicalGroups.BALI;
import static seedu.awe.testutil.TypicalPersons.HOON;
import static seedu.awe.testutil.TypicalPersons.IDA;

import org.junit.jupiter.api.Test;

import seedu.awe.testutil.GroupBuilder;

public class GroupTest {
    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Group group = new GroupBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> group.getTags().remove(0));
    }

    @Test
    public void isSameGroup() {
        // same object -> returns true
        assertTrue(BALI.isSameGroup(BALI));

        // null -> returns false
        assertFalse(BALI.isSameGroup(null));

        // same name, all members different -> returns true
        Group editedBali = new GroupBuilder(BALI).withMembers(IDA).build();
        assertTrue(BALI.isSameGroup(editedBali));

        // same name, all tags different -> returns true
        editedBali = new GroupBuilder(BALI).withTags("Business").build();
        assertTrue(BALI.isSameGroup(editedBali));

        // name differs in case, all other attributes same -> returns false
        editedBali = new GroupBuilder(BALI).withGroupName(BALI.getGroupName().getName().toLowerCase()).build();
        assertFalse(BALI.isSameGroup(editedBali));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = BALI.getGroupName().getName() + " ";
        editedBali = new GroupBuilder(BALI).withGroupName(nameWithTrailingSpaces).build();
        assertFalse(BALI.isSameGroup(editedBali));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Group baliCopy = new GroupBuilder(BALI).build();
        assertTrue(BALI.equals(baliCopy));

        // same object -> returns true
        assertTrue(BALI.equals(BALI));

        // null -> returns false
        assertFalse(BALI.equals(null));

        // different type -> returns false
        assertFalse(BALI.equals(5));

        // different person -> returns false
        assertFalse(BALI.equals(AMSTERDAM));

        // different name -> returns false
        Group editedBali = new GroupBuilder(BALI).withGroupName(AMSTERDAM.getGroupName().getName()).build();
        assertFalse(BALI.equals(editedBali));

        // different tags -> returns true
        editedBali = new GroupBuilder(BALI).withTags("Family").build();
        assertTrue(BALI.equals(editedBali));

        // different members -> returns true
        editedBali = new GroupBuilder(BALI).withMembers(IDA, HOON).build();
        assertTrue(BALI.equals(BALI));
    }
}
