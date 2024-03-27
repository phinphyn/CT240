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

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class LoginController {
    private String host = "localhost";
    @FXML
    private TextField textfield1;
    @FXML
    private TextField textfield2;
    @FXML
    private Button signIn;
    @FXML
    private Button signUp;
    @FXML
    private Label testing;

    @FXML
    public void loginButtonOnAction(ActionEvent event) throws IOException, SQLException {

        String user_name = textfield1.getText();
        String password = textfield2.getText();


        if (user_name.isEmpty()) {
            return;
        }

        System.out.println(user_name + ", " + password);

        try {
            int port = 2089;
            Socket s = new Socket(host, port);
            DataInputStream dataIn = new DataInputStream(s.getInputStream());
            DataOutputStream dataOut = new DataOutputStream(s.getOutputStream());
            dataOut.writeUTF(user_name);

            String loginStatus = new DataInputStream(s.getInputStream()).readUTF();
//            if (loginStatus.equals("You are already logged in")) {
//
//            }
            System.out.println(loginStatus);

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!user_name.isEmpty() && !password.isEmpty()) {
//            validateLogin();
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
