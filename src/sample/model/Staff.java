package sample.model;

public class Staff {
    private String firstName ;
    private String lastName ;
    private String address ;
    private String SSN ;
    private String dOb ;
    private String eMail;
    private LogIn logIn;
    private double salary ;

    public Staff(String firstName, String lastName, String address, String SSN, String dOb, String eMail, LogIn logIn, double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.SSN = SSN;
        this.dOb = dOb;
        this.eMail = eMail;
        this.logIn = logIn;
        this.salary = salary;
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

    public String getdOb() {
        return dOb;
    }

    public void setdOb(String age) {
        this.dOb = dOb;
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

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
