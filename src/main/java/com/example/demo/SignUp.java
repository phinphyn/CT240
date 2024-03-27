package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage signUp) throws IOException {
        String csslink = this.getClass().getResource("css/login.css").toExternalForm();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SignUp.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);

        scene.getStylesheets().add(csslink);
        signUp.setTitle("LOGIN!");

        signUp.setScene(scene);
        signUp.show();
    }
}

