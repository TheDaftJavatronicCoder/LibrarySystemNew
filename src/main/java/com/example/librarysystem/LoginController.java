package com.example.librarysystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import java.sql.SQLException;
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
    @FXML
    private TextField firstnameTextField;
    @FXML
    private TextField lastnameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField adressTextField;
    @FXML
    private TextField phonenumberTextField;
    @FXML
    private TextField ageTextField;
    @FXML
    private TextField roleTextField;

    @FXML
    private Label usernameTaken1;

    public LoginController() {
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    protected void onLoginClick(ActionEvent e) throws SQLException, IOException {
        String creditNam = this.usernameTextField.getText();
        String creditPass = this.passwordTextField.getText();
        if (DatabaseConnection.signIn(creditNam, creditPass)) {
            usernameTaken1.setText("Login successful");
            this.continueToLoan();
        } else {
            usernameTaken1.setText("Login failed");
        }

    }

    @FXML
    private void createClass() throws SQLException {
        DatabaseConnection databas3 = new DatabaseConnection();
        String firstname = this.firstnameTextField.getText();
        String lastname = this.lastnameTextField.getText();
        String email = this.emailTextField.getText();
        String phoneNumber = this.phonenumberTextField.getText();
        String address = this.adressTextField.getText();
        int agereal = 0;
        String age = this.ageTextField.getText();

        try {
            agereal = Integer.parseInt(age);
        } catch (NumberFormatException e) {
            usernameTaken1.setText("Age is a number, put in a number!");
            return;
        }

        String role = this.roleTextField.getText();
        String username = this.usernameTextField1.getText();

        if (!role.equals("Student") && !role.equals("Teacher") && !role.equals("Scientist")){
            usernameTaken1.setText("Invalid role! Please choose from Student, Teacher, or Scientist. ");
            return;
        }

        if (DatabaseConnection.checkUsername(username)){
            usernameTaken1.setText("Username is already taken!");
            return;
        }

        String pass = this.passwordTextField1.getText();
        System.out.println(firstname + " " + lastname);

        databas3.insertPost(firstname, lastname, email, phoneNumber, address, agereal, role, username, pass);
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

    public void onCreateButton() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("loan.fxml"));
        Parent myPagesParent = fxmlLoader.load();
        Scene myPagesScene = new Scene(myPagesParent);
        Stage currentStage = (Stage) createAccountButton2.getScene().getWindow();
        currentStage.setScene(myPagesScene);
    }

    }

