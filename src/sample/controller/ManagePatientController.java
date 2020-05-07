package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.databaseConnection.PatientQueries;
import sample.model.DataSource;
import sample.model.PatientTable;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ManagePatientController implements Initializable {
    private ObservableList<PatientTable> obList = FXCollections.observableArrayList();
    private Connection conn;
    PreparedStatement pstmt = null;
   private ResultSet rs;

    @FXML private TableView <PatientTable> table;
    @FXML private TableColumn <PatientTable, String> ssncol;
    @FXML private TableColumn <PatientTable, String> firstnamecol;
    @FXML private TableColumn <PatientTable, String> lastnamecol;
    @FXML private TableColumn <PatientTable, String> dobcol;
    @FXML private TableColumn <PatientTable, String> gendercol;
    @FXML private TextField ssntextfield;
    @FXML private TextField firstnametextfield;
    @FXML private TextField lastnametextfield;
    @FXML private TextField datetextfield;
    @FXML private TextField gendertextfield;
    private SwitchScene sc = new SwitchScene();


    public void refreshDataSource(){
        try {
            PatientQueries p = new PatientQueries();
            p.viewPatientTable();

            DataSource.getInstance().setPatient(p.getPatientsinfo());
        }
        catch (Exception e){
            System.out.println(e.getMessage());

        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            handleView();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void handleAdd(ActionEvent ae){
      try{
          conn = DriverManager.getConnection("jdbc:mysql://den1.mysql3.gear.host:3306/nursinghome",
                  "nursinghome", "Vw3J!60l-0kd");
          String insertQuery = "INSERT INTO patient(SSN, FirstName, LastName, DateOfBirth, Gender) VALUES(?,?,?,?,?);";
         pstmt = conn.prepareStatement(insertQuery);
          pstmt.setString(1,ssntextfield.getText());
          pstmt.setString(2,firstnametextfield.getText());
          pstmt.setString(3,lastnametextfield.getText());
          pstmt.setString(4,datetextfield.getText());
          pstmt.setString(5,gendertextfield.getText());
          pstmt.executeUpdate();
          Alert a = new Alert(Alert.AlertType.CONFIRMATION);
          a.setHeaderText("Information added");
          a.showAndWait();
         table.getItems().clear();
         handleView();
          ssntextfield.clear();
          firstnametextfield.clear();
          lastnametextfield.clear();
          datetextfield.clear();
          gendertextfield.clear();

      } catch (SQLException e) {
          System.out.println(e.getMessage());
      }
    }



      @FXML public void handleupdate(){

        try{
            conn = DriverManager.getConnection("jdbc:mysql://den1.mysql3.gear.host:3306/nursinghome",
                    "nursinghome", "Vw3J!60l-0kd");
            String updateQuery = "UPDATE patient SET FirstName = ?,LastName = ?,DateOfBirth = ?, Gender = ?  WHERE SSN = ?";
            pstmt = conn.prepareStatement(updateQuery);
            pstmt.setString(1,firstnametextfield.getText());
            pstmt.setString(2,lastnametextfield.getText());
            pstmt.setString(3,datetextfield.getText());
            pstmt.setString(4,gendertextfield.getText());
            pstmt.setString(5,ssntextfield.getText());
            pstmt.executeUpdate();
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setHeaderText("Information updated");
            a.showAndWait();
            table.getItems().clear();
            handleView();
            ssntextfield.clear();
            firstnametextfield.clear();
            lastnametextfield.clear();
            datetextfield.clear();
            gendertextfield.clear();
    } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
      }



       public void handleView() throws SQLException {
          String selectQuery = "SELECT * FROM PATIENT;";

          try {
              conn = DriverManager.getConnection("jdbc:mysql://den1.mysql3.gear.host:3306/nursinghome",
                      "nursinghome", "Vw3J!60l-0kd");
          } catch (SQLException e) {
              e.printStackTrace();
          }
          try {
              rs = conn.createStatement().executeQuery(selectQuery);
          } catch (SQLException e) {
              e.printStackTrace();
          }


          ssncol.setCellValueFactory(new PropertyValueFactory<>("Ssn"));
          firstnamecol.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
          lastnamecol.setCellValueFactory(new PropertyValueFactory<>("LastName"));
          dobcol.setCellValueFactory(new PropertyValueFactory<>("DateOfBirth"));
          gendercol.setCellValueFactory(new PropertyValueFactory<>("Gender"));


          while (true){
              try {
                  if (!rs.next()) break;
              } catch (SQLException e) {
                  e.printStackTrace();
              }
              PatientTable pt = new PatientTable("SSN", "FirstName", "LastName","DateOfBirth","Gender");


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

              obList.add(pt);

          }

          table.setItems(obList);

}

    @FXML
    public void back(ActionEvent ae) throws IOException {
        sc.newScene(ae,"/sample/view/nurse.fxml");
    }

    @FXML
    public void exit(ActionEvent ae) throws IOException {
        System.exit(0);

    }

    public void help(ActionEvent actionEvent) {
    }
}