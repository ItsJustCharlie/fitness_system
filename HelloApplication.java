package org.example.fitnessproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static org.example.fitnessproject.MenuWindow.getWindow;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        MenuWindow.setPrimaryStage(stage);
        Scene scene = MenuWindow.getWindow();
        stage.setScene(scene);
        stage.setTitle("Menu Window");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}