package seedu.awe.ui.person;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.awe.commons.core.LogsCenter;
import seedu.awe.model.ReadOnlyAwe;
import seedu.awe.model.person.Person;
import seedu.awe.ui.UiPart;

/**
 * Panel containing the list of persons.
 */
public class ContactListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ContactListPanel.class);

    @FXML
    private ListView<Person> personListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public ContactListPanel(ObservableList<Person> personList, ReadOnlyAwe addressBook) {
        super(FXML);
        personListView.setItems(personList);
        personListView.setCellFactory(listView -> new PersonListViewCell(addressBook));
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Person> {
        private final ReadOnlyAwe addressBook;

        public PersonListViewCell(ReadOnlyAwe addressBook) {
            this.addressBook = addressBook;
        }

        @Override
        protected void updateItem(Person person, boolean isEmpty) {
            super.updateItem(person, isEmpty);

            if (isEmpty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ContactCard(person, getIndex() + 1, this.addressBook).getRoot());
            }
        }
    }

}
