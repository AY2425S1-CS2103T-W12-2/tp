package seedu.address.logic.parser.volunteercommandparser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.VOLUNTEER_PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.VOLUNTEER_PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.VOLUNTEER_PREFIX_PHONE;

import java.util.stream.Stream;

import seedu.address.logic.commands.volunteercommands.VolunteerAddCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.VolunteerParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.volunteer.Email;
import seedu.address.model.volunteer.Name;
import seedu.address.model.volunteer.Phone;
import seedu.address.model.volunteer.Volunteer;

/**
 * Parses input arguments and creates a new VolunteerAddCommand object.
 */
public class VolunteerAddCommandParser implements Parser<VolunteerAddCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the VolunteerAddCommand
     * and returns a VolunteerAddCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public VolunteerAddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, VOLUNTEER_PREFIX_NAME, VOLUNTEER_PREFIX_PHONE,
                        VOLUNTEER_PREFIX_EMAIL);

        // Check if mandatory prefixes are present and preamble is empty
        if (!arePrefixesPresent(argMultimap, VOLUNTEER_PREFIX_NAME, VOLUNTEER_PREFIX_PHONE,
                VOLUNTEER_PREFIX_EMAIL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, VolunteerAddCommand.MESSAGE_USAGE));
        }

        // Ensure no duplicate prefixes are provided
        argMultimap.verifyNoDuplicatePrefixesFor(VOLUNTEER_PREFIX_NAME, VOLUNTEER_PREFIX_PHONE,
                VOLUNTEER_PREFIX_EMAIL);

        Name name = VolunteerParserUtil.parseName(argMultimap.getValue(VOLUNTEER_PREFIX_NAME).get());
        Phone phone = VolunteerParserUtil.parsePhone(argMultimap.getValue(VOLUNTEER_PREFIX_PHONE).get());
        Email email = VolunteerParserUtil.parseEmail(argMultimap.getValue(VOLUNTEER_PREFIX_EMAIL).get());

        Volunteer volunteer = new Volunteer(name, phone, email);
        return new VolunteerAddCommand(volunteer);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
