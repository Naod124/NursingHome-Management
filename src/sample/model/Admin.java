package sample.model;

public class Admin extends Staff{
    private static Admin admin = null;

    private Admin(String firstName, String lastName, String address, String SSN, int age, String eMail, LogIn logIn, double salary) {
        super(firstName, lastName, address, SSN, age, eMail, logIn, salary);

    }


    public static Admin getInstance() {
        if (admin == null) {
            admin = new Admin("firstName","lastName","address","SSN",0,  "eMail", new LogIn("userName","password"),20000);
        }
        return admin;
    }

    public static Admin getAdmin() {
        return admin;
    }

    public static void setAdmin(Admin admin) {
        Admin.admin = admin;
    }
}
