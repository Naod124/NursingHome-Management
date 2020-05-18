package sample.databaseConnection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.model.*;

import java.sql.*;
import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class PatientQueries {

    private Connection connection;
    private ResultSet rs;
    private Statement stmt;
    private PreparedStatement pstmt;

    private ArrayList<sample.model.Patient> patientsinfo;

    ObservableList<PatientTable> patients = FXCollections.observableArrayList();

    private ObservableList<Object> obList = FXCollections.observableArrayList();
    private ObservableList<Object> obzList = FXCollections.observableArrayList();
    private ObservableList<Object> obfList = FXCollections.observableArrayList();

    public PatientQueries() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://den1.mysql3.gear.host:3306/nursinghome",
                    "nursinghome", "Vw3J!60l-0kd");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        patientsinfo = new ArrayList<>();
    }

    public void viewPatientTable() throws SQLException {

        String selectQuery = "SELECT * FROM PATIENT;";

        try {
            connection = DriverManager.getConnection("jdbc:mysql://den1.mysql3.gear.host:3306/nursinghome",
                    "nursinghome", "Vw3J!60l-0kd");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            rs = connection.createStatement().executeQuery(selectQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                if (!rs.next())
                    break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            PatientTable pt = new PatientTable("SSN", "FirstName", "LastName", "DateOfBirth", "Gender", "FreeTime");

            try {
                pt.setSsn(rs.getString("SSN"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                pt.setFirstName(rs.getString("FirstName"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                pt.setLastName(rs.getString("LastName"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                pt.setDateOfBirth(rs.getString("DateOfBirth"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                pt.setGender(rs.getString("Gender"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                pt.setFreeTime(rs.getString("FreeTime"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            obList.add(pt);
            setObList(obList);
        }

    }

    public void insertIntoPatientTable(String SSN, String FirstName, String LastName, String DateOfBirth, String Gender)
            throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://den1.mysql3.gear.host:3306/nursinghome", "nursinghome",
                "Vw3J!60l-0kd");

        String insertQuery = "INSERT INTO patient(SSN, FirstName, LastName, DateOfBirth, Gender) VALUES(?,?,?,?,?);";

        try (PreparedStatement pstmt = connection.prepareStatement(insertQuery)) {
            pstmt.setString(1, SSN);
            pstmt.setString(2, FirstName);
            pstmt.setString(3, LastName);
            pstmt.setString(4, DateOfBirth);
            pstmt.setString(5, Gender);
            pstmt.executeUpdate();
        }

    }

    public void updateIntoPatientTable(String FirstName, String LastName, String Date, String Gender, String SSN)
            throws SQLException {

        connection = DriverManager.getConnection("jdbc:mysql://den1.mysql3.gear.host:3306/nursinghome", "nursinghome",
                "Vw3J!60l-0kd");
        String updateQuery = "UPDATE patient SET FirstName = ?,LastName = ?,DateOfBirth = ?, Gender = ?  WHERE SSN = ?";
        pstmt = connection.prepareStatement(updateQuery);
        pstmt.setString(1, FirstName);
        pstmt.setString(2, LastName);
        pstmt.setString(3, Date);
        pstmt.setString(4, Gender);
        pstmt.setString(5, SSN);
        pstmt.executeUpdate();

    }

    public void deleteFromPatientTable(String SSN, String FirstName, String LastName, String DateOfBirth, String Gender)
            throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://den1.mysql3.gear.host:3306/nursinghome", "nursinghome",
                "Vw3J!60l-0kd");

        String deleteQuery = "DELETE FROM patient WHERE SSN = ?";
        try (PreparedStatement ptsmt = connection.prepareStatement(deleteQuery)) {
            ptsmt.setString(1, SSN);
            ptsmt.setString(2, FirstName);
            ptsmt.setString(3, LastName);
            ptsmt.setString(4, DateOfBirth);
            ptsmt.setString(5, Gender);
            ptsmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }

    public void removePatient(String ssn) throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://den1.mysql3.gear.host:3306/nursinghome", "nursinghome",
                "Vw3J!60l-0kd");
        String updateQuery = "DELETE FROM patient WHERE SSN = ?";
        pstmt = connection.prepareStatement(updateQuery);
        pstmt.setString(1, ssn);
        pstmt.executeUpdate();
    }

    public void scheduleView() {
        ObservableList<PatientTable> patients = FXCollections.observableArrayList();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://den1.mysql3.gear.host:3306/nursinghome",
                    "nursinghome", "Vw3J!60l-0kd");
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM schedule;");
            while (rs.next()) {
                patients.add(new PatientTable(rs.getString("patient_name"), rs.getString("time_from"),
                        rs.getString("time_to"), rs.getString("description")));
            }
            FXCollections.sort(patients);
            setPatients(patients);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void sortByName() {

        String selectQuery = "SELECT * FROM PATIENT order by FirstName";

        try {
            connection = DriverManager.getConnection("jdbc:mysql://den1.mysql3.gear.host:3306/nursinghome",
                    "nursinghome", "Vw3J!60l-0kd");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            rs = connection.createStatement().executeQuery(selectQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                if (!rs.next())
                    break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            PatientTable pt = new PatientTable("SSN", "FirstName", "LastName", "DateOfBirth", "Gender");

            try {
                pt.setSsn(rs.getString("SSN"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                pt.setFirstName(rs.getString("FirstName"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                pt.setLastName(rs.getString("LastName"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                pt.setDateOfBirth(rs.getString("DateOfBirth"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                pt.setGender(rs.getString("Gender"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            obzList.add(pt);
            setObzList(obzList);
        }
    }

    public void sortZtoA() {

        String selectQuery = "SELECT * FROM PATIENT order by FirstName desc";

        try {
            connection = DriverManager.getConnection("jdbc:mysql://den1.mysql3.gear.host:3306/nursinghome",
                    "nursinghome", "Vw3J!60l-0kd");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            rs = connection.createStatement().executeQuery(selectQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                if (!rs.next())
                    break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            PatientTable pt = new PatientTable("SSN", "FirstName", "LastName", "DateOfBirth", "Gender");

            try {
                pt.setSsn(rs.getString("SSN"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                pt.setFirstName(rs.getString("FirstName"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                pt.setLastName(rs.getString("LastName"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                pt.setDateOfBirth(rs.getString("DateOfBirth"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                pt.setGender(rs.getString("Gender"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            obfList.add(pt);
            setObzList(obfList);
        }
    }

    public void AssignNursetoPatient(String PatientSSN, String time, String nurseSSN, String Freetime)
            throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://den1.mysql3.gear.host:3306/nursinghome", "nursinghome",
                "Vw3J!60l-0kd");
        String updateQuery = "INSERT INTO timetable(appointed_time, occupiedby, SSN) VALUES(?,?,?);";
        pstmt = connection.prepareStatement(updateQuery);
        pstmt.setString(1, time);
        pstmt.setString(2, nurseSSN);
        pstmt.setString(3, PatientSSN);
        pstmt.executeUpdate();

        // update the free time of patient
        String updateQuery2 = "UPDATE patient SET FreeTime = ? WHERE SSN = ?;";
        PreparedStatement pstmt1 = connection.prepareStatement(updateQuery2);
        pstmt1.setString(1, Freetime);
        pstmt1.setString(2, PatientSSN);
        pstmt1.executeUpdate();

    }

    public int getNurseSSN(String username, String password) throws SQLException {
        int id = 0;
        connection = DriverManager.getConnection("jdbc:mysql://den1.mysql3.gear.host:3306/nursinghome", "nursinghome",
                "Vw3J!60l-0kd");
        String selectQuery = "SELECT staff_SSN FROM login where Username =? AND Password =?;";
        PreparedStatement statement = connection.prepareStatement(selectQuery);
        statement.setString(1, username);
        statement.setString(2, password);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            id = rs.getInt("staff_SSN");
        }

        return id;
    }


    // for getting item from the database
    public ArrayList<AssignTable> viewAssigneTable() throws SQLException {
        ArrayList<AssignTable> list = new ArrayList<>();
        // select query for getting data from patient and their diagnoses from diagnose table
        String selectQuery = "SELECT * FROM patient,timetable where patient.SSN = timetable.SSN;";

        try {
            connection = DriverManager.getConnection("jdbc:mysql://den1.mysql3.gear.host:3306/nursinghome",
                    "nursinghome", "Vw3J!60l-0kd");
            ResultSet resultSet = connection.createStatement().executeQuery(selectQuery);

            while (resultSet.next()) {
                // creating object and setting data
                AssignTable asign = new AssignTable("", "", "", "", "", "", "");
                asign.setSsn(resultSet.getString("SSN"));
                asign.setFirstName(resultSet.getString("FirstName"));
                asign.setLastName(resultSet.getString("LastName"));
                asign.setDateOfBirth(resultSet.getString("DateOfBirth"));
                asign.setGender(resultSet.getString("Gender"));
                asign.setAppointed_time(resultSet.getString("appointed_time"));
                asign.setOccupiedby(resultSet.getString("occupiedby"));

                list.add(asign);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void scheduleTruncateThread() {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Europe/Copenhagen"));
        ZonedDateTime nextRun = now.withHour(0).withMinute(0).withSecond(0);
        if (now.compareTo(nextRun) == 1) {
            nextRun = nextRun.plusDays(1);

        }

        Duration duration = Duration.between(now, nextRun);
        long initalDelay = duration.getSeconds();
        StaffTable p = new StaffTable();

        ScheduledExecutorService pool =  Executors.newScheduledThreadPool(1);
        pool.scheduleAtFixedRate(p, initalDelay, TimeUnit.DAYS.toSeconds(1), TimeUnit.SECONDS);

    }

    public void truncateSchedule() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://den1.mysql3.gear.host:3306/nursinghome", "nursinghome",
                "Vw3J!60l-0kd");
        String selectQuery = "TRUNCATE schedule;";
        Statement statement = connection.createStatement();
        statement.executeUpdate(selectQuery);
    }




    public ObservableList<Object> getObList() {
        return obList;
    }

    public void setObList(ObservableList<Object> obList) {
        this.obList = obList;
    }

    public ObservableList<Object> getObzList() {
        return obzList;
    }

    public void setObzList(ObservableList<Object> obzList) {
        this.obzList = obzList;
    }

    public ObservableList<Object> getObfList() {
        return obfList;
    }

    public void setObfList(ObservableList<Object> obfList) {
        this.obfList = obfList;
    }

    public ObservableList<PatientTable> getPatients() {
        return patients;
    }

    public void setPatients(ObservableList<PatientTable> patients) {
        this.patients = patients;
    }

    public ArrayList<Patient> getPatientsinfo() throws SQLException {
        getAllPatientsToArrayList();
        return patientsinfo;
    }

    public void getAllPatientsToArrayList() throws SQLException {
        Connection connect = DriverManager.getConnection(Connect.CONNECTION_URL, Connect.DB_NAME, Connect.PASSWORD);
        PreparedStatement statement = connect.prepareStatement("select * From patient;");
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            patientsinfo.add(
                    new Patient(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
        }

    }

}
