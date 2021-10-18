package seedu.awe.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.awe.logic.commands.exceptions.CommandException;
import seedu.awe.model.Model;
import seedu.awe.model.group.Group;


public class ListTransactionSummaryCommand extends Command {
    public static final String COMMAND_WORD = "transactionsummary";

    public static final String MESSAGE_USAGE = "transactionsummary gn/GROUP_NAME";
    public static final String MESSAGE_GROUP_NOT_FOUND = "The specified group does not exists.";
    public static final String MESSAGE_SUCCESS = "Expenses for individual group members are listed.";

    private final Group group;

    public ListTransactionSummaryCommand(Group group) {
        this.group = group;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(group);

        if (!model.hasGroup(group)) {
            throw new CommandException(MESSAGE_GROUP_NOT_FOUND);
        }

        Group group = model.getAddressBook().getGroupByName(this.group.getGroupName());

        // LOGIC GOES HERE

        return new CommandResult(MESSAGE_SUCCESS, false, false, false, false, false);
    }
}
