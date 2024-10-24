package seedu.address.logic.parser.generalcommandparser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.util.CliSyntax.UNASSIGN_EVENT_PREFIX_NAME;
import static seedu.address.logic.parser.util.CliSyntax.UNASSIGN_VOLUNTEER_PREFIX_NAME;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.generalcommands.UnassignCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.util.ArgumentMultimap;
import seedu.address.logic.parser.util.ArgumentTokenizer;
import seedu.address.logic.parser.util.EventParserUtil;
import seedu.address.logic.parser.util.Prefix;
import seedu.address.logic.parser.util.VolunteerParserUtil;

/**
 * Parses input arguments and creates a new UnassignCommand object.
 */
public class UnassignCommandParser implements Parser<UnassignCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the UnassignCommand
     * and returns an UnassignCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public UnassignCommand parseCommand(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, UNASSIGN_VOLUNTEER_PREFIX_NAME, UNASSIGN_EVENT_PREFIX_NAME);

        // Check if mandatory prefixes are present and preamble is empty
        if (!arePrefixesPresent(argMultimap, UNASSIGN_VOLUNTEER_PREFIX_NAME, UNASSIGN_EVENT_PREFIX_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnassignCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(UNASSIGN_VOLUNTEER_PREFIX_NAME, UNASSIGN_EVENT_PREFIX_NAME);

        try {
            // Parse the volunteer and event indices
            Index volunteerIndex = VolunteerParserUtil.parseIndex(argMultimap.getValue(UNASSIGN_VOLUNTEER_PREFIX_NAME)
                    .orElseThrow(() -> new ParseException("Volunteer ID is required.")));
            Index eventIndex = EventParserUtil.parseIndex(argMultimap.getValue(UNASSIGN_EVENT_PREFIX_NAME)
                    .orElseThrow(() -> new ParseException("Event ID is required.")));
            return new UnassignCommand(volunteerIndex, eventIndex);
        } catch (NumberFormatException e) {
            // Handle cases where the input is not a valid integer
            throw new ParseException("Volunteer ID and Event ID must be valid integers.", e);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnassignCommand.MESSAGE_USAGE), pe);
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    @Override
    public UnassignCommand parse(String userInput) throws ParseException {
        return null;
    }
}
