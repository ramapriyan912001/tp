package seedu.awe.logic.parser;

import static seedu.awe.commons.core.Messages.MESSAGE_GROUPREMOVETAGCOMMAND_USAGE;
import static seedu.awe.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.awe.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.awe.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.awe.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.awe.logic.commands.GroupRemoveTagCommand;
import seedu.awe.model.group.GroupName;
import seedu.awe.model.tag.Tag;
import seedu.awe.testutil.ModelBuilder;

public class GroupRemoveTagCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_GROUPREMOVETAGCOMMAND_USAGE);
    private static final String MESSAGE_GROUP_NAME_INVALID = GroupName.MESSAGE_CONSTRAINTS;
    private static final Tag[] tags = {new Tag("Friends"), new Tag("family")};
    private static final Tag[] tagsInGroup = {new Tag("friends"), new Tag("3days2night")};
    private static final Set<Tag> TAGS_NOT_IN_GROUP = new HashSet<>(Arrays.asList(tags));
    private static final Set<Tag> TAGS_IN_GROUP = new HashSet<>(Arrays.asList(tagsInGroup));
    private GroupRemoveTagCommandParser parser = new GroupRemoveTagCommandParser(new ModelBuilder().build());

    @Test
    public void parse_missingParts_failure() {
        // no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);

        // empty input
        assertParseFailure(parser, " ", MESSAGE_INVALID_FORMAT);

        // only 1 group name specified
        assertParseFailure(parser, " gn/London", MESSAGE_INVALID_FORMAT);

        // only 1 member name specified
        assertParseFailure(parser, " t/friends", MESSAGE_INVALID_FORMAT);

        // empty gn tag
        assertParseFailure(parser, " gn/ t/friends", MESSAGE_GROUP_NAME_INVALID);

        // preambleEmpty
        assertParseFailure(parser, PREAMBLE_NON_EMPTY, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // more than 1 group name
        assertParseFailure(parser, " gn/Japan gn/London t/friends", MESSAGE_INVALID_FORMAT);

        // 1 gn/ tag, 1 t/ tag but no group name
        assertParseFailure(parser, " gn/ t/friends", MESSAGE_GROUP_NAME_INVALID);

        // 1 gn/ tag, 1 t/ tag but no member name
        assertParseFailure(parser, " gn/London t/", Tag.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidArguments_failure() {
        // invalid group name
        assertParseFailure(parser, " gn/* t/friends", MESSAGE_GROUP_NAME_INVALID);

        // invalid member name
        assertParseFailure(parser, " gn/Bali t/%s", Tag.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_validGroupName_success() {
        //Removing tags from group
        GroupRemoveTagCommand expectedCommand = new GroupRemoveTagCommand(new GroupName("Bali"),
                TAGS_IN_GROUP,
                true);
        assertParseSuccess(parser, " gn/Bali t/friends t/3days2night", expectedCommand);

        //reset parser
        parser = new GroupRemoveTagCommandParser(new ModelBuilder().build());

        //Removing tags that are not from the group (checking for this is done in command)
        GroupRemoveTagCommand expectedCommandDuplicatePerson = new GroupRemoveTagCommand(new GroupName("Bali"),
                TAGS_NOT_IN_GROUP,
                true);
        assertParseSuccess(parser, " gn/Bali t/Friends t/family", expectedCommandDuplicatePerson);
    }
}
