package seedu.awe.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.awe.commons.core.Messages.MESSAGE_LISTTRANSACTIONSUMMARYCOMMAND_GROUP_NOT_FOUND;
import static seedu.awe.commons.core.Messages.MESSAGE_LISTTRANSACTIONSUMMARYCOMMAND_SUCCESS;

import java.util.HashMap;

import seedu.awe.logic.commands.exceptions.CommandException;
import seedu.awe.model.Model;
import seedu.awe.model.expense.Cost;
import seedu.awe.model.group.Group;
import seedu.awe.model.person.Person;

public class ListTransactionSummaryCommand extends Command {
    public static final String COMMAND_WORD = "transactionsummary";

    private final Group group;

    /**
     * Constructor for ListTransactionSummaryCommand
     *
     * @param group Group to show transaction summary
     */
    public ListTransactionSummaryCommand(Group group) {
        requireNonNull(group);
        this.group = group;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(group);

        if (!model.hasGroup(group)) {
            throw new CommandException(MESSAGE_LISTTRANSACTIONSUMMARYCOMMAND_GROUP_NOT_FOUND);
        }

        Group group = model.getAwe().getGroupByName(this.group.getGroupName());
        HashMap<Person, Cost> summary = group.getSplitExpenses();

        model.setTransactionSummary(summary);

        return new CommandResult(MESSAGE_LISTTRANSACTIONSUMMARYCOMMAND_SUCCESS, false, false,
                false, false, false,
                true, false);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ListTransactionSummaryCommand)) { // instanceof handles nulls
            return false;
        }

        return group.equals(((ListTransactionSummaryCommand) other).group); // state check
    }
}
