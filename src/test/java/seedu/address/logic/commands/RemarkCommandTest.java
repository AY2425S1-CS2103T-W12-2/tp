package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class RemarkCommandTest {

    private Model model;

    @Test
    public void execute_remarkCommand_failure() {
        RemarkCommand remarkCommand = new RemarkCommand();
        model = new ModelManager();
        assertCommandFailure(remarkCommand, model, "Not implemented yet");
    }
}
