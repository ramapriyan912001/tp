package seedu.awe.logic.parser;

import static seedu.awe.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.awe.logic.commands.CommandTestUtil.GROUPNAME_DESC_BALI;
import static seedu.awe.logic.commands.CommandTestUtil.INVALID_GROUP_NAME_DESC;
import static seedu.awe.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.awe.logic.commands.CommandTestUtil.NAME_DESC_ALICE;
import static seedu.awe.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.awe.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.awe.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.awe.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.awe.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.awe.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.awe.testutil.Assert.assertThrows;
import static seedu.awe.testutil.TypicalGroups.BALI;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.awe.logic.commands.CreateGroupCommand;
import seedu.awe.model.group.Group;
import seedu.awe.model.group.GroupName;
import seedu.awe.model.person.Person;
import seedu.awe.testutil.ModelBuilder;

public class CreateGroupCommandParserTest {
    private CreateGroupCommandParser parser = new CreateGroupCommandParser(new ModelBuilder().build());

    /**
     * Resets parser. Necessary as CreateGroupCommand parser needs to be initialised with a model for each call.
     * Failure to reset parser will result in Duplicate exceptions being raised.
     */
    public void resetParser() {
        parser = new CreateGroupCommandParser(new ModelBuilder().build());
    }

    @Test
    public void parse_allFieldsPresent_success() {
        GroupName expectedGroupName = BALI.getGroupName();
        ArrayList<Person> expectedGroupMembers = new ArrayList<>(BALI.getMembers());

        // regular input for CreateGroupCommand
        assertParseSuccess(parser, GROUPNAME_DESC_BALI + NAME_DESC_ALICE + NAME_DESC_BOB + NAME_DESC_AMY,
                new CreateGroupCommand(expectedGroupName, expectedGroupMembers, true));

        resetParser();
        // names in different order
        assertParseSuccess(parser, GROUPNAME_DESC_BALI + NAME_DESC_BOB + NAME_DESC_AMY + NAME_DESC_ALICE,
                new CreateGroupCommand(expectedGroupName, expectedGroupMembers, true));

        resetParser();
        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + GROUPNAME_DESC_BALI
                        + NAME_DESC_ALICE + NAME_DESC_BOB + NAME_DESC_AMY,
                new CreateGroupCommand(expectedGroupName, expectedGroupMembers, true));

        resetParser();
        // name repeats
        assertParseSuccess(parser, GROUPNAME_DESC_BALI
                        + NAME_DESC_BOB + NAME_DESC_AMY + NAME_DESC_ALICE + NAME_DESC_ALICE,
                new CreateGroupCommand(expectedGroupName, expectedGroupMembers, true));

        resetParser();
        // multiple name repeats
        assertParseSuccess(parser, GROUPNAME_DESC_BALI
                        + NAME_DESC_BOB + NAME_DESC_BOB + NAME_DESC_AMY + NAME_DESC_ALICE + NAME_DESC_ALICE,
                new CreateGroupCommand(expectedGroupName, expectedGroupMembers, true));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateGroupCommand.MESSAGE_USAGE);

        resetParser();
        // missing group prefix
        assertParseFailure(parser, NAME_DESC_BOB + NAME_DESC_AMY + NAME_DESC_ALICE, expectedMessage);

        resetParser();
        // missing name prefix
        assertParseFailure(parser, GROUPNAME_DESC_BALI, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateGroupCommand.MESSAGE_USAGE);
        resetParser();
        // invalid group name
        assertParseFailure(parser, INVALID_GROUP_NAME_DESC + NAME_DESC_BOB + NAME_DESC_AMY + NAME_DESC_ALICE,
                Group.MESSAGE_CONSTRAINTS);

        resetParser();
        // invalid name returns IllegalArgumentException
        String userInput = GROUPNAME_DESC_BALI + INVALID_NAME_DESC + NAME_DESC_AMY + NAME_DESC_ALICE;
        assertThrows(IllegalArgumentException.class, GroupName.MESSAGE_CONSTRAINTS, () -> parser.parse(userInput));

        resetParser();
        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + GROUPNAME_DESC_BALI
                        + NAME_DESC_ALICE + NAME_DESC_BOB + NAME_DESC_AMY, expectedMessage);
    }
}
