package com.example.librarysystem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {
    static String name = "";
    static String bookNames = "";
    static Connection conn = null;
    static Statement st = null;

    public static void connect() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/Testingbase2"; // Replace with your database URL
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

    public static void disconnect() throws SQLException {
        conn.close();
        st.close();
    }

    public static boolean signIn(String uN, String uP) throws SQLException {
        ResultSet resultSet = st.executeQuery("SELECT kund_Username,kund_Password FROM kund");
        while (resultSet.next()) {
            String username = resultSet.getString("kund_Username");
            String password = resultSet.getString("kund_Password");
            if (username.equals(uN) && password.equals(uP)) {
                return true;
            }
        }
        return false;
    }


//    public static String testSearch() throws SQLException {
//        // Database credentials
//
//        ResultSet resultSet = st.executeQuery("SELECT * FROM users");
//
//        name = "";
//        while (resultSet.next()) {
//            int id = resultSet.getInt("id");
//            String firstName = resultSet.getString("firstname");
//            String lastName = resultSet.getString("lastname");
//            String fullName = firstName + " " + lastName;
//            name += fullName + "\n";
//        }
//
//        // Don't forget to close the connection and result set when you're done
//        resultSet.close();
//
//        return name;
//    }

    public static String searchBook(String term) {
        String searchString = term;
        StringBuilder result = new StringBuilder();
        ResultSet rs = null;
        try {
            rs =  st.executeQuery("SELECT * FROM bok");

            // the string to search for
            while (rs.next()) {
                boolean matchFound = false;
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    String columnValue = rs.getString(i);
                    if (columnValue.toLowerCase().contains(searchString.toLowerCase())) {
                        matchFound = true;
                        break;
                    }
                }
                if (matchFound) {
                    String bookName = rs.getString("bok_Namn");
                    String bokAr = rs.getString("bok_Ar");
                    bookNames += bookName + " ("+ bokAr + ")" + "\n";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String returnString = bookNames;
        bookNames = "";
        return returnString;
    }

    private static final String INSERT_QUERY = "INSERT INTO Kund (namn_F, namn_E, epost, telefon_Nr, adress, age, kund_Typ, kund_Username, kund_Password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public void insertPost(String namnF, String namnE, String epost, String telefonNr, String adress, int age, String kundTyp, String kundUsername, String kundPassword) throws SQLException {

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


    public String getLoanInfo() throws SQLException {
        String sql = "SELECT * FROM bok_lan JOIN lan JOIN bok WHERE lan.lan_Id = bok_lan.lan_Id AND" +
                " lan.kund_Id = 2 AND bok.barcode_Bok = bok_lan.barcode_Bok;";
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        List<String> resultList = new ArrayList<>();
        String fullresult = "";

        while (resultSet.next()) {
            String nameBok = resultSet.getString("bok_Namn");
            String barcodeBok = resultSet.getString("barcode_Bok");
            String datumFaktiskRetur = resultSet.getString("datum_Faktisk_retur");
            String bokStatus = resultSet.getString("bok_Status");
            String skuldString = String.valueOf(resultSet.getInt("skuld"));


            String result = String.format("Titel: "+nameBok+ "\nBarcode: "+ barcodeBok+ "\nRetur Datum: "+  datumFaktiskRetur+
                    "\nStatus: "+  bokStatus+ "\nSkuld: "+  skuldString);
            fullresult = fullresult +  result + "\n" + "---------------------------------------------------------" + "\n";

        }
        sql = "SELECT * FROM dvd_lan JOIN lan JOIN dvd WHERE lan.lan_Id = dvd_lan.lan_Id AND" +
                " lan.kund_Id = 2 AND dvd.barcode_DVD = dvd_lan.barcode_DVD";
        statement = conn.prepareStatement(sql);
        resultSet = statement.executeQuery();


        while (resultSet.next()) {
            String namedvd = resultSet.getString("dvd_Namn");
            String barcodeDVD = resultSet.getString("barcode_DVD");
            String datumFaktiskRetur = resultSet.getString("datum_Faktisk_retur");
            String DVDStatus = resultSet.getString("dvd_Status");
            String skuldString = String.valueOf(resultSet.getInt("skuld"));

            String result = String.format("Titel: "+namedvd+ "\nBarcode: "+ barcodeDVD+ "\nRetur Datum: "+  datumFaktiskRetur+
                    "\nStatus: "+  DVDStatus+ "\nSkuld: "+  skuldString);
            fullresult = fullresult +  result + "\n" + "---------------------------------------------------------" + "\n";
        }
        return fullresult;

    }

    public static String RameinderEmail() throws SQLException {
        String result = "";
        String sql = "SELECT epost FROM kund JOIN lan ON kund.kund_Id = lan.kund_Id JOIN bok_lan ON" +
                " lan.lan_Id = bok_lan.lan_Id JOIN dvd_lan ON lan.lan_Id = dvd_lan.lan_Id WHERE" +
                " (dvd_lan.datum_Faktisk_retur <= CURDATE() OR bok_lan.datum_Faktisk_retur <= CURDATE())";
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            String emails = resultSet.getString("epost");
            result = (result + emails + "\n");

        }
        return result;
    }

    private static final String INSERT_QUERY2 = "INSERT INTO Bok (barcode_Bok, bok_Namn, bok_Ar, bok_Genre, kategori, bok_Forfattare, Hylla, Antal_Kopior_Inne, ISBN) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public void insertBok(String barcode_Bok, String bok_Namn, int bok_Ar, String bok_Genre, String kategori, String bok_Forfattare, int Hylla, int Antal_Kopior_Inne, String ISBN) throws SQLException {

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

    private static final String INSERT_QUERY3 = "INSERT INTO DVD (barcode_DVD, dvd_Namn, dvd_Ar, dvd_Genre, dvd_Regissor, aldersgrans, Hylla, Antal_Kopior_Inne) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    public void insertDVD(String barcode_DVD, String dvd_Namn, int dvd_Ar, String dvd_Genre, String dvd_Regissor, int aldersgrans, int Hylla, int Antal_Kopior_Inne) throws SQLException {

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





