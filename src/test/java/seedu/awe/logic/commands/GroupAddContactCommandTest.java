package seedu.awe.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.awe.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.awe.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.awe.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.awe.testutil.Assert.assertThrows;
import static seedu.awe.testutil.TypicalGroups.BALI;
import static seedu.awe.testutil.TypicalPersons.BOB;
import static seedu.awe.testutil.TypicalPersons.ELLE;
import static seedu.awe.testutil.TypicalPersons.FIONA;
import static seedu.awe.testutil.TypicalPersons.GEORGE;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.awe.commons.core.Messages;
import seedu.awe.model.Model;
import seedu.awe.model.group.Group;
import seedu.awe.model.group.GroupName;
import seedu.awe.model.person.Person;
import seedu.awe.testutil.ModelBuilder;

public class GroupAddContactCommandTest {
    private static final GroupName BALI_GROUP_NAME = new GroupName("Bali");
    private static final GroupName JAPAN_GROUP_NAME = new GroupName("Japan");
    private static final Person[] members = {ELLE, FIONA, GEORGE};
    private static final Person[] memberInGroup = {BOB, ELLE};
    private static final ArrayList<Person> GROUP_MEMBERS_NOT_IN_GROUP = new ArrayList<>(Arrays.asList(members));
    private static final ArrayList<Person> GROUP_MEMBERS_IN_GROUP = new ArrayList<>(Arrays.asList(memberInGroup));


    private Model model = new ModelBuilder().build();

    @Test
    public void constructor_nullGroup_throwsNullPointerException() {
        ArrayList<Person> members = new ArrayList<>();
        // group name null
        assertThrows(NullPointerException.class, () ->
                new GroupAddContactCommand(null, members, true));

        // members null
        assertThrows(NullPointerException.class, () ->
                new GroupAddContactCommand(BALI_GROUP_NAME, null, true));
    }

    @Test
    public void getters_validConstructor_success() {
        GroupAddContactCommand groupAddContactCommandTrue =
                new GroupAddContactCommand(BALI_GROUP_NAME, GROUP_MEMBERS_NOT_IN_GROUP, true);

        GroupAddContactCommand groupAddContactCommandFalse =
                new GroupAddContactCommand(BALI_GROUP_NAME, GROUP_MEMBERS_NOT_IN_GROUP, false);

        // getGroupName
        assertEquals(groupAddContactCommandTrue.getGroupName(), BALI_GROUP_NAME);

        // getGroupMembers
        assertEquals(groupAddContactCommandTrue.getNewMembers(), GROUP_MEMBERS_NOT_IN_GROUP);

        // getValidCommand
        assertEquals(groupAddContactCommandTrue.getValidCommand(), true);

        // getValidCommand
        assertEquals(groupAddContactCommandFalse.getValidCommand(), false);
    }

    @Test
    public void execute_noGroupFound_failure() {
        GroupAddContactCommand groupAddContactCommand =
                new GroupAddContactCommand(JAPAN_GROUP_NAME, GROUP_MEMBERS_NOT_IN_GROUP, true);

        String expectedMessage = String.format(Messages.MESSAGE_NONEXISTENT_GROUP, JAPAN_GROUP_NAME);

        assertCommandFailure(groupAddContactCommand, model, expectedMessage);
    }

    @Test
    public void execute_personInGroup_failure() {
        GroupAddContactCommand groupAddContactCommand =
                new GroupAddContactCommand(BALI_GROUP_NAME, GROUP_MEMBERS_IN_GROUP, true);

        String expectedMessage = String.format(
                Messages.MESSAGE_GROUPADDCONTACTCOMMAND_DUPLICATE_PERSON, VALID_NAME_BOB);

        assertCommandFailure(groupAddContactCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidCommandFound_failure() {
        GroupAddContactCommand groupAddContactCommand =
                new GroupAddContactCommand(BALI_GROUP_NAME, GROUP_MEMBERS_NOT_IN_GROUP, false);

        String expectedMessage = Messages.MESSAGE_GROUPADDCONTACTCOMMAND_NONEXISTENT_PERSON;

        assertCommandFailure(groupAddContactCommand, model, expectedMessage);
    }

    @Test
    public void execute_validInputs_success() {
        GroupAddContactCommand groupAddContactCommand =
                new GroupAddContactCommand(BALI_GROUP_NAME, GROUP_MEMBERS_NOT_IN_GROUP, true);

        CommandResult expectedCommandResult = new CommandResult(
                String.format(Messages.MESSAGE_GROUPADDCONTACTCOMMAND_SUCCESS, BALI_GROUP_NAME));

        ModelBuilder builder = new ModelBuilder();
        Model expectedModel = builder.build();
        Group updatedGroup = new Group(BALI_GROUP_NAME, new ArrayList<Person>());
        for (Person member : GROUP_MEMBERS_NOT_IN_GROUP) {
            updatedGroup.addMember(member);
        }
        expectedModel.setGroup(BALI, updatedGroup);

        assertCommandSuccess(groupAddContactCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void equals() {
        GroupAddContactCommand groupAddContactCommandTrue =
                new GroupAddContactCommand(BALI_GROUP_NAME, GROUP_MEMBERS_NOT_IN_GROUP, true);

        GroupAddContactCommand groupAddContactCommandFalse =
                new GroupAddContactCommand(BALI_GROUP_NAME, GROUP_MEMBERS_NOT_IN_GROUP, false);

        // null input -> false
        assertFalse(groupAddContactCommandTrue.equals(null));

        // Not same class -> false
        assertFalse(groupAddContactCommandTrue.equals(new ListContactsCommand()));

        // Same instance -> true
        assertTrue(groupAddContactCommandTrue.equals(groupAddContactCommandTrue));

        // Different instance but same details -> true
        GroupAddContactCommand groupAddContactCommandToCheck =
                new GroupAddContactCommand(BALI_GROUP_NAME, GROUP_MEMBERS_NOT_IN_GROUP, true);
        assertTrue(groupAddContactCommandTrue.equals(groupAddContactCommandToCheck));

        // Different instance with different members -> false
        GroupAddContactCommand groupAddContactCommandDifferentMembers =
                new GroupAddContactCommand(BALI_GROUP_NAME, new ArrayList<Person>(), true);
        assertFalse(groupAddContactCommandTrue.equals(groupAddContactCommandDifferentMembers));

        // Different instance with different valid command -> false
        assertFalse(groupAddContactCommandTrue.equals(groupAddContactCommandFalse));
    }
}
