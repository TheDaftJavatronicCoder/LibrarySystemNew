package com.example.librarysystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DeleteObjectController implements Initializable{



    @FXML
    private TextField deleteBookBarcodeTextField;

    @FXML
    private Button deleteBookFromSystemButton;

    @FXML
    private TextField deleteDVDBarcodeTextField;

    @FXML
    private Button deleteDVDFromSystemButton;

    @FXML
    private Button returnBackToAdminPage;




        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {

        }
    @FXML
    private void deleteBookClass() throws SQLException {
        String barcode_Bok = this.deleteBookBarcodeTextField.getText();

        DatabaseInsertHandling database = new DatabaseInsertHandling();
        database.deleteBook(barcode_Bok);
    }

    @FXML
    private void deleteDVDClass() throws SQLException {
        String barcode_DVD = this.deleteDVDBarcodeTextField.getText();

        DatabaseInsertHandling database = new DatabaseInsertHandling();
        database.deleteDVD(barcode_DVD);
    }



    public void switchToBackAdminPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("adminpage.fxml"));
        Parent myPagesParent = fxmlLoader.load();
        Scene myPagesScene = new Scene(myPagesParent);
        Stage currentStage = (Stage) returnBackToAdminPage.getScene().getWindow();
        currentStage.setScene(myPagesScene);
    }

}


