package com.projekt.organizacijarecepata.Controller;

import com.projekt.organizacijarecepata.Base.Database;
import com.projekt.organizacijarecepata.entiteti.Category;
import com.projekt.organizacijarecepata.entiteti.MainIngredient;
import com.projekt.organizacijarecepata.entiteti.Recept;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Logger;

/**
 * Controller class for the UpdateMyRecepies view.
 */

public class UpdateMyRecepiesController implements Initializable {
    @FXML
    TextField recepieNameTextField;

    @FXML
    ComboBox<Category> categoryComboBox;

    @FXML
    ComboBox<Recept> receptComboBox;

    @FXML
    ComboBox<MainIngredient> ingredientComboBox;

    @FXML
    TextArea textArea;

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
        List<Recept> receptList = new ArrayList<>();
        List<MainIngredient> mainIngredientList = new ArrayList<>();
        List<Category> categoryList = new ArrayList<>();

        try{
            receptList = Database
                    .getRecepies()
                    .stream()
                    .filter(recept -> recept.getAuthor().getId().equals(WelcomeScreenController.activeUser.getId()))
                    .toList();
            mainIngredientList = Database.getIngredients();
            categoryList = Database.getCategories();
        }catch (SQLException | IOException e){
            e.printStackTrace();
        }

        receptComboBox.setItems(FXCollections.observableList(receptList));
        categoryComboBox.setItems(FXCollections.observableList(categoryList));
        ingredientComboBox.setItems(FXCollections.observableList(mainIngredientList));
    }

    @FXML
    public void update(){
        Recept recepieToUpdate = receptComboBox.getValue();
        Recept oldData = receptComboBox.getValue();
        LocalDate updateDate = LocalDate.now();
        String name = recepieNameTextField.getText();
        String text  = textArea.getText();
        Category category = categoryComboBox.getValue();
        MainIngredient mainIngredient = ingredientComboBox.getValue();
        Boolean check = false;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Add new recipe");
        alert.setHeaderText("Confirm adding new");
        alert.setContentText("Are you sure you want to add new recepie?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {

            if (recepieToUpdate != null && (!name.isBlank() || !text.isBlank() || category != null || category != null || mainIngredient != null)) {
                if (!name.isBlank() && !name.equals(recepieToUpdate.getName())) {
                    recepieToUpdate.setName(name);
                    check = true;
                }
                if (!text.isBlank() && !text.equals(recepieToUpdate.getText())) {
                    recepieToUpdate.setText(text);
                    check = true;
                }
                if (category != null && !category.equals(recepieToUpdate.getCategory())) {
                    recepieToUpdate.setCategory(category);
                    check = true;
                }
                if (mainIngredient != null && !mainIngredient.equals(recepieToUpdate.getMainIngredient())) {
                    recepieToUpdate.setMainIngredient(mainIngredient);
                    check = true;
                }
                recepieToUpdate.setLastUpdate(updateDate);
                check = true;
            }
            if (check) {
                try {
                    Database.updateRecepie(recepieToUpdate, oldData);
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
                } catch (SQLException | IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
