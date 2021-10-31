package seedu.awe.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.awe.commons.core.Messages.MESSAGE_CLEARALLDATACOMMAND_SUCCESS;

import seedu.awe.model.AddressBook;
import seedu.awe.model.Model;

/**
 * Clears the awe book.
 */
public class ClearAllDataCommand extends Command {

    public static final String COMMAND_WORD = "clearalldata";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setAddressBook(new AddressBook());
        return new CommandResult(MESSAGE_CLEARALLDATACOMMAND_SUCCESS);
    }
}
