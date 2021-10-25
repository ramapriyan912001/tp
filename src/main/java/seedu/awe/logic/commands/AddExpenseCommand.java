package seedu.awe.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.awe.commons.core.Messages.MESSAGE_ADDEXPENSECOMMAND_ALL_MEMBERS_EXCLUDED;
import static seedu.awe.commons.core.Messages.MESSAGE_ADDEXPENSECOMMAND_CANNOT_ADD_EXCLUDED_MEMBER;
import static seedu.awe.commons.core.Messages.MESSAGE_ADDEXPENSECOMMAND_COST_ZERO_OR_LESS;
import static seedu.awe.commons.core.Messages.MESSAGE_ADDEXPENSECOMMAND_NOT_PART_OF_GROUP;
import static seedu.awe.commons.core.Messages.MESSAGE_ADDEXPENSECOMMAND_SUCCESS;
import static seedu.awe.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import seedu.awe.logic.commands.exceptions.CommandException;
import seedu.awe.model.Model;
import seedu.awe.model.expense.Cost;
import seedu.awe.model.expense.Expense;
import seedu.awe.model.group.Group;
import seedu.awe.model.group.GroupName;
import seedu.awe.model.group.exceptions.DuplicateGroupException;
import seedu.awe.model.person.Person;

/**
 * Adds an expense to a group.
 */
public class AddExpenseCommand extends Command {

    public static final String COMMAND_WORD = "addexpense";

    private Expense expense;
    private final GroupName groupName;
    private final List<Person> selfPayees;
    private final List<Cost> selfCosts;
    private final List<Person> excluded;

    /**
     * Creates a AddExpenseCommand from the specified Person {@code Index} for the specified {@code Group}
     *
     * @param expense Expense to be added.
     * @param groupName Name of group to add expense to.
     * @param selfPayees List of persons to exclude from the expense.
     * @param selfCosts List of costs to exclude from the expense.
     */
    public AddExpenseCommand(Expense expense, GroupName groupName,
                             List<Person> selfPayees, List<Cost> selfCosts, List<Person> excluded) {
        requireNonNull(expense);
        requireNonNull(groupName);
        requireAllNonNull(selfPayees);
        requireAllNonNull(selfCosts);

        this.expense = expense;
        this.groupName = groupName;
        this.selfPayees = selfPayees;
        this.selfCosts = selfCosts;
        this.excluded = excluded;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException, DuplicateGroupException {
        requireNonNull(model);

        Group group = model.getGroupByName(groupName);
        Cost finalCost = expense.getCost();

        if (!group.isPartOfGroup(expense.getPayer())) {
            return new CommandResult(MESSAGE_ADDEXPENSECOMMAND_NOT_PART_OF_GROUP);
        }

        for (Person exclude : excluded) {
            if (!group.isPartOfGroup(exclude)) {
                return new CommandResult(MESSAGE_ADDEXPENSECOMMAND_NOT_PART_OF_GROUP);
            }
        }

        if (group.getMembers().size() == excluded.size()) {
            return new CommandResult(MESSAGE_ADDEXPENSECOMMAND_ALL_MEMBERS_EXCLUDED);
        }

        return calculateExpense(group, expense.getPayer(), finalCost, model);
    }

    private CommandResult calculateExpense(Group group, Person payer, Cost finalCost, Model model) {
        HashMap<Person, Cost> paidByPayees = new HashMap<>();
        Cost paidAmount = new Cost(finalCost.getCost());
        for (int i = 0; i < selfPayees.size(); i++) {
            Person currentPayer = selfPayees.get(i);
            Cost indivCost = selfCosts.get(i);
            if (excluded.contains(currentPayer)) {
                return new CommandResult(MESSAGE_ADDEXPENSECOMMAND_CANNOT_ADD_EXCLUDED_MEMBER);
            }
            if (currentPayer == null || !group.isPartOfGroup(currentPayer)) {
                return new CommandResult(MESSAGE_ADDEXPENSECOMMAND_NOT_PART_OF_GROUP);
            }

            finalCost = finalCost.subtract(indivCost);
            paidByPayees.merge(currentPayer, indivCost, (original, toAdd) -> original.add(toAdd));
        }
        HashMap<Person, Cost> paidByPayers = group.getPaidByPayers();
        paidByPayers.merge(payer, paidAmount, (original, toAdd) -> original.add(paidAmount));

        if (finalCost.cost <= 0) {
            return new CommandResult(MESSAGE_ADDEXPENSECOMMAND_COST_ZERO_OR_LESS);
        }
        ArrayList<Person> groupMembers = removeExcludedFromGroup(group.getMembers());
        Cost toSplit = finalCost.divide(groupMembers.size());

        parseSplitExpenses(groupMembers, paidByPayees, toSplit);
        expense = expense.setIncluded(groupMembers);
        expense = expense.setIndividualExpenses(paidByPayees);
        Group newGroup = group.addExpenseWithIndivPayments(expense, paidByPayees);
        newGroup = new Group(newGroup.getGroupName(), newGroup.getMembers(),
                newGroup.getTags(), newGroup.getExpenses(), paidByPayers, newGroup.getPaidByPayees());
        model.setGroup(group, newGroup);
        model.addExpense(expense, newGroup);
        return new CommandResult(String.format(MESSAGE_ADDEXPENSECOMMAND_SUCCESS, expense));
    }

    private ArrayList<Person> removeExcludedFromGroup(ArrayList<Person> members) {
        ArrayList<Person> groupMembers = new ArrayList<>(members);
        for (Person toExclude : excluded) {
            groupMembers.remove(toExclude);
        }
        return groupMembers;
    }

    private void parseSplitExpenses(ArrayList<Person> groupMembers, HashMap<Person, Cost> paidByPayees, Cost toSplit) {
        for (int i = 0; i < groupMembers.size(); i++) {
            Person currentPayer = groupMembers.get(i);
            paidByPayees.merge(currentPayer, toSplit, (original, toAdd) -> original.add(toAdd));
        }
    }

    public Expense getExpense() {
        return expense;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (!(other instanceof AddExpenseCommand)) {
            return false;
        } else {
            AddExpenseCommand otherCommand = (AddExpenseCommand) other;
            return expense.equals(otherCommand.expense)
                    && groupName.equals(otherCommand.groupName);
        }
    }
}
