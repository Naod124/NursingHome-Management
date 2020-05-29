package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sample.databaseConnection.StaffQueries;
import sample.model.AlertMaker;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;

public class ForgotPasswordController implements Initializable {

    @FXML
    private TextField emailtxtfield;

    @FXML
    private PasswordField newPassword;

    @FXML
    private TextField resettxtfield;

    @FXML
    private Button confirmButon;

    @FXML
    private PasswordField verifypassword;

    @FXML
    private Button sendButton;


    private SwitchScene sc = new SwitchScene();
    private AlertMaker alertMaker = new AlertMaker();
    Alert a = new Alert(Alert.AlertType.INFORMATION);
    Alert f = new Alert(Alert.AlertType.ERROR);


    Random rand = new Random();

    int randomCode = rand.nextInt(999999);


    @FXML
    public void send() throws SQLException {

        try {
            StaffQueries sq = new StaffQueries();
            sq.sendEmail(emailtxtfield.getText());

            String Host = "smtp.gmail.com";
            String User = "nursingHomeManagement@gmail.com";
            String pass = "nursingHome10";
            String to = emailtxtfield.getText();
            String Subject = "Reset code";
            String message = "Your reset code is " + randomCode;
            boolean sessionDebug = false;

            Properties props = new Properties();
            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
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
            alertMaker.infoAlert("Reset code has been sent to your email", "Successfully!");
        } catch (MessagingException mx) {
            System.out.println(mx.getMessage());
            alertMaker.errorAlert("Sorry, the desired information could not be sent!" + "\n" + "Try again...","Error!");
        }

    }


    @FXML
    public void confirmButton() {

        int b = Integer.parseInt(resettxtfield.getText());



        if (randomCode == b) {

            if (newPassword.getText().equals(verifypassword.getText())) {
                StaffQueries sq = new StaffQueries();
                sq.newPassword(emailtxtfield.getText(), verifypassword.getText());
                alertMaker.infoAlert("Password Updated!","Successfully!");

                emailtxtfield.clear();
                resettxtfield.clear();
                newPassword.clear();
                verifypassword.clear();

            } else {
                alertMaker.errorAlert("Password does not match","Error!");
            }
        } else {
            alertMaker.errorAlert("It doesn't match with the one sent to your email","Reset code does not match");
        }


    }

    @FXML
    public void signOut(ActionEvent ae) throws IOException {
        sc.newScene(ae, "../view/LogIn.fxml");
    }

    @FXML
    public void back(ActionEvent ae) throws IOException {
        sc.newScene(ae, "../view/logInPanel.fxml");

    }

    @FXML
    public void exit() {
        System.exit(0);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        final Tooltip tooltipEmail = new Tooltip();
        tooltipEmail.setText("Enter your Email where you will receive a reset code. Email shall be your username.");
        emailtxtfield.setTooltip(tooltipEmail);

        final Tooltip tooltipSend = new Tooltip();
        tooltipSend.setText("Press this button after you entered your email to receive a reset code so you can create new password");
        sendButton.setTooltip(tooltipSend);

        final Tooltip tooltipNewPassword = new Tooltip();
        tooltipNewPassword.setText("Enter a the new password");
        newPassword.setTooltip(tooltipNewPassword);

        final Tooltip tooltipReset = new Tooltip();
        tooltipReset.setText("Check your email and enter the reset code you have received there");
        resettxtfield.setTooltip(tooltipReset);

        final Tooltip tooltipVerifyPassword = new Tooltip();
        tooltipVerifyPassword.setText("Repeat the password you entered for safety reason");
        verifypassword.setTooltip(tooltipVerifyPassword);

        final Tooltip tooltipConfirm = new Tooltip();
        tooltipConfirm.setText("Press this button when you have filled up all these textfields to update your password");
        confirmButon.setTooltip(tooltipConfirm);

    }
}