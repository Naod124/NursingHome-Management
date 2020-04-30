package sample.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
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

        table.setEditable(true);
        ssncol.setEditable(true);


    }

    public void statements(){
    try {
        conn = DriverManager.getConnection("jdbc:mysql://den1.mysql3.gear.host:3306/nursinghome",
                "nursinghome", "Vw3J!60l-0kd");
        pstmt.setString(1,ssntextfield.getText());
        pstmt.setString(2,firstnametextfield.getText());
        pstmt.setString(3,lastnametextfield.getText());
        pstmt.setString(4,datetextfield.getText());
        pstmt.setString(5,gendertextfield.getText());
        pstmt.executeUpdate();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setHeaderText("Information updated");
        a.showAndWait();


    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    }

    @FXML
    public void handleAdd(ActionEvent ae){
      /*  table.getItems().clear();
        if (ae.getSource() == addbutton){

            for (int i = 0; i < DataSource.getInstance().getPatient().size(); i++) {
                obList.add(new PatientTable(DataSource.getInstance().getPatient().get(i).getSSN(),
                        DataSource.getInstance().getPatient().get(i).getFirstName(),
                        DataSource.getInstance().getPatient().get(i).getLastName(),
                        DataSource.getInstance().getPatient().get(i).getDob(),
                        DataSource.getInstance().getPatient().get(i).getGender()));
            }
            table.setItems(obList);
            refreshDataSource();

        }
*/
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



      @FXML public void handleView() throws SQLException {
          ResultSet rs;
          String selectQuery = "SELECT * FROM PATIENT;";

          conn = DriverManager.getConnection("jdbc:mysql://den1.mysql3.gear.host:3306/nursinghome",
                  "nursinghome", "Vw3J!60l-0kd");
          rs = conn.createStatement().executeQuery(selectQuery);


          ssncol.setCellValueFactory(new PropertyValueFactory<>("Ssn"));
          firstnamecol.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
          lastnamecol.setCellValueFactory(new PropertyValueFactory<>("LastName"));
          dobcol.setCellValueFactory(new PropertyValueFactory<>("DateOfBirth"));
          gendercol.setCellValueFactory(new PropertyValueFactory<>("Gender"));


          while (rs.next()){
              PatientTable pt = new PatientTable("SSN", "FirstName", "LastName","DateOfBirth","Gender");


              pt.setSsn(rs.getString("SSN"));
              pt.setFirstName(rs.getString("FirstName"));
              pt.setLastName(rs.getString("LastName"));
              pt.setDateOfBirth(rs.getString("DateOfBirth"));
              pt.setGender(rs.getString("Gender"));

              obList.add(pt);

          }

          table.setItems(obList);



         /* PatientQueries p = new PatientQueries();

          DataSource.getInstance().setPatient(p.getPatientsinfo());

        for (int i = 0; i < DataSource.getInstance().getPatient().size(); i++) {
                        obList.addAll(new PatientTable(
                                DataSource.getInstance().getPatient().get(i).getSSN(),
                                DataSource.getInstance().getPatient().get(i).getFirstName() ,
                                       DataSource.getInstance().getPatient().get(i).getLastName(),
                                DataSource.getInstance().getPatient().get(i).getDob(),
                                DataSource.getInstance().getPatient().get(i).getGender()));

      }
          table.getItems().addAll(obList);
*/}

    @FXML
    public void back(ActionEvent ae) throws IOException {
        Node node = (Node) ae.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/nurse.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            System.out.println("Hi");
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    public void exit(ActionEvent ae) throws IOException {
        System.exit(0);

    }

}