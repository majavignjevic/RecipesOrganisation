package com.projekt.organizacijarecepata.Controller;

import com.projekt.organizacijarecepata.Base.Database;
import com.projekt.organizacijarecepata.entiteti.Person;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Controller class for the MyProfile view.
 */

public class MyProfileController implements Initializable {
    @FXML
    private TextField usernameTextField;

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

    public static Person activePerson;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(LoginController.isAdmin){
            navigation.setVisible(false);
            navigation_admin.setVisible(true);
        }
        else if(LoginController.isAdmin==false){
            navigation.setVisible(true);
            navigation_admin.setVisible(false);
        }

        usernameTextField.setText(WelcomeScreenController.activeUser.getUsername());
        try{
            activePerson = Database.getActivePerson(LoginController.user);
            activePerson.setUsername(LoginController.user.getUsername());
            activePerson.setPassword(LoginController.user.getPassword());
        }catch (SQLException e){
            e.printStackTrace();
        }

        surnameTextField.setText(activePerson.getSurname());
        nameTextField.setText(activePerson.getName());
        DOBDatePicker.setValue(activePerson.getDOB());
    }

}
