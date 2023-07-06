package com.projekt.organizacijarecepata.Controller;

import com.projekt.organizacijarecepata.Base.Database;
import com.projekt.organizacijarecepata.entiteti.Person;
import com.projekt.organizacijarecepata.entiteti.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Controller class for the AllProfiles view.
 */
public class AllProfilesController implements Initializable {

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField surnameTextField;

    @FXML
    private DatePicker DOBDatePicker;

    @FXML
    private TableView<Person> userTableView;

    @FXML
    private TableColumn<User, String> usernameTableColumn;

    @FXML
    private TableColumn<Person, String> nameTableColumn;

    @FXML
    private TableColumn<Person, String> surnameTableColumn;

    @FXML
    private TableColumn<Person, LocalDate> DOBTableColumn;

    @FXML
    private AnchorPane navigation;

    @FXML
    private AnchorPane navigation_admin;

    /**
     * Initializes the controller.
     * Called after the FXML file has been loaded.
     *
     * @param url            The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (LoginController.isAdmin) {
            navigation.setVisible(false);
            navigation_admin.setVisible(true);
        } else if (!LoginController.isAdmin) {
            navigation.setVisible(true);
            navigation_admin.setVisible(false);
        }

        List<Person> personList = new ArrayList<>();

        try {
            personList = Database.getPersonwithUsername();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        usernameTableColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        nameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameTableColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        DOBTableColumn.setCellValueFactory(new PropertyValueFactory<>("DOB"));
        ObservableList<Person> personObservableList = FXCollections.observableList(personList);
        userTableView.setItems(personObservableList);
    }

    /**
     * Handles the search action.
     * Filters the personList based on the provided search criteria and updates the table view.
     */
    @FXML
    public void search() {
        List<Person> personList = new ArrayList<>();
        String username = usernameTextField.getText();
        String name = nameTextField.getText();
        String surname = surnameTextField.getText();
        LocalDate dob = DOBDatePicker.getValue();
        usernameTextField.setText("");
        nameTextField.setText("");
        surnameTextField.setText("");
        DOBDatePicker.setValue(null);

        try {
            personList = Database.getPersonwithUsername();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (!username.isEmpty()) {
            personList = personList.stream().filter(person -> person.getUsername().equals(username)).collect(Collectors.toList());
        }
        if (!name.isEmpty()) {
            personList = personList.stream().filter(person -> person.getName().equals(name)).collect(Collectors.toList());
        }
        if (!surname.isEmpty()) {
            personList = personList.stream().filter(person -> person.getSurname().equals(surname)).collect(Collectors.toList());
        }
        if (DOBDatePicker.getValue() != null) {
            personList = personList.stream().filter(person -> person.getDOB().equals(dob)).collect(Collectors.toList());
        }

        usernameTableColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        nameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameTableColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        DOBTableColumn.setCellValueFactory(new PropertyValueFactory<>("DOB"));
        ObservableList<Person> personObservableList = FXCollections.observableList(personList);
        userTableView.setItems(personObservableList);
    }
}
