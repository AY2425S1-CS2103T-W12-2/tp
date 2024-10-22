package seedu.address.model.event.exceptions;

/**
 * Signals that the operation will result in duplicate Events (Events are considered duplicates if they have the same
 * identity).
 * TODO: Change the checking condition for duplicate events if necessary
 */
public class DuplicateEventException extends RuntimeException {
    public DuplicateEventException() {
        super("Operation would result in duplicate events");
    }
}
