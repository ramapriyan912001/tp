package seedu.awe.logic.parser;

import seedu.awe.commons.core.index.Index;
import seedu.awe.commons.exceptions.IllegalValueException;
import seedu.awe.logic.commands.AddExpenseCommand;
import seedu.awe.logic.parser.exceptions.ParseException;
import seedu.awe.model.expense.Cost;
import seedu.awe.model.expense.Description;
import seedu.awe.model.group.GroupName;

import static java.util.Objects.requireNonNull;
import static seedu.awe.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.awe.logic.parser.CliSyntax.PREFIX_COST;
import static seedu.awe.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.awe.logic.parser.CliSyntax.PREFIX_GROUP_NAME;

public class AddExpenseCommandParser implements Parser<AddExpenseCommand> {
    public AddExpenseCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_GROUP_NAME, PREFIX_COST, PREFIX_DESCRIPTION);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddExpenseCommand.MESSAGE_USAGE), ive);
        }

        GroupName groupName = ParserUtil.parseGroupName((argMultimap.getValue(PREFIX_GROUP_NAME)).orElse(""));
        Cost cost = ParserUtil.parseCost(argMultimap.getValue(PREFIX_COST).orElse(""));
        Description description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).orElse(""));

        return new AddExpenseCommand(index, groupName, cost, description);
    }
}
