package com.example.librarysystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AdminLoginController {

    @FXML
    private Button cancelButton;

    @FXML
    private Label loginMessageLabel;

    @FXML
    public void loginButtonOnAction(ActionEvent e){
        loginMessageLabel.setText("You try to login!");

    }

    @FXML
    public void cancelButtonOnAction(ActionEvent e){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();

    }

}
