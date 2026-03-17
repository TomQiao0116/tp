package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.DeleteInterviewRecordCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@link DeleteInterviewRecordCommand} object.
 * <p>
 * Expects input in the following format:
 * <pre>
 * {@code
 * deleteInterviewRecord ID
 * }
 * </pre>
 * For example:
 * <pre>
 * {@code
 * deleteInterviewRecord INT-456
 * }
 * </pre>
 * Throws a {@link ParseException} if the input is empty or does not conform to the expected format.
 */
public class DeleteInterviewRecordCommandParser implements Parser<DeleteInterviewRecordCommand> {

    @Override
    public DeleteInterviewRecordCommand parse(String args) throws ParseException {
        String trimmed = args.trim();

        if (trimmed.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteInterviewRecordCommand.MESSAGE_USAGE));
        }

        return new DeleteInterviewRecordCommand(trimmed);
    }
}
