package seedu.awe.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.awe.model.Model;

/**
 * Lists all expenses of the group to the user.
 */
public class ListExpensesCommand extends Command {

    public static final String COMMAND_WORD = "expenses";

    public static final String MESSAGE_SUCCESS = "Listed all expenses";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(MESSAGE_SUCCESS, false, false, false, false, true);
    }
}

