package sample.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Stack;

public class Patient implements Serializable {

    private String SSN;
    private String firstName;
    private String lastName;
    private String dob;
    private String gender;
    private ArrayList<Diagnose> diagnoses;

    public Patient(String SSN, String firstName, String lastName, String dob, String gender) {
        this.SSN = SSN;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.gender = gender;
    }

    public String getSSN() {
        return SSN;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public ArrayList<Diagnose> getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(ArrayList<Diagnose> diagnoses) {
        this.diagnoses = diagnoses;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "SSN='" + SSN + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob='" + dob + '\'' +
                ", gender='" + gender + '\'' +
                ", diagnoses=" + diagnoses +
                '}';
    }

}
