package seedu.awe.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.awe.testutil.Assert.assertThrows;
import static seedu.awe.testutil.TypicalIndividualAmounts.ALICE_INDIVIDUAL_AMOUNT;

import org.junit.jupiter.api.Test;

import seedu.awe.commons.exceptions.IllegalValueException;

public class JsonAdaptedIndividualAmountTest {
    private static final JsonAdaptedPerson VALID_PERSON = new JsonAdaptedPerson(ALICE_INDIVIDUAL_AMOUNT.getPerson());
    private static final String VALID_EXPENDITURE = ALICE_INDIVIDUAL_AMOUNT.getExpenditure().toString();
    private static final JsonAdaptedPerson INVALID_PERSON = new JsonAdaptedPerson(null, null, null);

    @Test
    public void toModelType_validIndividualAmountDetails_returnsIndividualAmount() throws Exception {
        JsonAdaptedIndividualAmount individualAmount = new JsonAdaptedIndividualAmount(VALID_PERSON,
                VALID_EXPENDITURE);
        assertEquals(ALICE_INDIVIDUAL_AMOUNT, individualAmount.toModelType());
    }

    @Test
    public void toModelType_validIndividualAmount_returnsIndividualAmount() throws Exception {
        JsonAdaptedIndividualAmount individualAmount = new JsonAdaptedIndividualAmount(
                ALICE_INDIVIDUAL_AMOUNT);
        assertEquals(ALICE_INDIVIDUAL_AMOUNT, individualAmount.toModelType());
    }

    @Test
    public void toModelType_invalidPerson_throwsIllegalValueException() {
        JsonAdaptedIndividualAmount individualAmount = new JsonAdaptedIndividualAmount(
            INVALID_PERSON, VALID_EXPENDITURE);
        assertThrows(IllegalValueException.class, individualAmount::toModelType);
    }
}
