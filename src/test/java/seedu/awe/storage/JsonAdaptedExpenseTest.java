package seedu.awe.storage;

import org.junit.jupiter.api.Test;
import seedu.awe.commons.exceptions.IllegalValueException;
import seedu.awe.model.expense.Cost;

import static seedu.awe.storage.JsonAdaptedExpense.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.awe.testutil.Assert.assertThrows;

import seedu.awe.model.expense.Description;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.awe.testutil.TypicalExpenses.HOLIDAY;

public class JsonAdaptedExpenseTest {

    private static final JsonAdaptedPerson VALID_PAYER = new JsonAdaptedPerson(HOLIDAY.getPayer());
    private static final String VALID_COST = "1000";
    private static final String VALID_DESCRIPTION = HOLIDAY.getDescription().getFullDescription();
    private final List<JsonAdaptedPerson> VALID_INCLUDED = new ArrayList<>();
    private final List<JsonAdaptedIndividualAmount> VALID_INDIVIDUAL_EXPENSES = new ArrayList<>();
    private static final String INVALID_DESCRIPTION = "Desc@ription";
    private static final String INVALID_LENGTH_DESCRIPTION = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
            + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";


    @Test
    public void toModelType_validExpenseDetails_returnsExpense() throws Exception {
        JsonAdaptedExpense expense = new JsonAdaptedExpense(VALID_PAYER, VALID_COST,
                VALID_DESCRIPTION, VALID_INCLUDED, VALID_INDIVIDUAL_EXPENSES);
        assertEquals(HOLIDAY, expense.toModelType());
    }

    @Test
    public void toModelType_validExpense_returnsExpense() throws Exception {
        JsonAdaptedExpense expense = new JsonAdaptedExpense(HOLIDAY);
        assertEquals(HOLIDAY, expense.toModelType());
    }

    @Test
    public void toModelType_nullCost_throwsIllegalValueException() {
        JsonAdaptedExpense expense = new JsonAdaptedExpense(VALID_PAYER, null,
            VALID_DESCRIPTION, VALID_INCLUDED, VALID_INDIVIDUAL_EXPENSES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Cost.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, expense::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedExpense expense = new JsonAdaptedExpense(VALID_PAYER, VALID_COST,
            null, VALID_INCLUDED, VALID_INDIVIDUAL_EXPENSES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, expense::toModelType);
    }

    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() {
        JsonAdaptedExpense expense = new JsonAdaptedExpense(VALID_PAYER, VALID_COST,
            INVALID_DESCRIPTION, VALID_INCLUDED, VALID_INDIVIDUAL_EXPENSES);
        assertThrows(IllegalValueException.class, Description.MESSAGE_CONSTRAINTS, expense::toModelType);
    }

    @Test
    public void toModelType_descriptionTooLong_throwsIllegalValueException() {
        JsonAdaptedExpense expense = new JsonAdaptedExpense(VALID_PAYER, VALID_COST,
            INVALID_LENGTH_DESCRIPTION, VALID_INCLUDED, VALID_INDIVIDUAL_EXPENSES);
        assertThrows(IllegalValueException.class, Description.MESSAGE_CONSTRAINTS, expense::toModelType);
    }
}

