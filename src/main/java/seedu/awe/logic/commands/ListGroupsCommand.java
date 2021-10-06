package seedu.awe.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.awe.model.Model;

/**
 * Lists all Groups in the awe book to the user.
 */
public class ListGroupsCommand extends Command {

    public static final String COMMAND_WORD = "groups";

    public static final String MESSAGE_SUCCESS = "Listed all groups";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        //model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS, false, false, true, false, false);
    }
}
