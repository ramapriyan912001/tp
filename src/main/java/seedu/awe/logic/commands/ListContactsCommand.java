package seedu.awe.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.awe.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.awe.model.Model;

/**
 * Lists all persons in the awe book to the user.
 */
public class ListContactsCommand extends Command {

    public static final String COMMAND_WORD = "contacts";

    public static final String MESSAGE_SUCCESS = "Listed all contacts";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS, false, false, false,
                true, false,
                false, false);
    }
}
