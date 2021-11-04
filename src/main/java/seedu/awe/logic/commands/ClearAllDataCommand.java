package seedu.awe.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.awe.commons.core.Messages.MESSAGE_CLEARALLDATACOMMAND_SUCCESS;

import seedu.awe.model.Awe;
import seedu.awe.model.Model;

/**
 * Clears the awe book.
 */
public class ClearAllDataCommand extends Command {

    public static final String COMMAND_WORD = "clearalldata";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setAwe(new Awe());
        return new CommandResult(MESSAGE_CLEARALLDATACOMMAND_SUCCESS,
                false, false, false, true, false, false, false);
    }
}
