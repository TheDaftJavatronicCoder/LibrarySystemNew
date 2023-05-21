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
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminController implements Initializable {


    @FXML
    private TextField DVDAgeRestrictionTextField;


    @FXML
    private TextField DVDBarcodeTextField;



    @FXML
    private TextField DVDCopiesTextField;



    @FXML
    private TextField DVDDirectorTextButton;



    @FXML
    private TextField DVDGenreTextField;



    @FXML
    private TextField DVDNameTextFIeld;


    @FXML
    private TextField DVDRealeseYearTextField;



    @FXML
    private TextField DVDShelfTesxtField;

    @FXML
    private Button addDVDButton;

    @FXML
    private Button addObjectButton;

    @FXML
    private TextField bookAuthorTextField1;

    @FXML
    private TextField bookBarcodeTextField1;

    @FXML
    private TextField bookCategoryTextField1;

    @FXML
    private TextField bookCopiesTextField1;

    @FXML
    private TextField bookGenreTextField1;

    @FXML
    private TextField bookISBNTextField1;

    @FXML
    private TextField bookNameTextField1;

    @FXML
    private TextField bookRealeseYearTextField1;

    @FXML
    private TextField bookShelfTextField1;


    @FXML
    private GridPane gpAddBook;

    @FXML
    private GridPane gpAddDVD;



    @FXML
    private Button insertDVDButton;

   @FXML
   private Button insertBookButton;

    @FXML
    private Button updateBookButton;

    @FXML
    private Label lblStatus;

    @FXML
    private Pane pnlStatus;

    @FXML
    private Button startPageButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    @FXML
    private void createBokClass() throws SQLException {
        String barcode_Bok = this.bookBarcodeTextField1.getText();
        DatabaseConnection databas5 = null;
        if (databas5.checkBarcode_Bok(barcode_Bok)) {
            System.out.println("BARCODE IS ALREADY IN USE");
            return;
        }

        String bok_Namn = this.bookNameTextField1.getText();

        int bok_Ar = 0;
        String bok_ArReal = this.bookRealeseYearTextField1.getText();

        try {
            bok_Ar = Integer.parseInt(bok_ArReal);
        } catch (NumberFormatException e) {
            System.out.println("Invalid format, should be a number: ");
            return;
        }
        String bok_Genre = this.bookGenreTextField1.getText();
        String kategori = this.bookCategoryTextField1.getText();
        String bok_Forfattare = this.bookAuthorTextField1.getText();

        int HyllaReal = 0;
        String Hylla = this.bookShelfTextField1.getText();

        try {
            HyllaReal = Integer.parseInt(Hylla);
        } catch (NumberFormatException e) {
            System.out.println("Invalid format, should be a number: ");
            return;
        }

        String ISBN = this.bookISBNTextField1.getText();

        int Antal_Kopior_InneReal = 0;
        String Antal_Kopior_Inne = this.bookCopiesTextField1.getText();

        try {
            Antal_Kopior_InneReal = Integer.parseInt(Antal_Kopior_Inne);
        } catch (NumberFormatException e) {
            System.out.println("Invalid format, should be a number: ");
            return;
        }

        databas5 = new DatabaseConnection();
        databas5.insertBok(barcode_Bok, bok_Namn, bok_Ar, bok_Genre, kategori, bok_Forfattare, HyllaReal, Antal_Kopior_InneReal, ISBN);
    }
    @FXML
    private void updateBokClass() throws SQLException {
        String barcode_Bok = this.bookBarcodeTextField1.getText();
        String bok_Namn = this.bookNameTextField1.getText();
        int bok_Ar = 0;
        String bok_ArReal = this.bookRealeseYearTextField1.getText();

        try {
            bok_Ar = Integer.parseInt(bok_ArReal);
        } catch (NumberFormatException e) {
            System.out.println("Invalid format, should be a number: ");
            return;
        }

        String bok_Genre = this.bookGenreTextField1.getText();
        String kategori = this.bookCategoryTextField1.getText();
        String bok_Forfattare = this.bookAuthorTextField1.getText();

        int hyllaReal = 0;
        String hylla = this.bookShelfTextField1.getText();

        try {
            hyllaReal = Integer.parseInt(hylla);
        } catch (NumberFormatException e) {
            System.out.println("Invalid format, should be a number: ");
            return;
        }

        int antal_Kopior_InneReal = 0;
        String antal_Kopior_Inne = this.bookCopiesTextField1.getText();

        try {
            antal_Kopior_InneReal = Integer.parseInt(antal_Kopior_Inne);
        } catch (NumberFormatException e) {
            System.out.println("Invalid format, should be a number: ");
            return;
        }

        String ISBN = this.bookISBNTextField1.getText();

        DatabaseConnection database = new DatabaseConnection();
        database.updateBok(barcode_Bok, bok_Namn, bok_Ar, bok_Genre, kategori, bok_Forfattare, hyllaReal, antal_Kopior_InneReal, ISBN);
    }





    @FXML
    private void createDVDClass() throws SQLException {
        String barcode_DVD = this.DVDBarcodeTextField.getText();
        DatabaseConnection databas6 = null;


        String dvd_Namn = this.DVDNameTextFIeld.getText();

        int dvd_Ar = 0;
        String dvd_ArReal = this.DVDRealeseYearTextField.getText();

        try {
            dvd_Ar = Integer.parseInt(dvd_ArReal);
        } catch (NumberFormatException e) {
            System.out.println("Invalid format, should be a number: ");
            return;
        }
        String dvd_Genre = this.DVDGenreTextField.getText();
        String dvd_Regissor = this.DVDDirectorTextButton.getText();
        int aldersgransReal = 0;
        String aldersgrans = this.DVDAgeRestrictionTextField.getText();
        try {
            aldersgransReal = Integer.parseInt(aldersgrans);
        } catch (NumberFormatException e) {
            System.out.println("Invalid format, should be a number: ");
            return;
        }

        int HyllaReal = 0;
        String Hylla1 = this.DVDShelfTesxtField.getText();

        try {
            HyllaReal = Integer.parseInt(Hylla1);
        } catch (NumberFormatException e) {
            System.out.println("Invalid format, should be a number: ");
            return;
        }


        int Antal_Kopior_InneReal = 0;
        String Antal_Kopior_Inne = this.DVDCopiesTextField.getText();

        try {
            Antal_Kopior_InneReal = Integer.parseInt(Antal_Kopior_Inne);
        } catch (NumberFormatException e) {
            System.out.println("Invalid format, should be a number: ");
            return;
        }

        databas6 = new DatabaseConnection();
        databas6.insertDVD(barcode_DVD, dvd_Namn, dvd_Ar, dvd_Genre, dvd_Regissor, aldersgransReal, HyllaReal, Antal_Kopior_InneReal);
    }

    @FXML
    private void updateDVDClass() throws SQLException {
        String barcodeDVD = this.DVDBarcodeTextField.getText();
        String dvdNamn = this.DVDNameTextFIeld.getText();
        int dvdAr = 0;
        String dvdArReal = this.DVDRealeseYearTextField.getText();

        try {
            dvdAr = Integer.parseInt(dvdArReal);
        } catch (NumberFormatException e) {
            System.out.println("Invalid format, should be a number: ");
            return;
        }

        String dvdGenre = this.DVDGenreTextField.getText();
        String dvdRegissor = this.DVDDirectorTextButton.getText();

        int aldersgransReal = 0;
        String aldersgrans = this.DVDAgeRestrictionTextField.getText();

        try {
            aldersgransReal = Integer.parseInt(aldersgrans);
        } catch (NumberFormatException e) {
            System.out.println("Invalid format, should be a number: ");
            return;
        }

        int hyllaReal = 0;
        String hylla = this.DVDShelfTesxtField.getText();

        try {
            hyllaReal = Integer.parseInt(hylla);
        } catch (NumberFormatException e) {
            System.out.println("Invalid format, should be a number: ");
            return;
        }

        int antalKopiorInneReal = 0;
        String antalKopiorInne = this.DVDCopiesTextField.getText();

        try {
            antalKopiorInneReal = Integer.parseInt(antalKopiorInne);
        } catch (NumberFormatException e) {
            System.out.println("Invalid format, should be a number: ");
            return;
        }

        DatabaseConnection database = new DatabaseConnection();
        database.updateDVD(barcodeDVD, dvdNamn, dvdAr, dvdGenre, dvdRegissor, aldersgransReal, hyllaReal, antalKopiorInneReal);
    }





    @FXML
    private void handleClicks(ActionEvent event) {

        if (event.getSource() == addObjectButton) {

            lblStatus.setText("Edit Book");
            pnlStatus.setBackground(new Background(new BackgroundFill(Color.rgb(38, 63, 115), CornerRadii.EMPTY, Insets.EMPTY)));
            gpAddBook.toFront();
            //handle startPageButton
        } else if (event.getSource() == addDVDButton) {

            lblStatus.setText("Edit DVD");
            pnlStatus.setBackground(new Background(new BackgroundFill(Color.rgb(45, 110, 80), CornerRadii.EMPTY, Insets.EMPTY)));
            gpAddDVD.toFront();

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

