package com.projekt.organizacijarecepata.Controller;

import com.projekt.organizacijarecepata.Base.Database;
import com.projekt.organizacijarecepata.entiteti.*;
import com.projekt.organizacijarecepata.entiteti.MainIngredient;
import com.projekt.organizacijarecepata.entiteti.Category;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class InsertNewHealthyRecepieController implements Initializable {
    @FXML
    TextField recepieNameTextField;

    @FXML
    ComboBox<Category> categoryComboBox;

    @FXML
    ComboBox<MainIngredient> ingredientComboBox;

    @FXML
    TextArea textArea;

    @FXML
    TextField calorieTextField;

    @FXML
    private AnchorPane navigation;

    @FXML
    private AnchorPane navigation_admin;

    private LoggerPrinter<InsertNewHealthyRecepieController> loggerPrinter;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (LoginController.isAdmin) {
            navigation.setVisible(false);
            navigation_admin.setVisible(true);
        } else {
            navigation.setVisible(true);
            navigation_admin.setVisible(false);
        }

        List<Category> categoryList = new ArrayList<>();
        List<MainIngredient> mainIngredientList = new ArrayList<>();

        try {
            categoryList = Database.getCategories();
            mainIngredientList = Database.getIngredients();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ingredientComboBox.setItems(FXCollections.observableList(mainIngredientList));
        categoryComboBox.setItems(FXCollections.observableList(categoryList));

        loggerPrinter = new LoggerPrinter<>(this);
    }

    @FXML
    public void insert() {
        List<MainIngredient> ingredients = new ArrayList<>();
        User author = WelcomeScreenController.activeUser;
        LocalDate insertDate = LocalDate.now();
        String name = recepieNameTextField.getText();
        String text = textArea.getText();
        Category category = categoryComboBox.getValue();
        MainIngredient mainIngredient = ingredientComboBox.getValue();
        Double calories = Double.valueOf(0);
        try {
            calories = Double.valueOf(calorieTextField.getText());
        } catch (RuntimeException e){
            loggerPrinter.logError("Calories are empty");
        }
        if(recepieNameTextField.getText().isBlank() || textArea.getText().isBlank() || category == null || mainIngredient == null || calorieTextField.getText().isBlank()){
            Alert notFully = new Alert(Alert.AlertType.WARNING);
            notFully.setTitle("Warning");
            notFully.setHeaderText("There is not everything");
            notFully.setContentText("Check and make sure you wrote everything please!");
            notFully.showAndWait();
        }
        else{
            HealthyRecepies newHealthyRecepie = new HealthyRecepies(Long.parseLong("0"), name, text, author, category, mainIngredient, insertDate, insertDate, calories);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Add new healthy recipe");
            alert.setHeaderText("Confirm adding new");
            alert.setContentText("Are you sure you want to add new recipe?");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    Database.insertnewHealthyRecepie(newHealthyRecepie);
                    loggerPrinter.logInfo("New recipe inserted: " + name);
                } catch (IOException | SQLException e) {
                    e.printStackTrace();
                    loggerPrinter.logError("Error occurred while inserting new recipe: " + e.getMessage());
                }
            }
        }


    }
}
