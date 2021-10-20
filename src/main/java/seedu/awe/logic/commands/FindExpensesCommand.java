
package seedu.awe.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.awe.logic.parser.CliSyntax.PREFIX_GROUP_NAME;

import seedu.awe.commons.core.Messages;
import seedu.awe.logic.commands.exceptions.CommandException;
import seedu.awe.model.Model;
import seedu.awe.model.expense.DescriptionContainsKeywordsPredicate;
import seedu.awe.model.group.Group;
import seedu.awe.model.group.GroupName;
import seedu.awe.model.group.exceptions.GroupNotFoundException;

/**
 * Finds and lists all expenses in a group whose description contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindExpensesCommand extends Command {

    public static final String COMMAND_WORD = "findexpenses";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all expenses within the specified group "
            + "which description contains any of the specified keywords (case-insensitive) and displays them as a"
            + "list with index numbers.\n"
            + "Parameters:"
            + "KEYWORD [MORE_KEYWORDS]...\n"
            + PREFIX_GROUP_NAME + " GROUP NAME"
            + " Example: " + COMMAND_WORD + "pizza pasta " + PREFIX_GROUP_NAME + " london";

    public static final String MESSAGE_GROUP_NOT_FOUND = "The specified group does not exists.";

    private final GroupName groupName;
    private final DescriptionContainsKeywordsPredicate predicate;

    /**
     * A public constructor to initialise the group name and predicate to
     * the given ones.
     *
     * @param groupName Name of the group to find expenses from.
     * @param predicate The predicate to filter the expenses list.
     */
    public FindExpensesCommand(GroupName groupName, DescriptionContainsKeywordsPredicate predicate) {
        this.groupName = groupName;
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        try {
            Group group = model.getGroupByName(groupName);
            model.setExpenses(group);
            model.updateFilteredExpenseList(predicate);
            return new CommandResult(
                    String.format(Messages.MESSAGE_EXPENSES_LISTED_OVERVIEW, model.getExpenses().size()),
                    false, false, false, false, true, false);
        } catch (GroupNotFoundException e) {
            throw new CommandException(MESSAGE_GROUP_NOT_FOUND);
        }

    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof FindExpensesCommand)) { // instanceof handles nulls
            return false;
        }

        return predicate.equals(((FindExpensesCommand) other).predicate); // state check
    }
}
