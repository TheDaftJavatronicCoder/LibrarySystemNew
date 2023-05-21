package com.example.librarysystem;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseSearchHandling extends DatabaseConnection {

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

    public static String searchDVD(String term) {
        String searchString = term;
        StringBuilder result = new StringBuilder();
        ResultSet rs = null;
        try {
            rs =  st.executeQuery("SELECT * FROM dvd");

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
                    String bookDvd = rs.getString("dvd_Namn");
                    String DvdAr = rs.getString("dvd_Ar");
                    bookNames += bookDvd + " ("+ DvdAr + ")" + "\n";
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
