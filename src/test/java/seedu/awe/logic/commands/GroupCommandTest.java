package seedu.awe.logic.commands;

import static seedu.awe.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.awe.model.Model;
import seedu.awe.model.ModelManager;

public class GroupCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_showGroup_success() {
        CommandResult expectedCommandResult = new CommandResult(ListGroupsCommand.MESSAGE_SUCCESS,
                false, false, true, false);
        assertCommandSuccess(new ListGroupsCommand(), model, expectedCommandResult, expectedModel);
    }
}
