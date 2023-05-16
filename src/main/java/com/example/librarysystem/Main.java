package com.example.librarysystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {



            Parent root = FXMLLoader.load(getClass().getResource("loan.fxml"));

            Scene scene = new Scene(root);
            stage.setTitle("LTU Library System");
            stage.setScene(scene);
            stage.show();
        }



    public static void main(String[] args) {
        launch();

    }
}