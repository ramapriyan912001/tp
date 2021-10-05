package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedGroup.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGroups.BALI;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.group.GroupName;

public class JsonAdaptedGroupTest {
    private static final String INVALID_GROUP_NAME = "R@chel Birthday";
    private static final String INVALID_TAG = "#friend";
    private static final List<JsonAdaptedPerson> VALID_MEMBERS = BALI.getMembers().stream()
            .map(JsonAdaptedPerson::new)
            .collect(Collectors.toList());
    private static final String VALID_NAME = BALI.getGroupName().getName();
    private static final List<JsonAdaptedTag> VALID_TAGS = BALI.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidGroupName_throwsIllegalValueException() {
        JsonAdaptedGroup group =
                new JsonAdaptedGroup(INVALID_GROUP_NAME, new ArrayList<>());
        String expectedMessage = GroupName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, group::toModelType);
    }

    @Test
    public void toModelType_nullGroupName_throwsIllegalValueException() {
        JsonAdaptedGroup group = new JsonAdaptedGroup(null, new ArrayList<>());
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, GroupName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, group::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedGroup group =
                new JsonAdaptedGroup(VALID_NAME, VALID_MEMBERS, invalidTags);
        assertThrows(IllegalValueException.class, group::toModelType);
    }
}
