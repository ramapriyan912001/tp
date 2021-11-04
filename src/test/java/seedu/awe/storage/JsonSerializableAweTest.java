package seedu.awe.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.awe.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.awe.commons.exceptions.IllegalValueException;
import seedu.awe.commons.util.JsonUtil;
import seedu.awe.model.Awe;
import seedu.awe.testutil.TypicalPersons;

public class JsonSerializableAweTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAweTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsAwe.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonAwe.json");
    private static final Path DUPLICATE_GROUP_FILE = TEST_DATA_FOLDER.resolve("duplicateGroupAwe.json");
    private static final Path INVALID_MEMBERS_IN_GROUP_FILE = TEST_DATA_FOLDER
            .resolve("validPersonAndInvalidGroupAwe.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableAddressBook.class).get();
        Awe aweFromFile = dataFromFile.toModelType();
        Awe typicalPersonsAwe = TypicalPersons.getTypicalAddressBook();
        assertEquals(aweFromFile, typicalPersonsAwe);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateGroups_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_GROUP_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_GROUP,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_inValidMemberInGroup_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_MEMBERS_IN_GROUP_FILE,
            JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_INVALID_GROUP_MEMBER,
            dataFromFile::toModelType);
    }
}
