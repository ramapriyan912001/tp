package seedu.address.model.group.exceptions;

import seedu.address.logic.parser.exceptions.ParseException;

public class DuplicateGroupException extends ParseException {
    public DuplicateGroupException() {
        super("Operation would result in duplicate groups");
    }
}
