package seedu.awe.ui.payment;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.awe.commons.core.LogsCenter;
import seedu.awe.model.payment.Payment;
import seedu.awe.ui.UiPart;

public class PaymentListPanel extends UiPart<Region> {

    private static final String FXML = "PaymentListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PaymentListPanel.class);

    @javafx.fxml.FXML
    private ListView<Payment> paymentListView;

    /**
     * Creates an {@code PaymentListPanel} with the given {@code ObservableList}.
     */
    public PaymentListPanel(ObservableList<Payment> paymentList) {
        super(FXML);
        paymentListView.setItems(paymentList);
        paymentListView.setCellFactory(listView -> new PaymentListPanel.PaymentListViewCell());
    }

    /**
     * Custom {@code PaymentListCell} that displays the graphics of
     * a {@code Payment} using a {@code PaymentCard}.
     */
    class PaymentListViewCell extends ListCell<Payment> {
        @Override
        protected void updateItem(Payment payment, boolean empty) {
            super.updateItem(payment, empty);
            if (empty || payment == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PaymentCard(payment, getIndex() + 1).getRoot());
            }
        }
    }
}
