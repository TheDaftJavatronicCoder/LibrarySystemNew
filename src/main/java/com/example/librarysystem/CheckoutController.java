package com.example.librarysystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class CheckoutController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    @FXML
    private Button startPageButton5;

    @FXML
    private Button GetInfo;

    @FXML
    private TextArea LoanDisplay;


    public void switchToStartPage5() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("startpage.fxml"));
        Parent myPagesParent = fxmlLoader.load();
        Scene myPagesScene = new Scene(myPagesParent);
        Stage currentStage = (Stage) startPageButton5.getScene().getWindow();
        currentStage.setScene(myPagesScene);

    }

    @FXML
    private void getLoans() throws SQLException {
        DatabaseConnection dbcon = new DatabaseConnection();
        String loanlistinfo = dbcon.getLoanInfo();
        LoanDisplay.setText(loanlistinfo);
    }



}
