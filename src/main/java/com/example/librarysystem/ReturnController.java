package com.example.librarysystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

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

    public void goToHome() throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("startpage.fxml"));
        Parent myPagesParent = fxmlLoader.load();
        Scene myPagesScene = new Scene(myPagesParent);
        Stage currentStage = (Stage) returnButton.getScene().getWindow();
        currentStage.setScene(myPagesScene);
    }



}
