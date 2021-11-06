package seedu.awe.logic.parser;

import static seedu.awe.commons.core.Messages.MESSAGE_FINDEXPENSESCOMMAND_USAGE;
import static seedu.awe.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.awe.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.awe.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.awe.logic.commands.FindExpensesCommand;
import seedu.awe.model.expense.DescriptionContainsKeywordsPredicate;
import seedu.awe.model.group.GroupName;

public class FindExpensesCommandParserTest {
    private FindExpensesCommandParser parser = new FindExpensesCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        String userInput = "     ";
        assertParseFailure(parser, userInput,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_FINDEXPENSESCOMMAND_USAGE));
    }

    @Test
    public void parse_doesNotStartWithKeyword_throwsParseException() {
        String userInputKeywordAbsent = " gn/London";

        assertParseFailure(parser, userInputKeywordAbsent,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_FINDEXPENSESCOMMAND_USAGE));
    }

    @Test
    public void parse_groupNameAbsent_throwsParseException() {
        String userInput = "test London";

        assertParseFailure(parser, userInput,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_FINDEXPENSESCOMMAND_USAGE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        String userInput = "Lgn/ondon";

        assertParseFailure(parser, userInput,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_FINDEXPENSESCOMMAND_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindExpensesCommand() {
        // no leading and trailing whitespaces
        String userInput = "test gn/Japan";
        FindExpensesCommand expectedFindExpensesCommand =
                new FindExpensesCommand(new GroupName("Japan"),
                        new DescriptionContainsKeywordsPredicate(Arrays.asList("test")));
        assertParseSuccess(parser, userInput, expectedFindExpensesCommand);

        // multiple whitespaces between keywords
        String userInputWithWhitespace = "\t test \t \n gn/Japan \n";
        assertParseSuccess(parser, userInputWithWhitespace, expectedFindExpensesCommand);
    }
}
