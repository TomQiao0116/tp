package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.interview.InterviewRecord;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final List<InterviewRecord> interviewRecords = new ArrayList<>();

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        this(name, phone, email, address, tags, List.of());
    }

    /**
     * Every field must be present and not null. New constructor that adds the interviewRecords field.
     */
    public Person(Name name, Phone phone, Email email, Address address,
                  Set<Tag> tags, List<InterviewRecord> interviewRecords) {
        requireAllNonNull(name, phone, email, address, tags, interviewRecords);

        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;

        this.tags.addAll(tags);
        this.interviewRecords.addAll(interviewRecords);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns an immutable interviewRecord list, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public List<InterviewRecord> getInterviewRecords() {
        return Collections.unmodifiableList(interviewRecords);
    }

    /**
     * Returns a new Person with the given interview record added.
     */
    public Person addInterviewRecord(InterviewRecord interviewRecord) {
        requireAllNonNull(interviewRecord);
        List<InterviewRecord> updatedInterviewRecords = new ArrayList<>(interviewRecords);
        updatedInterviewRecords.add(interviewRecord);
        return new Person(name, phone, email, address, tags, updatedInterviewRecords);
    }

    /**
     * Returns a new Person with the given interview record index removed.
     */
    public Person removeInterviewRecord(int index) {
        List<InterviewRecord> updatedInterviewRecords = new ArrayList<>(interviewRecords);
        updatedInterviewRecords.remove(index);
        return new Person(name, phone, email, address, tags, updatedInterviewRecords);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && address.equals(otherPerson.address)
                && tags.equals(otherPerson.tags)
                && interviewRecords.equals(otherPerson.interviewRecords);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags, interviewRecords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("tags", tags)
                .add("interviewRecords", interviewRecords)
                .toString();
    }

}
