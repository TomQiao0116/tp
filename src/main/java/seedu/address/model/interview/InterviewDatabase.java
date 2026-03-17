package seedu.address.model.interview;

import java.util.HashMap;
import java.util.Map;

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

    private final ObservableList<InterviewRecord> interviews = FXCollections.observableArrayList();
    private final Map<String, InterviewRecord> records = new HashMap<>();

    /**
     * Adds an interview record to the database.
     *
     * @param record The interview object to add.
     */
    public void addInterviewRecord(InterviewRecord record) {
        interviews.add(record);
        records.put(record.getId(), record);
    }

    public boolean contains(String id) {
        return records.containsKey(id);
    }

    /**
     * Gets an interview record from the database.
     *
     * @param id The interview object to retrieve.
     */
    public InterviewRecord getInterviewRecord(String id) {
        return records.get(id);
    }

    /**
     * Removes an interview record from the database.
     *
     * @param id The id of the interview object to remove.
     */
    public void removeInterviewRecord(String id) {
        InterviewRecord toRemove = records.remove(id);
        if (toRemove != null) {
            interviews.remove(toRemove);
        }
    }

    /**
     * Returns an unmodifiable view of the interview list.
     *
     * This prevents external classes from modifying the internal
     * storage directly.
     *
     * @return Observable list of interview records.
     */
    public ObservableList<InterviewRecord> getInterviewRecordList() {
        return FXCollections.unmodifiableObservableList(interviews);
    }

    public void setInterviewRecords(ObservableList<InterviewRecord> newRecords) {
        interviews.setAll(newRecords);
        records.clear();
        newRecords.forEach(r -> records.put(r.getId(), r));
    }
}
