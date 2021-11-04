package seedu.awe.model.expense;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.awe.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CostTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Cost(null));
    }

    @Test
    public void constructor_invalidCost_throwsIllegalArgumentException() {
        String invalidCost = "";
        assertThrows(IllegalArgumentException.class, () -> new Cost(invalidCost));

        String invalidCost2 = "abc";
        assertThrows(IllegalArgumentException.class, () -> new Cost(invalidCost2));
    }

    @Test
    public void constructor_longCost_throwsIllegalArgumentException() {
        String invalidCost = "12.3456789012345678901234567890123456789012345678901";
        assertThrows(IllegalArgumentException.class, () -> new Cost(invalidCost));
    }

    @Test
    public void constructor_bigCost_throwsIllegalArgumentException() {
        String bigCost = "1000000001";
        assertThrows(IllegalArgumentException.class, () -> new Cost(bigCost));
    }

    @Test
    public void add_validArgs_costAdded() {
        Cost cost1 = new Cost(10);
        Cost cost2 = new Cost(20);
        Cost expectedSum = new Cost(30);
        assertEquals(expectedSum, cost1.add(cost2));
    }

    @Test
    public void add_resultGreaterThanMaxCost_costConvertedToMaxCost() {
        Cost cost1 = new Cost(Cost.MAX_COST);
        Cost cost2 = new Cost(20);
        Cost expectedSum = new Cost(Cost.MAX_COST);
        assertEquals(expectedSum, cost1.add(cost2));
    }

    @Test
    public void subtract_validArgs_costSubtracted() {
        Cost cost1 = new Cost(10);
        Cost cost2 = new Cost(20);
        Cost expectedSum = new Cost(10);
        assertEquals(expectedSum, cost2.subtract(cost1));
    }

    @Test
    public void subtract_resultLessThanZero_costConvertedToZero() {
        Cost cost1 = new Cost(10);
        Cost cost2 = new Cost(20);
        Cost expectedSum = new Cost(0);
        assertEquals(expectedSum, cost1.subtract(cost2));
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
        assertFalse(Cost.isValidCost("12.3456789012345678901234567"
                + "890123456789012345678901")); // contains more than 50 characters
        assertFalse(Cost.isValidCost("50505050505050.50")); // cost greater than max cost

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
