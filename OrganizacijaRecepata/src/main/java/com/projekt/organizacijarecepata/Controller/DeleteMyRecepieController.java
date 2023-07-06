package com.projekt.organizacijarecepata.Controller;

import com.projekt.organizacijarecepata.Base.Database;
import com.projekt.organizacijarecepata.entiteti.Recept;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Controller class for the DeleteMyRecepie view.
 */

public class DeleteMyRecepieController implements Initializable {
    @FXML
    ListView<Recept> receptListView;

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
        else if(LoginController.isAdmin==false){
            navigation.setVisible(true);
            navigation_admin.setVisible(false);
        }

        List<Recept> recepiesList = new ArrayList<>();

        try{
            recepiesList = Database.getRecepies();
        }catch (SQLException | IOException e){
            e.printStackTrace();
        }

        recepiesList = recepiesList.stream().filter(recept -> recept.getAuthor().getPersonId().equals(WelcomeScreenController.activePerson.getId())).collect(Collectors.toList());

        receptListView.setItems(FXCollections.observableList(recepiesList));
    }

    @FXML
    public void delete() {
        Recept chosenRecipe = receptListView.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Recipe");
        alert.setHeaderText("Confirm Deletion");
        alert.setContentText("Are you sure you want to delete the selected recipe?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                Database.deleteRecepie(chosenRecipe);

                Thread refreshThread = new Thread(() -> {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Platform.runLater(() -> {
                        initialize(null, null);
                    });
                });

                refreshThread.start();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
