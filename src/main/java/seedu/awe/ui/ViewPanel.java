package seedu.awe.ui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.awe.logic.Logic;
import seedu.awe.ui.expense.ExpenseListPanel;
import seedu.awe.ui.group.GroupListPanel;
import seedu.awe.ui.person.PersonListPanel;


/**
 * The View Window. Handles the displaying of individual viewPanel.
 */
public class ViewPanel extends UiPart<Region> {

    private static final String FXML = "ViewPanel.fxml";

    private Logic logic;

    // Panels for toggling
    private PersonListPanel personListPanel;
    private GroupListPanel groupListPanel;
    private ExpenseListPanel expenseListPanel;

    @FXML
    private StackPane viewListPlaceholder;

    /**
     * Constructor for ViewPanel
     *
     * @param logic To get the respective list for ListPanel to render.
     */
    public ViewPanel(Logic logic) {
        super(FXML);
        this.logic = logic;

        fillInnerParts();
    }

    /**
     * Fills up all the placeholders of this window.
     */
    public void fillInnerParts() {
        personListPanel = new PersonListPanel(logic.getFilteredPersonList(), logic.getAddressBook());
        groupListPanel = new GroupListPanel(logic.getFilteredGroupList());
        expenseListPanel = new ExpenseListPanel(logic.getExpenses());

        toggleView(UiView.ADDRESS_BOOK);
    }

    /**
     * Switches different view for AddressBook, GroupsPage and ExpensesPage.
     *
     * @param uiView Page to be changed
     */
    public void toggleView(UiView uiView) {
        viewListPlaceholder.getChildren().clear();

        if (uiView == UiView.ADDRESS_BOOK) {
            viewListPlaceholder.getChildren().add(personListPanel.getRoot());
        } else if (uiView == UiView.GROUP_PAGE) {
            viewListPlaceholder.getChildren().add(groupListPanel.getRoot());
        } else if (uiView == UiView.EXPENSE_PAGE) {
            viewListPlaceholder.getChildren().add(expenseListPanel.getRoot());
        } else {
            throw new AssertionError("Toggle tab not found");
        }
    }
}
