package seedu.awe.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.awe.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Name(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> Name.isValidGroupName(null));

        // invalid name
        assertFalse(Name.isValidGroupName("")); // empty string
        assertFalse(Name.isValidGroupName(" ")); // spaces only
        assertFalse(Name.isValidGroupName("^")); // only non-alphanumeric characters
        assertFalse(Name.isValidGroupName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Name.isValidGroupName("peter jack")); // alphabets only
        assertTrue(Name.isValidGroupName("12345")); // numbers only
        assertTrue(Name.isValidGroupName("peter the 2nd")); // alphanumeric characters
        assertTrue(Name.isValidGroupName("Capital Tan")); // with capital letters
        assertTrue(Name.isValidGroupName("David Roger Jackson Ray Jr 2nd")); // long names
    }
}
