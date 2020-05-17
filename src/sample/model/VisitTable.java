package sample.model;

import javafx.beans.property.SimpleStringProperty;

public class VisitTable {
	
	private SimpleStringProperty ssn, firstName, lastName, dateOfBirth, gender, visitor, FreeTime, Visitortime;

	public VisitTable() {

	}
	
	public VisitTable(String ssn, String firstName, String lastName, String dateOfBirth, String gender, String freetime, String visitor, String Visitortime) {
		this.ssn = new SimpleStringProperty(ssn);
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		this.dateOfBirth = new SimpleStringProperty(dateOfBirth);
		this.gender = new SimpleStringProperty(gender);
		this.FreeTime = new SimpleStringProperty(freetime);
		this.visitor = new SimpleStringProperty(visitor);
		this.Visitortime = new SimpleStringProperty(Visitortime);

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

	public String genderProperty() {
		return gender.get();
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

	public String getVisitor() {
		return visitor.get();
	}

	public void setVisitor(String visitor) {
		this.visitor.set(visitor);
	}

	public String getVisitortime() {
		return Visitortime.get();
	}

	public void setVisitortime(String visitortime) {
		Visitortime.set(visitortime);
	}
	
	
}
