package seedu.awe.logic.commands;

import static seedu.awe.testutil.TypicalGroups.getTypicalAddressBook;

import seedu.awe.model.Model;
import seedu.awe.model.ModelManager;
import seedu.awe.model.UserPrefs;
import seedu.awe.model.group.exceptions.DuplicateGroupException;

public class DeleteGroupCommandTest {

    private Model model;

    public DeleteGroupCommandTest() throws DuplicateGroupException {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }
}
