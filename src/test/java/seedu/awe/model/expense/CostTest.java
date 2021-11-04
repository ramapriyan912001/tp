package seedu.awe.model.expense;

import org.junit.jupiter.api.Test;
import seedu.awe.model.person.Name;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.awe.testutil.Assert.assertThrows;

public class CostTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Cost(null));
    }

    @Test
    public void constructor_invalidCost_throwsIllegalArgumentException() {
        String invalidCost = "";
        assertThrows(IllegalArgumentException.class, () -> new Cost(invalidCost));
    }

    @Test
    public void constructor_longCost_throwsIllegalArgumentException() {
        String invalidCost = "12.3456789012345678901234567890123456789012345678901";
        assertThrows(IllegalArgumentException.class, () -> new Cost(invalidCost));
    }

    @Test
    public void isValidName() {
        // null cost
        assertThrows(NullPointerException.class, () -> Cost.isValidCost(null));

        // invalid cost
        assertFalse(Cost.isValidCost("")); // empty string
        assertFalse(Cost.isValidCost(" ")); // spaces only
        assertFalse(Cost.isValidCost("^")); // only non-alphanumeric characters
        assertFalse(Cost.isValidCost("abc")); // only non-numeric characters
        assertFalse(Cost.isValidCost("30*")); // contains non-alphanumeric characters
        assertFalse(Cost.isValidCost("123456789012345678901234567"
                + "890123456789012345678901")); // contains more than 50 characters
        assertFalse(Cost.isValidCost("50505050.50")); // cost greater than max cost

        // valid costs
        assertTrue(Cost.isValidCost("50")); // numeric characters only
        assertTrue(Cost.isValidCost("50.50")); // with 2 decimal places only
        assertTrue(Cost.isValidCost("30.303")); // more than 2 decimal places
        assertTrue(Cost.isValidCost("12.3456789012345678901234567"
                + "8901234567890123456789")); // contains exactly 50 characters
    }

    @Test
    public void equals() {
        Cost cost = new Cost("50");

        // same instance -> true
        assertTrue(cost.equals(cost));

        // same value but different input -> return true
        assertTrue(cost.equals(new Cost("50.00")));

        // null -> false
        assertFalse(cost.equals(null));

        // String is passed in -> false
        assertFalse(cost.equals("50"));

        // Double is passed in -> false
        assertFalse(cost.equals(50.0));

        // different cost -> return false
        assertFalse(cost.equals(new Cost("30")));
    }
}
