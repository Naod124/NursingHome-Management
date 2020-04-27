package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.databaseConnection.Connect;
import sample.databaseConnection.Medcin;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Connect conn = new Connect();
        //conn.open();


        Parent root = FXMLLoader.load(getClass().getResource("/view/LogIn.fxml"));
        primaryStage.setTitle("Nursing Home System");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
