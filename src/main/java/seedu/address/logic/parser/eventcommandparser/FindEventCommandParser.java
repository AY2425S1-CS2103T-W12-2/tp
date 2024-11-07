package seedu.address.logic.parser.eventcommandparser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.eventcommands.FindEventCommand;
import seedu.address.logic.commands.volunteercommands.FindVolunteerCommand;
import seedu.address.logic.parser.EventParserUtil;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FindEventCommand object
 */
public class FindEventCommandParser implements Parser<FindEventCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindEventCommand
     * and returns a FindEventCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public FindEventCommand parse(String args) throws ParseException {
        try {
            String searchTerm = EventParserUtil.parseSearchEventName(args);
            return new FindEventCommand(searchTerm);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FindVolunteerCommand.MESSAGE_USAGE));
        }
    }
}
