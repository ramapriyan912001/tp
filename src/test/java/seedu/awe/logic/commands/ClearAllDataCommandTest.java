package seedu.awe.logic.commands;

import static seedu.awe.commons.core.Messages.MESSAGE_CLEARALLDATACOMMAND_SUCCESS;
import static seedu.awe.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.awe.testutil.TypicalPersons.getTypicalAwe;

import org.junit.jupiter.api.Test;

import seedu.awe.model.Awe;
import seedu.awe.model.Model;
import seedu.awe.model.ModelManager;
import seedu.awe.model.UserPrefs;

public class ClearAllDataCommandTest {

    @Test
    public void execute_emptyAwe_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        CommandResult commandResult = new CommandResult(MESSAGE_CLEARALLDATACOMMAND_SUCCESS,
                false, false, false, true, false, false, false);

        assertCommandSuccess(new ClearAllDataCommand(), model, commandResult, expectedModel);

    }

    @Test
    public void execute_nonEmptyAwe_success() {
        Model model = new ModelManager(getTypicalAwe(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAwe(), new UserPrefs());
        expectedModel.setAwe(new Awe());

        CommandResult commandResult = new CommandResult(MESSAGE_CLEARALLDATACOMMAND_SUCCESS,
                false, false, false, true, false, false, false);

        assertCommandSuccess(new ClearAllDataCommand(), model, commandResult, expectedModel);

    }

}
