package seedu.awe.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.awe.commons.core.Messages.MESSAGE_DELETECONTACTCOMMAND_CANNOT_BE_DELETED;
import static seedu.awe.commons.core.Messages.MESSAGE_DELETECONTACTCOMMAND_DELETE_PERSON_SUCCESS;
import static seedu.awe.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.awe.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.awe.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.awe.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.awe.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.awe.testutil.TypicalPersons.getTypicalAwe;

import org.junit.jupiter.api.Test;

import seedu.awe.commons.core.Messages;
import seedu.awe.commons.core.index.Index;
import seedu.awe.model.Model;
import seedu.awe.model.ModelManager;
import seedu.awe.model.UserPrefs;
import seedu.awe.model.person.Person;
import seedu.awe.ui.MainWindow;
import seedu.awe.ui.UiView;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteContactCommandTest {

    private Model model = new ModelManager(getTypicalAwe(), new UserPrefs());


    @Test
    public void execute_notOnContactsPage_failure() {
        MainWindow.setViewEnum(UiView.EXPENSE_PAGE);
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST.getZeroBased());
        DeleteContactCommand deleteContactCommand = new DeleteContactCommand(INDEX_FIRST);

        String expectedMessage = MESSAGE_DELETECONTACTCOMMAND_CANNOT_BE_DELETED;

        assertCommandFailure(deleteContactCommand, model, expectedMessage);
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        MainWindow.setViewEnum(UiView.CONTACT_PAGE);
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST.getZeroBased());
        DeleteContactCommand deleteContactCommand = new DeleteContactCommand(INDEX_FIRST);

        CommandResult commandResult = new CommandResult(
                String.format(MESSAGE_DELETECONTACTCOMMAND_DELETE_PERSON_SUCCESS, personToDelete),
                false, false, false, true, false, false, false);

        ModelManager expectedModel = new ModelManager(model.getAwe(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);


        assertCommandSuccess(deleteContactCommand, model, commandResult, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        MainWindow.setViewEnum(UiView.CONTACT_PAGE);
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DeleteContactCommand deleteContactCommand = new DeleteContactCommand(outOfBoundIndex);

        assertCommandFailure(deleteContactCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        MainWindow.setViewEnum(UiView.CONTACT_PAGE);
        showPersonAtIndex(model, INDEX_FIRST);

        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST.getZeroBased());
        DeleteContactCommand deleteContactCommand = new DeleteContactCommand(INDEX_FIRST);

        CommandResult commandResult = new CommandResult(
                String.format(MESSAGE_DELETECONTACTCOMMAND_DELETE_PERSON_SUCCESS, personToDelete),
                false, false, false, true, false, false, false);

        Model expectedModel = new ModelManager(model.getAwe(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);
        showNoPerson(expectedModel);

        assertCommandSuccess(deleteContactCommand, model, commandResult, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        MainWindow.setViewEnum(UiView.CONTACT_PAGE);
        showPersonAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of awe book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAwe().getPersonList().size());

        DeleteContactCommand deleteContactCommand = new DeleteContactCommand(outOfBoundIndex);

        assertCommandFailure(deleteContactCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        MainWindow.setViewEnum(UiView.CONTACT_PAGE);
        DeleteContactCommand deleteFirstCommand = new DeleteContactCommand(INDEX_FIRST);
        DeleteContactCommand deleteSecondCommand = new DeleteContactCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteContactCommand deleteFirstCommandCopy = new DeleteContactCommand(INDEX_FIRST);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        MainWindow.setViewEnum(UiView.CONTACT_PAGE);
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }
}
