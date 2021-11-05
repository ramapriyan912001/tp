package seedu.awe.logic.parser;

import static seedu.awe.commons.core.Messages.MESSAGE_DELETECONTACTCOMMAND_USAGE;
import static seedu.awe.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.awe.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.awe.logic.parser.ParserUtil.MESSAGE_INVALID_LENGTH_INDEX;

import seedu.awe.commons.core.Messages;
import seedu.awe.commons.core.index.Index;
import seedu.awe.logic.commands.DeleteContactCommand;
import seedu.awe.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteContactCommandParser implements Parser<DeleteContactCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteContactCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteContactCommand(index);
        } catch (ParseException pe) {
            String exceptionMessage = pe.getMessage();
            if (exceptionMessage.equals(MESSAGE_INVALID_LENGTH_INDEX) ||
                    exceptionMessage.equals(MESSAGE_INVALID_INDEX)) {
                throw new ParseException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            } else {
                throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_DELETECONTACTCOMMAND_USAGE), pe);
            }
        }
    }
}

