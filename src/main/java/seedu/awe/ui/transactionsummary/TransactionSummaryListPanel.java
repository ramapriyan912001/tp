package seedu.awe.ui.transactionsummary;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.awe.commons.core.LogsCenter;
import seedu.awe.model.transactionsummary.TransactionSummary;
import seedu.awe.ui.UiPart;

public class TransactionSummaryListPanel extends UiPart<Region> {

    private static final String FXML = "TransactionSummaryListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TransactionSummaryListPanel.class);

    @javafx.fxml.FXML
    private ListView<TransactionSummary> transactionSummaryListView;

    /**
     * Creates an {@code TransactionSummaryListPanel} with the given {@code ObservableList}.
     */
    public TransactionSummaryListPanel(ObservableList<TransactionSummary> transactionSummaryList) {
        super(FXML);
        transactionSummaryListView.setItems(transactionSummaryList);
        transactionSummaryListView.setCellFactory(listView -> new TransactionSummaryListViewCell());
    }

    /**
     * Custom {@code TransactionSummaryListCell} that displays the graphics of
     * a {@code Transaction} using a {@code TransactionSummaryCard}.
     */
    class TransactionSummaryListViewCell extends ListCell<TransactionSummary> {
        @Override
        protected void updateItem(TransactionSummary transactionSummary, boolean empty) {
            super.updateItem(transactionSummary, empty);
            if (empty || transactionSummary == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TransactionSummaryCard(transactionSummary, getIndex() + 1).getRoot());
            }
        }
    }

}
