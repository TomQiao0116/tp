package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.AddInterviewRecordCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.interview.InterviewRecord;

/**
 * Parses input arguments and creates a new {@link AddInterviewRecordCommand} object.
 * <p>
 * Expects input in the following format:
 * <pre>
 * {@code
 * addInterviewRecord id/ID d/DATE nt/NOTES
 * }
 * </pre>
 * For example:
 * <pre>
 * {@code
 * addInterviewRecord id/INT-456 d/2026-04-10 nt/2nd technical round
 * }
 * </pre>
 * Throws a {@link ParseException} if the input does not conform to the expected format
 * or if required prefixes are missing.
 */
public class AddInterviewRecordCommandParser implements Parser<AddInterviewRecordCommand> {

    private static final Prefix PREFIX_ID = new Prefix("id/");
    private static final Prefix PREFIX_DATE = new Prefix("d/");
    private static final Prefix PREFIX_NOTES = new Prefix("nt/");

    @Override
    public AddInterviewRecordCommand parse(String args) throws ParseException {
        requireNonNull(args);

        // Tokenize the input with all expected prefixes
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ID, PREFIX_DATE, PREFIX_NOTES);

        // Ensure there’s no preamble (unexpected text before prefixes)
        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddInterviewRecordCommand.MESSAGE_USAGE));
        }

        // Verify that each prefix occurs at most once (optional, like your EditCommand example)
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_ID, PREFIX_DATE, PREFIX_NOTES);

        // Parse each value; throw error if a required prefix is missing
        String id = argMultimap.getValue(PREFIX_ID)
                .orElseThrow(() -> new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AddInterviewRecordCommand.MESSAGE_USAGE)))
                .trim();

        String date = argMultimap.getValue(PREFIX_DATE)
                .orElseThrow(() -> new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AddInterviewRecordCommand.MESSAGE_USAGE)))
                .trim();

        // Notes could be optional if you want, otherwise enforce presence
        String notes = argMultimap.getValue(PREFIX_NOTES)
                .orElseThrow(() -> new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AddInterviewRecordCommand.MESSAGE_USAGE)))
                .trim();

        InterviewRecord record = new InterviewRecord(id, date, notes);
        return new AddInterviewRecordCommand(record);
    }
}
