package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class NurseController {

    @FXML
    private Button managepatientbutton;
    @FXML
    private Button removepatientbutton;
   @FXML private Button exitbutton;
   @FXML private Button backbutton;

    private SwitchScene sc = new SwitchScene();


    @FXML
    public void managepatientscene(ActionEvent ae) throws IOException {
        Node node = (Node) ae.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample/view/ManagePatient.fxml"));
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
    public void removescene(ActionEvent ae) throws IOException {
        Node node = (Node) ae.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample/view/RemovePatient.fxml"));
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
    public void back(ActionEvent ae) throws IOException {
        Node node = (Node) ae.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/LogIn.fxml"));
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


