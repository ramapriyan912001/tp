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

    private final Person payer;
    private final GroupName groupName;
    private final Cost cost;
    private final Description description;
    private final List<Person> selfPayees;
    private final List<Cost> selfCosts;
    private final List<Person> excluded;

    /**
     * Creates a AddExpenseCommand from the specified Person {@code Index} for the specified {@code Group}
     *
     * @param payer of the person in the filtered person list to add an expense to.
     * @param groupName of the group to have the expense added to.
     * @param cost of the expense.
     * @param description of the expense.
     * @param excluded List of persons to exclude from the expense.
     */
    public AddExpenseCommand(Person payer, GroupName groupName, Cost cost, Description description,
                             List<Person> selfPayees, List<Cost> selfCosts, List<Person> excluded) {
        requireNonNull(payer);
        requireNonNull(groupName);
        requireNonNull(cost);
        requireNonNull(description);
        requireAllNonNull(excluded);

        this.payer = payer;
        this.groupName = groupName;
        this.cost = cost;
        this.description = description;
        this.selfPayees = selfPayees;
        this.selfCosts = selfCosts;
        this.excluded = excluded;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException, DuplicateGroupException {
        requireNonNull(model);

        Group group = model.getGroupByName(groupName);
        Cost finalCost = cost;

        if (!group.isPartOfGroup(payer)) {
            return new CommandResult(MESSAGE_NOT_PART_OF_GROUP);
        }

        for (Person exclude : excluded) {
            if (!group.isPartOfGroup(exclude)) {
                return new CommandResult(MESSAGE_NOT_PART_OF_GROUP);
            }
        }

        if (group.getMembers().size() == excluded.size()) {
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

        Expense expense;
        if (excluded.size() > 0) {
            expense = new Expense(payer, finalCost, description, excluded);

        } else {
            expense = new Expense(payer, finalCost, description);
        }

        Group newGroup = group.addExpense(expense);
        model.setGroup(group, newGroup);

        return new CommandResult(MESSAGE_SUCCESS);
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
                    && groupName.equals(otherCommand.groupName)
                    && cost.equals(otherCommand.cost)
                    && description.equals(otherCommand.description);
        }
    }
}
