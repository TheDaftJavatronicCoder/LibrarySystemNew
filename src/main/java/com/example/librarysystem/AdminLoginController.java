package com.example.librarysystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;

public class AdminLoginController {

    @FXML
    private Button cancelButton;

    @FXML
    private Label loginMessageLabel;

    @FXML
    private TextField usernameTextfield1;

    @FXML
    private PasswordField passwordTextfield1;



    @FXML
    public void loginButtonOnAction(ActionEvent e) throws SQLException {

        if (usernameTextfield1.getText().isBlank() == false && passwordTextfield1.getText().isBlank() == false){
            String loginMessage;
            String userName = usernameTextfield1.getText();
            String passWord = passwordTextfield1.getText();
            //loginMessageLabel.setText("You try to login!");
            DatabaseConnection connectDB = new DatabaseConnection();
            if(connectDB.signInAdmin(userName,passWord)) {
                loginMessage = "login successful!";
                System.out.println(loginMessage);
            } else {
                loginMessage = "login failed!";
                System.out.println(loginMessage);
            }

            } else {
            loginMessageLabel.setText("Please enter username and password");

        }

    }
    @FXML
    public void cancelButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

        }




