package com.example.librarysystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    private Button cancelButton;

@FXML
    private Label loginMessageLabel;
@FXML
    private TextField usernameTextField;
@FXML
    private TextField passwordTextField;


public void loginButtonOnAction(ActionEvent e) {
    loginMessageLabel.setText("You try to login!");

    if (usernameTextField.getText().isBlank() == false && passwordTextField.getText().isBlank() == false) {
        loginMessageLabel.setText("Please enter username and password");
    } else {
        loginMessageLabel.setText("Please enter username and password");
    }

}


        public void cancelButtonOnAction(ActionEvent e){
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        }


}