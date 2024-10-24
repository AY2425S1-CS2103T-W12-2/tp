package seedu.address.logic.parser.volunteercommandparser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.volunteercommands.VolunteerFindCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FindVolunteerCommand object.
 */
public class FindVolunteerCommandParser implements Parser<VolunteerFindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindVolunteerCommand
     * and returns a FindVolunteerCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public VolunteerFindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, VolunteerFindCommand.MESSAGE_USAGE));
        }

        return new VolunteerFindCommand(trimmedArgs);
    }
}
