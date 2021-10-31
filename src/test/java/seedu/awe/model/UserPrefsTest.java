package seedu.awe.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.awe.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class UserPrefsTest {

    private UserPrefs userPrefs = new UserPrefs();

    public void resetUserPrefs() {
        userPrefs = new UserPrefs();
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        resetUserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.setGuiSettings(null));
    }

    @Test
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        resetUserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.setAddressBookFilePath(null));
    }

    @Test
    public void equals() {
        resetUserPrefs();
        assertEquals(userPrefs, userPrefs);
        assertNotEquals(userPrefs, new AddressBook());
    }

    @Test
    public void hashcode() {
        resetUserPrefs();
        assertEquals(userPrefs.hashCode(), new UserPrefs().hashCode());
    }
}
