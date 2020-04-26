package sample.controller;

import com.jfoenix.controls.JFXCheckBox;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import sample.model.Staff;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RemoveEmployeeController implements Initializable {
    @FXML
    private JFXCheckBox all;
    @FXML
    private JFXCheckBox doctor;
    @FXML
    private JFXCheckBox nurse;
    @FXML
    private JFXCheckBox planer;
    @FXML
    private JFXCheckBox assistant;
    @FXML
    private ComboBox<String> staffToView;
    @FXML
    private ListView<String> employees;
    ArrayList<Staff> staff = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

//        staffToView.setValue("Doctors");
//        staffToView.setValue("Nurses");
//        staffToView.setValue("Planers");
//        staffToView.setValue("Assistants");
//        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("employees.ser"))) {
//            staff = (ArrayList<Staff>) objectInputStream.readObject();
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }

    }
}

