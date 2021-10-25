package seedu.awe.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.awe.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import seedu.awe.commons.core.Messages;
import seedu.awe.commons.core.index.Index;
import seedu.awe.logic.commands.exceptions.CommandException;
import seedu.awe.model.Model;
import seedu.awe.model.expense.Expense;
import seedu.awe.model.group.Group;
import seedu.awe.ui.MainWindow;
import seedu.awe.ui.UiView;

public class DeleteExpenseCommand extends Command {

    public static final String COMMAND_WORD = "deleteexpense";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the expense identified by the index number used in the displayed expense list.\n"
            + "Parameters: INDEX (must be a positive integer) within range of index numbers seen on screen.\n"
            + "Example: " + COMMAND_WORD + " 1";
    public static final String MESSAGE_SUCCESS = "Expense %s successfully deleted!";
    private Index index;

    /**
     * Constructor for Delete Expense Command.
     * @param index Position of expense to be deleted in observable list.
     */
    public DeleteExpenseCommand(Index index) {
        requireAllNonNull(index);
        this.index = index;
    }

    public Index getIndex() {
        return index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Expense> expenseList = model.getExpenses();

        if (MainWindow.getViewEnum() != UiView.EXPENSE_PAGE) {
            throw new CommandException(Messages.MESSAGE_EXPENSE_CANNOT_BE_DELETED);
        }

        if (index.getZeroBased() >= expenseList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EXPENSE_DISPLAYED_INDEX);
        }

        Expense expenseToDelete = expenseList.get(index.getZeroBased());
        Group group = model.getActiveGroupFromAddressBook();
        Group newGroup = group.deleteExpense(expenseToDelete);
        model.setGroup(group, newGroup);
        model.deleteExpense(expenseToDelete, newGroup);
        String expenseToDeleteDescriptionString = expenseToDelete.getDescription().getFullDescription();
        return new CommandResult(String.format(MESSAGE_SUCCESS, expenseToDeleteDescriptionString), false,
                false, false, false, true,
                false, false);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (!(other instanceof DeleteExpenseCommand)) {
            return false;
        } else {
            DeleteExpenseCommand otherCommand = (DeleteExpenseCommand) other;
            return index.equals(otherCommand.getIndex());
        }
    }
}
