package sample.model;

import javafx.beans.property.SimpleStringProperty;

public class PatientTable implements Comparable<PatientTable> {

    private SimpleStringProperty ssn, firstName, lastName, fullName, dateOfBirth, gender, timeTo, timeFrom, description,
            FreeTime;

    public PatientTable(String ssn, String firstName, String lastName, String dateOfBirth, String gender) {
        this.ssn = new SimpleStringProperty(ssn);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.dateOfBirth = new SimpleStringProperty(dateOfBirth);
        this.gender = new SimpleStringProperty(gender);
    }

    public PatientTable(String fullName, String timeTo, String timeFrom, String description) {
        this.fullName = new SimpleStringProperty(fullName);
        this.timeTo = new SimpleStringProperty(timeTo);
        this.timeFrom = new SimpleStringProperty(timeFrom);
        this.description = new SimpleStringProperty(description);
    }

    public PatientTable(String ssn, String firstName, String lastName, String dateOfBirth, String gender,
                        String freetime) {
        this.ssn = new SimpleStringProperty(ssn);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.dateOfBirth = new SimpleStringProperty(dateOfBirth);
        this.gender = new SimpleStringProperty(gender);
        this.FreeTime = new SimpleStringProperty(freetime);

    }

    public String getFullName() {
        return fullName.get();
    }

    public SimpleStringProperty fullNameProperty() {
        return fullName;
    }

    public String getTimeTo() {
        return timeTo.get();
    }

    public SimpleStringProperty timeToProperty() {
        return timeTo;
    }

    public String getTimeFrom() {
        return timeFrom.get();
    }

    public SimpleStringProperty timeFromProperty() {
        return timeFrom;
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public String getSsn() {
        return ssn.get();
    }

    public SimpleStringProperty ssnProperty() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn.set(ssn);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getDateOfBirth() {
        return dateOfBirth.get();
    }

    public SimpleStringProperty dateOfBirthProperty() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth.set(dateOfBirth);
    }

    public String getGender() {
        return gender.get();
    }

    public SimpleStringProperty genderProperty() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }

    public String getFreeTime() {
        return FreeTime.get();
    }

    public void setFreeTime(String freeTime) {
        this.FreeTime.set(freeTime);
    }

    @Override
    public int compareTo(PatientTable o) {
        if (getTimeFrom().charAt(0) > o.getTimeFrom().charAt(0)) {
            return 1;
        } else if (getTimeFrom().charAt(0) < o.getTimeFrom().charAt(0)) {
            return -1;
        } else {
            return 0;
        }
    }
}
