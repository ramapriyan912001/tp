package seedu.awe.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.awe.commons.exceptions.DataConversionException;
import seedu.awe.model.ReadOnlyAwe;
import seedu.awe.model.ReadOnlyUserPrefs;
import seedu.awe.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends AweStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getAweFilePath();

    @Override
    Optional<ReadOnlyAwe> readAwe() throws DataConversionException, IOException;

    @Override
    void saveAwe(ReadOnlyAwe awe) throws IOException;

}
