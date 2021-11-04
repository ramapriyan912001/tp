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
    public void constructor_tooLongName_throwsIllegalArgumentException() {
        String invalidName = "123456789012345678901234567890123456789012345678901";
        assertThrows(IllegalArgumentException.class, () -> new Name(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> Name.isValidName(null));

        // invalid name
        assertFalse(Name.isValidName("")); // empty string
        assertFalse(Name.isValidName(" ")); // spaces only
        assertFalse(Name.isValidName("^")); // only non-alphanumeric characters
        assertFalse(Name.isValidName("peter*")); // contains non-alphanumeric characters
        assertFalse(Name.isValidName("123456789012345678901234567"
                + "890123456789012345678901")); // contains more than 50 characters

        // valid name
        assertTrue(Name.isValidName("peter jack")); // alphabets only
        assertTrue(Name.isValidName("12345")); // numbers only
        assertTrue(Name.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(Name.isValidName("Capital Tan")); // with capital letters
        assertTrue(Name.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
        assertTrue(Name.isValidName("123456789012345678901234567"
                + "89012345678901234567890")); // contains exactly 50 characters
    }

    @Test
    public void equals() {
        Name name = new Name("John");

        // same instance -> true
        assertTrue(name.equals(name));

        // null -> false
        assertFalse(name.equals(null));

        // String is passed in -> false
        assertFalse(name.equals("John"));

        // different name -> return false
        assertFalse(name.equals(new Name("Amy")));

        // different case -> return false
        assertFalse(name.equals(new Name("john")));
    }
}
