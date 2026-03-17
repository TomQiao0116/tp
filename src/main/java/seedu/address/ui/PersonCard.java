package seedu.address.ui;

import java.util.Comparator;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.interview.InterviewDatabase;
import seedu.address.model.interview.InterviewRecord;
import seedu.address.model.person.Person;


/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;
    private final InterviewDatabase interviewDatabase;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;
    @FXML
    private Label interviewRecords;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex, InterviewDatabase interviewDatabase) {
        super(FXML);
        this.person = person;
        this.interviewDatabase = interviewDatabase;

        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);

        String recordsText = person.getInterviewIds().stream()
                .map(interviewDatabase::getInterviewRecord)
                .filter(record -> record != null)
                .map(InterviewRecord::toString)
                .collect(Collectors.joining("\n"));

        if (recordsText.isEmpty()) {
            interviewRecords.setText("");
        } else {
            interviewRecords.setText("Interview Records:\n" + recordsText);
        }

        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        updateInterviewRecords();
    }

    private void updateInterviewRecords() {
        // Instead of person.getInterviewRecords(), use the IDs to fetch from the DB
        String recordsText = person.getInterviewIds().stream() // list of String IDs
                .map(id -> {
                    InterviewRecord record = interviewDatabase.getInterviewRecord(id); // fetch the full record
                    return record == null ? "" : record.toString();
                })
                .filter(text -> !text.isEmpty()) // remove any null/empty records
                .collect(Collectors.joining("\n"));

        interviewRecords.setText(recordsText.isEmpty() ? "" : "Interview Records:\n" + recordsText);
    }
}
