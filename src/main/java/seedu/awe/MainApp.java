package seedu.awe;

import static seedu.awe.model.util.SampleDataUtil.getSampleAwe;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.awe.commons.core.Config;
import seedu.awe.commons.core.LogsCenter;
import seedu.awe.commons.core.Version;
import seedu.awe.commons.exceptions.DataConversionException;
import seedu.awe.commons.util.ConfigUtil;
import seedu.awe.commons.util.StringUtil;
import seedu.awe.logic.Logic;
import seedu.awe.logic.LogicManager;
import seedu.awe.model.Model;
import seedu.awe.model.ModelManager;
import seedu.awe.model.ReadOnlyAwe;
import seedu.awe.model.ReadOnlyUserPrefs;
import seedu.awe.model.UserPrefs;
import seedu.awe.model.util.SampleDataUtil;
import seedu.awe.storage.AweStorage;
import seedu.awe.storage.JsonAweStorage;
import seedu.awe.storage.JsonUserPrefsStorage;
import seedu.awe.storage.Storage;
import seedu.awe.storage.StorageManager;
import seedu.awe.storage.UserPrefsStorage;
import seedu.awe.ui.Ui;
import seedu.awe.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(1, 4, 0, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;
    protected boolean isDataError;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing Awe ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        AweStorage aweStorage = new JsonAweStorage(userPrefs.getAweFilePath());
        storage = new StorageManager(aweStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s awe book and {@code userPrefs}. <br>
     * The data from the sample awe book will be used instead if {@code storage}'s awe book is not found or
     * if errors occur when reading {@code storage}'s awe book.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyAwe> addressBookOptional;
        ReadOnlyAwe initialData;
        try {
            addressBookOptional = storage.readAwe();
            if (!addressBookOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample AWE book.");
            }
            initialData = addressBookOptional.orElseGet(SampleDataUtil::getSampleAwe);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with a sample AWE book.");
            isDataError = true;
            initialData = getSampleAwe();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with a sample AWE book.");
            isDataError = true;
            initialData = getSampleAwe();
        }
        return new ModelManager(initialData, userPrefs);
    }

    private void initLogging(Config config) {
        LogsCenter.init(config);
    }

    /**
     * Returns a {@code Config} using the file at {@code configFilePath}. <br>
     * The default file path {@code Config#DEFAULT_CONFIG_FILE} will be used instead
     * if {@code configFilePath} is null.
     */
    protected Config initConfig(Path configFilePath) {
        Config initializedConfig;
        Path configFilePathUsed;

        configFilePathUsed = Config.DEFAULT_CONFIG_FILE;

        if (configFilePath != null) {
            logger.info("Custom Config file specified " + configFilePath);
            configFilePathUsed = configFilePath;
        }

        logger.info("Using config file : " + configFilePathUsed);

        try {
            Optional<Config> configOptional = ConfigUtil.readConfig(configFilePathUsed);
            initializedConfig = configOptional.orElse(new Config());
        } catch (DataConversionException e) {
            logger.warning("Config file at " + configFilePathUsed + " is not in the correct format. "
                    + "Using default config properties");
            initializedConfig = new Config();
        }

        //Update config file in case it was missing to begin with or there are new/unused fields
        try {
            ConfigUtil.saveConfig(initializedConfig, configFilePathUsed);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }
        return initializedConfig;
    }

    /**
     * Returns a {@code UserPrefs} using the file at {@code storage}'s user prefs file path,
     * or a new {@code UserPrefs} with default configuration if errors occur when
     * reading from the file.
     */
    protected UserPrefs initPrefs(UserPrefsStorage storage) {
        Path prefsFilePath = storage.getUserPrefsFilePath();
        logger.info("Using prefs file : " + prefsFilePath);

        UserPrefs initializedPrefs;
        try {
            Optional<UserPrefs> prefsOptional = storage.readUserPrefs();
            initializedPrefs = prefsOptional.orElse(new UserPrefs());
        } catch (DataConversionException e) {
            logger.warning("UserPrefs file at " + prefsFilePath + " is not in the correct format. "
                    + "Using default user prefs");
            initializedPrefs = new UserPrefs();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty Awe");
            initializedPrefs = new UserPrefs();
        }

        //Update prefs file in case it was missing to begin with or there are new/unused fields
        try {
            storage.saveUserPrefs(initializedPrefs);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }

        return initializedPrefs;
    }

    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting Awe " + MainApp.VERSION);
        ui.setIsDataError(isDataError);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Address Book ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
