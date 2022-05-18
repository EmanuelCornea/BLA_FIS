package com.example.bla;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class LogIn implements Initializable {
    @FXML
    private Button button_login;

    @FXML
    private Button button_signup;

    @FXML
    private TextField tf_username;

    @FXML
    private TextField tf_password;

    @FXML
    private ImageView fundalImg;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File fundalFile = new File("src/main/java/com/example/bla/book-stack.jpeg");
        Image fundalImage = new Image(fundalFile.toURI().toString());
        fundalImg.setImage(fundalImage);

        button_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {


                    DBUtils.logInUser(event,tf_username.getText(),tf_password.getText());
                }


        });
        button_signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "sign-up.fxml", "Sign Up!", null);
            }
        });

    }
}
