package seedu.awe.logic.commands;

import seedu.awe.model.Model;
import seedu.awe.testutil.ModelBuilder;
import seedu.awe.testutil.TypicalGroups;

public class CalculatePaymentsCommandTest {

    private Model model = new ModelBuilder().build();

    public void resetModel() {
        model = new ModelBuilder().build();
    }

    CalculatePaymentsCommand calculatePaymentsCommand = new CalculatePaymentsCommand(TypicalGroups.BALI);

}
