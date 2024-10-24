package seedu.address.logic.commands.eventcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.managers.Model.PREDICATE_SHOW_ALL_EVENTS;

import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.event.Event;
import seedu.address.model.managers.Model;

/**
 * Finds events whose names start with the specified prefix (case-insensitive).
 */
public class EventFindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds events whose names start with the specified prefix (case-insensitive).\n"
            + "Parameters: PREFIX (must be a non-empty string)\n"
            + "Example: " + COMMAND_WORD + " eventSearchString";

    public static final String MESSAGE_EVENT_FOUND = "Found %d event(s) starting with '%s':";
    public static final String MESSAGE_EVENT_NOT_FOUND = "No events found starting with '%s'.";

    private final String searchString;

    /**
     * Constructs a FindEventCommand that searches for events containing the given string.
     *
     * @param searchString The string to search for.
     */
    public EventFindCommand(String searchString) {
        requireNonNull(searchString);
        this.searchString = searchString.trim();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (searchString.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_EVENT_NOT_FOUND, searchString));
        }

        Predicate<Event> eventContainsSearchString = event ->
                event.getName().toString().toLowerCase().contains(searchString.toLowerCase());
        model.updateFilteredEventList(eventContainsSearchString);

        ObservableList<Event> filteredEvents = model.getFilteredEventList();
        if (filteredEvents.isEmpty()) {
            model.updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);
            return new CommandResult(String.format(MESSAGE_EVENT_NOT_FOUND, searchString));
        }

        String resultMessage = String.format(MESSAGE_EVENT_FOUND, filteredEvents.size(), searchString);
        for (Event event : filteredEvents) {
            resultMessage += "\n" + event.getName().toString();
        }

        return new CommandResult(resultMessage);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof EventFindCommand)) {
            return false;
        }

        EventFindCommand otherFindCommand = (EventFindCommand) other;
        return searchString.equals(otherFindCommand.searchString);
    }

    @Override
    public String toString() {
        return "FindEventCommand[searchString=" + searchString + "]";
    }
}
