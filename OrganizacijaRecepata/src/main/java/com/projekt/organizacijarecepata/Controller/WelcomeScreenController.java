package com.projekt.organizacijarecepata.Controller;

import com.projekt.organizacijarecepata.Base.Database;
import com.projekt.organizacijarecepata.Threads.ReadLastChangeThread;
import com.projekt.organizacijarecepata.entiteti.Person;
import com.projekt.organizacijarecepata.entiteti.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controller class for the WelcomeScreen view.
 */

public class WelcomeScreenController implements Initializable {

    @FXML
    private AnchorPane navigation;

    @FXML
    private AnchorPane navigation_admin;

    public static Person activePerson;

    public static User activeUser;


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
        try {
            List<Person> personwithUsername = Database.getPersonwithUsername();
            List<User> userList = Database.getUsers();
            activeUser = userList.stream().filter(user -> user.getUsername().equals(LoginController.user.getUsername())).findFirst().get();
            activePerson = Database.getActivePerson(activeUser);
        }catch (SQLException e){
            e.printStackTrace();
        }
        ReadLastChangeThread readLastChangeThread = new ReadLastChangeThread();
        readLastChangeThread.start();
    }
}
