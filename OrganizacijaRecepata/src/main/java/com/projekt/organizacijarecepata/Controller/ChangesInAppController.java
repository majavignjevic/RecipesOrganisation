package com.projekt.organizacijarecepata.Controller;

import com.projekt.organizacijarecepata.Agent.ChangesInAppAgent;
import com.projekt.organizacijarecepata.Exceptions.EmptyFileException;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controller class for the ChangesInApp view.
 */
public class ChangesInAppController implements Initializable {
    @FXML
    private TableView<String> changedObjectsTableView;

    @FXML
    private TableColumn<String, String> changeTableColumn;

    @FXML
    private AnchorPane navigation;

    @FXML
    private AnchorPane navigation_admin;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (LoginController.isAdmin) {
            navigation.setVisible(false);
            navigation_admin.setVisible(true);
        } else {
            navigation.setVisible(true);
            navigation_admin.setVisible(false);
        }

        try {
            List<String> changesRead = ChangesInAppAgent.getChanges();
            if (changesRead.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No Recent Changes");
                alert.setHeaderText("Warning");
                alert.setContentText("There are no recent changes available.");
                alert.showAndWait();
                throw new EmptyFileException("File is empty");
            }
            changeTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));
            changedObjectsTableView.getItems().setAll(changesRead);
            Thread refreshThread = new Thread(() -> {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Platform.runLater(() -> initialize(null, null));
            });

            refreshThread.start();
        } catch (EmptyFileException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("An error occurred");
            alert.setContentText("Failed to retrieve changes: " + e.getMessage());
            alert.showAndWait();
        }
    }

}
