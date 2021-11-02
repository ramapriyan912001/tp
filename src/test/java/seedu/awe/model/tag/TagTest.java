package seedu.awe.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.awe.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidTagName));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));
    }

    @Test
    public void getTagName() {
        String validTagName = "Friends";

        Tag validTag = new Tag("Friends");

        assertEquals(validTagName, validTag.getTagName());
    }
    @Test
    public void equals() {
        Tag tag = new Tag("Family");

        // same instance -> true
        assertTrue(tag.equals(tag));

        // null -> false
        assertFalse(tag.equals(null));

        // String is passed in -> false
        assertFalse(tag.equals("Family"));

        // different name -> return false
        assertFalse(tag.equals(new Tag("Friends")));

        // different case -> return false
        assertFalse(tag.equals(new Tag("family")));
    }
}
