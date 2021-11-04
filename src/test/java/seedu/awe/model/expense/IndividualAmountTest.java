package seedu.awe.model.expense;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.awe.testutil.Assert.assertThrows;
import static seedu.awe.testutil.TypicalIndividualAmounts.ALICE_INDIVIDUAL_AMOUNT;
import static seedu.awe.testutil.TypicalIndividualAmounts.BOB_INDIVIDUAL_AMOUNT;
import static seedu.awe.testutil.TypicalPersons.ALICE;
import static seedu.awe.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

public class IndividualAmountTest {

    @Test
    public void constructor_nullArgs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new IndividualAmount(null, 0));
    }

    @Test
    public void getPerson_success() {
        assertEquals(ALICE, ALICE_INDIVIDUAL_AMOUNT.getPerson());
    }

    @Test
    public void getExpenditure_success() {
        assertEquals(20, ALICE_INDIVIDUAL_AMOUNT.getExpenditure());
    }

    @Test
    public void equals() {

        // different individual amounts
        assertFalse(ALICE_INDIVIDUAL_AMOUNT.equals(BOB_INDIVIDUAL_AMOUNT));

        // different expenditures
        assertFalse(ALICE_INDIVIDUAL_AMOUNT.equals(new IndividualAmount(ALICE, 10)));

        // different persons
        assertFalse(ALICE_INDIVIDUAL_AMOUNT.equals(new IndividualAmount(BOB, 20)));

        // different type
        assertFalse(ALICE_INDIVIDUAL_AMOUNT.equals(20));

        assertTrue(ALICE_INDIVIDUAL_AMOUNT.equals(ALICE_INDIVIDUAL_AMOUNT));
        assertTrue(ALICE_INDIVIDUAL_AMOUNT.equals(new IndividualAmount(ALICE, 20)));
    }
}
