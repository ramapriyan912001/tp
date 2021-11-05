package seedu.awe.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.awe.logic.commands.AddContactCommand;
import seedu.awe.logic.commands.ListExpensesCommand;
import seedu.awe.logic.commands.ListTransactionSummaryCommand;
import seedu.awe.logic.parser.exceptions.ParseException;
import seedu.awe.model.group.GroupName;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.awe.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.awe.commons.core.Messages.MESSAGE_LISTEXPENSESCOMMAND_USAGE;
import static seedu.awe.logic.commands.CommandTestUtil.*;
import static seedu.awe.logic.parser.CliSyntax.PREFIX_GROUP_NAME;
import static seedu.awe.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.awe.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.awe.testutil.Assert.assertThrows;
import static seedu.awe.testutil.TypicalGroups.*;

public class ListExpensesCommandParserTest {

    private ListExpensesCommandParser parser = new ListExpensesCommandParser();

    public void resetParser() {
        parser = new ListExpensesCommandParser();
    }

    @Test
    public void parse_allFieldsPresent_success() {
       // regular input for ListExpensesCommand
       assertParseSuccess(parser, GROUPNAME_DESC_BALI,
           new ListExpensesCommand(new GroupName(VALID_GROUPNAME_BALI)));

       // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + GROUPNAME_DESC_BALI,
            new ListExpensesCommand(BALI.getGroupName()));

        // multiple group names - last group name accepted
        assertParseSuccess(parser, GROUPNAME_DESC_BALI + GROUPNAME_DESC_OSLO,
                new ListExpensesCommand(OSLO.getGroupName()));
    }


    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            MESSAGE_LISTEXPENSESCOMMAND_USAGE);

        // missing group prefix
        String inputMissingGroupPrefix = " Bali";
        assertParseFailure(parser, inputMissingGroupPrefix, expectedMessage);

        // empty command
        assertParseFailure(parser, " ", expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            MESSAGE_LISTEXPENSESCOMMAND_USAGE);

        // invalid group name
        assertParseFailure(parser, INVALID_GROUP_NAME_DESC, GroupName.MESSAGE_CONSTRAINTS);

        // empty group name
        String noGroupName = " " + PREFIX_GROUP_NAME;
        assertParseFailure(parser, noGroupName, GroupName.MESSAGE_CONSTRAINTS);

        // invalid group name throws Exception
        assertThrows(ParseException.class, GroupName.MESSAGE_CONSTRAINTS, () -> parser.parse(INVALID_GROUP_NAME_DESC));

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + GROUPNAME_DESC_BALI, expectedMessage);
    }

}
