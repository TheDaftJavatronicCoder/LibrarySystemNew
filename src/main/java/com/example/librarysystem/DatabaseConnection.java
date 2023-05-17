package com.example.librarysystem;

import java.sql.*;

public class DatabaseConnection {

    static String name = "";
    static String bookNames = "";
    static Connection conn = null;
    static Statement st = null;

    public static void connect() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/Testingbase"; // Replace with your database URL
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
            rs =  st.executeQuery("SELECT * FROM bok");;

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


    }

