package com.example.librarysystem;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseInsertHandling extends DatabaseConnection{

    public void insertPost(String namnF, String namnE, String epost, String telefonNr, String adress, int age, String kundTyp, String kundUsername, String kundPassword) throws SQLException {

        String INSERT_QUERY = "INSERT INTO Kund (namn_F, namn_E, epost, telefon_Nr, adress, age, kund_Typ, kund_Username, kund_Password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = conn.prepareStatement(INSERT_QUERY); {
            statement.setString(1, namnF);
            statement.setString(2, namnE);
            statement.setString(3, epost);
            statement.setString(4, telefonNr);
            statement.setString(5, adress);
            statement.setInt(6, age);
            statement.setString(7, kundTyp);
            statement.setString(8, kundUsername);
            statement.setString(9, kundPassword);


            int rowsAffected = statement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);

        }
    }



    public void insertBok(String barcode_Bok, String bok_Namn, int bok_Ar, String bok_Genre, String kategori, String bok_Forfattare, int Hylla, int Antal_Kopior_Inne, String ISBN) throws SQLException {

        String INSERT_QUERY2 = "INSERT INTO Bok (barcode_Bok, bok_Namn, bok_Ar, bok_Genre, kategori, bok_Forfattare, Hylla, Antal_Kopior_Inne, ISBN) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = conn.prepareStatement(INSERT_QUERY2); {
            statement.setString(1, barcode_Bok);
            statement.setString(2, bok_Namn);
            statement.setInt(3, bok_Ar);
            statement.setString(4, bok_Genre);
            statement.setString(5, kategori);
            statement.setString(6, bok_Forfattare);
            statement.setInt(7, Hylla);
            statement.setInt(8, Antal_Kopior_Inne);
            statement.setString(9, ISBN);

            int rowsAffected = statement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);

        }
    }





    public void insertDVD(String barcode_DVD, String dvd_Namn, int dvd_Ar, String dvd_Genre, String dvd_Regissor, int aldersgrans, int Hylla, int Antal_Kopior_Inne) throws SQLException {

        String INSERT_QUERY3 = "INSERT INTO DVD (barcode_DVD, dvd_Namn, dvd_Ar, dvd_Genre, dvd_Regissor, aldersgrans, Hylla, Antal_Kopior_Inne) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = conn.prepareStatement(INSERT_QUERY3); {
            statement.setString(1, barcode_DVD);
            statement.setString(2, dvd_Namn);
            statement.setInt(3, dvd_Ar);
            statement.setString(4, dvd_Genre);
            statement.setString(5, dvd_Regissor);
            statement.setInt(6, aldersgrans);
            statement.setInt(7, Hylla);
            statement.setInt(8, Antal_Kopior_Inne);


            int rowsAffected = statement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);

        }
    }

    public void updateBok(String barcode_Bok, String bok_Namn, int bok_Ar, String bok_Genre, String kategori, String bok_Forfattare, int hylla, int antal_Kopior_Inne, String ISBN) throws SQLException {
        String UPDATE_QUERY2 = "UPDATE your_table_name SET bok_Namn=?, bok_Ar=?, bok_Genre=?, kategori=?, bok_Forfattare=?, hylla=?, antal_Kopior_Inne=?, ISBN=? WHERE barcode_Bok=?";

        PreparedStatement statement = conn.prepareStatement(UPDATE_QUERY2);
        statement.setString(1, bok_Namn);
        statement.setInt(2, bok_Ar);
        statement.setString(3, bok_Genre);
        statement.setString(4, kategori);
        statement.setString(5, bok_Forfattare);
        statement.setInt(6, hylla);
        statement.setInt(7, antal_Kopior_Inne);
        statement.setString(8, ISBN);
        statement.setString(9, barcode_Bok);

        int rowsAffected = statement.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Book updated successfully");
        } else {
            System.out.println("Failed to update book");
        }
    }

    public void updateDVD(String barcode_DVD, String dvd_Namn, int dvd_Ar, String dvd_Genre, String dvd_Regissor, int aldersgrans, int hylla, int Antal_Kopior_Inne) throws SQLException {
        String UPDATE_QUERY3 = "UPDATE DVD SET dvd_Namn=?, dvd_Ar=?, dvd_Genre=?, dvd_Regissor=?, aldersgrans=?, hylla=?, antal_Kopior_Inne=? WHERE barcode_DVD=?";

        PreparedStatement statement = conn.prepareStatement(UPDATE_QUERY3);
        statement.setString(1, dvd_Namn);
        statement.setInt(2, dvd_Ar);
        statement.setString(3, dvd_Genre);
        statement.setString(4, dvd_Regissor);
        statement.setInt(5, aldersgrans);
        statement.setInt(6, hylla);
        statement.setInt(7, Antal_Kopior_Inne);
        statement.setString(8, barcode_DVD);

        int rowsAffected = statement.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("DVD updated successfully");
        } else {
            System.out.println("Failed to update DVD");
        }
    }

    public void deleteBook(String barcode_Bok) throws SQLException {
        String DELETE_QUERY = "DELETE FROM Bok WHERE barcode_Bok=?";

        PreparedStatement statement = conn.prepareStatement(DELETE_QUERY);
        statement.setString(1, barcode_Bok);

        int rowsAffected = statement.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Book deleted successfully");
        } else {
            System.out.println("Failed to delete book");
        }
    }

    public void deleteDVD(String barcode_Bok) throws SQLException {
        String DELETE_QUERY = "DELETE FROM DVD WHERE barcode_DVD=?";

        PreparedStatement statement = conn.prepareStatement(DELETE_QUERY);
        statement.setString(1, barcode_Bok);

        int rowsAffected = statement.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("DVD deleted successfully");
        } else {
            System.out.println("Failed to delete DVD");
        }
    }

}
