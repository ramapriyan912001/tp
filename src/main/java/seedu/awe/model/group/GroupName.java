package seedu.awe.model.group;

import static java.util.Objects.requireNonNull;
import static seedu.awe.commons.util.AppUtil.checkArgument;

public class GroupName {
    public static final String MESSAGE_CONSTRAINTS =
            "Group Names should only contain alphanumeric characters and spaces, "
                    + "should be 50 characters or less and it should not be blank";

    /*
     * The first character of the awe must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public static final int MAX_LENGTH = 50;

    private final String name;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public GroupName(String name) {
        requireNonNull(name);
        checkArgument(isValidGroupName(name), MESSAGE_CONSTRAINTS);
        checkArgument(name.length() <= MAX_LENGTH, MESSAGE_CONSTRAINTS);
        this.name = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidGroupName(String test) {
        return test.matches(VALIDATION_REGEX) && test.length() <= MAX_LENGTH;
    }

    /**
     * Name getter
     * @return String name
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof GroupName)) { // instanceof handles nulls
            return false;
        }

        return name.equals(((GroupName) other).name); // state check
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

}
