package seedu.awe.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.awe.commons.exceptions.DataConversionException;
import seedu.awe.model.Awe;
import seedu.awe.model.ReadOnlyAwe;

/**
 * Represents a storage for {@link Awe}.
 */
public interface AweStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getAweFilePath();

    /**
     * Returns Awe data as a {@link ReadOnlyAwe}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyAwe> readAwe() throws DataConversionException, IOException;

    /**
     * @see #getAweFilePath()
     */
    Optional<ReadOnlyAwe> readAwe(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyAwe} to the storage.
     * @param awe cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAwe(ReadOnlyAwe awe) throws IOException;

    /**
     * @see #saveAwe(ReadOnlyAwe)
     */
    void saveAwe(ReadOnlyAwe awe, Path filePath) throws IOException;

}
