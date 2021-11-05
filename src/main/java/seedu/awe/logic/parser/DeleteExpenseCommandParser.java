package seedu.awe.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.awe.commons.core.Messages.MESSAGE_DELETEEXPENSECOMMAND_USAGE;
import static seedu.awe.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.awe.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.awe.logic.parser.ParserUtil.MESSAGE_INVALID_LENGTH_INDEX;

import seedu.awe.commons.core.Messages;
import seedu.awe.commons.core.index.Index;
import seedu.awe.logic.commands.DeleteExpenseCommand;
import seedu.awe.logic.parser.exceptions.ParseException;

public class DeleteExpenseCommandParser implements Parser<DeleteExpenseCommand> {

    @Override
    public DeleteExpenseCommand parse(String args) throws ParseException {
        requireNonNull(args);
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteExpenseCommand(index);
        } catch (ParseException pe) {
            String exceptionMessage = pe.getMessage();
            if (exceptionMessage.equals(MESSAGE_INVALID_LENGTH_INDEX) ||
                    exceptionMessage.equals(MESSAGE_INVALID_INDEX)) {
                throw new ParseException(Messages.MESSAGE_INVALID_EXPENSE_DISPLAYED_INDEX);
            } else {
                throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_DELETEEXPENSECOMMAND_USAGE), pe);
            }
        }
    }

}
