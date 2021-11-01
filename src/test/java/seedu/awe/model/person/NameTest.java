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
        assertFalse(Name.isValidGroupName("123456789012345678901234567"
                + "890123456789012345678901")); // contains more than 50 characters

        // valid name
        assertTrue(Name.isValidGroupName("peter jack")); // alphabets only
        assertTrue(Name.isValidGroupName("12345")); // numbers only
        assertTrue(Name.isValidGroupName("peter the 2nd")); // alphanumeric characters
        assertTrue(Name.isValidGroupName("Capital Tan")); // with capital letters
        assertTrue(Name.isValidGroupName("David Roger Jackson Ray Jr 2nd")); // long names
        assertTrue(Name.isValidGroupName("123456789012345678901234567"
                + "89012345678901234567890")); // contains exactly 50 characters
    }
}
