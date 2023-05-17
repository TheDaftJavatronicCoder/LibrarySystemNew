package com.example.librarysystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException, SQLException {

            Parent root = FXMLLoader.load(getClass().getResource("startpage.fxml"));

            Scene scene = new Scene(root);
            stage.setTitle("LTU Library System");
            stage.setScene(scene);
            stage.show();
            DatabaseConnection databaseConnection = new DatabaseConnection();
            databaseConnection.connect();
        }




    public static void main(String[] args) {
        launch();

    }
}