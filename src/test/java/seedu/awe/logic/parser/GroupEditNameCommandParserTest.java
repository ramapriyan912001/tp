package seedu.awe.logic.parser;

import static seedu.awe.commons.core.Messages.MESSAGE_GROUPEDITNAMECOMMAND_USAGE;
import static seedu.awe.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.awe.logic.parser.CliSyntax.PREFIX_GROUP_NAME;
import static seedu.awe.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.awe.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.awe.logic.commands.GroupEditNameCommand;
import seedu.awe.model.group.GroupName;

public class GroupEditNameCommandParserTest {
    private static final String TAG_EMPTY = " " + PREFIX_GROUP_NAME;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_GROUPEDITNAMECOMMAND_USAGE);
    private static final String MESSAGE_GROUP_NAME_INVALID = GroupName.MESSAGE_CONSTRAINTS;

    private GroupEditNameCommandParser parser = new GroupEditNameCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);

        // only 1 group name specified
        assertParseFailure(parser, " gn/London", MESSAGE_INVALID_FORMAT);

        // empty gn tag
        assertParseFailure(parser, TAG_EMPTY, MESSAGE_GROUP_NAME_INVALID);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // more than 2 group name
        assertParseFailure(parser, " gn/Japan gn/London gn/Taiwan", MESSAGE_INVALID_FORMAT);

        // 2 gn/ tag but 1 group name
        assertParseFailure(parser, " gn/London gn/", MESSAGE_GROUP_NAME_INVALID);
    }

    @Test
    public void parse_invalidGroupName_failure() {
        // invalid group name
        assertParseFailure(parser, " gn/Japan gn/*", MESSAGE_GROUP_NAME_INVALID);
    }

    @Test
    public void parse_validGroupName_success() {
        GroupEditNameCommand expectedCommand = new GroupEditNameCommand(new GroupName("Japan"),
                new GroupName("Malaysia"), true);
        assertParseSuccess(parser, " gn/Japan gn/Malaysia", expectedCommand);
    }
}
