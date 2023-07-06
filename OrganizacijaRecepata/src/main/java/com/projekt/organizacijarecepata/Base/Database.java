package com.projekt.organizacijarecepata.Base;

import com.projekt.organizacijarecepata.Exceptions.NotConnectedToDatabase;
import com.projekt.organizacijarecepata.Threads.WriteAChangeThread;
import com.projekt.organizacijarecepata.entiteti.*;
import javafx.scene.control.Alert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


/**
 * The Database class provides methods to interact with the database and perform various database operations.
 */
public class Database {

    public static Logger logger = LoggerFactory.getLogger(Database.class);

    /**
     * Establishes a connection to the database using the configuration provided in the database.properties file.
     *
     * @return the database connection
     * @throws NotConnectedToDatabase if a database access error occurs
     */
    private static Connection connectToDatabase() throws NotConnectedToDatabase {
        Properties configuration = new Properties();
        try {
            configuration.load(new FileReader("doc/database.properties"));

            String databaseURL = configuration.getProperty("databaseURL");
            String databaseUsername = configuration.getProperty("databaseUsername");
            String databasePassword = configuration.getProperty("databasePassword");

            Connection connection = DriverManager.getConnection(databaseURL, databaseUsername, databasePassword);
            return connection;
        } catch (IOException | SQLException e) {
            throw new NotConnectedToDatabase("Failed to connect to the database.", e);
        }
    }

    /**
     * Updates the information of a person in the database.
     *
     * @param person the Person object containing the updated information
     * @throws SQLException if a database access error occurs
     * @throws IOException  if an I/O error occurs
     */
    public static void updatePerson(Person person) throws SQLException, IOException {
        try (Connection connection = connectToDatabase();
             PreparedStatement sqlStatement = connection.prepareStatement("UPDATE PERSONS SET NAME=?, SURNAME=?, DOB=? WHERE ID=?")) {

            sqlStatement.setString(1, person.getName());
            sqlStatement.setString(2, person.getSurname());
            sqlStatement.setDate(3, Date.valueOf(person.getDOB()));
            sqlStatement.setLong(4, person.getId());
            sqlStatement.executeUpdate();
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("We have an amazing information!");
        alert.setHeaderText("Congrats");
        alert.setContentText("You have successfully updated your info!");
        alert.showAndWait();

        logger.info("Person " + person.getName() + "was updated");

        String change ="Person " + person.getName() + " was updated by user: " + person.getUsername();
        writeChangeToBinaryFile(change);
    }
    /**
     * Inserts a new regular recipe into the database.
     *
     * @param newRegularRecepie the RegularRecepies object representing the new recipe
     * @throws SQLException if a database access error occurs
     * @throws IOException  if an I/O error occurs
     */
    public static void insertNewRegularRecepie(RegularRecepies newRegularRecepie) throws SQLException, IOException{
        Connection connection = connectToDatabase();

        PreparedStatement statement = connection.prepareStatement("INSERT INTO REGULAR_RECEPIE (NAME, TEXT, CATEGORY_ID, INGREDIENT_ID, INSERT_DATE, UPDATE_DATE, USER_ID) VALUES (?, ?, ?, ?, ?, ?, ?)");
        statement.setString(1, newRegularRecepie.getName());
        statement.setString(2, newRegularRecepie.getText());
        statement.setInt(3, ( newRegularRecepie.getCategory().getId()));
        statement.setInt(4, newRegularRecepie.getMainIngredient().getId());
        statement.setDate(5, Date.valueOf(newRegularRecepie.getInsert()));
        statement.setDate(6, Date.valueOf(newRegularRecepie.getLastUpdate()));
        statement.setInt(7, newRegularRecepie.getAuthor().getId().intValue());
        statement.executeUpdate();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("We have an amazing information!");
        alert.setHeaderText("Congrats");
        alert.setContentText("You have successfully added a new recepie!");
        alert.showAndWait();

        logger.info("Recepie " + newRegularRecepie.getName() + " was inserted.");

        String change ="Regular recepie " + newRegularRecepie.getName() + " was inserted by user: " + newRegularRecepie.getAuthor().getUsername();
        writeChangeToBinaryFile(change);
    }
    /**
     * Inserts a new healthy recipe into the database.
     *
     * @param newHealthyRecepie the HealthyRecepies object representing the new recipe
     * @throws SQLException if a database access error occurs
     * @throws IOException  if an I/O error occurs
     */
    public static void insertnewHealthyRecepie(HealthyRecepies newHealthyRecepie) throws SQLException, IOException{
        Connection connection = connectToDatabase();

        PreparedStatement statement = connection.prepareStatement("INSERT INTO HEALTHY_RECEPIE (NAME, TEXT, CATEGORY_ID, INGREDIENT_ID, INSERT_DATE, UPDATE_DATE, CALORIES, USER_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        statement.setString(1, newHealthyRecepie.getName());
        statement.setString(2, newHealthyRecepie.getText());
        statement.setInt(3, ( newHealthyRecepie.getCategory().getId()));
        statement.setInt(4, newHealthyRecepie.getMainIngredient().getId());
        statement.setDate(5, Date.valueOf(newHealthyRecepie.getInsert()));
        statement.setDate(6, Date.valueOf(newHealthyRecepie.getLastUpdate()));
        statement.setInt(7, newHealthyRecepie.getKalorije().intValue());
        statement.setInt(8, newHealthyRecepie.getAuthor().getId().intValue());
        statement.executeUpdate();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("We have an amazing information!");
        alert.setHeaderText("Congrats");
        alert.setContentText("You have successfully added a new recepie!");
        alert.showAndWait();

        logger.info("Recepie " + newHealthyRecepie.getName() + " was inserted");

        String change ="Healthy recepie " + newHealthyRecepie.getName() + " was inserted by user: " + newHealthyRecepie.getAuthor().getUsername();
        writeChangeToBinaryFile(change);

    }
    /**
     * Inserts a new healthy recipe into the database.
     *
     * @param chosenRecepie the HealthyRecepies object representing the new recipe
     * @throws SQLException if a database access error occurs
     * @throws IOException  if an I/O error occurs
     */
    public static void deleteRecepie(Recept chosenRecepie) throws SQLException {
        Connection connection = connectToDatabase();

        PreparedStatement preparedStatement= connection.prepareStatement("DELETE FROM HEALTHY_RECEPIE WHERE ID=? AND NAME=?");
        preparedStatement.setInt(1, chosenRecepie.getId().intValue());
        preparedStatement.setString(2, chosenRecepie.getName());
        preparedStatement.executeUpdate();

        PreparedStatement prPStatement= connection.prepareStatement("DELETE FROM REGULAR_RECEPIE WHERE ID=? AND NAME=?");
        prPStatement.setInt(1, chosenRecepie.getId().intValue());
        prPStatement.setString(2, chosenRecepie.getName());
        prPStatement.executeUpdate();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("We have an amazing information!");
        alert.setHeaderText("Congrats");
        alert.setContentText("You have successfully deleted a recepie!");
        alert.showAndWait();

        logger.info("Recepie " + chosenRecepie.getName() + " was deleted.");

        String change ="Recepie " + chosenRecepie.getName() + " was deleted by user: " + chosenRecepie.getAuthor().getUsername();
        writeChangeToBinaryFile(change);
    }

    public static void updateRecepie(Recept recepieToUpdate, Recept oldRecepieData) throws SQLException, IOException {
        Boolean check = false;
        String change = "";
        try (Connection connection = connectToDatabase()) {
            if (recepieToUpdate.getType().equals("Healthy")) {
                int newCalories = ((HealthyRecepies) recepieToUpdate).getKalorije().intValue();
                int oldCalories =((HealthyRecepies) oldRecepieData).getKalorije().intValue();
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE HEALTHY_RECEPIE SET NAME=?, TEXT=?, CATEGORY_ID=?, INGREDIENT_ID=?, CALORIES=?, UPDATE_DATE=? WHERE ID=?");
                preparedStatement.setString(1, recepieToUpdate.getName());
                preparedStatement.setString(2, recepieToUpdate.getText());
                preparedStatement.setInt(3, recepieToUpdate.getCategory().getId());
                preparedStatement.setInt(4, recepieToUpdate.getMainIngredient().getId());
                preparedStatement.setInt(5, newCalories);
                preparedStatement.setDate(6, Date.valueOf(recepieToUpdate.getLastUpdate()));
                preparedStatement.setInt(7, recepieToUpdate.getId().intValue());
                preparedStatement.executeUpdate();
                check = true;

                change = "Recipe " + oldRecepieData.getName()
                        + " with old data: name: " + oldRecepieData.getName()
                        + ", category: " + oldRecepieData.getCategory().name()
                        + ", main ingredient: " + oldRecepieData.getMainIngredient().getIngredientName()
                        + ", calories: " + oldCalories
                        + ", text: " + oldRecepieData.getText()
                        + " was updated to " + recepieToUpdate.getName()
                        + ", category: " + recepieToUpdate.getCategory().name()
                        + ", main ingredient: " + recepieToUpdate.getMainIngredient().getIngredientName()
                        + ", calories: " + newCalories
                        + ", text: " + recepieToUpdate.getText()
                        + " by user: " + recepieToUpdate.getAuthor().getUsername();
            }
            else if(recepieToUpdate.getType().equals("Regular")) {
                PreparedStatement prpStatement = connection.prepareStatement("UPDATE REGULAR_RECEPIE SET NAME=?, TEXT=?, CATEGORY_ID=?, INGREDIENT_ID=?, UPDATE_DATE=? WHERE ID=?");
                prpStatement.setString(1, recepieToUpdate.getName());
                prpStatement.setString(2, recepieToUpdate.getText());
                prpStatement.setInt(3, recepieToUpdate.getCategory().getId());
                prpStatement.setInt(4, recepieToUpdate.getMainIngredient().getId());
                prpStatement.setDate(5, Date.valueOf(recepieToUpdate.getLastUpdate()));
                prpStatement.setInt(6, recepieToUpdate.getId().intValue());
                prpStatement.executeUpdate();
                check=true;
                change = "Recepie " + oldRecepieData.getName()
                        + "with old data: name: " + oldRecepieData.getName()
                        + ", category: " + oldRecepieData.getCategory().name()
                        + ", main ingredient: " + oldRecepieData.getMainIngredient().getIngredientName()
                        + ", text: " + oldRecepieData.getText()
                        + " was updated  to " +recepieToUpdate.getName()
                        + ", category: " + recepieToUpdate.getCategory().name()
                        + ", main ingredient: " + recepieToUpdate.getMainIngredient().getIngredientName()
                        + ", text: " + recepieToUpdate.getText()
                        + " by user: " + recepieToUpdate.getAuthor().getUsername();
            }
        }
        if(check) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("We have an amazing information!");
            alert.setHeaderText("Congrats");
            alert.setContentText("You have successfully updated your recepie!");
            alert.showAndWait();

            logger.info("Recepie " + recepieToUpdate.getName() + "was updated.");

            writeChangeToBinaryFile(change);
        }
    }

    public static List<User> getUsers() throws SQLException {
        List<User> userList = new ArrayList<>();

        try (Connection connection = connectToDatabase();
             Statement sqlStatement = connection.createStatement();
             ResultSet userResultSet = sqlStatement.executeQuery("SELECT * FROM USERS")) {

            while (userResultSet.next()) {
                User newUser = getUserSet(userResultSet);
                userList.add(newUser);
            }
        }

        return userList;
    }

    public static List<Category> getCategories() throws SQLException {
        List<Category> categories = new ArrayList<>();

        try (Connection connection = connectToDatabase();
             Statement sqlStatement = connection.createStatement();
             ResultSet categoriesResultSet = sqlStatement.executeQuery("SELECT * FROM CATEGORY")) {

            while (categoriesResultSet.next()) {
                Category newCategory = getCategorySet(categoriesResultSet);
                categories.add(newCategory);
            }
        }

        return categories;
    }

    public static List<MainIngredient> getIngredients() throws SQLException {
        List<MainIngredient> ingredients = new ArrayList<>();

        try (Connection connection = connectToDatabase();
             Statement sqlStatement = connection.createStatement();
             ResultSet ingredientResultSet = sqlStatement.executeQuery("SELECT * FROM INGREDIENT")) {

            while (ingredientResultSet.next()) {
                MainIngredient newIngredient = getIngredientSet(ingredientResultSet);
                ingredients.add(newIngredient);
            }
        }

        return ingredients;
    }

    public static Person getActivePerson(User user) throws SQLException {
        Person wantedPerson = null;

        try (Connection connection = connectToDatabase();
             PreparedStatement sqlStatement = connection.prepareStatement("SELECT PERSONS.* FROM USERS JOIN PERSONS ON USERS.PERSON_ID=PERSONS.ID WHERE USERS.USERNAME=?")) {

            sqlStatement.setString(1, user.getUsername());
            ResultSet personResultSet = sqlStatement.executeQuery();

            while (personResultSet.next()) {
                wantedPerson = getPersonSet(personResultSet);
            }
        }

        return wantedPerson;
    }

    public static List<Person> getPersonwithUsername() throws SQLException {
        List<Person> personList = new ArrayList<>();

        try (Connection connection = connectToDatabase();
             Statement statement = connection.createStatement();
             ResultSet usersPersonsResultSet = statement.executeQuery("SELECT U.USERNAME, U.PASSWORD, P.ID, P.NAME, P.SURNAME, P.DOB FROM USERS U JOIN PERSONS P ON U.PERSON_ID=P.ID")) {

            while (usersPersonsResultSet.next()) {
                Person newPerson = getUserPersonResultSet(usersPersonsResultSet);
                personList.add(newPerson);
            }
        }

        return personList;
    }

    public static List<Person> getPersons() throws SQLException, IOException {
        List<Person> personList = new ArrayList<>();

        try (Connection connection = connectToDatabase();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT P.ID, P.NAME, P.SURNAME, P.DOB FROM PERSONS P")) {

            while (resultSet.next()) {
                Person newPerson = getPersonsResultSet(resultSet);
                personList.add(newPerson);
            }
        }

        return personList;
    }

    public static Person getPersonsResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("ID");
        String name = resultSet.getString("NAME");
        String surname = resultSet.getString("SURNAME");
        LocalDate dob = resultSet.getDate("DOB").toLocalDate();

        return new Person(id,name, surname, dob);
    }

    public static List<Recept> getRecepies() throws SQLException, IOException {
        List<Recept> recepts = new ArrayList<>();
        recepts.addAll(getRegularRecepies());
        recepts.addAll(getHealthyRecepies());
        return recepts;
    }

    private static List<Recept> getRegularRecepies() throws SQLException, IOException {
        Connection connection = connectToDatabase();
        List<Recept> regularRecepies = new ArrayList<>();

        Statement sqlStatement = connection.createStatement();
        ResultSet regularRecepiesResultSet = sqlStatement.executeQuery("SELECT ID, NAME, TEXT, USER_ID, CATEGORY_ID, INGREDIENT_ID, INSERT_DATE, UPDATE_DATE FROM REGULAR_RECEPIE");

        while (regularRecepiesResultSet.next()) {
            Recept newRecepie = getRegularResultSet(regularRecepiesResultSet);
            regularRecepies.add(newRecepie);
        }

        return regularRecepies;
    }

    private static List<Recept> getHealthyRecepies() throws SQLException, IOException {
        Connection connection = connectToDatabase();
        List<Recept> healthyRecepies = new ArrayList<>();

        Statement sqlStatement = connection.createStatement();
        ResultSet healthyRecepiesResultSet = sqlStatement.executeQuery("SELECT ID, NAME, TEXT, USER_ID, CATEGORY_ID, INGREDIENT_ID, INSERT_DATE, UPDATE_DATE, CALORIES FROM HEALTHY_RECEPIE");

        while (healthyRecepiesResultSet.next()) {
            Recept newRecepie = getHealthyResultSet(healthyRecepiesResultSet);
            healthyRecepies.add(newRecepie);
        }

        return healthyRecepies;
    }

    private static Recept getRegularResultSet(ResultSet regularRecepiesResultSet) throws SQLException {
        Long id = regularRecepiesResultSet.getLong("ID");
        String name = regularRecepiesResultSet.getString("NAME");
        String text = regularRecepiesResultSet.getString("TEXT");
        Long categoryId = regularRecepiesResultSet.getLong("CATEGORY_ID");
        Long ingredientId = regularRecepiesResultSet.getLong("INGREDIENT_ID");
        LocalDate insertDate = regularRecepiesResultSet.getDate("INSERT_DATE").toLocalDate();
        LocalDate updateDate = regularRecepiesResultSet.getDate("UPDATE_DATE").toLocalDate();
        int userId = regularRecepiesResultSet.getInt("USER_ID");

        List<User> userList = new ArrayList<>();
        List<Category> categoryList = new ArrayList<>();
        List<MainIngredient> mainIngredientList = new ArrayList<>();

        try {
            userList = getUsers();
            categoryList = getCategories();
            mainIngredientList = getIngredients();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        User author = userList.stream().filter(user -> user.getId().intValue() == userId).findFirst().get();
        Category category = categoryList.stream().filter(category1 -> category1.getId() == categoryId.intValue()).findFirst().get();
        MainIngredient ingredient = mainIngredientList.stream().filter(mainIngredient -> mainIngredient.getId() == ingredientId.intValue()).findFirst().get();

        return new RegularRecepies(id, name, text, author, category, ingredient, insertDate, updateDate);
    }

    private static Recept getHealthyResultSet(ResultSet healthyRecepiesResultSet) throws SQLException {
        Long id = healthyRecepiesResultSet.getLong("ID");
        String name = healthyRecepiesResultSet.getString("NAME");
        String text = healthyRecepiesResultSet.getString("TEXT");
        Long categoryId = healthyRecepiesResultSet.getLong("CATEGORY_ID");
        Long ingredientId = healthyRecepiesResultSet.getLong("INGREDIENT_ID");
        LocalDate insertDate = healthyRecepiesResultSet.getDate("INSERT_DATE").toLocalDate();
        LocalDate updateDate = healthyRecepiesResultSet.getDate("UPDATE_DATE").toLocalDate();
        int userId = healthyRecepiesResultSet.getInt("USER_ID");
        Double calories = healthyRecepiesResultSet.getDouble("CALORIES");

        List<User> userList = new ArrayList<>();
        List<Category> categoryList = new ArrayList<>();
        List<MainIngredient> mainIngredientList = new ArrayList<>();

        try {
            userList = getUsers();
            categoryList = getCategories();
            mainIngredientList = getIngredients();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        User author = userList.stream().filter(user -> user.getId().intValue() == userId).findFirst().get();
        Category category = categoryList.stream().filter(category1 -> category1.getId() == categoryId.intValue()).findFirst().get();
        MainIngredient ingredient = mainIngredientList.stream().filter(mainIngredient -> mainIngredient.getId() == ingredientId.intValue()).findFirst().get();

        return new HealthyRecepies(id, name, text, author, category, ingredient, insertDate, updateDate, calories);
    }

    private static Person getUserPersonResultSet(ResultSet usersPersonsResultSet) throws SQLException{
    //ID, USERNAME, PASSWORD, ID, NAME, SURNAME, DOB
        String username = usersPersonsResultSet.getString("USERNAME");
        String password = usersPersonsResultSet.getString("PASSWORD");
        Long id = usersPersonsResultSet.getLong("ID");
        String name = usersPersonsResultSet.getString("NAME");
        String surname = usersPersonsResultSet.getString("SURNAME");
        LocalDate DOB = usersPersonsResultSet.getDate("DOB").toLocalDate();

       return new Person(username, password,id, name, username, DOB);
    }

    private static User getUserSet(ResultSet userResultSet) throws SQLException {
        //ID, USERNAME, PASSWORD, PERSON_ID
        Long id = userResultSet.getLong("ID");
        String username = userResultSet.getString("USERNAME");
        String pass = userResultSet.getString("PASSWORD");
        Long PersonId = userResultSet.getLong("PERSON_ID");

        return new User(id, username, pass, PersonId);
    }

    private static Person getPersonSet(ResultSet personResultSet) throws SQLException{
        //ID, NAME, SURNAME, DOB
        Long id = personResultSet.getLong("ID");
        String name = personResultSet.getString("NAME");
        String surname = personResultSet.getString("SURNAME");
        LocalDate dateOfBirth = personResultSet.getDate("DOB").toLocalDate();

        return new Person(id,name,surname,dateOfBirth);
    }

    private static MainIngredient getIngredientSet(ResultSet ingredientResultSet) throws SQLException {
        // ID, NAME
        int id = ingredientResultSet.getInt("ID");

        for (MainIngredient ingredient : MainIngredient.values()) {
            if (ingredient.getId() == id) {
                return ingredient;
            }
        }
        throw new IllegalArgumentException("Invalid ingredient ID: " + id);
    }

    private static Category getCategorySet(ResultSet categoryResultSet) throws SQLException{
        //ID, NAME
        Long id = categoryResultSet.getLong("ID");
        String name = categoryResultSet.getString("NAME");

        return Category.valueOf(name);
    }

    private static void writeChangeToBinaryFile(String change) {
        WriteAChangeThread writeThread = new WriteAChangeThread(change);
        writeThread.start();
    }
}
