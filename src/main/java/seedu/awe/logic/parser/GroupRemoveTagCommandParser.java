package seedu.awe.logic.parser;
import static seedu.awe.commons.core.Messages.MESSAGE_GROUPREMOVETAGCOMMAND_USAGE;
import static seedu.awe.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.awe.commons.core.Messages.MESSAGE_NONEXISTENT_GROUP;
import static seedu.awe.logic.parser.CliSyntax.PREFIX_GROUP_NAME;
import static seedu.awe.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Objects;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.awe.logic.commands.GroupRemoveTagCommand;
import seedu.awe.logic.parser.exceptions.ParseException;
import seedu.awe.model.Model;
import seedu.awe.model.ReadOnlyAddressBook;
import seedu.awe.model.group.Group;
import seedu.awe.model.group.GroupName;
import seedu.awe.model.tag.Tag;

public class GroupRemoveTagCommandParser implements Parser<GroupRemoveTagCommand> {
    private ObservableList<Group> allGroups;

    /**
     * Creates new GroupRemoveTagCommandParser object.
     *
     * @param model Model object passed into constructor to provide list of groups.
     */
    public GroupRemoveTagCommandParser(Model model) {
        ReadOnlyAddressBook addressBook = model.getAddressBook();
        this.allGroups = addressBook.getGroupList();
    }
    /**
     * Returns GroupRemoveTagCommand based on user input.
     *
     * @param args User input into contact list.
     * @return GroupRemoveTagCommand object to represent command to be executed.
     * @throws ParseException If user input is incorrectly formatted.
     */
    @Override
    public GroupRemoveTagCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_GROUP_NAME, PREFIX_TAG);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_GROUP_NAME, PREFIX_TAG)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MESSAGE_GROUPREMOVETAGCOMMAND_USAGE));
        }

        GroupName groupName = ParserUtil.parseGroupName(argMultimap.getValue(PREFIX_GROUP_NAME).get());
        if (!ParserUtil.findExistingGroupName(groupName, allGroups)) {
            throw new ParseException(String.format(MESSAGE_NONEXISTENT_GROUP, groupName));
        }

        Set<Tag> tagsToBeRemoved = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        boolean isValidCommand = true;
        if (Objects.isNull(tagsToBeRemoved)) {
            isValidCommand = false;
        }

        return new GroupRemoveTagCommand(groupName, tagsToBeRemoved, isValidCommand);
    }
}
