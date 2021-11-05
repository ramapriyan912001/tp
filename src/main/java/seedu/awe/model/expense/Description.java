package seedu.awe.model.expense;

import static java.util.Objects.requireNonNull;
import static seedu.awe.commons.util.AppUtil.checkArgument;

public class Description {
    public static final String MESSAGE_CONSTRAINTS =
            "Description should only contain alphanumeric characters and spaces, "
                    + "should be 50 characters or less and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public static final int MAX_LENGTH = 50;

    private final String fullDescription;

    /**
     * Constructs a {@code Name}.
     *
     * @param description A valid name.
     */
    public Description(String description) {
        requireNonNull(description);
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS);
        fullDescription = description;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidDescription(String test) {
        return test.matches(VALIDATION_REGEX) && test.length() <= MAX_LENGTH;
    }


    @Override
    public String toString() {
        return fullDescription;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Description // instanceof handles nulls
                && fullDescription.equals(((Description) other).fullDescription)); // state check
    }

    @Override
    public int hashCode() {
        return fullDescription.hashCode();
    }
}
