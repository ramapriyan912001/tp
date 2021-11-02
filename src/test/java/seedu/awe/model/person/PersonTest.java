package seedu.awe.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.awe.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.awe.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.awe.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.awe.testutil.Assert.assertThrows;
import static seedu.awe.testutil.TypicalGroups.BALI;
import static seedu.awe.testutil.TypicalGroups.DOHA;
import static seedu.awe.testutil.TypicalGroups.INDIA;
import static seedu.awe.testutil.TypicalGroups.OSLO;
import static seedu.awe.testutil.TypicalPersons.ALICE;
import static seedu.awe.testutil.TypicalPersons.BOB;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.awe.model.group.Group;
import seedu.awe.testutil.PersonBuilder;

public class PersonTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Person person = new PersonBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> person.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSamePerson(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePerson(null));

        // same name, all other attributes different -> returns true
        Person editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Person editedBob = new PersonBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new PersonBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSamePerson(editedBob));
    }

    @Test
    public void getPersonGroups_personInAGroup_success() {
        ObservableList<Group> groups = FXCollections.observableArrayList(BALI, OSLO);

        ArrayList<Group> expectedList = new ArrayList<>();
        expectedList.add(BALI);

        assertEquals(expectedList, ALICE.getPersonGroups(groups));
    }

    @Test
    public void getPersonGroups_personInMultipleGroup_success() {
        ObservableList<Group> groups = FXCollections.observableArrayList(BALI, OSLO, DOHA);

        ArrayList<Group> expectedList = new ArrayList<>();
        expectedList.add(BALI);
        expectedList.add(DOHA);

        assertEquals(expectedList, ALICE.getPersonGroups(groups));
    }
    @Test
    public void getPersonGroups_personNotInGroup_success() {
        ObservableList<Group> groups = FXCollections.observableArrayList(INDIA, OSLO);

        ArrayList<Group> expectedList = new ArrayList<>();

        assertEquals(expectedList, ALICE.getPersonGroups(groups));
    }

    @Test
    public void getGroupName_personInAGroup_success() {
        ObservableList<Group> groups = FXCollections.observableArrayList(BALI, OSLO);

        String expectedResult = BALI.getGroupName().getName();

        assertEquals(expectedResult, ALICE.getGroupsName(groups));
    }

    @Test
    public void getGroupName_personInMultipleGroup_success() {
        ObservableList<Group> groups = FXCollections.observableArrayList(BALI, OSLO, DOHA);

        String expectedResult = String.format("%s, %s", BALI.getGroupName().getName(), DOHA.getGroupName().getName());

        assertEquals(expectedResult, ALICE.getGroupsName(groups));
    }

    @Test
    public void getGroupName_personNotInGroup_success() {
        ObservableList<Group> groups = FXCollections.observableArrayList(INDIA, OSLO);

        String expectedResult = "";

        assertEquals(expectedResult, ALICE.getGroupsName(groups));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Person aliceCopy = new PersonBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different person -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Person editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
