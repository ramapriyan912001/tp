package seedu.awe.ui.listener;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import seedu.awe.ui.MainWindow;
import seedu.awe.ui.UiView;

public class ContactButtonListener implements EventHandler<ActionEvent> {

    private MainWindow mainWindow;

    public ContactButtonListener(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    /**
     * Invoked when a specific event of the type for which this handler is
     * registered happens.
     *
     * @param event the event which occurred
     */
    @Override
    public void handle(ActionEvent event) {
        mainWindow.toggleView(UiView.CONTACT_PAGE);
    }
}
