package seedu.awe.model.group;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.awe.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.awe.model.person.Name;

public class GroupNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new GroupName(null));
    }

    @Test
    public void constructor_invalidGroupName_throwsIllegalArgumentException() {
        String invalidGroupName = "";
        assertThrows(IllegalArgumentException.class, () -> new GroupName(invalidGroupName));
    }

    @Test
    public void constructor_tooLongGroupName_throwsIllegalArgumentException() {
        String invalidName = "GroupNameeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee";
        assertThrows(IllegalArgumentException.class, () -> new Name(invalidName));
    }

    @Test
    public void isValidGroupName() {
        // null group name
        assertThrows(NullPointerException.class, () -> GroupName.isValidGroupName(null));

        // invalid group name
        assertFalse(GroupName.isValidGroupName("")); // empty string
        assertFalse(GroupName.isValidGroupName(" ")); // spaces only
        assertFalse(GroupName.isValidGroupName("^")); // only non-alphanumeric characters
        assertFalse(GroupName.isValidGroupName("bali*")); // contains non-alphanumeric characters
        assertFalse(GroupName.isValidGroupName("123456789012345678901234567"
                + "890123456789012345678901")); // contains > 50 characters

        // valid group name
        assertTrue(GroupName.isValidGroupName("hong kong")); // alphabets only
        assertTrue(GroupName.isValidGroupName("12345")); // numbers only
        assertTrue(GroupName.isValidGroupName("budapest 2nd time")); // alphanumeric characters
        assertTrue(GroupName.isValidGroupName("Capital Bangkok")); // with capital letters
        assertTrue(GroupName.isValidGroupName("The Grand Canyon and Niagara Falls")); // long names
        assertTrue(GroupName.isValidGroupName("123456789012345678901234567"
                + "89012345678901234567890")); // contains 50 characters
    }

    @Test
    public void equals() {
        GroupName groupName = new GroupName("Africa");

        // same instance -> true
        assertTrue(groupName.equals(groupName));

        // null -> false
        assertFalse(groupName.equals(null));

        // not instance of GroupName -> false
        assertFalse(groupName.equals(new Name("Africa")));

        // String is passed in -> false
        assertFalse(groupName.equals("Africa"));

        // different group name -> return false
        assertFalse(groupName.equals(new GroupName("South Africa")));

        // different case -> return false
        assertFalse(groupName.equals(new GroupName("africa")));
    }
}
