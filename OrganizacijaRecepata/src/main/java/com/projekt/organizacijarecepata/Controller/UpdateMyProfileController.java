package com.projekt.organizacijarecepata.Controller;

import com.projekt.organizacijarecepata.Base.Database;
import com.projekt.organizacijarecepata.entiteti.Person;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Controller class for the UpdateMyProfile view.
 */

public class UpdateMyProfileController implements Initializable {

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField surnameTextField;

    @FXML
    private DatePicker DOBDatePicker;

    @FXML
    private AnchorPane navigation;

    @FXML
    private AnchorPane navigation_admin;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(LoginController.isAdmin){
            navigation.setVisible(false);
            navigation_admin.setVisible(true);
        }
        else if(LoginController.isAdmin==false) {
            navigation.setVisible(true);
            navigation_admin.setVisible(false);
        }
        usernameTextField.setText(WelcomeScreenController.activeUser.getUsername());
        nameTextField.setText(WelcomeScreenController.activePerson.getName());
        surnameTextField.setText(WelcomeScreenController.activePerson.getSurname());
        DOBDatePicker.setValue(WelcomeScreenController.activePerson.getDOB());
    }

    @FXML
    public void update(){
        String newName = nameTextField.getText();
        String newSurname = surnameTextField.getText();
        LocalDate newDOB = DOBDatePicker.getValue();
        Boolean changed = false;

        Person person;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Update my profile");
        alert.setHeaderText("Confirm Update");
        alert.setContentText("Are you sure you want to update your profile?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {

            if (!newName.isBlank() && !newName.equals(WelcomeScreenController.activePerson.getName())) {
                WelcomeScreenController.activePerson.setName(newName);
                changed = true;
            }
            if (!newSurname.isBlank() && !newSurname.equals(WelcomeScreenController.activePerson.getSurname())) {
                WelcomeScreenController.activePerson.setSurname(newSurname);
                changed = true;
            }
            if (newDOB != null && !newDOB.equals(WelcomeScreenController.activePerson.getDOB())) {
                WelcomeScreenController.activePerson.setDOB(newDOB);
                changed = true;
            }
            if (changed) {
                try {
                    Database.updatePerson(WelcomeScreenController.activePerson);
                } catch (SQLException | IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
 }
