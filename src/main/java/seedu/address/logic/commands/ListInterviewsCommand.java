package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Lists all interview records in the interview database.
 */
public class ListInterviewsCommand extends Command {

    public static final String COMMAND_WORD = "listInterviews";

    public static final String MESSAGE_SUCCESS = "Listed all interview records:";

    public static final String MESSAGE_NO_INTERVIEWS = "No interview records found.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        var db = model.getInterviewDatabase();
        var records = db.getInterviewRecordList();

        if (records.isEmpty()) {
            return new CommandResult(MESSAGE_NO_INTERVIEWS);
        }

        StringBuilder sb = new StringBuilder(MESSAGE_SUCCESS + "\n");
        int index = 1;
        for (var record : records) {
            sb.append(String.format("%2d. %s\n", index++, record.toString()));
        }

        return new CommandResult(sb.toString());
    }
}
