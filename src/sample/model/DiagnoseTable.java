package sample.model;

import javafx.beans.property.SimpleStringProperty;

public class DiagnoseTable {

	private SimpleStringProperty ssn, firstName, lastName, dateOfBirth, gender, diagnosis, FreeTime;

	public DiagnoseTable() {

	}
	
	public DiagnoseTable(String ssn, String firstName, String lastName, String dateOfBirth, String gender, String freetime, String diagnosis) {
		this.ssn = new SimpleStringProperty(ssn);
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		this.dateOfBirth = new SimpleStringProperty(dateOfBirth);
		this.gender = new SimpleStringProperty(gender);
		this.FreeTime = new SimpleStringProperty(freetime);
		this.diagnosis = new SimpleStringProperty(diagnosis);
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
	
	public String getDiagnosis() {
		return diagnosis.get();
	}
	
	public void setDiagnosis(String diagnosis) {
		this.diagnosis.set(diagnosis);
	}

	
	
}
