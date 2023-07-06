package com.projekt.organizacijarecepata.Service;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Run extends Application {
    public static Stage mainStage;

    @Override
    public void start(Stage stage) throws IOException {
        mainStage=stage;
        getFXML("/com/projekt/organizacijarecepata/Controller/login.fxml", "Welcome!");
    }

    public static void main(String[] args) {
        launch();
    }

    public static Stage getMainStage(){
        return mainStage;
    }

    public static void getFXML(String fileName, String windowName)  throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Run.class.getResource(fileName));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        getMainStage().setTitle(windowName);
        getMainStage().setScene(scene);
        getMainStage().show();
    }
}