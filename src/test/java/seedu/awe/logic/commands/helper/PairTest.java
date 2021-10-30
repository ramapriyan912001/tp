package seedu.awe.logic.commands.helper;

import org.junit.jupiter.api.Test;
import seedu.awe.logic.commands.CalculatePaymentsCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.awe.testutil.TypicalGroups.BALI_WITH_EXPENSES;
import static seedu.awe.testutil.TypicalPersons.ALICE;
import static seedu.awe.testutil.TypicalPersons.BOB;

public class PairTest {

    @Test
    public void testPairEquals() {
        CalculatePaymentsCommand command = new CalculatePaymentsCommand(BALI_WITH_EXPENSES);
        assertFalse(new Pair(20.0, ALICE).equals(20.0));
        assertNotEquals(new Pair(20.0, ALICE),
                new Pair(1000.0, ALICE));
    }

    @Test
    public void testHashCode() {
        assertEquals(new Pair(20.0, ALICE).hashCode(),
                new Pair(20.0, ALICE).hashCode());
        assertNotEquals(new Pair(20.0, ALICE),
                new Pair(100.0, ALICE));
    }

    @Test
    public void constructor_invalidInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Pair(100.00, null));
    }

    @Test
    public void getPerson_success() {
        assertEquals(new Pair(20.0, ALICE).getPerson(), ALICE);
        assertNotEquals(new Pair(20.0, ALICE).getPerson(), BOB);
    }

    @Test
    public void getSurplus_success() {
        assertEquals(new Pair(20.0, ALICE).getSurplus(), 20.0);
        assertNotEquals(new Pair(20.0, ALICE).getSurplus(), 22);
    }

    @Test
    public void equals() {
        assertEquals(new Pair(20.0, ALICE), new Pair(20.0, ALICE));
        assertNotEquals(new Pair(20.0, ALICE), new Pair(29.0, ALICE));
        assertNotEquals(new Pair(20.0, ALICE), new Pair(20.0, BOB));
        assertFalse(new Pair(20.0, ALICE).equals(20.0));
        Pair pair = new Pair(20.0, ALICE);
        assertEquals(pair, pair);
    }
}
