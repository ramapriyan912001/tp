package seedu.awe.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.awe.testutil.Assert.assertThrows;
import static seedu.awe.testutil.TypicalPersons.ALICE;
import static seedu.awe.testutil.TypicalPersons.HOON;
import static seedu.awe.testutil.TypicalPersons.IDA;
import static seedu.awe.testutil.TypicalPersons.getTypicalAwe;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.awe.commons.exceptions.DataConversionException;
import seedu.awe.model.Awe;
import seedu.awe.model.ReadOnlyAwe;

public class JsonAweStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonAweStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readAwe_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readAwe(null));
    }

    private java.util.Optional<ReadOnlyAwe> readAwe(String filePath) throws Exception {
        return new JsonAweStorage(Paths.get(filePath)).readAwe(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readAwe("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readAwe("notJsonFormatAwe.json"));
    }

    @Test
    public void readAwe_invalidPersonAwe_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAwe("invalidPersonAwe.json"));
    }

    @Test
    public void readAwe_invalidAndValidPersonAwe_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAwe("invalidAndValidPersonAwe.json"));
    }

    @Test
    public void readAndSaveAwe_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAwe.json");
        Awe original = getTypicalAwe();
        JsonAweStorage jsonAweStorage = new JsonAweStorage(filePath);

        // Save in new file and read back
        jsonAweStorage.saveAwe(original, filePath);
        ReadOnlyAwe readBack = jsonAweStorage.readAwe(filePath).get();
        assertEquals(original, new Awe(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonAweStorage.saveAwe(original, filePath);
        readBack = jsonAweStorage.readAwe(filePath).get();
        assertEquals(original, new Awe(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonAweStorage.saveAwe(original); // file path not specified
        readBack = jsonAweStorage.readAwe().get(); // file path not specified
        assertEquals(original, new Awe(readBack));

    }

    @Test
    public void saveAwe_nullAwe_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAwe(null, "SomeFile.json"));
    }

    /**
     * Saves {@code awe} at the specified {@code filePath}.
     */
    private void saveAwe(ReadOnlyAwe awe, String filePath) {
        try {
            new JsonAweStorage(Paths.get(filePath))
                    .saveAwe(awe, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAwe_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAwe(new Awe(), null));
    }
}
