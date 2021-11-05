package seedu.awe.model.expense;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.awe.testutil.Assert.assertThrows;
import static seedu.awe.testutil.TypicalExpenses.HOLIDAY;

import org.junit.jupiter.api.Test;

public class DescriptionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Cost(null));
    }

    @Test
    public void constructor_invalidDescription_throwsIllegalArgumentException() {
        String invalidDescription = "";
        assertThrows(IllegalArgumentException.class, () -> new Cost(invalidDescription));
    }

    @Test
    public void constructor_longDescription_throwsIllegalArgumentException() {
        String invalidDescription = "123456789012345678901234567890123456789012345678901";
        assertThrows(IllegalArgumentException.class, () -> new Cost(invalidDescription));
    }

    @Test
    public void isValidName() {
        // null cost
        assertThrows(NullPointerException.class, () -> Description.isValidDescription(null));

        // invalid cost
        assertFalse(Description.isValidDescription("")); // empty string
        assertFalse(Description.isValidDescription(" ")); // spaces only
        assertFalse(Description.isValidDescription("^")); // only non-alphanumeric characters
        assertFalse(Description.isValidDescription("dinner*")); // contains non-alphanumeric characters
        assertFalse(Description.isValidDescription("123456789012345678901234567"
                + "890123456789012345678901")); // contains more than 50 characters

        // valid costs
        assertTrue(Description.isValidDescription("abcabc")); // alphabet characters only
        assertTrue(Description.isValidDescription("5050")); // with 2 decimal places only
        assertTrue(Description.isValidDescription("Abc Abc")); // more than 2 decimal places
        assertTrue(Description.isValidDescription("Very very very very long description")); // long description
        assertTrue(Description.isValidDescription("123456789012345678901234567"
                + "89012345678901234567890")); // contains exactly 50 characters
    }

    @Test
    public void equals() {
        Description description = new Description("dinner");

        // same instance -> true
        assertTrue(description.equals(description));

        // same value -> return true
        assertTrue(description.equals(new Description("dinner")));

        // null -> false
        assertFalse(description.equals(null));

        // String is passed in -> false
        assertFalse(description.equals("dinner"));

        // different description -> return false
        assertFalse(description.equals(new Description("lunch")));
    }

    @Test
    public void hashcode() {
        // same object
        assertEquals(HOLIDAY.getDescription().hashCode(), HOLIDAY.getDescription().hashCode());

        assertEquals(HOLIDAY.getDescription().hashCode(), new Description("Holiday").hashCode());
    }
}
