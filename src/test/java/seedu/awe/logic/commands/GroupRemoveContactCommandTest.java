package seedu.awe.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.awe.commons.core.Messages.MESSAGE_GROUPREMOVECONTACTCOMMAND_GROUP_DELETED;
import static seedu.awe.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.awe.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.awe.testutil.Assert.assertThrows;
import static seedu.awe.testutil.TypicalGroups.BALI;
import static seedu.awe.testutil.TypicalPersons.ALICE;
import static seedu.awe.testutil.TypicalPersons.AMY;
import static seedu.awe.testutil.TypicalPersons.BENSON;
import static seedu.awe.testutil.TypicalPersons.BOB;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.awe.commons.core.Messages;
import seedu.awe.model.Model;
import seedu.awe.model.group.Group;
import seedu.awe.model.group.GroupName;
import seedu.awe.model.person.Person;
import seedu.awe.testutil.ModelBuilder;

public class GroupRemoveContactCommandTest {
    private static final GroupName BALI_GROUP_NAME = new GroupName("Bali");
    private static final GroupName JAPAN_GROUP_NAME = new GroupName("Japan");
    private static final Person[] membersToRemove = {BOB, AMY, ALICE};
    private static final Person[] memberToRemove = {BOB};
    private static final Person[] absentMemberToRemove = {BENSON, BOB};
    private static final ArrayList<Person> GROUP_MEMBERS_TO_REMOVE = new ArrayList<>(Arrays.asList(membersToRemove));
    private static final ArrayList<Person> GROUP_MEMBER_TO_REMOVE = new ArrayList<>(Arrays.asList(memberToRemove));
    private static final ArrayList<Person> GROUP_ABSENT_MEMBER_TO_REMOVE =
            new ArrayList<>(Arrays.asList(absentMemberToRemove));


    private Model model = new ModelBuilder().build();

    @Test
    public void constructor_nullGroup_throwsNullPointerException() {
        // group name null
        assertThrows(NullPointerException.class, () ->
                new GroupRemoveContactCommand(null, GROUP_MEMBERS_TO_REMOVE, true));

        // members null
        assertThrows(NullPointerException.class, () ->
                new GroupRemoveContactCommand(BALI_GROUP_NAME, null, true));
    }

    @Test
    public void getters_validConstructor_success() {
        GroupRemoveContactCommand groupRemoveContactCommandTrue =
                new GroupRemoveContactCommand(BALI_GROUP_NAME, GROUP_MEMBERS_TO_REMOVE, true);

        GroupRemoveContactCommand groupRemoveContactCommandFalse =
                new GroupRemoveContactCommand(BALI_GROUP_NAME, GROUP_MEMBERS_TO_REMOVE, false);

        // getGroupName
        assertEquals(groupRemoveContactCommandTrue.getGroupName(), BALI_GROUP_NAME);

        // getGroupMembers
        assertEquals(groupRemoveContactCommandTrue.getMembersToBeRemoved(), GROUP_MEMBERS_TO_REMOVE);

        // getValidCommand
        assertEquals(groupRemoveContactCommandTrue.isValidCommand(), true);

        // getValidCommand
        assertEquals(groupRemoveContactCommandFalse.isValidCommand(), false);
    }

    @Test
    public void execute_noGroupFound_failure() {
        GroupRemoveContactCommand groupRemoveContactCommand =
                new GroupRemoveContactCommand(JAPAN_GROUP_NAME, GROUP_MEMBERS_TO_REMOVE, true);

        String expectedMessage = String.format(Messages.MESSAGE_NONEXISTENT_GROUP, JAPAN_GROUP_NAME);

        assertCommandFailure(groupRemoveContactCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidCommandFound_failure() {
        GroupRemoveContactCommand groupRemoveContactCommandTrue =
                new GroupRemoveContactCommand(BALI_GROUP_NAME, GROUP_ABSENT_MEMBER_TO_REMOVE, true);
        String expectedMessage = Messages.MESSAGE_GROUPREMOVECONTACTCOMMAND_NONEXISTENT_PERSON;
        assertCommandFailure(groupRemoveContactCommandTrue, model, expectedMessage);

        GroupRemoveContactCommand groupRemoveContactCommandFalse =
                new GroupRemoveContactCommand(BALI_GROUP_NAME, GROUP_MEMBERS_TO_REMOVE, false);
        assertCommandFailure(groupRemoveContactCommandTrue, model, expectedMessage);
    }

    @Test
    public void execute_validInputs_success() {
        GroupRemoveContactCommand groupRemoveContactCommand =
                new GroupRemoveContactCommand(BALI_GROUP_NAME, GROUP_MEMBER_TO_REMOVE, true);

        CommandResult expectedCommandResult = new CommandResult(
                String.format(Messages.MESSAGE_GROUPREMOVECONTACTCOMMAND_SUCCESS, BALI_GROUP_NAME));

        ModelBuilder builder = new ModelBuilder();
        Model expectedModel = builder.build();
        Group updatedGroup = BALI;
        for (Person member : GROUP_MEMBER_TO_REMOVE) {
            updatedGroup.removeMember(member);
        }
        expectedModel.setGroup(BALI, updatedGroup);

        assertCommandSuccess(groupRemoveContactCommand, model, expectedCommandResult, expectedModel);
    }


    @Test
    public void execute_validInputs_successDeleteGroup() {
        GroupRemoveContactCommand groupRemoveContactCommand =
                new GroupRemoveContactCommand(BALI_GROUP_NAME, GROUP_MEMBERS_TO_REMOVE, true);

        CommandResult expectedCommandResult = new CommandResult(
                String.format(Messages.MESSAGE_GROUPREMOVECONTACTCOMMAND_SUCCESS
                        + String.format(MESSAGE_GROUPREMOVECONTACTCOMMAND_GROUP_DELETED,
                        BALI_GROUP_NAME), BALI_GROUP_NAME));

        ModelBuilder builder = new ModelBuilder();
        Model expectedModel = builder.build();
        Group updatedGroup = BALI;
        for (Person member : GROUP_MEMBERS_TO_REMOVE) {
            updatedGroup.removeMember(member);
        }
        expectedModel.deleteGroup(BALI);

        assertCommandSuccess(groupRemoveContactCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void equals() {
        GroupRemoveContactCommand groupRemoveContactCommandTrue =
                new GroupRemoveContactCommand(BALI_GROUP_NAME, GROUP_MEMBERS_TO_REMOVE, true);

        GroupRemoveContactCommand groupRemoveContactCommandFalse =
                new GroupRemoveContactCommand(BALI_GROUP_NAME, GROUP_MEMBERS_TO_REMOVE, false);

        // null input -> false
        assertFalse(groupRemoveContactCommandTrue.equals(null));

        // Not same class -> false
        assertFalse(groupRemoveContactCommandTrue.equals(new ListContactsCommand()));

        // Same instance -> true
        assertTrue(groupRemoveContactCommandTrue.equals(groupRemoveContactCommandTrue));

        // Different instance but same details -> true
        GroupRemoveContactCommand groupRemoveContactCommandToCheck =
                new GroupRemoveContactCommand(BALI_GROUP_NAME, GROUP_MEMBERS_TO_REMOVE, true);
        assertTrue(groupRemoveContactCommandTrue.equals(groupRemoveContactCommandToCheck));

        // Different instance with different members -> false
        System.out.println(GROUP_MEMBERS_TO_REMOVE.size());
        GroupRemoveContactCommand groupRemoveContactCommandDifferentMembers =
                new GroupRemoveContactCommand(BALI_GROUP_NAME, new ArrayList<Person>(), true);
        assertFalse(groupRemoveContactCommandTrue.equals(groupRemoveContactCommandDifferentMembers));

        // Different instance with different valid command -> false
        assertFalse(groupRemoveContactCommandTrue.equals(groupRemoveContactCommandFalse));
    }
}
