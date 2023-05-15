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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
public class LoanController implements Initializable {

    @FXML
    private Button loanPageMangeLoanButton;
    @FXML
    private Button returnLoanMangeLoanButton;

    @FXML
    private Button startPageButton2;

    @FXML
    private Label labelStatus;

    @FXML
    private Pane panelStatus;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    private void handleClicks(ActionEvent event) {

        if (event.getSource() == loanPageMangeLoanButton) {

            labelStatus.setText("Loan");
            panelStatus.setBackground(new Background(new BackgroundFill(Color.rgb(43,63,90),CornerRadii.EMPTY, Insets.EMPTY)));

            //handle startPageButton
        } else if (event.getSource() == returnLoanMangeLoanButton) {

            labelStatus.setText("Return Loan");
            panelStatus.setBackground(new Background(new BackgroundFill(Color.rgb(60,20,99),CornerRadii.EMPTY, Insets.EMPTY)));

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
}
