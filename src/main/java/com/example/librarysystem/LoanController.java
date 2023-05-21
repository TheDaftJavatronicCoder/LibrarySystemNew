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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
public class LoanController implements Initializable {

    @FXML
    private GridPane gpLoan;

    @FXML
    private GridPane gpReturnLoan;

    @FXML
    private Label labelStatus;

    @FXML
    private Button loanPageMangeLoanButton;

    @FXML
    private Pane panelStatus;

    @FXML
    private Button returnLoanMangeLoanButton;

    @FXML
    private Button startPageButton2;

    @FXML
    private Button confirmLoanButton;

    @FXML
    private TextArea loanArea;

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField usernameTextField1;

    @FXML
    private Button testBtn;

    @FXML
    private Label Info;

    private static String showLoan = "";

    private boolean wantReserve = false;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

//    public static void addDVDLoan(String pickedDVD) {
//
//        loanList.add("DVD: "+pickedDVD);
//
//    }
//
//    public static void addBookLoan(String pickedbook) {
//
//        loanList.add("Book: "+pickedbook);
//
//    }

    @FXML
    public void UpdateText() {

        System.out.println(showLoan);
        loanArea.setText(showLoan);
    }

    @FXML
    public static void UpString(String lis) {

        showLoan = lis;

    }

    @FXML
    public void ReserveItems(){
        Info.setText("Du vill reservera istället");
        wantReserve = true;
    }


    @FXML
    private void handleClicks(ActionEvent event) {

        if (event.getSource() == loanPageMangeLoanButton) {

            labelStatus.setText("Loan");
            panelStatus.setBackground(new Background(new BackgroundFill(Color.rgb(38, 63, 115),CornerRadii.EMPTY, Insets.EMPTY)));
gpLoan.toFront();
            //handle startPageButton
        } else if (event.getSource() == returnLoanMangeLoanButton) {

            labelStatus.setText("Return Loan");
            panelStatus.setBackground(new Background(new BackgroundFill(Color.rgb(60,20,99),CornerRadii.EMPTY, Insets.EMPTY)));
gpReturnLoan.toFront();
            //handle addObjectButton
        }
    }



    public void switchToStartPage2() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("startpage.fxml"));
        Parent myPagesParent = fxmlLoader.load();
        Scene myPagesScene = new Scene(myPagesParent);
        Stage currentStage = (Stage) startPageButton2.getScene().getWindow();
        currentStage.setScene(myPagesScene);
    }

    public void switchToCheckout() throws IOException, SQLException {
        DatabaseLoanHandling db = new DatabaseLoanHandling();
        if (db.GetLogin() == false){
            Info.setText("Du måste logga in först");
        }
        db.addNewLoan(showLoan, wantReserve);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("checkout.fxml"));
        Parent myPagesParent = fxmlLoader.load();
        Scene myPagesScene = new Scene(myPagesParent);
        Stage currentStage = (Stage) startPageButton2.getScene().getWindow();
        currentStage.setScene(myPagesScene);
    }



}
