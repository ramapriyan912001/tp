package seedu.awe.testutil;

import static seedu.awe.testutil.TypicalGroups.AMSTERDAM_WITH_EXPENSES;
import static seedu.awe.testutil.TypicalGroups.BALI_WITH_EXPENSES;
import static seedu.awe.testutil.TypicalGroups.CHINA;
import static seedu.awe.testutil.TypicalGroups.DUBAI;
import static seedu.awe.testutil.TypicalGroups.HELSINKI;
import static seedu.awe.testutil.TypicalGroups.INDIA;
import static seedu.awe.testutil.TypicalGroups.LONDON;
import static seedu.awe.testutil.TypicalGroups.MALIBU_WITH_EXPENSES;
import static seedu.awe.testutil.TypicalGroups.OSLO;
import static seedu.awe.testutil.TypicalGroups.PARIS;
import static seedu.awe.testutil.TypicalGroups.PERU_WITH_EXPENSES_INVALID;
import static seedu.awe.testutil.TypicalGroups.RIO_WITH_EXPENSES_INVALID;
import static seedu.awe.testutil.TypicalGroups.SANTIAGO_WITH_EXPENSES;
import static seedu.awe.testutil.TypicalPersons.ALICE;
import static seedu.awe.testutil.TypicalPersons.AMY;
import static seedu.awe.testutil.TypicalPersons.BENSON;
import static seedu.awe.testutil.TypicalPersons.BOB;
import static seedu.awe.testutil.TypicalPersons.CARL;
import static seedu.awe.testutil.TypicalPersons.DANIEL;
import static seedu.awe.testutil.TypicalPersons.ELLE;
import static seedu.awe.testutil.TypicalPersons.FIONA;
import static seedu.awe.testutil.TypicalPersons.GEORGE;
import static seedu.awe.testutil.TypicalPersons.HOON;
import static seedu.awe.testutil.TypicalPersons.IDA;

import javafx.collections.transformation.FilteredList;
import seedu.awe.model.Awe;
import seedu.awe.model.Model;
import seedu.awe.model.ModelManager;
import seedu.awe.model.UserPrefs;
import seedu.awe.model.group.Group;
import seedu.awe.model.group.exceptions.DuplicateGroupException;
import seedu.awe.model.person.Person;

/**
 * A utility class to help with building Person objects.
 */
public class ModelBuilder {

    public static final Awe DEFAULT_ADDRESSBOOK = new Awe();
    public static final UserPrefs DEFAULT_USERPREFS = new UserPrefs();
    public static final FilteredList<Person> DEFAULT_FILTEREDPERSONS =
            new FilteredList<Person>(DEFAULT_ADDRESSBOOK.getPersonList());
    public static final FilteredList<Group> DEFAULT_FILTEREDGROUPS =
            new FilteredList<Group>(DEFAULT_ADDRESSBOOK.getGroupList());

    private Awe awe;
    private UserPrefs userPrefs;
    private FilteredList<Person> filteredPersons;
    private FilteredList<Group> filteredGroups;

    /**
     * Creates a {@code ModelBuilder} with the default details.
     */
    public ModelBuilder() {
        awe = new Awe();
        addTypicalPersons(awe);
        addTypicalGroups(awe);
        userPrefs = DEFAULT_USERPREFS;
        filteredPersons = DEFAULT_FILTEREDPERSONS;
        filteredGroups = DEFAULT_FILTEREDGROUPS;
    }

    /**
     * Initializes the ModelBuilder with the data of {@code ModelToCopy}.
     */
    public ModelBuilder(Model modelToCopy) {
        awe = (Awe) modelToCopy.getAwe();
        userPrefs = (UserPrefs) modelToCopy.getUserPrefs();
        filteredPersons = (FilteredList<Person>) modelToCopy.getFilteredPersonList();
        filteredGroups = (FilteredList<Group>) modelToCopy.getFilteredGroupList();
    }

    /**
     * Sets the {@code Awe} of the {@code Model} that we are building.
     */
    public ModelBuilder withAddressBook(Awe awe) {
        this.awe = awe;
        return this;
    }

    /**
     * Sets the {@code UserPrefs} of the {@code Model} that we are building.
     */
    public ModelBuilder withUserPrefs(UserPrefs userPrefs) {
        this.userPrefs = userPrefs;
        return this;
    }

    /**
     * Adds all Person attributes from TypicalPersons into the sample addressbook.
     *
     * @param awe Awe object to be used for testing purposes.
     */
    public void addTypicalPersons(Awe awe) {
        awe.addPerson(ALICE);
        awe.addPerson(BENSON);
        awe.addPerson(CARL);
        awe.addPerson(DANIEL);
        awe.addPerson(ELLE);
        awe.addPerson(FIONA);
        awe.addPerson(GEORGE);
        awe.addPerson(HOON);
        awe.addPerson(IDA);
        awe.addPerson(AMY);
        awe.addPerson(BOB);
    }

    /**
     * Adds all Person attributes from TypicalPersons into the sample addressbook.
     *
     * @param awe Awe object to be used for testing purposes.
     */
    public void addTypicalGroups(Awe awe) throws DuplicateGroupException {
        awe.addGroup(AMSTERDAM_WITH_EXPENSES);
        awe.addGroup(BALI_WITH_EXPENSES);
        awe.addGroup(PERU_WITH_EXPENSES_INVALID);
        awe.addGroup(RIO_WITH_EXPENSES_INVALID);
        awe.addGroup(INDIA);
        awe.addGroup(LONDON);
        awe.addGroup(DUBAI);
        awe.addGroup(PARIS);
        awe.addGroup(CHINA);
        awe.addGroup(MALIBU_WITH_EXPENSES);
        awe.addGroup(SANTIAGO_WITH_EXPENSES);
        awe.addGroup(HELSINKI);
        awe.addGroup(OSLO);
    }

    public Model build() {
        return new ModelManager(awe, userPrefs);
    }

    public Model buildEmptyModel() {
        return new ModelManager(new Awe(), new UserPrefs());
    }
}

