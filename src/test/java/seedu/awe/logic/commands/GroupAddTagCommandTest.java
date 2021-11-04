package seedu.awe.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.awe.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.awe.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.awe.testutil.Assert.assertThrows;
import static seedu.awe.testutil.TypicalGroups.BALI;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.awe.commons.core.Messages;
import seedu.awe.model.Model;
import seedu.awe.model.group.Group;
import seedu.awe.model.group.GroupName;
import seedu.awe.model.tag.Tag;
import seedu.awe.testutil.ModelBuilder;

public class GroupAddTagCommandTest {
    private static final GroupName BALI_GROUP_NAME = new GroupName("Bali");
    private static final GroupName JAPAN_GROUP_NAME = new GroupName("Japan");
    private static final Tag[] tags = {new Tag("Friends"), new Tag("family")};
    private static final Tag[] tagsInGroup = {new Tag("friends"), new Tag("family")};
    private static final Set<Tag> TAGS_NOT_IN_GROUP = new HashSet<>(Arrays.asList(tags));
    private static final Set<Tag> TAGS_IN_GROUP = new HashSet<>(Arrays.asList(tagsInGroup));


    private Model model = new ModelBuilder().build();

    @Test
    public void constructor_nullGroup_throwsNullPointerException() {
        // group name null
        assertThrows(NullPointerException.class, () ->
                new GroupAddTagCommand(null, TAGS_NOT_IN_GROUP));

        // members null
        assertThrows(NullPointerException.class, () ->
                new GroupAddTagCommand(BALI_GROUP_NAME, null));
    }

    @Test
    public void getters_validConstructor_success() {
        GroupAddTagCommand groupAddTagCommand =
                new GroupAddTagCommand(BALI_GROUP_NAME, TAGS_NOT_IN_GROUP);

        // getGroupName
        assertEquals(groupAddTagCommand.getGroupName(), BALI_GROUP_NAME);

        // getGroupTags
        assertEquals(groupAddTagCommand.getNewTags(), TAGS_NOT_IN_GROUP);
    }

    @Test
    public void execute_noGroupFound_failure() {
        GroupAddTagCommand groupAddTagCommand =
                new GroupAddTagCommand(JAPAN_GROUP_NAME, TAGS_NOT_IN_GROUP);

        String expectedMessage = String.format(Messages.MESSAGE_NONEXISTENT_GROUP, JAPAN_GROUP_NAME);

        assertCommandFailure(groupAddTagCommand, model, expectedMessage);
    }

    @Test
    public void execute_tagInGroup_failure() {
        GroupAddTagCommand groupAddTagCommand =
                new GroupAddTagCommand(BALI_GROUP_NAME, TAGS_IN_GROUP);

        String expectedMessage = String.format(
                Messages.MESSAGE_GROUPADDTAGCOMMAND_DUPLICATE_TAG, "friends");

        assertCommandFailure(groupAddTagCommand, model, expectedMessage);
    }

    @Test
    public void execute_validInputs_success() {
        GroupAddTagCommand groupAddTagCommand =
                new GroupAddTagCommand(BALI_GROUP_NAME, TAGS_NOT_IN_GROUP);

        CommandResult expectedCommandResult = new CommandResult(Messages.MESSAGE_GROUPADDTAGCOMMAND_SUCCESS);

        ModelBuilder builder = new ModelBuilder();
        Model expectedModel = builder.build();
        Group updatedGroup = new Group(BALI_GROUP_NAME, BALI.getMembers());
        updatedGroup.addTag(TAGS_NOT_IN_GROUP);
        expectedModel.setGroup(BALI, updatedGroup);

        assertCommandSuccess(groupAddTagCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void equals() {
        GroupAddTagCommand groupAddTagCommand =
                new GroupAddTagCommand(BALI_GROUP_NAME, TAGS_NOT_IN_GROUP);

        // null input -> false
        assertFalse(groupAddTagCommand.equals(null));

        // Not same class -> false
        assertFalse(groupAddTagCommand.equals(new ListContactsCommand()));

        // Same instance -> true
        assertTrue(groupAddTagCommand.equals(groupAddTagCommand));

        // Different instance but same details -> true
        GroupAddTagCommand groupAddTagCommandToCheck =
                new GroupAddTagCommand(BALI_GROUP_NAME, TAGS_NOT_IN_GROUP);
        assertTrue(groupAddTagCommand.equals(groupAddTagCommandToCheck));

        // Different instance with different members -> false
        GroupAddTagCommand groupAddTagCommandDifferentMembers =
                new GroupAddTagCommand(BALI_GROUP_NAME, TAGS_IN_GROUP);
        assertFalse(groupAddTagCommand.equals(groupAddTagCommandDifferentMembers));
    }
}
