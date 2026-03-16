package seedu.address.model.interview;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Container class that stores all interview records in the system.
 *
 * This class acts as the database for interview records, similar to how
 * AddressBook stores Person objects in AB3.
 *
 * Interviews are stored as Interview (but the actual Interview
 * class will be implemented by another teammate soon)
 *
 * Supports a few operations related to adding and removing interview records
 */
public class InterviewDatabase {

    /**
     * Internal list storing interview records.
     */
    private final ObservableList<Interview> internalList = FXCollections.observableArrayList();

    /**
     * Adds an interview record to the database.
     *
     * @param interview The interview object to add.
     */
    public void addInterview(Interview interview) {
        internalList.add(interview);
    }

    /**
     * Removes an interview record from the database.
     *
     * @param interview The interview object to remove.
     */
    public void removeInterview(Interview interview) {
        internalList.remove(interview);
    }

    /**
     * Returns an unmodifiable view of the interview list.
     *
     * This prevents external classes from modifying the internal
     * storage directly.
     *
     * @return Observable list of interview records.
     */
    public ObservableList<Interview> getInterviewList() {
        return FXCollections.unmodifiableObservableList(internalList);
    }
}
