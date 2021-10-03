package seedu.address.logic.parser.exceptions;

import seedu.address.commons.exceptions.IllegalValueException;

public class EmptyGroupException extends ParseException {

    public EmptyGroupException(String message) {
        super(message);
    }

    public EmptyGroupException(String message, Throwable cause) {
        super(message, cause);
    }
}
