package seedu.awe.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.awe.commons.core.Messages.MESSAGE_ADDEXPENSECOMMAND_PAYER_DOES_NOT_EXIST;
import static seedu.awe.commons.core.Messages.MESSAGE_ADDEXPENSECOMMAND_USAGE;
import static seedu.awe.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.awe.logic.commands.CommandTestUtil.COST_DESC_FIFTY;
import static seedu.awe.logic.commands.CommandTestUtil.COST_DESC_TWO;
import static seedu.awe.logic.commands.CommandTestUtil.DESCRIPTION_DESC_HOLIDAY;
import static seedu.awe.logic.commands.CommandTestUtil.EXCLUDE_DESC_ALICE;
import static seedu.awe.logic.commands.CommandTestUtil.EXCLUDE_DESC_BOB;
import static seedu.awe.logic.commands.CommandTestUtil.GROUPNAME_DESC_BALI;
import static seedu.awe.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.awe.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.awe.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.awe.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.awe.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.awe.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.awe.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.awe.commons.core.GuiSettings;
import seedu.awe.logic.commands.AddExpenseCommand;
import seedu.awe.logic.commands.exceptions.CommandException;
import seedu.awe.model.AddressBook;
import seedu.awe.model.Model;
import seedu.awe.model.ReadOnlyAddressBook;
import seedu.awe.model.ReadOnlyUserPrefs;
import seedu.awe.model.expense.Cost;
import seedu.awe.model.expense.Expense;
import seedu.awe.model.group.Group;
import seedu.awe.model.group.GroupName;
import seedu.awe.model.group.exceptions.DuplicateGroupException;
import seedu.awe.model.group.exceptions.GroupNotFoundException;
import seedu.awe.model.payment.Payment;
import seedu.awe.model.person.Person;
import seedu.awe.model.transactionsummary.TransactionSummary;
import seedu.awe.testutil.ExpenseBuilder;
import seedu.awe.testutil.GroupBuilder;
import seedu.awe.testutil.PersonBuilder;

public class AddExpenseCommandParserTest {
    private Person validPerson = new PersonBuilder().build();
    private Person validSelfPayee = new PersonBuilder().withName("Bob").build();
    private Person validExcludedPerson = new PersonBuilder().withName(VALID_NAME_BOB).build();
    private Group validGroup = new GroupBuilder().build();
    private Expense validExpense = new ExpenseBuilder().build();

    @Test
    public void parse_validArgs_returnsAddExpenseCommand() {
        ModelStubAcceptingExpenseAdded modelStub = new ModelStubAcceptingExpenseAdded();
        modelStub.addPerson(validPerson);
        modelStub.addPerson(validExcludedPerson);
        Cost validCost = new Cost(20);
        AddExpenseCommandParser parser = new AddExpenseCommandParser(modelStub);

        // No individual payments, no excluded
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_AMY + GROUPNAME_DESC_BALI
                        + COST_DESC_FIFTY + DESCRIPTION_DESC_HOLIDAY,
                new AddExpenseCommand(validPerson, validExpense.getCost(), validExpense.getDescription(),
                        validGroup.getGroupName(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));

        // With individual payments, no excluded
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_AMY + GROUPNAME_DESC_BALI
                        + COST_DESC_FIFTY + DESCRIPTION_DESC_HOLIDAY + NAME_DESC_BOB + COST_DESC_TWO,
                new AddExpenseCommand(validPerson, validExpense.getCost(), validExpense.getDescription(),
                        validGroup.getGroupName(), new ArrayList<>(Arrays.asList(validSelfPayee)),
                        new ArrayList<>(Arrays.asList(validCost)), new ArrayList<>()));

        // No individual payments, with excluded
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_AMY + GROUPNAME_DESC_BALI
                        + COST_DESC_FIFTY + DESCRIPTION_DESC_HOLIDAY + EXCLUDE_DESC_BOB,
                new AddExpenseCommand(validPerson, validExpense.getCost(), validExpense.getDescription(),
                        validGroup.getGroupName(), new ArrayList<>(), new ArrayList<>(),
                        new ArrayList<>(Arrays.asList(validExcludedPerson))));

        // With both individual payments and excluded
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_AMY + GROUPNAME_DESC_BALI
                        + COST_DESC_FIFTY + DESCRIPTION_DESC_HOLIDAY + NAME_DESC_BOB + COST_DESC_TWO
                        + EXCLUDE_DESC_ALICE,
                new AddExpenseCommand(validPerson, validExpense.getCost(), validExpense.getDescription(),
                        validGroup.getGroupName(), new ArrayList<>(Arrays.asList(validSelfPayee)),
                        new ArrayList<>(Arrays.asList(validCost)),
                        new ArrayList<>(Arrays.asList(validExcludedPerson))));

        // Payer not part of group
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_AMY + GROUPNAME_DESC_BALI
                        + COST_DESC_FIFTY + DESCRIPTION_DESC_HOLIDAY,
                new AddExpenseCommand(validPerson, validExpense.getCost(), validExpense.getDescription(),
                        validGroup.getGroupName(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        ModelStubAcceptingExpenseAdded modelStub = new ModelStubAcceptingExpenseAdded();
        modelStub.addPerson(validPerson);
        AddExpenseCommandParser parser = new AddExpenseCommandParser(modelStub);

        // Missing name argument
        assertParseFailure(parser, PREAMBLE_WHITESPACE + GROUPNAME_DESC_BALI
                        + COST_DESC_FIFTY + DESCRIPTION_DESC_HOLIDAY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_ADDEXPENSECOMMAND_USAGE));

        // Missing group name argument
        assertParseFailure(parser, PREAMBLE_WHITESPACE + NAME_DESC_AMY
                        + COST_DESC_FIFTY + DESCRIPTION_DESC_HOLIDAY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_ADDEXPENSECOMMAND_USAGE));

        // Missing cost argument
        assertParseFailure(parser, PREAMBLE_WHITESPACE + NAME_DESC_AMY + GROUPNAME_DESC_BALI
                        + DESCRIPTION_DESC_HOLIDAY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_ADDEXPENSECOMMAND_USAGE));

        // Missing description argument
        assertParseFailure(parser, PREAMBLE_WHITESPACE + NAME_DESC_AMY + GROUPNAME_DESC_BALI
                        + COST_DESC_FIFTY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_ADDEXPENSECOMMAND_USAGE));

        // Non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_AMY + GROUPNAME_DESC_BALI
                        + COST_DESC_FIFTY + DESCRIPTION_DESC_HOLIDAY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_ADDEXPENSECOMMAND_USAGE));

        // More names than costs
        assertParseFailure(parser, PREAMBLE_WHITESPACE + NAME_DESC_AMY + GROUPNAME_DESC_BALI
                        + COST_DESC_FIFTY + DESCRIPTION_DESC_HOLIDAY + NAME_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_ADDEXPENSECOMMAND_USAGE));

        // More costs than names
        assertParseFailure(parser, PREAMBLE_WHITESPACE + NAME_DESC_AMY + GROUPNAME_DESC_BALI
                        + COST_DESC_FIFTY + DESCRIPTION_DESC_HOLIDAY + COST_DESC_TWO,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_ADDEXPENSECOMMAND_USAGE));

        // Payer does not exist
        assertParseFailure(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + GROUPNAME_DESC_BALI
                        + COST_DESC_FIFTY + DESCRIPTION_DESC_HOLIDAY,
                String.format(MESSAGE_ADDEXPENSECOMMAND_PAYER_DOES_NOT_EXIST));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Group getActiveGroupFromAddressBook() throws CommandException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTransactionSummary(HashMap<Person, Cost> summary) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAllMembersOfGroup(Group group) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteGroup(Group group) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasGroup(Group group) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPayments(List<Payment> payments) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Returns an unmodifiable view of the list of {@code Group} backed by the internal list of
         * {@code versionedAddressBook}
         */
        @Override
        public ObservableList<Group> getFilteredGroupList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredGroupList(Predicate<Group> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Group getGroupByName(GroupName groupName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addGroup(Group group) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGroup(Group group, Group newGroup) throws DuplicateGroupException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredExpenseList(Predicate<Expense> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addExpense(Expense expense, Group group) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteExpense(Expense expense, Group group) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Payment> getPayments() {
            return null;
        }

        @Override
        public ObservableList<Expense> getExpenses() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setExpenses(Group group) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<TransactionSummary> getTransactionSummary() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isCurrentExpenseList(Group group) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Expense getExpense(int index) {
            return null;
        }

    }

    /**
     * A Model stub that always accept the expense being added.
     */
    private class ModelStubAcceptingExpenseAdded extends ModelStub {
        final ArrayList<Person> personsAdded = new ArrayList<>();
        final ArrayList<Group> groups = new ArrayList<>();
        final ArrayList<Expense> expenses = new ArrayList<>();

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isSamePerson);
        }

        @Override
        public void addPerson(Person person) {
            requireNonNull(person);
            personsAdded.add(person);
        }

        @Override
        public void addExpense(Expense expense, Group group) {
            requireNonNull(expense);
            requireNonNull(group);
            expenses.add(expense);
        }

        @Override
        public void addGroup(Group group) {
            requireNonNull(group);
            groups.add(group);
        }

        @Override
        public void setGroup(Group group, Group newGroup) {
            int index = groups.indexOf(group);
            groups.set(index, newGroup);
        }

        @Override
        public Group getGroupByName(GroupName groupName) {
            requireNonNull(groupName);
            for (Group group : groups) {
                if (group.getGroupName().equals(groupName)) {
                    return group;
                }
            }
            throw new GroupNotFoundException();
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            AddressBook toReturn = new AddressBook();
            toReturn.setPersons(personsAdded);
            toReturn.setGroups(groups);
            return toReturn;
        }
    }
}
