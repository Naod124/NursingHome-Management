package sample.model;

import javafx.beans.property.SimpleStringProperty;
import sample.databaseConnection.PatientQueries;

import java.sql.SQLException;

public class StaffTable implements Runnable{

    private SimpleStringProperty firstName,lastName, ssn, email, address, role;

    public StaffTable(String firstName,String lastName, String ssn, String email, String address, String role) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.ssn = new SimpleStringProperty(ssn);
        this.email = new SimpleStringProperty(email);
        this.address = new SimpleStringProperty(address);
        this.role = new SimpleStringProperty(role);
    }
    public StaffTable() {

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

    public String getSsn() {
        return ssn.get();
    }

    public SimpleStringProperty ssnProperty() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn.set(ssn);
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getAddress() {
        return address.get();
    }

    public SimpleStringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getRole() {
        return role.get();
    }

    public SimpleStringProperty roleProperty() {
        return role;
    }

    public void setRole(String role) {
        this.role.set(role);
    }

    @Override
    public void run() {
        PatientQueries pq = new PatientQueries();
        try {
            pq.truncateSchedule();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
