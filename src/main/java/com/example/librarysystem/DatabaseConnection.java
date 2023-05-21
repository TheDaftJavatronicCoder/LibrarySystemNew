package com.example.librarysystem;

import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DatabaseConnection {
    protected static String name = "";
    protected static String bookNames = "";
    protected static Connection conn = null;
    protected static Statement st = null;

    protected static int kundIDcurr = 0;
    protected int lanID = 0;

    protected String barcodeAddLoan = "";

    protected String kategoriAddLoan = "";

    protected String gottenRole = "";
    protected int gottenAge = 0;

    protected int LoanLimitCurr = 0;

    protected boolean skipNextLoan = false;

    protected boolean WantToReserve = false;
    protected int agelimitCurr;


    //If you want to connect with default credentials and url
    public static void connect() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/Testingbase3"; // Replace with your database URL
        String username = "root"; // Replace with your MySQL username
        String password = "admin"; // Replace with your MySQL password

        // Establishing the connection
        try {
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database!");
            st = conn.createStatement();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Polymorphism if you want to connect with diffrent credentials and url

    public static void connect(String url, String username, String password) throws SQLException {
        // Establishing the connection
        try {
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database!");
            st = conn.createStatement();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Polymorphism if you want to connect with diffrent credentials only

    public static void connect(String username, String password) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/Testingbase3"; // Replace with your database URL

        try {
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database!");
            st = conn.createStatement();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void disconnect() throws SQLException {
        conn.close();
        st.close();
    }

    public boolean signIn(String uN, String uP) throws SQLException {
        ResultSet resultSet = st.executeQuery("SELECT kund_Username,kund_Password, kund_Id FROM kund");
        while (resultSet.next()) {
            kundIDcurr = resultSet.getInt("kund_Id");
            String username = resultSet.getString("kund_Username");
            String password = resultSet.getString("kund_Password");


            if (username.equals(uN) && password.equals(uP)) {
                resultSet = st.executeQuery("SELECT lan_Id FROM lan WHERE lan.kund_Id = "+kundIDcurr);
                while (resultSet.next()) {
                    lanID = resultSet.getInt("lan_Id");
                }
                return true;
            }
        }
        return false;
    }


    public static boolean signInAdmin(String uN, String uP) throws SQLException {
        ResultSet resultSet = st.executeQuery("SELECT a_Id, a_Password FROM Personal");
        while (resultSet.next()) {
            String username = resultSet.getString("a_Id");
            String password = resultSet.getString("a_Password");
            if (username.equals(uN) && password.equals(uP)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkUsername(String nameNewUser) throws SQLException {
        ResultSet resultSet = st.executeQuery("SELECT kund_Username FROM kund");
        while (resultSet.next()) {
            String UserExist = resultSet.getString("kund_Username");
            if (UserExist.equals(nameNewUser)) {
                return true;
            }
        }
        return false;
    }


    public String getLoanInfo() throws SQLException {
        String sql = "SELECT * FROM Bok_Lan bl " +
                "JOIN Lan l ON l.lan_Id = bl.lan_Id " +
                "JOIN Bok b ON b.barcode_Bok = bl.barcode_Bok " +
                "WHERE l.kund_Id = ? " +
                "AND DATE(bl.datum_Utlanad) = CURDATE() " +
                "AND (bl.bok_Status = 'Aktiv' OR bl.bok_Status = 'Reserverad')";


        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, kundIDcurr);
        ResultSet resultSet = statement.executeQuery();


        String fullresult = "";

        while (resultSet.next()) {
            String nameBok = resultSet.getString("bok_Namn");
            String barcodeBok = resultSet.getString("barcode_Bok");
            String datumPlanRetur = resultSet.getString("datum_Planerad_return");
            String bokStatus = resultSet.getString("bok_Status");


            String result = String.format("Titel: "+nameBok+ "\nBarcode: "+ barcodeBok+ "\nRetur Datum: "+  datumPlanRetur+
                    "\nStatus: "+  bokStatus);
            fullresult = fullresult +  result + "\n" + "---------------------------------------------------------" + "\n";

        }
        sql = "SELECT * FROM DVD_Lan dl " +
                "JOIN Lan l ON l.lan_Id = dl.lan_Id " +
                "JOIN DVD d ON d.barcode_DVD = dl.barcode_DVD " +
                "WHERE l.kund_Id = ? " +
                "AND DATE(dl.datum_Utlanad) = CURDATE() " +
                "AND (dl.dvd_Status = 'Aktiv' OR dl.dvd_Status = 'Reserverad')";

        statement = conn.prepareStatement(sql);
        statement.setInt(1, kundIDcurr);
        resultSet = statement.executeQuery();


        while (resultSet.next()) {
            String namedvd = resultSet.getString("dvd_Namn");
            String barcodeDVD = resultSet.getString("barcode_DVD");
            String datumPlanRetur = resultSet.getString("datum_Planerad_return");
            String DVDStatus = resultSet.getString("dvd_Status");

            String result = String.format("Titel: "+namedvd+ "\nBarcode: "+ barcodeDVD+ "\nRetur Datum: "+  datumPlanRetur+
                    "\nStatus: "+  DVDStatus);
            fullresult = fullresult +  result + "\n" + "---------------------------------------------------------" + "\n";
        }
        return fullresult;

    }

    public static String RameinderEmail() throws SQLException {
        String result = "";
        String sql = "SELECT epost FROM kund JOIN lan ON kund.kund_Id = lan.kund_Id JOIN bok_lan ON" +
                " lan.lan_Id = bok_lan.lan_Id JOIN dvd_lan ON lan.lan_Id = dvd_lan.lan_Id WHERE" +
                " (dvd_lan.datum_Planerad_return <= CURDATE() OR bok_lan.datum_Planerad_return <= CURDATE())";
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            String emails = resultSet.getString("epost");
            result = (result + emails + "\n");

        }
        return result;
    }

    public static boolean checkBarcode_Bok(String barcodeBok) throws SQLException {
        ResultSet resultSet = st.executeQuery("SELECT barcode_Bok FROM Bok");
        while (resultSet.next()) {
            String UserExist = resultSet.getString("barcode_Bok");
            if (UserExist.equals(barcodeBok)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkBarcode_DVD(String barcodeBok) throws SQLException {
        ResultSet resultSet = st.executeQuery("SELECT barcode_DVD FROM DVD");
        while (resultSet.next()) {
            String UserExist = resultSet.getString("barcode_DVD");
            if (UserExist.equals(barcodeBok)) {
                return true;
            }
        }
        return false;
    }




}


