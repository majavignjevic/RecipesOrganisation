package com.projekt.organizacijarecepata.Controller;

import com.projekt.organizacijarecepata.Base.Database;
import com.projekt.organizacijarecepata.entiteti.Category;
import com.projekt.organizacijarecepata.entiteti.MainIngredient;
import com.projekt.organizacijarecepata.entiteti.RegularRecepies;
import com.projekt.organizacijarecepata.entiteti.User;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Controller class for the InsertNewRecepie view.
 */

public class InsertNewRecepieController implements Initializable {
    @FXML
    TextField recepieNameTextField;

    @FXML
    ComboBox<Category> categoryComboBox;

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
        else{
            navigation.setVisible(true);
            navigation_admin.setVisible(false);
        }

        List<Category> categoryList = new ArrayList<>();
        List<MainIngredient> mainIngredientList = new ArrayList<>();

        try{
            categoryList = Database.getCategories();
            mainIngredientList = Database.getIngredients();
        }catch (SQLException e){
            e.printStackTrace();
        }

        categoryComboBox.setItems(FXCollections.observableList(categoryList));
        ingredientComboBox.setItems(FXCollections.observableList(mainIngredientList));
    }

    @FXML
    public void insert(){
        User author = WelcomeScreenController.activeUser;
        LocalDate insertDate = LocalDate.now();
        String name = recepieNameTextField.getText();
        String text = textArea.getText();
        Category category = categoryComboBox.getValue();
        MainIngredient mainIngredient = ingredientComboBox.getValue();

        RegularRecepies newRegularRecepie = new RegularRecepies(Long.parseLong("0"), name, text, author, category,mainIngredient, insertDate,insertDate);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Add new recipe");
        alert.setHeaderText("Confirm adding new");
        alert.setContentText("Are you sure you want to add new recepie?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            if(name.isBlank() || text.isBlank() || category == null || ingredientComboBox == null){
                Alert notFully = new Alert(Alert.AlertType.WARNING);
                notFully.setTitle("Warning");
                notFully.setHeaderText("There is not everything");
                notFully.setContentText("Check and make sure you wrote everything please!");
                notFully.showAndWait();
            }
            else {
                try {
                    Database.insertNewRegularRecepie(newRegularRecepie);
                } catch (IOException | SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
