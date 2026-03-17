package seedu.address.logic.parser;

import seedu.address.logic.commands.ListInterviewsCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ListInterviewsCommand object
 */
public class ListInterviewsCommandParser implements Parser<ListInterviewsCommand> {

    @Override
    public ListInterviewsCommand parse(String args) throws ParseException {
        if (!args.trim().isEmpty()) {
            throw new ParseException("listInterviews command does not take any arguments.");
        }
        return new ListInterviewsCommand();
    }
}
