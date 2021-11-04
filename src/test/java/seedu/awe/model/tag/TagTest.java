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

        // Invalid tag
        assertFalse(Tag.isValidTagName("")); // empty string
        assertFalse(Tag.isValidTagName(" ")); // spaces only
        assertFalse(Tag.isValidTagName("^")); // only non-alphanumeric characters
        assertFalse(Tag.isValidTagName("friend*")); // contains non-alphanumeric characters
        assertFalse(Tag.isValidTagName("friends from schoool")); // Contains spaces
        assertFalse(Tag.isValidTagName("123456789012345678901234567"
                + "890123456789012345678901")); // contains more than 50 characters

        // valid Tag name
        assertTrue(Tag.isValidTagName("enemy")); // alphabets only
        assertTrue(Tag.isValidTagName("12345")); // numbers only
        assertTrue(Tag.isValidTagName("CapitalTan")); // with capital letters
        assertTrue(Tag.isValidTagName("DavidRogerJacksonRayJr2nd")); // long Tagnames
        assertTrue(Tag.isValidTagName("123456789012345678901234567"
                + "89012345678901234567890")); // contains exactly 50 characters
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
