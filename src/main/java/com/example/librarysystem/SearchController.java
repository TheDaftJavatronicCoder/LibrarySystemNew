package com.example.librarysystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

import java.sql.SQLException;

public class SearchController {

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

    String pickedbook;


    @FXML
    protected void SearchFunc(ActionEvent e) throws SQLException {
        TextFieldResult.getChildren().clear();
        String textFieldText = searchTextfield.getText();
        String s1 = DatabaseConnection.searchBook(textFieldText);
        String[] resultArray = s1.split("\n");

        for (String result : resultArray) {
            Text resultText = new Text(" "+result + "\n");
            resultText.setFill(Color.BLUE);
            resultText.setOnMouseClicked(event -> {
                Picked.setText(resultText.getText());
                pickedbook = resultText.getText();
            });
            resultText.setTextAlignment(TextAlignment.LEFT);
            resultText.setWrappingWidth(TextFieldResult.getPrefWidth());
            TextFieldResult.getChildren().add(resultText);
        }

        scrollPane1.setContent(TextFieldResult);
    }

    @FXML
    protected void ClickButton(ActionEvent e) throws SQLException {
        if(pickedbook == null){
            Picked.setText("Please pick a book");
        }else{
            System.out.println("proceed to loan: "+pickedbook);
        }


    }
}
