package com.example.librarysystem;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ReturnController {

    @FXML
    private Label successfulLabel;

    @FXML
    private Button returnButton;

    @FXML
    private TextArea TextFieldReturn;

    private String barcodeItem;

    public ReturnController(){
    }

    @FXML
    private void returnButtonAction(){
        barcodeItem = TextFieldReturn.getText();

        DatabaseLoanHandling dbcon = new DatabaseLoanHandling();
        if(dbcon.GetLogin()){
        } else {
            successfulLabel.setText("You need to login first!");
            return;
        }
        boolean isSuccess = dbcon.returnLoan(barcodeItem); // Call a method to perform the return operation and return a boolean indicating success or failure

        if (isSuccess) {
            successfulLabel.setText("Return successful!");
        } else {
            successfulLabel.setText("");
        }
    }

    private boolean performReturn() {
        // Perform the return operation here
        // Return true if successful, false otherwise
        return true; // Placeholder value, replace with your actual implementation
    }
}
