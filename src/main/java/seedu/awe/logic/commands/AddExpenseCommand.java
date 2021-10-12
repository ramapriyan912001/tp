package seedu.awe.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.awe.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.awe.logic.parser.CliSyntax.PREFIX_COST;
import static seedu.awe.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.awe.logic.parser.CliSyntax.PREFIX_EXCLUDE;
import static seedu.awe.logic.parser.CliSyntax.PREFIX_GROUP_NAME;
import static seedu.awe.logic.parser.CliSyntax.PREFIX_NAME;

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

    private Expense expense;
    private final GroupName groupName;
    private final List<Person> selfPayees;
    private final List<Cost> selfCosts;

    /**
     * Creates a AddExpenseCommand from the specified Person {@code Index} for the specified {@code Group}
     *
     * @param expense Expense to be added.
     * @param groupName Name of group to add expense to.
     * @param selfPayees List of persons to exclude from the expense.
     * @param selfCosts List of costs to exclude from the expense.
     */
    public AddExpenseCommand(Expense expense, GroupName groupName,
                             List<Person> selfPayees, List<Cost> selfCosts) {
        requireNonNull(expense);
        requireNonNull(groupName);
        requireAllNonNull(selfPayees);
        requireAllNonNull(selfCosts);

        this.expense = expense;
        this.groupName = groupName;
        this.selfPayees = selfPayees;
        this.selfCosts = selfCosts;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException, DuplicateGroupException {
        requireNonNull(model);

        Group group = model.getGroupByName(groupName);
        Cost finalCost = expense.getCost();

        if (!group.isPartOfGroup(expense.getPayer())) {
            return new CommandResult(MESSAGE_NOT_PART_OF_GROUP);
        }

        for (Person exclude : expense.getExcluded()) {
            if (!group.isPartOfGroup(exclude)) {
                return new CommandResult(MESSAGE_NOT_PART_OF_GROUP);
            }
        }

        if (group.getMembers().size() == expense.getExcluded().size()) {
            return new CommandResult(MESSAGE_ALL_MEMBERS_EXCLUDED);
        }

        for (int i = 0; i < selfPayees.size(); i++) {
            if (selfPayees.get(i) == null || !group.isPartOfGroup(selfPayees.get(i))) {
                return new CommandResult(MESSAGE_NOT_PART_OF_GROUP);
            }
            finalCost = finalCost.subtract(selfCosts.get(i));
        }

        if (finalCost.cost <= 0) {
            return new CommandResult(MESSAGE_COST_ZERO_OR_LESS);
        }

        expense = expense.setCost(finalCost);

        Group newGroup = group.addExpense(expense);
        model.setGroup(group, newGroup);

        return new CommandResult(String.format(MESSAGE_SUCCESS, expense));
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
