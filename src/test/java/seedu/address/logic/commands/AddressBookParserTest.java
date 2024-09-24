package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.logic.parser.AddressBookParser;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddressBookParserTest {
    @Test
    public void parseCommand_remark() throws Exception {
        AddressBookParser parser = new AddressBookParser();

        assertTrue(parser.parseCommand(RemarkCommand.COMMAND_WORD) instanceof RemarkCommand);
    }
}
