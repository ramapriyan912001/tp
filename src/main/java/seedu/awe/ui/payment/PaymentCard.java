package seedu.awe.ui.payment;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.awe.model.payment.Payment;
import seedu.awe.ui.UiPart;

public class PaymentCard extends UiPart<Region> {
    private static final String FXML = "PaymentListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Payment payment;

    @javafx.fxml.FXML
    private HBox cardPane;

    @FXML
    private Label id;
    @FXML
    private Label payer;
    @FXML
    private Label payee;
    @FXML
    private Label amount;

    /**
     * Creates an {@code Payment} with the given {@code Payment} and index to display.
     */
    public PaymentCard(Payment payment, int displayedIndex) {
        super(FXML);
        this.payment = payment;
        id.setText(displayedIndex + ".");
        payer.setText(payment.getPayer().getName().toString());
        payee.setText(payment.getPayee().getName().toString());
        amount.setText(payment.getCost().toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PaymentCard)) {
            return false;
        }

        // state check
        PaymentCard card = (PaymentCard) other;
        return id.getText().equals(card.id.getText())
                && payment.equals(card.payment);
    }
}
