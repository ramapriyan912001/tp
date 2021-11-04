package seedu.awe.logic.parser;

import static seedu.awe.commons.core.Messages.MESSAGE_DELETEEXPENSECOMMAND_USAGE;
import static seedu.awe.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.awe.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.awe.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.awe.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import seedu.awe.logic.commands.DeleteExpenseCommand;

public class DeleteExpenseCommandParserTest {

    private DeleteExpenseCommandParser parser = new DeleteExpenseCommandParser();

    //EP: positive integer
    @Test
    public void parse_validArgs_returnsDeleteExpenseCommand() {
        assertParseSuccess(parser, "1", new DeleteExpenseCommand(INDEX_FIRST));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        //EP: not a number
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            MESSAGE_DELETEEXPENSECOMMAND_USAGE));

        //EP: negative integer
        assertParseFailure(parser, "-1", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            MESSAGE_DELETEEXPENSECOMMAND_USAGE));

        //EP: zero
        assertParseFailure(parser, "0", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            MESSAGE_DELETEEXPENSECOMMAND_USAGE));
    }
}
