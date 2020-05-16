package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import sample.databaseConnection.StaffQueries;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Random;

public class ResetPasswordController   {

    @FXML
    private TextField emailtxtfield;
    SwitchScene sc = new SwitchScene();



    Random rand = new Random();

    int randomCode = rand.nextInt(999999);


    @FXML public void send() throws SQLException {

       try{
        StaffQueries sq = new StaffQueries();
        sq.sendEmail(emailtxtfield.getText());

            String Host = "smtp.gmail.com";
            String User = "nursingHomeManagement@gmail.com";
            String pass = "nursingHome10";
            String to = emailtxtfield.getText();
            String Subject = "Forgot password";
            String message = "Your password is " + sq.getPassword();
            boolean sessionDebug = false;

            Properties props = new Properties();
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "host");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");

            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(User));
            InternetAddress[] addresses = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, addresses);
            msg.setSubject(Subject);
            msg.setText(message);
            Transport transport = mailSession.getTransport("smtp");
            transport.connect(Host, User, pass);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();

            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("Your password has been sent to your email");
            a.showAndWait();


        } catch (MessagingException mx){
           Alert a = new Alert(Alert.AlertType.ERROR);
           a.setTitle("Error!");
           a.setContentText("Sorry, the desired information could not be sent!"+"\n"+"Try again...");

        }
    }

    @FXML public void signOut(ActionEvent ae) throws IOException {
        sc.newScene(ae, "../view/LogIn.fxml");
    }

    @FXML public void back(ActionEvent ae) throws IOException {
        sc.newScene(ae, "../view/logInPanel.fxml");

    }
    @FXML public void exit(){
        System.exit(0);

    }


}