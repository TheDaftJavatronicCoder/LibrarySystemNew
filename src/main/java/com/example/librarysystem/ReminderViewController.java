package com.example.librarysystem;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class ReminderViewController {

    @FXML
    private TextArea emailTextField;
    @FXML
    private Button sendReminderButton;
    @FXML
    private Label sentEmailLabel;

    public ReminderViewController(){
    }

    @FXML
    private void sendReminder() throws SQLException {
        DatabaseConnection dbcon = new DatabaseConnection();
        String resultemail = dbcon.RameinderEmail();
        emailTextField.setText(resultemail);
    }

    @FXML
    private void sendReminderButton() {
        this.sentEmailLabel.setText("Email sent!");
    }
}
