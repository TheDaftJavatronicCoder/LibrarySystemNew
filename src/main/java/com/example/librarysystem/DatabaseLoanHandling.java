package com.example.librarysystem;

import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DatabaseLoanHandling extends DatabaseConnection{

    public void addNewLoan(String lone, boolean bool) throws SQLException {
        if(kundIDcurr == 0){
            System.out.println("you need to log in, in order to loan");
        }
        ResultSet resultSet = st.executeQuery("SELECT kund_Id FROM lan");
        boolean controllLan = true;
        WantToReserve = bool;
        while (resultSet.next()) {
            int gottenId = resultSet.getInt("kund_Id");
            if (kundIDcurr == gottenId) {
                controllLan = false;
            }
        }
        if (controllLan) {
            String query = "INSERT INTO lan (kund_Id) VALUES (?)";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, kundIDcurr);
            statement.executeUpdate();


        }
        resultSet = st.executeQuery("SELECT lan_Id FROM lan");
        while (resultSet.next()) {
            lanID = resultSet.getInt("lan_Id");
        }

        String sql = "SELECT * FROM kund WHERE kund_Id = (?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, kundIDcurr);

        resultSet = statement.executeQuery();

        if (resultSet.next()) {
            gottenRole = resultSet.getString("kund_Typ"); // Assuming "role" is the column name for the role in the table
            gottenAge = resultSet.getInt("age"); // Assuming "age" is the column name for the age in the table
        }
        System.out.println(gottenRole);
        System.out.println(gottenAge);

        LoanLimitCurr = calculateLoanLimit(gottenRole);

        String[] loneArray = lone.split("\n");
        for (int i = 0; i < loneArray.length; i++) {
            if(calculateEntryCount(lanID) >= LoanLimitCurr){
                System.out.println("Max Loanes achived");
                return;
            }

            if (loneArray[i].startsWith("Book: ")) {
                String updatedString = loneArray[i].replaceFirst("Book: ", "");
                updatedString = updatedString.substring(0, updatedString.length() - 7).trim();

                try {
                    Statement statement2 = conn.createStatement();

                    String query = "SELECT kategori, barcode_Bok, bok_Namn FROM Bok";
                    resultSet = statement2.executeQuery(query);

                    while (resultSet.next()) {
                        String nammmn = resultSet.getString("bok_Namn");
                        String kategori = resultSet.getString("kategori");
                        String barcode = resultSet.getString("barcode_Bok");
                        if(nammmn.equals(updatedString)) {
                            barcodeAddLoan = barcode;
                            kategoriAddLoan = kategori;
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }



                String insertQuery = "INSERT INTO Bok_Lan (datum_Utlanad, datum_Planerad_return, datum_Faktisk_retur, skuld, bok_Status, barcode_Bok, lan_Id) VALUES (?, ?, ?, ?, ?, ?, ?)";

                try (PreparedStatement statement3 = conn.prepareStatement(insertQuery)) {
                    Date InDate = calcReturnDate(kategoriAddLoan);
                    LocalDate currentDate = LocalDate.now();
                    statement3.setDate(1, Date.valueOf(currentDate)); // Replace with the actual date of borrowing
                    statement3.setDate(2, InDate); // Replace with the actual return date
                    statement3.setDate(3, null); // Replace with the actual value for "datum_Faktisk_retur"
                    statement3.setInt(4, 0); // Replace with the actual value for "skuld"
                    statement3.setString(5, "Aktiv"); // Replace with the actual value for "bok_Status"
                    statement3.setString(6, barcodeAddLoan);
                    statement3.setInt(7, lanID);

                    if(WantToReserve){
                        statement3.setDate(1, null);
                        statement3.setDate(2, null);
                        statement3.setString(5, "Reserverad");
                    }

                    int rowsAffected = statement3.executeUpdate();
                    System.out.println(rowsAffected + " row(s) inserted into Bok_Lan table.");

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }if(loneArray[i].startsWith("DVD: ")){
                String updatedString = loneArray[i].replaceFirst("DVD: ", "");
                updatedString = updatedString.substring(0, updatedString.length() - 7).trim();

                try {
                    Statement statement4 = conn.createStatement();

                    String query = "SELECT aldersgrans, barcode_DVD, dvd_Namn FROM dvd";
                    resultSet = statement4.executeQuery(query);

                    while (resultSet.next()) {
                        String nammmn = resultSet.getString("dvd_Namn");
                        int agelimitCurrHolder = resultSet.getInt("aldersgrans");
                        String barcode = resultSet.getString("barcode_DVD");
                        if(nammmn.equals(updatedString)) {
                            barcodeAddLoan = barcode;
                            agelimitCurr = agelimitCurrHolder;
                        }

                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if(agelimitCurr >= gottenAge){
                    System.out.println("age limit reached, aborting this loan");
                    skipNextLoan = true;
                }

                String insertQuery = "INSERT INTO DVD_Lan (datum_Utlanad, datum_Planerad_return, datum_Faktisk_retur, skuld, dvd_Status, barcode_DVD, lan_Id) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)";
                if(!skipNextLoan){

                    try (PreparedStatement statement5 = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
                        Date borrowDate = Date.valueOf(LocalDate.now()); // Replace with the actual borrow date
                        Date returnDate = calcReturnDate("dvd"); // Replace with the actual return date

                        statement5.setDate(1, borrowDate);
                        statement5.setDate(2, returnDate);
                        statement5.setDate(3, null); // Replace with the actual value for "datum_Faktisk_retur"
                        statement5.setInt(4, 0); // Replace with the actual value for "skuld"
                        statement5.setString(5, "Aktiv"); // Replace with the actual value for "dvd_Status"
                        statement5.setString(6, barcodeAddLoan);
                        statement5.setInt(7, lanID); // Replace with the actual value for "lan_Id"

                        if(WantToReserve){
                            statement5.setDate(1, null);
                            statement5.setDate(2, null);
                            statement5.setString(5, "Reserverad");
                        }

                        int rowsAffected = statement5.executeUpdate();
                        System.out.println(rowsAffected + " row(s) inserted into DVD_Lan table.");

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                skipNextLoan = false;
            }

        }

    }
    public static Date calcReturnDate(String kategoriAddLoan) {
        LocalDate currentDate = LocalDate.now();

        if (kategoriAddLoan.equals("skönlitterär")) {
            // Add 2 weeks to the current date
            LocalDate returnDate = currentDate.plus(4, ChronoUnit.WEEKS);
            return Date.valueOf(returnDate);
        } else if (kategoriAddLoan.equals("skolbok")) {
            // Add 1 week to the current date
            LocalDate returnDate = currentDate.plus(2, ChronoUnit.WEEKS);
            return Date.valueOf(returnDate);
        } else if (kategoriAddLoan.equals("dvd")) {
            // Return current date without any addition
            LocalDate returnDate = currentDate.plus(1, ChronoUnit.WEEKS);
            return Date.valueOf(returnDate);
        } else {
            System.out.println("didnt find anything");
            // Invalid category, return null or throw an exception
            // Handle the case based on your application's requirements
            return null;
        }

    }

    public int calculateLoanLimit(String occupation) {
        int loanLimit;

        if (occupation.equalsIgnoreCase("teacher")) {
            loanLimit = 20;
        } else if (occupation.equalsIgnoreCase("student")) {
            loanLimit = 10;
        } else {
            loanLimit = 5; // Assuming the default loan limit for other occupations is 5
        }
        return loanLimit;
    }

    public int calculateEntryCount(int lanId) {
        int entryCount = 0;

        try {
            String sql = "SELECT COUNT(*) AS entryCount FROM Bok_Lan WHERE lan_Id = ? AND bok_Status = 'Aktiv' UNION ALL " +
                    "SELECT COUNT(*) AS entryCount FROM DVD_Lan WHERE lan_Id = ? AND dvd_Status = 'Aktiv'";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, lanId);
            statement.setInt(2, lanId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                entryCount += resultSet.getInt("entryCount");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entryCount;
    }

    public boolean returnLoan(String savedbarcode){

        String barcodesReturn[] = savedbarcode.split("\n");
        for (int i = 0; i < barcodesReturn.length; i++) {
            savedbarcode = barcodesReturn[i];

            try {
                String sql = "UPDATE bok_lan SET bok_Status = 'Returnerad', datum_Faktisk_retur = ? " +
                        "WHERE lan_Id = ? AND bok_Status = 'Aktiv' AND barcode_Bok = ?";

                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setDate(1, Date.valueOf(LocalDate.now()));
                statement.setInt(2, lanID);
                statement.setString(3, savedbarcode);

                int rowsAffectedLoan = 0;
                rowsAffectedLoan = statement.executeUpdate();
                System.out.println(rowsAffectedLoan + " row(s) updated successfully.");
                if (rowsAffectedLoan != 0) {
                    rowsAffectedLoan = 0;
                    return true;
                }
                sql = "UPDATE dvd_lan SET dvd_Status = 'Returnerad', datum_Faktisk_retur = ? " +
                        "WHERE lan_Id = ? AND dvd_Status = 'Aktiv' AND barcode_DVD = ?";

                statement.setDate(1, Date.valueOf(LocalDate.now()));
                statement.setInt(2, kundIDcurr);
                statement.setString(3, savedbarcode);

                rowsAffectedLoan = 0;
                rowsAffectedLoan = statement.executeUpdate();
                System.out.println(rowsAffectedLoan + " row(s) updated successfully.");
                if (rowsAffectedLoan != 0) {
                    rowsAffectedLoan = 0;
                    return true;
                }

                return false;
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        }
        return false;
    }

}
