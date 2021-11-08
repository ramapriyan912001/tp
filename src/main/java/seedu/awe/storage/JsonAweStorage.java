package seedu.awe.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.awe.commons.core.LogsCenter;
import seedu.awe.commons.exceptions.DataConversionException;
import seedu.awe.commons.exceptions.IllegalValueException;
import seedu.awe.commons.util.FileUtil;
import seedu.awe.commons.util.JsonUtil;
import seedu.awe.model.ReadOnlyAwe;

/**
 * A class to access Awe data stored as a json file on the hard disk.
 */
public class JsonAweStorage implements AweStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonAweStorage.class);

    private Path filePath;

    public JsonAweStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getAweFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyAwe> readAwe() throws DataConversionException {
        return readAwe(filePath);
    }

    /**
     * Similar to {@link #readAwe()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyAwe> readAwe(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableAwe> jsonAwe = JsonUtil.readJsonFile(
                filePath, JsonSerializableAwe.class);
        if (!jsonAwe.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAwe.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveAwe(ReadOnlyAwe awe) throws IOException {
        saveAwe(awe, filePath);
    }

    /**
     * Similar to {@link #saveAwe(ReadOnlyAwe)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveAwe(ReadOnlyAwe awe, Path filePath) throws IOException {
        requireNonNull(awe);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableAwe(awe), filePath);
    }

}
