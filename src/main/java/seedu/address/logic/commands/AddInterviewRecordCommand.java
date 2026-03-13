package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.interview.InterviewRecord;
import seedu.address.model.person.Person;

/**
 * Adds an interview record to an existing person in the address book.
 */
public class AddInterviewRecordCommand extends Command {

    public static final String COMMAND_WORD = "addInterviewRecord";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds an interview record to the person identified "
            + "by the index number used in the displayed person list.\n"
            + "Parameters: PERSON_INDEX "
            + "id/INTERVIEW_ID "
            + "d/DATE "
            + "nt/NOTES\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "id/IR001 "
            + "d/2026-03-13 "
            + "nt/Strong communication skills";

    public static final String MESSAGE_SUCCESS = "New interview record added to: %1$s";

    private final Index personIndex;
    private final InterviewRecord toAdd;

    /**
     * Creates an AddInterviewRecordCommand to add the specified {@code InterviewRecord}
     * to the specified {@code Person}.
     */
    public AddInterviewRecordCommand(Index personIndex, InterviewRecord toAdd) {
        requireNonNull(personIndex);
        requireNonNull(toAdd);
        this.personIndex = personIndex;
        this.toAdd = toAdd;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> lastShownList = model.getFilteredPersonList();

        if (personIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(personIndex.getZeroBased());
        Person editedPerson = personToEdit.addInterviewRecord(toAdd);

        model.setPerson(personToEdit, editedPerson);

        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(editedPerson)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AddInterviewRecordCommand)) {
            return false;
        }

        AddInterviewRecordCommand otherCommand = (AddInterviewRecordCommand) other;
        return personIndex.equals(otherCommand.personIndex)
                && toAdd.equals(otherCommand.toAdd);
    }
}

