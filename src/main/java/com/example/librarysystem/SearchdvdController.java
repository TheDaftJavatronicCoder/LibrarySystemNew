package com.example.librarysystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SearchdvdController {

    @FXML
    TextField searchTextfield;
    @FXML
    TextFlow TextFieldResult;

    @FXML
    ScrollPane scrollPane1;

    @FXML
    Label Picked;

    @FXML
    Button addBook;

    @FXML
    Button goToLoanBtn;

    String pickedbook;

    String pickedDVD;

    private List<String> loanList = LoanList.getList();


    @FXML
    protected void SearchFundvd(ActionEvent e) throws SQLException {
        TextFieldResult.getChildren().clear();
        String textFieldText = searchTextfield.getText();
        String s1 = DatabaseConnection.searchDVD(textFieldText);
        String[] resultArray = s1.split("\n");

        for (String result : resultArray) {
            Text resultText = new Text(" "+result + "\n");
            resultText.setFill(Color.BLUE);
            resultText.setOnMouseClicked(event -> {
                Picked.setText(resultText.getText());
                pickedDVD = resultText.getText();
            });
            resultText.setTextAlignment(TextAlignment.LEFT);
            resultText.setWrappingWidth(TextFieldResult.getPrefWidth());
            TextFieldResult.getChildren().add(resultText);
        }

        scrollPane1.setContent(TextFieldResult);
    }


    @FXML
    protected void goToLoan() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("loan.fxml"));
        Parent myPagesParent = fxmlLoader.load();
        Scene myPagesScene = new Scene(myPagesParent);
        Stage currentStage = (Stage) goToLoanBtn.getScene().getWindow();
        currentStage.setScene(myPagesScene);
    }


    @FXML
    protected void ClickButtonDvd(ActionEvent e) throws SQLException {
        if(pickedDVD == null){
            Picked.setText("Please pick a book");
        }else{
            LoanList.addToList("dvd: ");
            LoanList.addToList(pickedDVD);
            LoanList.addToList("\n");
            Picked.setText("DVD was added");
            System.out.println(pickedDVD);
            System.out.println("liste item "+LoanList.getList().get(0));
        }


    }
}
