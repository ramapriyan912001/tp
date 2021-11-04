package seedu.awe.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.awe.storage.JsonAdaptedGroup.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.awe.testutil.Assert.assertThrows;
import static seedu.awe.testutil.TypicalGroups.BALI;
import static seedu.awe.testutil.TypicalGroups.BALI_WITH_EXPENSES;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.awe.commons.exceptions.IllegalValueException;
import seedu.awe.model.group.GroupName;
import seedu.awe.testutil.TypicalGroups;

public class JsonAdaptedGroupTest {
    private static final String INVALID_GROUP_NAME = "R@chel Birthday";
    private static final String INVALID_TAG = "#friend";
    private static final List<JsonAdaptedPerson> VALID_MEMBERS = BALI.getMembers().stream()
            .map(JsonAdaptedPerson::new)
            .collect(Collectors.toList());
    private static final String VALID_GROUP_NAME = BALI.getGroupName().getName();
    private static final List<JsonAdaptedTag> VALID_TAGS = BALI.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedExpense> VALID_EXPENSES = BALI_WITH_EXPENSES
            .getExpenses().stream()
            .map(JsonAdaptedExpense::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validGroupDetails_returnsGroup() throws Exception {
        JsonAdaptedGroup group = new JsonAdaptedGroup(VALID_GROUP_NAME, VALID_MEMBERS,
            VALID_TAGS, VALID_EXPENSES);
        assertEquals(BALI_WITH_EXPENSES, group.toModelType());
    }

    @Test
    public void toModelType_validGroup_returnsGroup() throws Exception {
        JsonAdaptedGroup group = new JsonAdaptedGroup(BALI_WITH_EXPENSES);
        assertEquals(BALI_WITH_EXPENSES, group.toModelType());
    }

    @Test
    public void toModelType_invalidGroupName_throwsIllegalValueException() {
        JsonAdaptedGroup group =
                new JsonAdaptedGroup(INVALID_GROUP_NAME, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        String expectedMessage = GroupName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, group::toModelType);
    }

    @Test
    public void toModelType_nullGroupName_throwsIllegalValueException() {
        JsonAdaptedGroup group = new JsonAdaptedGroup(null, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, GroupName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, group::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedGroup group =
                new JsonAdaptedGroup(VALID_GROUP_NAME, VALID_MEMBERS, invalidTags, new ArrayList<>());
        assertThrows(IllegalValueException.class, group::toModelType);
    }
}
