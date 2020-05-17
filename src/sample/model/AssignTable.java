package sample.model;

import javafx.beans.property.SimpleStringProperty;

public class AssignTable {

	private SimpleStringProperty ssn, firstName, lastName, dateOfBirth, gender, appointed_time , occupiedby ;
		
	public AssignTable() {

	}
	
	public AssignTable(String ssn, String firstName, String lastName, String dateOfBirth, String gender, String appointed_time, String occupiedby) {
		this.ssn = new SimpleStringProperty(ssn);
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		this.dateOfBirth = new SimpleStringProperty(dateOfBirth);
		this.gender = new SimpleStringProperty(gender);
		this.appointed_time = new SimpleStringProperty(appointed_time);
		this.occupiedby = new SimpleStringProperty(occupiedby);
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

	public String getAppointed_time() {
		return appointed_time.get();
	}

	public void setAppointed_time(String appointed_time) {
		this.appointed_time.set(appointed_time);
	}

	public String getOccupiedby() {
		return occupiedby.get();
	}

	public void setOccupiedby(String occupiedby) {
		this.occupiedby.set(occupiedby);
	}

	

	
	
}
