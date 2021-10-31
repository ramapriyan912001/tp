package seedu.awe.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.awe.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.awe.testutil.Assert.assertThrows;
import static seedu.awe.testutil.TypicalGroups.BUDAPEST_WITH_EXPENSES;
import static seedu.awe.testutil.TypicalGroups.DUBAI;
import static seedu.awe.testutil.TypicalGroups.INDIA;
import static seedu.awe.testutil.TypicalPersons.ALICE;
import static seedu.awe.testutil.TypicalPersons.BENSON;
import static seedu.awe.testutil.TypicalPersons.FIONA;
import static seedu.awe.testutil.TypicalPersons.IDA;
import static seedu.awe.testutil.TypicalPersons.NONEXISTENTPERSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.awe.commons.core.GuiSettings;
import seedu.awe.logic.commands.exceptions.CommandException;
import seedu.awe.model.expense.Cost;
import seedu.awe.model.expense.Description;
import seedu.awe.model.expense.Expense;
import seedu.awe.model.person.NameContainsKeywordsPredicate;
import seedu.awe.model.person.Person;
import seedu.awe.model.transactionsummary.TransactionSummary;
import seedu.awe.testutil.AddressBookBuilder;
import seedu.awe.testutil.ModelBuilder;

public class ModelManagerTest {

    private ModelManager emptyModelManager = (ModelManager) new ModelManager();
    private ModelManager modelManager = (ModelManager) new ModelBuilder().build();

    public void resetModelManager() {
        modelManager = (ModelManager) new ModelBuilder().build();
    }

    @Test
    public void constructor() {
        resetModelManager();
        assertEquals(new UserPrefs(), emptyModelManager.getUserPrefs());
        assertEquals(new GuiSettings(), emptyModelManager.getGuiSettings());
        assertEquals(new AddressBook(), new AddressBook(emptyModelManager.getAddressBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        resetModelManager();
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        resetModelManager();
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setAddressBookFilePath(Paths.get("awe/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setAddressBookFilePath(Paths.get("new/awe/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        resetModelManager();
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        resetModelManager();
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        resetModelManager();
        assertThrows(NullPointerException.class, () -> modelManager.setAddressBookFilePath(null));
    }

    @Test
    public void setAddressBookFilePath_validPath_setsAddressBookFilePath() {
        resetModelManager();
        Path path = Paths.get("awe/book/file/path");
        modelManager.setAddressBookFilePath(path);
        assertEquals(path, modelManager.getAddressBookFilePath());
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        resetModelManager();
        assertThrows(NullPointerException.class, () -> modelManager.hasPerson(null));
    }

    @Test
    public void hasGroup_nullGroup_throwsNullPointerException() {
        resetModelManager();
        assertThrows(NullPointerException.class, () -> modelManager.hasGroup(null));
    }

    @Test
    public void hasGroup_validGroupInAddressBook_returnsTrue() {
        resetModelManager();
        assertTrue(modelManager.hasGroup(INDIA));
    }

    @Test
    public void hasGroup_validGroupInAddressBook_returnsFalse() {
        resetModelManager();
        assertFalse(modelManager.hasGroup(BUDAPEST_WITH_EXPENSES));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        resetModelManager();
        assertFalse(modelManager.hasPerson(NONEXISTENTPERSON));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        resetModelManager();
        assertTrue(modelManager.hasPerson(ALICE));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        resetModelManager();
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPersonList().remove(0));
    }

    @Test
    public void getFilteredGroupList_modifyList_throwsUnsupportedOperationException() {
        resetModelManager();
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredGroupList().remove(0));
    }

    @Test
    public void getFilteredExpensesList_modifyList_throwsUnsupportedOperationException() {
        resetModelManager();
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getExpenses().remove(0));
    }

    @Test
    public void addExpense_success() {
        resetModelManager();
        Expense expense = new Expense(FIONA, new Cost(20.00), new Description("test"), List.of(IDA));
        modelManager.setExpenses(DUBAI);
        modelManager.addExpense(expense, DUBAI);
        assertEquals(modelManager.getExpense(0), expense);
    }

    @Test
    public void deleteExpense_success() {
        resetModelManager();
        Expense expense = new Expense(FIONA, new Cost(20.00), new Description("test"), List.of(IDA));
        modelManager.setExpenses(DUBAI);
        modelManager.addExpense(expense, DUBAI);
        modelManager.deleteExpense(expense, DUBAI);
        assertTrue(modelManager.getExpenses().isEmpty());
    }

    @Test
    public void isCurrentExpenseList_returnsFalse() {
        resetModelManager();
        modelManager.setExpenses(DUBAI);
        assertFalse(modelManager.isCurrentExpenseList(INDIA));
    }

    @Test
    public void isCurrentExpenseList_returnsTrue() {
        resetModelManager();
        modelManager.setExpenses(DUBAI);
        assertTrue(modelManager.isCurrentExpenseList(DUBAI));
    }

    @Test
    public void getActiveGroupFromAddressBook_throwsCommandException() {
        resetModelManager();
        assertThrows(CommandException.class, () -> modelManager.getActiveGroupFromAddressBook());
    }

    @Test
    public void getActiveGroupFromAddressBook_returnsGroup() throws CommandException {
        resetModelManager();
        modelManager.setExpenses(DUBAI);
        assertEquals(modelManager.getActiveGroupFromAddressBook(), DUBAI);
    }

    @Test
    public void addGroup_validGroup_success() {
        resetModelManager();
        modelManager.addGroup(BUDAPEST_WITH_EXPENSES);
        assertTrue(modelManager.hasGroup(BUDAPEST_WITH_EXPENSES));
    }

    @Test
    public void setTransactionSummary_nullMap_throwsNullPointerException() {
        resetModelManager();
        assertThrows(NullPointerException.class, () -> modelManager.setTransactionSummary(null));
    }

    @Test
    public void setTransactionSummary_validMap_success() {
        resetModelManager();
        HashMap<Person, Cost> summary = new HashMap<>();
        TransactionSummary transactionSummary = new TransactionSummary(ALICE, new Cost(20.0));
        summary.put(ALICE, new Cost(20.0));
        modelManager.setTransactionSummary(summary);
        List<TransactionSummary> transactionSummaryList = new ArrayList<>();
        transactionSummaryList.add(transactionSummary);
        assertEquals(modelManager.getTransactionSummary(), transactionSummaryList);
    }

    @Test
    public void getFilteredPaymentsList_modifyList_throwsUnsupportedOperationException() {
        resetModelManager();
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getPayments().remove(0));
    }

    @Test
    public void getFilteredTransactionSummaryList_modifyList_throwsUnsupportedOperationException() {
        resetModelManager();
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getTransactionSummary().remove(0));
    }

    @Test
    public void equals() {
        AddressBook addressBook = new AddressBookBuilder().withPerson(ALICE).withPerson(BENSON).build();
        AddressBook differentAddressBook = new AddressBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(addressBook, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(addressBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentAddressBook, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(addressBook, differentUserPrefs)));
    }
}
