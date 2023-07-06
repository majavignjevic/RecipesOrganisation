package com.projekt.organizacijarecepata.Controller;

import com.projekt.organizacijarecepata.Agent.LoginAgent;
import com.projekt.organizacijarecepata.Exceptions.UncorrectLoginException;
import com.projekt.organizacijarecepata.Service.Run;
import com.projekt.organizacijarecepata.entiteti.LoggerPrinter;
import com.projekt.organizacijarecepata.entiteti.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private TextField korisnickoImeTextField;

    @FXML
    private PasswordField passwordField;

    public static Boolean isAdmin;
    public static User user;
    private LoggerPrinter<LoginController> loggerPrinter;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<User> userList = LoginAgent.getUsers();
        loggerPrinter = new LoggerPrinter<>(this);
    }

    @FXML
    public void prijava() {
        try {
            String username = korisnickoImeTextField.getText();
            String password = passwordField.getText();

            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(password.getBytes());
            String passwordHash = new String(messageDigest.digest());
            user = new User(username, passwordHash);

            Boolean check = LoginAgent.checkUser(user);
            if (check) {
                if (user.getUsername().equals("admin")) {
                    isAdmin = true;
                } else {
                    isAdmin = false;
                }

                Run.getFXML("/com/projekt/organizacijarecepata/Controller/welcomeScreen.fxml", "Login successful!");
                loggerPrinter.logInfo("User " + username + " has successfully logged in");
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Pozor");
                alert.setHeaderText("");
                alert.setContentText("Netočni podaci za login");
                alert.showAndWait();
                loggerPrinter.logError("User " + username + " has not successfully logged in");
                throw new UncorrectLoginException("User " + username + " has not successfully logged in");
            }
        } catch (IOException | NoSuchAlgorithmException exception) {
            System.out.println(exception.getMessage());
        } catch (UncorrectLoginException e) {
        }
    }
}
