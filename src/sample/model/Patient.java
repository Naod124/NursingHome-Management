package sample.model;

import java.io.Serializable;
import java.util.Stack;

public class Patient implements Serializable {
    private String firstName;
    private String lastName;
    private String address;
    private String SSN;
    private int age;
    private String eMail;
    private LogIn logIn;
    private Stack<Diagnose> diagnoses;

    public Patient(String firstName, String lastName, String address, String SSN, int age, String eMail) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.SSN = SSN;
        this.age = age;
        this.eMail = eMail;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSSN() {
        return SSN;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public LogIn getLogIn() {
        return logIn;
    }

    public void setLogIn(LogIn logIn) {
        this.logIn = logIn;
    }

    public Stack<Diagnose> getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(Stack<Diagnose> diagnoses) {
        this.diagnoses = diagnoses;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", SSN='" + SSN + '\'' +
                ", age=" + age +
                ", eMail='" + eMail + '\'' +
                ", logIn=" + logIn +
                ", diagnoses=" + diagnoses +
                '}';
    }
}
