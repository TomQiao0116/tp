package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.LinkInterviewRecordCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new LinkInterviewRecordCommand object.
 */
public class LinkInterviewRecordCommandParser implements Parser<LinkInterviewRecordCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the LinkInterviewRecordCommand
     * and returns an LinkInterviewRecordCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public LinkInterviewRecordCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    LinkInterviewRecordCommand.MESSAGE_USAGE));
        }

        String[] parts = trimmedArgs.split("\\s+", 2);
        if (parts.length != 2) {
            throw new ParseException("Please provide both person index and interview ID.");
        }

        Index index;
        try {
            index = ParserUtil.parseIndex(parts[0]);
        } catch (ParseException pe) {
            throw new ParseException("Invalid person index: " + parts[0]);
        }

        String interviewId = parts[1].trim();
        if (interviewId.isEmpty()) {
            throw new ParseException("Interview ID cannot be empty.");
        }

        return new LinkInterviewRecordCommand(index, interviewId);
    }

    /**
     * Returns true if all prefixes contain non-empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

