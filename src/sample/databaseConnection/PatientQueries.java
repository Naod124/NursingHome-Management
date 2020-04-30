package sample.databaseConnection;

import sample.model.DataSource;
import sample.model.Patient;

import java.sql.*;
import java.util.ArrayList;

public class PatientQueries  {

    private Connection connection;
    private ResultSet rs;
    private Statement stmt;

    private ArrayList <sample.model.Patient> patientsinfo;


    public void pConnect() throws SQLException {

        connection = DriverManager.getConnection("jdbc:mysql://den1.mysql3.gear.host:3306/nursinghome",
                "nursinghome", "Vw3J!60l-0kd");

    }
    public PatientQueries() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://den1.mysql3.gear.host:3306/nursinghome",
                "nursinghome", "Vw3J!60l-0kd");


        patientsinfo = new ArrayList<>();
    }





    public void viewPatientTable() throws SQLException {


        String viewQuery = "SELECT * FROM patient;";

        connection = DriverManager.getConnection("jdbc:mysql://den1.mysql3.gear.host:3306/nursinghome",
                "nursinghome", "Vw3J!60l-0kd");
        try {
            stmt = connection.createStatement();

            rs = stmt.executeQuery(viewQuery);
            while (rs.next()){
                for (int i = 0; i < DataSource.getInstance().getPatient().size(); i++) {

                    patientsinfo.add(new sample.model.Patient(
                            rs.getString(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5)));
                }
           }
            connection.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }
    public void insertIntoPatientTable(String SSN, String FirstName, String LastName, String DateOfBirth, String Gender)
            throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://den1.mysql3.gear.host:3306/nursinghome",
                "nursinghome", "Vw3J!60l-0kd");

        String insertQuery = "INSERT INTO patient(SSN, FirstName, LastName, DateOfBirth, Gender) VALUES(?,?,?,?,?);";


        try (PreparedStatement pstmt = connection.prepareStatement(insertQuery)){
            pstmt.setString(1,SSN);
            pstmt.setString(2,FirstName);
            pstmt.setString(3,LastName);
            pstmt.setString(4,DateOfBirth);
            pstmt.setString(5,Gender);
            pstmt.executeUpdate();
        }


    }

    public void updateIntoPatientTable(String FirstName, String LastName, String Gender) throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://den1.mysql3.gear.host:3306/nursinghome",
                "nursinghome", "Vw3J!60l-0kd");
        String updateQuery = "UPDATE patient set FirstName = ?, LastName = ?, Gender = ? WHERE id = ?";
                 try (PreparedStatement ptsmt = connection.prepareStatement(updateQuery)){
                     ptsmt.setString(1,FirstName);
                     ptsmt.setString(2,LastName);
                     ptsmt.setString(3,Gender);
                     ptsmt.executeUpdate();

                 } catch (SQLException e){
                     System.out.println(e.getMessage());

                 }

    }
    public void deleteFromPatientTable(String SSN, String FirstName, String LastName, String DateOfBirth, String Gender) throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://den1.mysql3.gear.host:3306/nursinghome",
                "nursinghome", "Vw3J!60l-0kd");

        String deleteQuery =  "DELETE FROM patient WHERE SSN = ?";
              try (PreparedStatement ptsmt = connection.prepareStatement(deleteQuery)){
                 ptsmt.setString(1,SSN);
                 ptsmt.setString(2,FirstName);
                 ptsmt.setString(3,LastName);
                 ptsmt.setString(4,DateOfBirth);
                 ptsmt.setString(5,Gender);
                 ptsmt.executeUpdate();



    }       catch(SQLException e){
                  System.out.println(e.getMessage());

              }
    }

    public ArrayList<Patient> getPatientsinfo() {
        return patientsinfo;
    }
}
