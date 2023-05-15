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
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {


    @FXML
    private Button activeLoansButton;

    @FXML
    private Button addObjectButton;

    @FXML
    private Button feesButton;

    @FXML
    private Label lblStatus;

    @FXML
    private Pane pnlStatus;

    @FXML
    private GridPane gpFees;

    @FXML
    private GridPane gpActiveLoans;

    @FXML
    private GridPane gpAddObject;

    @FXML
    private Button startPageButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void handleClicks(ActionEvent event) {

        if (event.getSource() == addObjectButton) {

            lblStatus.setText("Add Object");
            pnlStatus.setBackground(new Background(new BackgroundFill(Color.rgb(38, 63, 115), CornerRadii.EMPTY, Insets.EMPTY)));
            gpAddObject.toFront();
            //handle startPageButton
        } else if (event.getSource() == activeLoansButton) {

            lblStatus.setText("Active Loans");
            pnlStatus.setBackground(new Background(new BackgroundFill(Color.rgb(45, 110, 80), CornerRadii.EMPTY, Insets.EMPTY)));
            gpActiveLoans.toFront();

            //handle addObjectButton
        } else if (event.getSource() == feesButton) {

            lblStatus.setText("Fees");
            pnlStatus.setBackground(new Background(new BackgroundFill(Color.rgb(90, 30, 10), CornerRadii.EMPTY, Insets.EMPTY)));
            gpFees.toFront();

            //handle addObjectButton

        }


    }

    public void switchToStartPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("startpage.fxml"));
        Parent myPagesParent = fxmlLoader.load();
        Scene myPagesScene = new Scene(myPagesParent);
        Stage currentStage = (Stage) startPageButton.getScene().getWindow();
        currentStage.setScene(myPagesScene);
    }

}

