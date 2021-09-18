package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.person.Person;

import java.util.ArrayList;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

public class CreateGroupCommand extends Command {
    public static final String COMMAND_WORD = "creategroup";
    public static final String MESSAGE_SUCCESS = "New group created";
    public static final String MESSAGE_ERROR = "Group not created. Be sure to use the exact names of group members";
    public static final String MESSAGE_DUPLICATE_GROUP = "This group already exists";

    private final ArrayList<Person> members;
    private String groupName;
    private final boolean validCommand;

    /**
     * Creates a CreateGroupCommand to create the specified {@code Group}
     */
    public CreateGroupCommand(String name, ArrayList<Person> members, boolean validCommand) {
        this.groupName = name;
        this.members = members;
        this.validCommand = validCommand;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!validCommand) {
            return new CommandResult(MESSAGE_ERROR);
        }
        new Group(groupName, members);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
