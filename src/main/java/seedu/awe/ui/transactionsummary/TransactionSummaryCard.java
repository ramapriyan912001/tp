package seedu.awe.ui.transactionsummary;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.awe.model.transactionsummary.TransactionSummary;
import seedu.awe.ui.UiPart;

public class TransactionSummaryCard extends UiPart<Region> {

    private static final String FXML = "TransactionSummaryListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final TransactionSummary transactionSummary;

    @javafx.fxml.FXML
    private HBox cardPane;
    @FXML
    private Label payer;
    @FXML
    private Label id;
    @FXML
    private Label cost;

    /**
     * Creates an {@code TransactionSummaryCard} with the given {@code TransactionSummary} and index to display.
     */
    public TransactionSummaryCard(TransactionSummary transactionSummary, int displayedIndex) {
        super(FXML);
        this.transactionSummary = transactionSummary;
        id.setText(displayedIndex + ".");
        payer.setText(transactionSummary.getPerson().getName().toString());
        cost.setText(transactionSummary.getCost().toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TransactionSummaryCard)) {
            return false;
        }

        // state check
        TransactionSummaryCard card = (TransactionSummaryCard) other;
        return id.getText().equals(card.id.getText())
                   && transactionSummary.equals(card.transactionSummary);
    }
}
