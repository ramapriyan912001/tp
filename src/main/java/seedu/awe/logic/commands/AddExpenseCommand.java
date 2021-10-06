package seedu.awe.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.awe.logic.parser.CliSyntax.PREFIX_COST;
import static seedu.awe.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.awe.logic.parser.CliSyntax.PREFIX_GROUP_NAME;

import java.util.List;

import seedu.awe.commons.core.Messages;
import seedu.awe.commons.core.index.Index;
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

    public static final String COMMAND_WORD = "expense";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an expense to a group. "
            + "Parameters: "
            + PREFIX_GROUP_NAME + "GROUP NAME "
            + PREFIX_COST + "COST "
            + PREFIX_DESCRIPTION + "DESCRIPTION";
    public static final String MESSAGE_ARGUMENTS = "Index:";
    public static final String MESSAGE_SUCCESS = "Expense added!";

    private final Index index;
    private final GroupName groupName;
    private final Cost cost;
    private final Description description;

    /**
     * Creates a AddExpenseCommand from the specified Person {@code Index} for the specified {@code Group}
     *
     * @param index of the person in the filtered person list to add an expense to
     * @param groupName of the group to have the expense added to
     * @param cost of the expense
     * @param description of the expense
     */
    public AddExpenseCommand(Index index, GroupName groupName, Cost cost, Description description) {
        requireNonNull(index);
        requireNonNull(groupName);
        requireNonNull(cost);
        requireNonNull(description);

        this.index = index;
        this.groupName = groupName;
        this.cost = cost;
        this.description = description;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException, DuplicateGroupException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person payer = lastShownList.get(index.getZeroBased());
        Group group = model.getGroupByName(groupName);
        Expense expense = new Expense(payer, cost, description);
        Group newGroup = group.addExpense(expense);
        model.setGroup(group, newGroup);
        model.addExpense(expense);

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
            return index.equals(otherCommand.index)
                    && groupName.equals(otherCommand.groupName)
                    && cost.equals(otherCommand.cost)
                    && description.equals(otherCommand.description);
        }
    }
}
