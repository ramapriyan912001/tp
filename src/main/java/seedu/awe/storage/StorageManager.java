package seedu.awe.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.awe.commons.core.LogsCenter;
import seedu.awe.commons.exceptions.DataConversionException;
import seedu.awe.model.ReadOnlyAwe;
import seedu.awe.model.ReadOnlyUserPrefs;
import seedu.awe.model.UserPrefs;

/**
 * Manages storage of Awe data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AweStorage aweStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AweStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(AweStorage aweStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.aweStorage = aweStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ Awe methods ==============================

    @Override
    public Path getAweFilePath() {
        return aweStorage.getAweFilePath();
    }

    @Override
    public Optional<ReadOnlyAwe> readAwe() throws DataConversionException, IOException {
        return readAwe(aweStorage.getAweFilePath());
    }

    @Override
    public Optional<ReadOnlyAwe> readAwe(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return aweStorage.readAwe(filePath);
    }

    @Override
    public void saveAwe(ReadOnlyAwe awe) throws IOException {
        saveAwe(awe, aweStorage.getAweFilePath());
    }

    @Override
    public void saveAwe(ReadOnlyAwe awe, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        aweStorage.saveAwe(awe, filePath);
    }

}
