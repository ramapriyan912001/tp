package seedu.awe.commons.core;

import seedu.awe.logic.commands.FindExpensesCommand;
import seedu.awe.logic.commands.ListExpensesCommand;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command.";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid.";
    public static final String MESSAGE_INVALID_EXPENSE_DISPLAYED_INDEX = "The expense index provided is invalid.";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_GROUPS_LISTED_OVERVIEW = "%1$d groups listed!";
    public static final String MESSAGE_EXPENSES_LISTED_OVERVIEW = "%1$d expenses listed!";
    public static final String MESSAGE_EXPENSE_CANNOT_BE_DELETED = "Expense cannot be deleted unless you are viewing "
            + "the expenses of a group. "
            + "To delete an expense, first enter the " + FindExpensesCommand.COMMAND_WORD + " or "
            + ListExpensesCommand.COMMAND_WORD + " command.";
}
