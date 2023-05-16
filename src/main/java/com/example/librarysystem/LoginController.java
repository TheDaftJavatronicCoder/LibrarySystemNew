package com.example.librarysystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController {

    @FXML
    private Button createAccountButton;

    @FXML
    private Button createAccountButton2;

    @FXML
    private Label labelStatusLoginPage;

    @FXML
    private Button loginButton3;

    @FXML
    private GridPane paneCreateForm;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private PasswordField passwordTextField1;

    @FXML
    private Pane pnlStatusLoginPage;


    @FXML
    private Button returnToStartpage3Button;

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField usernameTextField1;

    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    private void handleClicks(ActionEvent event)  {
        if (event.getSource() == createAccountButton) {
            labelStatusLoginPage.setText("Create Account");
            pnlStatusLoginPage.setBackground(new Background(new BackgroundFill(Color.rgb(38, 63, 115), CornerRadii.EMPTY, Insets.EMPTY)));
            paneCreateForm.toFront();
        }
    }
        public void returnToStartPage()  throws IOException{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("startpage.fxml"));
            Parent myPagesParent = fxmlLoader.load();
            Scene myPagesScene = new Scene(myPagesParent);
            Stage currentStage = (Stage) returnToStartpage3Button.getScene().getWindow();
            currentStage.setScene(myPagesScene);

    }

    public void continueToLoan()  throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("loan.fxml"));
        Parent myPagesParent = fxmlLoader.load();
        Scene myPagesScene = new Scene(myPagesParent);
        Stage currentStage = (Stage) loginButton3.getScene().getWindow();
        currentStage.setScene(myPagesScene);

    }

    }
