package seedu.address.logic.commands;

import static seedu.address.testutil.TypicalGroups.getTypicalAddressBook;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.group.exceptions.DuplicateGroupException;

public class CreateGroupCommandTest {
    private Model model;

    public CreateGroupCommandTest() throws DuplicateGroupException {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }
}
