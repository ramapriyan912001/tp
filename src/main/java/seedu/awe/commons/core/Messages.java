package seedu.awe.commons.core;
import static seedu.awe.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.awe.logic.parser.CliSyntax.PREFIX_COST;
import static seedu.awe.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.awe.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.awe.logic.parser.CliSyntax.PREFIX_EXCLUDE;
import static seedu.awe.logic.parser.CliSyntax.PREFIX_GROUP_NAME;
import static seedu.awe.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.awe.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.awe.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.awe.logic.commands.AddContactCommand;
import seedu.awe.logic.commands.AddExpenseCommand;
import seedu.awe.logic.commands.CalculatePaymentsCommand;
import seedu.awe.logic.commands.DeleteContactCommand;
import seedu.awe.logic.commands.DeleteExpenseCommand;
import seedu.awe.logic.commands.DeleteGroupCommand;
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

    //messages for contact related commands
    public static final String MESSAGE_ADDCONTACTCOMMAND_USAGE = AddContactCommand.COMMAND_WORD
            + ": Adds a person to the awe book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + AddContactCommand.COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";
    public static final String MESSAGE_ADDCONTACTCOMMAND_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_ADDCONTACTCOMMAND_DUPLICATE = "This person already exists in the awe book";


    public static final String MESSAGE_DELETECONTACTCOMMAND_USAGE = DeleteContactCommand.COMMAND_WORD
            + ": Deletes the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + DeleteContactCommand.COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETECONTACTCOMMAND_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";


    //messages for group related commands
    public static final String MESSAGE_CREATEGROUPCOMMAND_SUCCESS = "New group created";
    public static final String MESSAGE_CREATEGROUPCOMMAND_ERROR =
            "Group not created. Be sure to use the exact names of group members";
    public static final String MESSAGE_CREATEGROUPCOMMAND_DUPLICATE_GROUP = "This group already exists";
    public static final String MESSAGE_CREATEGROUPCOMMAND_USAGE =
            "creategroup gn/GROUPNAME n/NAME1 n/[OPTIONAL NAME2]...";
    public static final String MESSAGE_CREATEGROUPCOMMAND_EMPTY_GROUP = "Group requires at least 1 member. \n%1$s\n%s";
    public static final String MESSAGE_CREATEGROUPCOMMAND_INVALID_NAMES = "None of the names are in your contact book.";


    public static final String MESSAGE_DELETEGROUPCOMMAND_USAGE = DeleteGroupCommand.COMMAND_WORD
            + ": deletes a group from the awe book. "
            + "Parameters: "
            + PREFIX_GROUP_NAME + "GROUP_NAME\n"
            + "Example: " + DeleteGroupCommand.COMMAND_WORD + " "
            + PREFIX_GROUP_NAME + "Bali";
    public static final String MESSAGE_DELETEGROUPCOMMAND_SUCCESS = "Group %s with %d member(s) deleted";
    public static final String MESSAGE_DELETEGROUPCOMMAND_GROUP_DOES_NOT_EXIST =
            "This group does not exist in the awe book";


    //messages for expenses related commands
    public static final String MESSAGE_ADDEXPENSECOMMAND_USAGE = AddExpenseCommand.COMMAND_WORD
            + ": Adds an expense to a group. "
            + "Parameters: "
            + PREFIX_NAME + "PAYER NAME "
            + PREFIX_GROUP_NAME + "GROUP NAME "
            + PREFIX_COST + "COST "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + "[" + PREFIX_NAME + "PAYEE NAME" + "] "
            + "[" + PREFIX_COST + "PAYEE EXPENSE" + "]"
            + "[" + PREFIX_EXCLUDE + "EXCLUDED PERSON" + "]";
    public static final String MESSAGE_ADDEXPENSECOMMAND_SUCCESS = "Expense added!";
    public static final String MESSAGE_ADDEXPENSECOMMAND_NOT_PART_OF_GROUP =
            "The person isn't part of the specified group!";
    public static final String MESSAGE_ADDEXPENSECOMMAND_ALL_MEMBERS_EXCLUDED =
            "You can't exclude every member of the group!";
    public static final String MESSAGE_ADDEXPENSECOMMAND_COST_ZERO_OR_LESS =
            "The cost of this expense is zero or less!";
    public static final String MESSAGE_ADDEXPENSECOMMAND_CANNOT_ADD_EXCLUDED_MEMBER =
            "You tried to add an expense for an excluded member!";


    public static final String MESSAGE_DELETEEXPENSECOMMAND_USAGE = DeleteExpenseCommand.COMMAND_WORD
            + ": Deletes the expense identified by the index number used in the displayed expense list.\n"
            + "Parameters: INDEX (must be a positive integer) within range of index numbers seen on screen.\n"
            + "Example: " + DeleteExpenseCommand.COMMAND_WORD + " 1";
    public static final String MESSAGE_DELETEEXPENSECOMMAND_SUCCESS = "Expense %s successfully deleted!";
    public static final String MESSAGE_DELETEEXPENSECOMMAND_CANNOT_BE_DELETED =
            "Expense cannot be deleted unless you are viewing "
                    + "the expenses of a group. "
                    + "To delete an expense, first enter the " + FindExpensesCommand.COMMAND_WORD + " or "
                    + ListExpensesCommand.COMMAND_WORD + " command.";


    //messages for calculatepayment related commands
    public static final String MESSAGE_CALCULATEPAYMENTSCOMMAND_SUCCESS =
            "Payments to be made between group members are listed.";
    public static final String MESSAGE_CALCULATEPAYMENTSCOMMAND_USAGE = CalculatePaymentsCommand.COMMAND_WORD
            + ": Calculates the payments to be made between group members to settle debts.\n"
            + "Parameters: "
            + PREFIX_GROUP_NAME + "GROUP_NAME\n"
            + "Example: " + CalculatePaymentsCommand.COMMAND_WORD + " "
            + PREFIX_GROUP_NAME + "Bali";
    public static final String MESSAGE_CALCULATEPAYMENTSCOMMAND_PAYMENTS_EMPTY =
            "There are no payments to be made!";
    public static final String MESSAGE_CALCULATEPAYMENTSCOMMAND_GROUP_NOT_FOUND =
            "The specified group does not exists.";

    //messages for miscellaneous commands
    public static final String MESSAGE_CLEARCOMMAND_SUCCESS = "Address book has been cleared!";









}
