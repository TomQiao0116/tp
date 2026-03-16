package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RemoveInterviewRecordCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteInterviewRecordCommand object.
 */
public class RemoveInterviewRecordCommandParser implements Parser<RemoveInterviewRecordCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteInterviewRecordCommand
     * and returns a DeleteInterviewRecordCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RemoveInterviewRecordCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        String[] parts = trimmedArgs.split("\\s+");

        if (parts.length != 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    RemoveInterviewRecordCommand.MESSAGE_USAGE));
        }

        Index personIndex = ParserUtil.parseIndex(parts[0]);
        Index recordIndex = ParserUtil.parseIndex(parts[1]);

        return new RemoveInterviewRecordCommand(personIndex, recordIndex);
    }
}

