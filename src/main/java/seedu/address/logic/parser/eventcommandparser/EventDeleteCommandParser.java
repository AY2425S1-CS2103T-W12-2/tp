package seedu.address.logic.parser.eventcommandparser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.eventcommands.EventDeleteCommand;
import seedu.address.logic.parser.util.EventParserUtil;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class EventDeleteCommandParser implements Parser<EventDeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EventDeleteCommand parse(String args) throws ParseException {
        try {
            Index index = EventParserUtil.parseIndex(args);
            return new EventDeleteCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EventDeleteCommand.MESSAGE_USAGE), pe);
        }
    }

}
