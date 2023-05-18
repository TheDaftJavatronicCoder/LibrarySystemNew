package com.example.librarysystem;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class ReminderViewController {

    @FXML
    TextField emailTextField;

    @FXML
    Button sendReminderButton;


    @FXML
    private void sendReminder() throws SQLException {
        DatabaseConnection dbcon = new DatabaseConnection();
        String resultemail = dbcon.RameinderEmail();
        emailTextField.setText(resultemail);

    }

}
