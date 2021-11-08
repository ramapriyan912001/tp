package seedu.awe.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.awe.commons.core.Messages.MESSAGE_ADDEXPENSECOMMAND_ALL_MEMBERS_EXCLUDED;
import static seedu.awe.commons.core.Messages.MESSAGE_ADDEXPENSECOMMAND_CANNOT_ADD_EXCLUDED_MEMBER;
import static seedu.awe.commons.core.Messages.MESSAGE_ADDEXPENSECOMMAND_COST_MORE_THAN_MAX;
import static seedu.awe.commons.core.Messages.MESSAGE_ADDEXPENSECOMMAND_COST_ZERO_OR_LESS;
import static seedu.awe.commons.core.Messages.MESSAGE_ADDEXPENSECOMMAND_GROUP_DOES_NOT_EXIST;
import static seedu.awe.commons.core.Messages.MESSAGE_ADDEXPENSECOMMAND_NOT_PART_OF_GROUP;
import static seedu.awe.commons.core.Messages.MESSAGE_ADDEXPENSECOMMAND_SUCCESS;
import static seedu.awe.testutil.Assert.assertThrows;
import static seedu.awe.testutil.TypicalPersons.ALICE;
import static seedu.awe.testutil.TypicalPersons.BOB;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.awe.commons.core.GuiSettings;
import seedu.awe.logic.commands.exceptions.CommandException;
import seedu.awe.model.Awe;
import seedu.awe.model.Model;
import seedu.awe.model.ReadOnlyAwe;
import seedu.awe.model.ReadOnlyUserPrefs;
import seedu.awe.model.expense.Cost;
import seedu.awe.model.expense.Description;
import seedu.awe.model.expense.Expense;
import seedu.awe.model.group.Group;
import seedu.awe.model.group.GroupName;
import seedu.awe.model.group.exceptions.DuplicateGroupException;
import seedu.awe.model.payment.Payment;
import seedu.awe.model.person.Person;
import seedu.awe.model.transactionsummary.TransactionSummary;
import seedu.awe.testutil.ExpenseBuilder;
import seedu.awe.testutil.GroupBuilder;
import seedu.awe.testutil.PersonBuilder;

public class AddExpenseCommandTest {
    private static final String COMMAND_FAIL_FAILED_MESSAGE = "Command should result in a command exception";

    @Test
    public void constructor_nullExpense_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddExpenseCommand(null,
                null, null, null, null, null, null));
    }

    @Test
    public void execute_expenseAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingExpenseAdded modelStub = new ModelStubAcceptingExpenseAdded();
        Person validPerson = new PersonBuilder().build();
        Group validGroup = new GroupBuilder().build();
        Expense validExpense = new ExpenseBuilder().build();
        validGroup = validGroup.addMember(validPerson);
        modelStub.addGroup(validGroup);
        GroupName groupName = validGroup.getGroupName();

        CommandResult commandResult = new AddExpenseCommand(validExpense.getPayer(), validExpense.getCost(),
                validExpense.getDescription(), groupName, new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>()).execute(modelStub);

        assertEquals(String.format(MESSAGE_ADDEXPENSECOMMAND_SUCCESS, validPerson),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_expenseWithExcludedAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingExpenseAdded modelStub = new ModelStubAcceptingExpenseAdded();
        Person validPerson = new PersonBuilder().build();
        Person validPerson2 = new PersonBuilder().withName("Bob").build();
        Group validGroup = new GroupBuilder().build();
        Expense validExpense = new ExpenseBuilder().build();
        validGroup = validGroup.addMember(validPerson);
        validGroup = validGroup.addMember(validPerson2);
        modelStub.addGroup(validGroup);
        GroupName groupName = validGroup.getGroupName();

        CommandResult commandResult = new AddExpenseCommand(validExpense.getPayer(), validExpense.getCost(),
                validExpense.getDescription(), groupName, new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>(Arrays.asList(validPerson2))).execute(modelStub);

        assertEquals(String.format(MESSAGE_ADDEXPENSECOMMAND_SUCCESS, validPerson),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_payerNotInGroup_throwsCommandException() throws Exception {
        ModelStubAcceptingExpenseAdded modelStub = new ModelStubAcceptingExpenseAdded();
        Person validPerson = new PersonBuilder().build();
        Group validGroup = new GroupBuilder().build();
        Expense validExpense = new ExpenseBuilder().build();
        modelStub.addGroup(validGroup);
        GroupName groupName = validGroup.getGroupName();

        try {
            CommandResult commandResult = new AddExpenseCommand(validExpense.getPayer(), validExpense.getCost(),
                    validExpense.getDescription(), groupName, new ArrayList<>(), new ArrayList<>(),
                    new ArrayList<>()).execute(modelStub);
            fail(COMMAND_FAIL_FAILED_MESSAGE);
        } catch (CommandException commandException) {
            assertEquals(String.format(MESSAGE_ADDEXPENSECOMMAND_NOT_PART_OF_GROUP, validPerson),
                    commandException.getMessage());
        }
    }

    @Test
    public void execute_allGroupMembersExcluded_throwsCommandException() throws Exception {
        ModelStubAcceptingExpenseAdded modelStub = new ModelStubAcceptingExpenseAdded();
        Person validPerson = new PersonBuilder().build();
        Group validGroup = new GroupBuilder().build();
        Expense validExpense = new ExpenseBuilder().build();
        validGroup = validGroup.addMember(validPerson);
        modelStub.addGroup(validGroup);
        GroupName groupName = validGroup.getGroupName();

        try {
            CommandResult commandResult = new AddExpenseCommand(validExpense.getPayer(), validExpense.getCost(),
                    validExpense.getDescription(), groupName, new ArrayList<>(), new ArrayList<>(),
                    new ArrayList<>(Arrays.asList(validPerson))).execute(modelStub);
            fail(COMMAND_FAIL_FAILED_MESSAGE);
        } catch (CommandException commandException) {
            assertEquals(String.format(MESSAGE_ADDEXPENSECOMMAND_ALL_MEMBERS_EXCLUDED, validPerson),
                    commandException.getMessage());
        }
    }

    @Test
    public void execute_excludedPersonNotPartOfGroup_throwsCommandException() throws Exception {
        ModelStubAcceptingExpenseAdded modelStub = new ModelStubAcceptingExpenseAdded();

        Person validPerson = new PersonBuilder().build();
        Person validPersonNotInGroup = new PersonBuilder().withName("nic").build();

        Group validGroup = new GroupBuilder().build();
        Expense validExpense = new ExpenseBuilder().build();

        validGroup = validGroup.addMember(validPerson);
        modelStub.addGroup(validGroup);
        GroupName groupName = validGroup.getGroupName();

        try {
            CommandResult commandResult = new AddExpenseCommand(validExpense.getPayer(), validExpense.getCost(),
                    validExpense.getDescription(), groupName, new ArrayList<>(), new ArrayList<>(),
                    new ArrayList<>(Arrays.asList(validPersonNotInGroup))).execute(modelStub);
            fail(COMMAND_FAIL_FAILED_MESSAGE);
        } catch (CommandException commandException) {
            assertEquals(String.format(MESSAGE_ADDEXPENSECOMMAND_NOT_PART_OF_GROUP, validPerson),
                    commandException.getMessage());
        }
    }

    @Test
    public void execute_invalidSplitExpenseCost_throwsCommandException() throws Exception {
        ModelStubAcceptingExpenseAdded modelStub = new ModelStubAcceptingExpenseAdded();
        Person validPerson = new PersonBuilder().build();
        Person validPersonBob = BOB;
        Group validGroup = new GroupBuilder().build();
        Expense validExpense = new ExpenseBuilder().withCost("0").build();
        Expense validIndividualExpense = new ExpenseBuilder().withCost("1").build();
        validGroup = validGroup.addMember(validPerson);
        validGroup = validGroup.addMember(validPersonBob);
        modelStub.addGroup(validGroup);
        GroupName groupName = validGroup.getGroupName();

        try {
            CommandResult commandResult = new AddExpenseCommand(validExpense.getPayer(), validExpense.getCost(),
                    validExpense.getDescription(), groupName, Arrays.asList(validPersonBob),
                    Arrays.asList(validIndividualExpense.getCost()),
                    new ArrayList<>()).execute(modelStub);
            fail(COMMAND_FAIL_FAILED_MESSAGE);
        } catch (CommandException commandException) {
            assertEquals(String.format(MESSAGE_ADDEXPENSECOMMAND_COST_ZERO_OR_LESS, validPerson),
                    commandException.getMessage());
        }
    }

    @Test
    public void execute_expenseCostOverMax_throwsCommandException() throws Exception {
        ModelStubAcceptingExpenseAdded modelStub = new ModelStubAcceptingExpenseAdded();
        Person validPerson = new PersonBuilder().build();
        Group validGroup = new GroupBuilder().build();
        Expense validExpense = new ExpenseBuilder().withCost("50").build();
        Expense maxExpense = new ExpenseBuilder().withCost("1000000000").build();
        validGroup = validGroup.addMember(validPerson);
        validGroup = validGroup.addExpense(maxExpense);
        modelStub.addGroup(validGroup);
        modelStub.addExpense(maxExpense, validGroup);
        GroupName groupName = validGroup.getGroupName();

        try {
            CommandResult commandResult = new AddExpenseCommand(validExpense.getPayer(), validExpense.getCost(),
                    validExpense.getDescription(), groupName, new ArrayList<>(), new ArrayList<>(),
                    new ArrayList<>()).execute(modelStub);
            fail(COMMAND_FAIL_FAILED_MESSAGE);
        } catch (CommandException commandException) {
            assertEquals(String.format(MESSAGE_ADDEXPENSECOMMAND_COST_MORE_THAN_MAX),
                    commandException.getMessage());
        }
    }

    @Test
    public void execute_withSelfPayees_addSuccessfulWithReducedCost() throws Exception {
        ModelStubAcceptingExpenseAdded modelStub = new ModelStubAcceptingExpenseAdded();
        Person validPerson = new PersonBuilder().build();
        Person validSelfPayee = new PersonBuilder().withName("nic").build();
        Group validGroup = new GroupBuilder().build();
        Expense validExpense = new ExpenseBuilder().build();
        validGroup = validGroup.addMember(validPerson);
        validGroup = validGroup.addMember(validSelfPayee);
        modelStub.addGroup(validGroup);
        GroupName groupName = validGroup.getGroupName();
        Cost costOfSelfPayment = new Cost("30");

        AddExpenseCommand addExpenseCommand = new AddExpenseCommand(validExpense.getPayer(), validExpense.getCost(),
                validExpense.getDescription(), groupName, Arrays.asList(validSelfPayee),
                Arrays.asList(costOfSelfPayment), new ArrayList<>());

        CommandResult commandResult = addExpenseCommand.execute(modelStub);

        Cost actualFinalCost = addExpenseCommand.getTotalCost();
        Cost expectedFinalCost = new Cost("50");

        assertEquals(String.format(actualFinalCost.toString()),
                expectedFinalCost.toString());
    }

    @Test
    public void execute_individualPayersNotInGroup_throwsCommandException() throws Exception {
        ModelStubAcceptingExpenseAdded modelStub = new ModelStubAcceptingExpenseAdded();
        Person validPerson = new PersonBuilder().build();
        Person validSelfPayee = new PersonBuilder().withName("nic").build();
        Group validGroup = new GroupBuilder().build();
        Expense validExpense = new ExpenseBuilder().build();
        validGroup = validGroup.addMember(validPerson);
        modelStub.addGroup(validGroup);
        GroupName groupName = validGroup.getGroupName();
        Cost costOfSelfPayment = new Cost("30");

        try {
            CommandResult commandResult = new AddExpenseCommand(validExpense.getPayer(), validExpense.getCost(),
                    validExpense.getDescription(), groupName, Arrays.asList(validSelfPayee),
                    Arrays.asList(costOfSelfPayment), new ArrayList<>()).execute(modelStub);
            fail(COMMAND_FAIL_FAILED_MESSAGE);
        } catch (CommandException exception) {
            assertEquals(String.format(MESSAGE_ADDEXPENSECOMMAND_NOT_PART_OF_GROUP), exception.getMessage());
        }
    }

    @Test
    public void execute_groupDoesNotExist_throwsCommandException() throws Exception {
        ModelStubAcceptingExpenseAdded modelStub = new ModelStubAcceptingExpenseAdded();
        Person validPerson = new PersonBuilder().build();
        Group validGroup = new GroupBuilder().build();
        Expense validExpense = new ExpenseBuilder().build();
        modelStub.addPerson(validPerson);

        try {
            CommandResult commandResult = new AddExpenseCommand(validExpense.getPayer(), validExpense.getCost(),
                    validExpense.getDescription(), validGroup.getGroupName(), new ArrayList<>(), new ArrayList<>(),
                    new ArrayList<>()).execute(modelStub);
            fail(COMMAND_FAIL_FAILED_MESSAGE);
        } catch (CommandException commandException) {
            assertEquals(String.format(MESSAGE_ADDEXPENSECOMMAND_GROUP_DOES_NOT_EXIST),
                    commandException.getMessage());
        }
    }

    @Test
    public void execute_individualPayerExcluded_throwsCommandException() {
        ModelStubAcceptingExpenseAdded modelStub = new ModelStubAcceptingExpenseAdded();
        Person validPerson = new PersonBuilder().build();
        Person validIndividualPayer = new PersonBuilder().withName("Bob").build();
        Group validGroup = new GroupBuilder().build();
        Expense validExpense = new ExpenseBuilder().build();
        Expense validIndividualExpense = new ExpenseBuilder().withCost("20").build();
        validGroup = validGroup.addMember(validPerson);
        validGroup = validGroup.addMember(validIndividualPayer);
        modelStub.addGroup(validGroup);
        try {
            CommandResult commandResult = new AddExpenseCommand(validExpense.getPayer(), validExpense.getCost(),
                    validExpense.getDescription(), validGroup.getGroupName(),
                    new ArrayList<>(Arrays.asList(validIndividualPayer)),
                    new ArrayList<>(Arrays.asList(validIndividualExpense.getCost())),
                    new ArrayList<>(Arrays.asList(validIndividualPayer))).execute(modelStub);
            fail(COMMAND_FAIL_FAILED_MESSAGE);
        } catch (CommandException commandException) {
            assertEquals(String.format(MESSAGE_ADDEXPENSECOMMAND_CANNOT_ADD_EXCLUDED_MEMBER, validPerson),
                    commandException.getMessage());
        }
    }

    @Test
    public void equals() {
        Expense alicePayer = new ExpenseBuilder().withPayer(ALICE).build();
        Expense bobPayer = new ExpenseBuilder().withPayer(BOB).build();
        GroupName groupName = new GroupName("arcade");
        AddExpenseCommand addAlicePayerCommand = new AddExpenseCommand(alicePayer.getPayer(), alicePayer.getCost(),
                alicePayer.getDescription(), groupName, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        AddExpenseCommand addBobPayerCommand = new AddExpenseCommand(bobPayer.getPayer(), alicePayer.getCost(),
                alicePayer.getDescription(), groupName, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        // same object -> returns true
        assertTrue(addAlicePayerCommand.equals(addAlicePayerCommand));

        // same values -> returns true
        AddExpenseCommand addAlicePayerCommandCopy = new AddExpenseCommand(alicePayer.getPayer(), alicePayer.getCost(),
                alicePayer.getDescription(), groupName, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        assertTrue(addAlicePayerCommandCopy.equals(addAlicePayerCommandCopy));

        // different types -> returns false
        assertFalse(addAlicePayerCommand.equals(1));

        // null -> returns false
        assertFalse(addAlicePayerCommand.equals(null));

        // different person -> returns false
        assertFalse(addAlicePayerCommand.equals(addBobPayerCommand));

        // different identifying data
        AddExpenseCommand addAlicePayerCommandDifferentPayer =
                new AddExpenseCommand(bobPayer.getPayer(), alicePayer.getCost(),
                alicePayer.getDescription(), groupName, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        AddExpenseCommand addAlicePayerCommandDifferentCost =
                new AddExpenseCommand(alicePayer.getPayer(), new Cost(1),
                        alicePayer.getDescription(), groupName, new ArrayList<>(),
                        new ArrayList<>(), new ArrayList<>());
        AddExpenseCommand addAlicePayerCommandDifferentDescription =
                new AddExpenseCommand(alicePayer.getPayer(), alicePayer.getCost(),
                        new Description("ok"), groupName, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        AddExpenseCommand addAlicePayerCommandDifferentGroupName =
                new AddExpenseCommand(alicePayer.getPayer(), alicePayer.getCost(),
                        bobPayer.getDescription(), new GroupName("not arcade"),
                        new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        assertFalse(addAlicePayerCommand.equals(addAlicePayerCommandDifferentPayer));
        assertFalse(addAlicePayerCommand.equals(addAlicePayerCommandDifferentCost));
        assertFalse(addAlicePayerCommand.equals(addAlicePayerCommandDifferentDescription));
        assertFalse(addAlicePayerCommand.equals(addAlicePayerCommandDifferentGroupName));
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
        public Path getAweFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAweFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAwe(ReadOnlyAwe newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAwe getAwe() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Group getActiveGroupFromAwe() throws CommandException {
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
         * {@code versionedAwe}
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
            return null;
        }

        @Override
        public ReadOnlyAwe getAwe() {
            return new Awe();
        }
    }

}
