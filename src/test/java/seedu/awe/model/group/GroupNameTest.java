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
        assertFalse(Name.isValidName("")); // empty string
        assertFalse(Name.isValidName(" ")); // spaces only
        assertFalse(Name.isValidName("^")); // only non-alphanumeric characters
        assertFalse(Name.isValidName("bali*")); // contains non-alphanumeric characters
        assertFalse(Name.isValidName("123456789012345678901234567"
                + "890123456789012345678901")); // contains > 50 characters

        // valid name
        assertTrue(Name.isValidName("hong kong")); // alphabets only
        assertTrue(Name.isValidName("12345")); // numbers only
        assertTrue(Name.isValidName("budapest 2nd time")); // alphanumeric characters
        assertTrue(Name.isValidName("Capital Bangkok")); // with capital letters
        assertTrue(Name.isValidName("The Grand Canyon and Niagara Falls")); // long names
        assertTrue(Name.isValidName("123456789012345678901234567"
                + "89012345678901234567890")); // contains 50 characters
    }
}
