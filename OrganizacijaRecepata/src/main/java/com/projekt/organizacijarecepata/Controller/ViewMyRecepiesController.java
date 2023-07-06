package com.projekt.organizacijarecepata.Controller;

import com.projekt.organizacijarecepata.Base.Database;
import com.projekt.organizacijarecepata.entiteti.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Controller class for the ViewMyRecepies view.
 */

public class ViewMyRecepiesController implements Initializable {

    @FXML
    private TextField recepieNameTextField;

    @FXML
    ComboBox<Category> categoryComboBox;

    @FXML
    ComboBox<MainIngredient> ingredientComboBox;

    @FXML
    private DatePicker insertDateDatePicker;

    @FXML
    private DatePicker updateDateDatePicker;

    @FXML
    TableView<Recept> receptTableView;

    @FXML
    TableColumn<Recept, String> recepieNameTableColumn;

    @FXML
    TableColumn<Recept, Category> categoryTableColumn;

    @FXML
    TableColumn<Recept, MainIngredient> ingredientTableColumn;

    @FXML
    TableColumn<Recept, Date> insertDateTableColumn;

    @FXML
    TableColumn<Recept, Date> updateDateTableColumn;

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
        List<Category> categoryList = new ArrayList<>();
        List<MainIngredient> mainIngredientList = new ArrayList<>();
        List<User> authorList = new ArrayList<>();
        List<Recept> recepieList = new ArrayList<>();
        try{
            categoryList = Database.getCategories();
            mainIngredientList = Database.getIngredients();
            authorList = Database.getUsers();
            recepieList = Database.getRecepies().stream()
                    .filter(recept -> recept.getAuthor().getId().equals(WelcomeScreenController.activeUser.getId()))
                    .toList();
        }catch (SQLException | IOException e){
            e.printStackTrace();
        }

        categoryComboBox.setItems(FXCollections.observableList(categoryList));
        ingredientComboBox.setItems(FXCollections.observableList(mainIngredientList));

        recepieNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        categoryTableColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        ingredientTableColumn.setCellValueFactory(new PropertyValueFactory<>("mainIngredient"));
        insertDateTableColumn.setCellValueFactory(new PropertyValueFactory<>("insert"));
        updateDateTableColumn.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));

        ObservableList<Recept> receptObservableList = FXCollections.observableList(recepieList);
        receptTableView.setItems(receptObservableList);

        receptTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, selectedRecipe) -> {
            if (selectedRecipe != null) {
                showAlert("Recipe Text", selectedRecipe.getText());
                Platform.runLater(() -> {
                    receptTableView.getSelectionModel().clearSelection();
                });
            }
        });

    }

    @FXML
    public void search(){
        List<Category> categoryList = new ArrayList<>();
        List<MainIngredient> mainIngredientList = new ArrayList<>();
        List<Recept> recepieList = new ArrayList<>();
        List<Recept> filteredRecepies = new ArrayList<>();

        String name = recepieNameTextField.getText();
        Category categoryToSearch = categoryComboBox.getValue();
        MainIngredient mainIngredientToSearch = ingredientComboBox.getValue();
        LocalDate insertDateToSearch = insertDateDatePicker.getValue();
        LocalDate updateDateToSearch = updateDateDatePicker.getValue();

        filteredRecepies = recepieList.stream().filter(recept -> recept.getAuthor().getUsername().equals(WelcomeScreenController.activePerson.getUsername())).collect(Collectors.toList());


        try{
            categoryList = Database.getCategories();
            mainIngredientList = Database.getIngredients();
            filteredRecepies = Database.getRecepies();
        }catch (SQLException | IOException e){
            e.printStackTrace();
        }

        recepieNameTextField.setText("");
        categoryComboBox.setValue(null);
        ingredientComboBox.setValue(null);
        insertDateDatePicker.setValue(null);
        updateDateDatePicker.setValue(null);

        if(!name.isBlank()){
            filteredRecepies = filteredRecepies.stream().filter(recept -> recept.getName().equals(name)).collect(Collectors.toList());
        }
        if(categoryToSearch!=null){
            filteredRecepies = filteredRecepies.stream().filter(recept -> recept.getCategory().name().equals(categoryToSearch.name())).collect(Collectors.toList());
        }
        if(mainIngredientToSearch!=null){
            filteredRecepies = filteredRecepies.stream().filter(recept -> recept.getMainIngredient().name().equals(mainIngredientToSearch.name())).collect(Collectors.toList());
        }
        if(insertDateToSearch!=null){
            filteredRecepies = filteredRecepies.stream().filter(recept -> recept.getInsert().equals(insertDateToSearch)).collect(Collectors.toList());
        }
        if(updateDateToSearch!=null){
            filteredRecepies = filteredRecepies.stream().filter(recept -> recept.getLastUpdate().equals(updateDateToSearch)).toList();
        }


        recepieNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        categoryTableColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        ingredientTableColumn.setCellValueFactory(new PropertyValueFactory<>("mainIngredient"));
        insertDateTableColumn.setCellValueFactory(new PropertyValueFactory<>("insert"));
        updateDateTableColumn.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));

        ObservableList<Recept> receptObservableList = FXCollections.observableList(filteredRecepies);
        receptTableView.setItems(receptObservableList);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
