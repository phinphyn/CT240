package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    @FXML
    private TextField textfield1;
    @FXML
    private TextField textfield2;
    @FXML
    private Button signIn;
    @FXML
    private Label testing;

    public void loginButtonOnAction(ActionEvent event) throws IOException, SQLException {

        if (!textfield1.getText().isBlank() && !textfield2.getText().isBlank()){

            validateLogin();

        }
    }






    private void validateLogin() throws SQLException {
        connector connectNow = new connector();
        try (Connection connectionDB = connectNow.getConnection()) {

            String verifyLogin = "SELECT COUNT(1) FROM taikhoan WHERE Username = ? AND Password = ?";
            try {
                PreparedStatement preparedStatement = connectionDB.prepareStatement(verifyLogin);
                preparedStatement.setString(1, textfield1.getText());
                preparedStatement.setString(2, textfield2.getText());

                ResultSet queryResult = preparedStatement.executeQuery();

                if (queryResult.next()) {
                    int count = queryResult.getInt(1);
                    if (count == 1) {


                        // Open home.fxml
                       testing.setText("success");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (connectionDB != null) {
                    connectionDB.close();
                }
            }
        }
    }
}
