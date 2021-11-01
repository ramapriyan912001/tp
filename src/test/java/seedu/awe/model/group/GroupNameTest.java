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
    public void isValidGroupName() {
        // null name
        assertThrows(NullPointerException.class, () -> GroupName.isValidGroupName(null));

        // invalid name
        assertFalse(Name.isValidGroupName("")); // empty string
        assertFalse(Name.isValidGroupName(" ")); // spaces only
        assertFalse(Name.isValidGroupName("^")); // only non-alphanumeric characters
        assertFalse(Name.isValidGroupName("bali*")); // contains non-alphanumeric characters
        assertFalse(Name.isValidGroupName("123456789012345678901234567"
                + "890123456789012345678901")); // contains > 50 characters

        // valid name
        assertTrue(Name.isValidGroupName("hong kong")); // alphabets only
        assertTrue(Name.isValidGroupName("12345")); // numbers only
        assertTrue(Name.isValidGroupName("budapest 2nd time")); // alphanumeric characters
        assertTrue(Name.isValidGroupName("Capital Bangkok")); // with capital letters
        assertTrue(Name.isValidGroupName("The Grand Canyon and Niagara Falls")); // long names
        assertTrue(Name.isValidGroupName("123456789012345678901234567"
                + "89012345678901234567890")); // contains 50 characters
    }
}
