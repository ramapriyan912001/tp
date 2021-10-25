package seedu.awe.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.awe.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.awe.logic.parser.CliSyntax.PREFIX_COST;
import static seedu.awe.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.awe.logic.parser.CliSyntax.PREFIX_EXCLUDE;
import static seedu.awe.logic.parser.CliSyntax.PREFIX_GROUP_NAME;
import static seedu.awe.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import seedu.awe.logic.commands.exceptions.CommandException;
import seedu.awe.model.Model;
import seedu.awe.model.expense.Cost;
import seedu.awe.model.expense.Description;
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
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an expense to a group. "
            + "Parameters: "
            + PREFIX_NAME + "PAYER NAME "
            + PREFIX_GROUP_NAME + "GROUP NAME "
            + PREFIX_COST + "COST "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + "[" + PREFIX_NAME + "PAYEE NAME" + "] "
            + "[" + PREFIX_COST + "PAYEE EXPENSE" + "]"
            + "[" + PREFIX_EXCLUDE + "EXCLUDED PERSON" + "]";

    public static final String MESSAGE_SUCCESS = "Expense added!";
    public static final String MESSAGE_NOT_PART_OF_GROUP = "The person isn't part of the specified group!";
    public static final String MESSAGE_ALL_MEMBERS_EXCLUDED = "You can't exclude every member of the group!";
    public static final String MESSAGE_COST_ZERO_OR_LESS = "The cost of this expense is zero or less!";
    public static final String MESSAGE_CANNOT_ADD_EXCLUDED_MEMBER = "You tried to add an expense"
            + "for an excluded member!";

    private final Person payer;
    private final Cost totalCost;
    private final Description description;
    private final GroupName groupName;
    private final List<Person> selfPayees;
    private final List<Cost> selfCosts;
    private final List<Person> excluded;

    /**
     * Creates a AddExpenseCommand from the specified Person {@code Index} for the specified {@code Group}
     *
     * @param payer Person paying for the expense.
     * @param totalCost Total amount of money paid for the expense.
     * @param description Description for the expense
     * @param groupName Name of group to add expense to.
     * @param selfPayees List of persons to exclude from the expense.
     * @param selfCosts List of costs to exclude from the expense.
     * @param excluded Person excluded from the expense.
     */
    public AddExpenseCommand(Person payer, Cost totalCost, Description description, GroupName groupName,
                             List<Person> selfPayees, List<Cost> selfCosts, List<Person> excluded) {
        requireAllNonNull(payer, totalCost, description, groupName, selfPayees, selfCosts, excluded);

        this.payer = payer;
        this.totalCost = totalCost;
        this.description = description;
        this.groupName = groupName;
        this.selfPayees = selfPayees;
        this.selfCosts = selfCosts;
        this.excluded = excluded;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException, DuplicateGroupException {
        requireNonNull(model);

        Group group = model.getGroupByName(groupName);

        if (!group.isPartOfGroup(payer)) {
            throw new CommandException(MESSAGE_NOT_PART_OF_GROUP);
        }

        for (Person exclude : excluded) {
            if (!group.isPartOfGroup(exclude)) {
                throw new CommandException(MESSAGE_NOT_PART_OF_GROUP);
            }
        }

        if (group.getMembers().size() == excluded.size()) {
            throw new CommandException(MESSAGE_ALL_MEMBERS_EXCLUDED);
        }

        return calculateExpense(group, model);
    }

    private CommandResult calculateExpense(Group group, Model model) throws CommandException {
        HashMap<Person, Cost> individualPayement = new HashMap<>();
        Cost leftoverExpenseAfterIndividualExpense = totalCost;
        for (int i = 0; i < selfPayees.size(); i++) {
            Person currentPayer = selfPayees.get(i);
            if (excluded.contains(currentPayer)) {
                throw new CommandException(MESSAGE_CANNOT_ADD_EXCLUDED_MEMBER);
            }
            if (currentPayer == null || !group.isPartOfGroup(currentPayer)) {
                throw new CommandException(MESSAGE_NOT_PART_OF_GROUP);
            }
            individualPayement.merge(currentPayer, selfCosts.get(i), (original, toAdd) -> original.add(toAdd));
            leftoverExpenseAfterIndividualExpense = leftoverExpenseAfterIndividualExpense.subtract(selfCosts.get(i));
        }

        if (leftoverExpenseAfterIndividualExpense.cost <= 0) {
            throw new CommandException(MESSAGE_COST_ZERO_OR_LESS);
        }

        ArrayList<Person> groupMembers = removeExcludedFromGroup(group.getMembers());

        return createExpense(model, group, groupMembers, individualPayement);
    }

    private CommandResult createExpense(Model model, Group group, List<Person> groupMembers,
                                        HashMap<Person, Cost> individualPayement) {
        Expense newExpense = new Expense(payer, totalCost, description, groupMembers, individualPayement);

        Group newGroup = group.addExpense(newExpense);
        model.setGroup(group, newGroup);
        model.addExpense(newExpense, newGroup);

        return new CommandResult(String.format(MESSAGE_SUCCESS, newExpense));
    }

    private ArrayList<Person> removeExcludedFromGroup(ArrayList<Person> members) {
        ArrayList<Person> groupMembers = new ArrayList<>(members);
        for (Person toExclude : excluded) {
            groupMembers.remove(toExclude);
        }
        return groupMembers;
    }

    public Cost getTotalCost() {
        return totalCost;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (!(other instanceof AddExpenseCommand)) {
            return false;
        } else {
            AddExpenseCommand otherCommand = (AddExpenseCommand) other;
            return payer.equals(otherCommand.payer)
                    && totalCost.equals(otherCommand.totalCost)
                    && description.equals(otherCommand.description)
                    && groupName.equals(otherCommand.groupName);
        }
    }
}
