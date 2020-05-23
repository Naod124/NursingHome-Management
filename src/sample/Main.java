package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.databaseConnection.Connect;
import sample.databaseConnection.PatientQueries;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("/sample/view/admin.fxml"));
        primaryStage.setTitle("Nursing Home System");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        PatientQueries pq = new PatientQueries();
        pq.scheduleTruncateThread();
    }

    @Override
    public void init() throws Exception {
        super.init();
        if(!Connect.getInstance().open()){
            System.out.println("Fatal error: Could not connect to database");
            Platform.exit();
        }
        else {
            System.out.println("Database connected!");
        }
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        Connect.getInstance().close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}