package com.projekt.organizacijarecepata.Controller;

import com.projekt.organizacijarecepata.Service.Run;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class for the Navigation view.
 */

public class NavigationController implements Initializable {
    @FXML
    public void logout() throws IOException{
        Run.getFXML("/com/projekt/organizacijarecepata/Controller/login.fxml", "View all recepies");
    }

    @FXML
    public void viewAllRecepies() throws IOException{
        Run.getFXML("/com/projekt/organizacijarecepata/Controller/viewAllRecepies.fxml", "View all recepies");
    }

    @FXML
    public void viewMyRecepies() throws IOException{
        Run.getFXML("/com/projekt/organizacijarecepata/Controller/viewMyRecepies.fxml", "My recepies");
    }

    @FXML
    public void updateMyRecepies() throws IOException{
        Run.getFXML("/com/projekt/organizacijarecepata/Controller/updateMyRecepies.fxml", "Update my recepies");
    }

    @FXML
    public void deleteMyRecepies() throws IOException{
        Run.getFXML("/com/projekt/organizacijarecepata/Controller/deleteMyRecepies.fxml", "Delete my recepie");
    }

    @FXML
    public void insertNewRecepies() throws IOException{
        Run.getFXML("/com/projekt/organizacijarecepata/Controller/insertNewRecepies.fxml", "Add a recepie");
    }

    @FXML
    public void profile() throws IOException{
        Run.getFXML("/com/projekt/organizacijarecepata/Controller/profile.fxml", "My profile");
    }

    @FXML
    public void updateMyProfile() throws IOException{
        Run.getFXML("/com/projekt/organizacijarecepata/Controller/updateMyProfile.fxml", "Update my profile");
    }

    @FXML
    public void insertNewHealthyRecepie() throws IOException{
        Run.getFXML("/com/projekt/organizacijarecepata/Controller/insertNewHealthyRecepie.fxml", "Add a healthy recepie");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
