package seedu.awe.logic.commands;

import static seedu.awe.commons.core.Messages.MESSAGE_CLEARCOMMAND_SUCCESS;
import static seedu.awe.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.awe.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.awe.model.AddressBook;
import seedu.awe.model.Model;
import seedu.awe.model.ModelManager;
import seedu.awe.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        CommandResult commandResult = new CommandResult(MESSAGE_CLEARCOMMAND_SUCCESS,
                false, false, false, true, false, false, false);

        assertCommandSuccess(new ClearCommand(), model, commandResult, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.setAddressBook(new AddressBook());

        CommandResult commandResult = new CommandResult(MESSAGE_CLEARCOMMAND_SUCCESS,
                false, false, false, true, false, false, false);

        assertCommandSuccess(new ClearCommand(), model, commandResult, expectedModel);
    }

}
