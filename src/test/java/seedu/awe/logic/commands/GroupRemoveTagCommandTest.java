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

public class GroupRemoveTagCommandTest {
    private static final GroupName BALI_GROUP_NAME = new GroupName("Bali");
    private static final GroupName JAPAN_GROUP_NAME = new GroupName("Japan");
    private static final Tag[] tagsToRemove = {new Tag("friends"), new Tag("3days2nights")};
    private static final Tag[] absentTagToRemove = {new Tag("family")};
    private static final Set<Tag> GROUP_TAGS_TO_REMOVE = new HashSet<>(Arrays.asList(tagsToRemove));
    private static final Set<Tag> GROUP_ABSENT_TAG_TO_REMOVE =
            new HashSet<>(Arrays.asList(absentTagToRemove));


    private Model model = new ModelBuilder().build();

    @Test
    public void constructor_nullGroup_throwsNullPointerException() {
        // group name null
        assertThrows(NullPointerException.class, () ->
                new GroupRemoveTagCommand(null, GROUP_TAGS_TO_REMOVE, true));

        // TAGS null
        assertThrows(NullPointerException.class, () ->
                new GroupRemoveTagCommand(BALI_GROUP_NAME, null, true));
    }

    @Test
    public void getters_validConstructor_success() {
        GroupRemoveTagCommand groupRemoveTagCommandTrue =
                new GroupRemoveTagCommand(BALI_GROUP_NAME, GROUP_TAGS_TO_REMOVE, true);

        GroupRemoveTagCommand groupRemoveTagCommandFalse =
                new GroupRemoveTagCommand(BALI_GROUP_NAME, GROUP_TAGS_TO_REMOVE, false);

        // getGroupName
        assertEquals(groupRemoveTagCommandTrue.getGroupName(), BALI_GROUP_NAME);

        // getGroupTAGS
        assertEquals(groupRemoveTagCommandTrue.getTagsToBeRemoved(), GROUP_TAGS_TO_REMOVE);

        // getValidCommand
        assertEquals(groupRemoveTagCommandTrue.getValidCommand(), true);

        // getValidCommand
        assertEquals(groupRemoveTagCommandFalse.getValidCommand(), false);
    }

    @Test
    public void execute_noGroupFound_failure() {
        GroupRemoveTagCommand groupRemoveTagCommand =
                new GroupRemoveTagCommand(JAPAN_GROUP_NAME, GROUP_TAGS_TO_REMOVE, true);

        String expectedMessage = String.format(Messages.MESSAGE_NONEXISTENT_GROUP, JAPAN_GROUP_NAME);

        assertCommandFailure(groupRemoveTagCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidCommandFound_failure() {
        GroupRemoveTagCommand groupRemoveTagCommandTrue =
                new GroupRemoveTagCommand(BALI_GROUP_NAME, GROUP_ABSENT_TAG_TO_REMOVE, true);
        String expectedMessage = String.format(Messages.MESSAGE_GROUPREMOVETAGCOMMAND_NONEXISTENT_TAG, "family");
        assertCommandFailure(groupRemoveTagCommandTrue, model, expectedMessage);

        GroupRemoveTagCommand groupRemoveTagCommandFalse =
                new GroupRemoveTagCommand(BALI_GROUP_NAME, GROUP_TAGS_TO_REMOVE, false);
        assertCommandFailure(groupRemoveTagCommandTrue, model, expectedMessage);
    }

    @Test
    public void execute_validInputs_success() {
        GroupRemoveTagCommand groupRemoveTagCommand =
                new GroupRemoveTagCommand(BALI_GROUP_NAME, GROUP_TAGS_TO_REMOVE, true);

        CommandResult expectedCommandResult = new CommandResult(
                String.format(Messages.MESSAGE_GROUPREMOVETAGCOMMAND_SUCCESS, BALI_GROUP_NAME));

        ModelBuilder builder = new ModelBuilder();
        Model expectedModel = builder.build();
        Group updatedGroup = BALI;
        for (Tag tag : GROUP_TAGS_TO_REMOVE) {
            updatedGroup.removeTag(tag);
        }
        expectedModel.setGroup(BALI, updatedGroup);

        assertCommandSuccess(groupRemoveTagCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void equals() {
        GroupRemoveTagCommand groupRemoveTagCommandTrue =
                new GroupRemoveTagCommand(BALI_GROUP_NAME, GROUP_TAGS_TO_REMOVE, true);

        GroupRemoveTagCommand groupRemoveTagCommandFalse =
                new GroupRemoveTagCommand(BALI_GROUP_NAME, GROUP_TAGS_TO_REMOVE, false);

        // null input -> false
        assertFalse(groupRemoveTagCommandTrue.equals(null));

        // Not same class -> false
        assertFalse(groupRemoveTagCommandTrue.equals(new ListContactsCommand()));

        // Same instance -> true
        assertTrue(groupRemoveTagCommandTrue.equals(groupRemoveTagCommandTrue));

        // Different instance but same details -> true
        GroupRemoveTagCommand groupRemoveTagCommandToCheck =
                new GroupRemoveTagCommand(BALI_GROUP_NAME, GROUP_TAGS_TO_REMOVE, true);
        assertTrue(groupRemoveTagCommandTrue.equals(groupRemoveTagCommandToCheck));

        // Different instance with different TAGS -> false
        System.out.println(GROUP_TAGS_TO_REMOVE.size());
        GroupRemoveTagCommand groupRemoveTagCommandDifferentTags =
                new GroupRemoveTagCommand(BALI_GROUP_NAME, GROUP_ABSENT_TAG_TO_REMOVE, true);
        assertFalse(groupRemoveTagCommandTrue.equals(groupRemoveTagCommandDifferentTags));

        // Different instance with different valid command -> false
        assertFalse(groupRemoveTagCommandTrue.equals(groupRemoveTagCommandFalse));
    }
}
