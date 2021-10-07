package seedu.awe.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.awe.logic.parser.CliSyntax.PREFIX_GROUP_NAME;

import seedu.awe.model.AddressBook;
import seedu.awe.model.Model;
import seedu.awe.model.ReadOnlyAddressBook;
import seedu.awe.model.expense.Expense;
import seedu.awe.model.group.Group;
import seedu.awe.model.group.GroupName;
import seedu.awe.model.group.exceptions.GroupNotFoundException;
import seedu.awe.model.person.Person;
import seedu.awe.ui.ViewPanel;

import java.util.ArrayList;
import java.util.List;

/**
 * Lists all expenses of the group to the user.
 */
public class ListExpensesCommand extends Command {

    public static final String COMMAND_WORD = "expenses";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists expenses of a group. "
            + "Parameters: "
            + PREFIX_GROUP_NAME + "GROUP NAME ";

    public static final String MESSAGE_SUCCESS = "Listed all expenses";

    private final GroupName groupName;

    public ListExpensesCommand(GroupName groupName) {
        requireNonNull(groupName);
        this.groupName = groupName;

    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setExpenses(groupName);
        return new CommandResult(MESSAGE_SUCCESS, false, false, false, false, true);
    }
}

