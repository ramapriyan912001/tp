package seedu.awe.logic.parser;

import static seedu.awe.commons.core.Messages.MESSAGE_FINDGROUPSCOMMAND_USAGE;
import static seedu.awe.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.awe.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.awe.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.awe.logic.commands.FindGroupsCommand;
import seedu.awe.model.group.GroupContainsKeywordsPredicate;


public class FindGroupsCommandParserTest {

    private FindGroupsCommandParser parser = new FindGroupsCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_FINDGROUPSCOMMAND_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindGroupsCommand expectedFindGroupsCommand =
                new FindGroupsCommand(new GroupContainsKeywordsPredicate(Arrays.asList("Japan", "Singapore")));
        assertParseSuccess(parser, "Japan Singapore", expectedFindGroupsCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Japan \n \t Singapore  \t", expectedFindGroupsCommand);
    }

}
