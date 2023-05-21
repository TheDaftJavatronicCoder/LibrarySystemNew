package com.example.librarysystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SearchController {

    @FXML
    TextField searchTextfield;
    @FXML
    TextFlow TextFieldResult;

    @FXML
    ScrollPane scrollPane1;

    @FXML
    Button pickBtn;

    @FXML
    Label Picked;

    @FXML
    Button addBook;

    @FXML
    Button goToLoanBtn;

    String pickedbook;

    String pickedDVD;

    String CompLoan = "";

    String Type;

    private List<String> loanList = LoanList.getList();


    @FXML
    protected void SearchFuncbook(ActionEvent e) throws SQLException {
        TextFieldResult.getChildren().clear();
        String textFieldText = searchTextfield.getText();
        String s1 = DatabaseSearchHandling.searchBook(textFieldText);
        String[] resultArray = s1.split("\n");

        for (String result : resultArray) {
            Text resultText = new Text(" "+result + "\n");
            resultText.setFill(Color.BLUE);
            resultText.setOnMouseClicked(event -> {
                Picked.setText(resultText.getText());
                pickedbook = resultText.getText();
                Type = "Book: ";
            });
            resultText.setTextAlignment(TextAlignment.LEFT);
            resultText.setWrappingWidth(TextFieldResult.getPrefWidth());
            TextFieldResult.getChildren().add(resultText);
        }

        scrollPane1.setContent(TextFieldResult);
    }

    @FXML
    protected void SearchFundvd(ActionEvent e) throws SQLException {
        TextFieldResult.getChildren().clear();
        String textFieldText = searchTextfield.getText();
        String s1 = DatabaseSearchHandling.searchDVD(textFieldText);
        String[] resultArray = s1.split("\n");

        for (String result : resultArray) {
            Text resultText = new Text(" "+result + "\n");
            resultText.setFill(Color.BLUE);
            resultText.setOnMouseClicked(event -> {
                Picked.setText(resultText.getText());
                pickedDVD = resultText.getText();
                Type = "DVD: ";
            });
            resultText.setTextAlignment(TextAlignment.LEFT);
            resultText.setWrappingWidth(TextFieldResult.getPrefWidth());
            TextFieldResult.getChildren().add(resultText);
        }

        scrollPane1.setContent(TextFieldResult);
    }



    @FXML
    protected void goToLoan() throws IOException {
        LoanController.UpString(CompLoan);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("loan.fxml"));
        Parent myPagesParent = fxmlLoader.load();
        Scene myPagesScene = new Scene(myPagesParent);
        Stage currentStage = (Stage) goToLoanBtn.getScene().getWindow();
        currentStage.setScene(myPagesScene);
    }


    @FXML
    protected void ClickButton(ActionEvent e) throws SQLException {
        if(Type.equals("Book: ")) {
            if (pickedbook == null) {
                Picked.setText("Please pick a Item");
            } else {
                Picked.setText("Item was added");
                System.out.println(pickedbook);
                CompLoan = CompLoan + Type + pickedbook + "\n";
            }
        }
        else if(Type.equals("DVD: ")){
            if (pickedDVD == null) {
                Picked.setText("Please pick a Item");
            } else {
                Picked.setText("Item was added");
                System.out.println(pickedDVD);
                CompLoan = CompLoan + Type + pickedDVD + "\n";
            }
        }
    }


}




