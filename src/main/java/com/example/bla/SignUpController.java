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

public class SignUpController implements Initializable {

    @FXML
    private Button button_login;

    @FXML
    private Button button_signup;

    @FXML
    private ImageView fundalImg;

    @FXML
    private TextField tf_username;

    @FXML
    private TextField tf_password;


    @FXML
    private ChoiceBox<String> myChoiceBox;
    private String[] choice={"reader", "writer"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        File fundalFile = new File("src/main/java/com/example/bla/book-stack.jpeg");
        Image fundalImage = new Image(fundalFile.toURI().toString());
        fundalImg.setImage(fundalImage);
        myChoiceBox.getItems().addAll(choice);



        button_signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if(!tf_username.getText().trim().isEmpty() && !tf_password.getText().trim().isEmpty()){
                    DBUtils.signUpUser(event,tf_username.getText(),tf_password.getText(), myChoiceBox.getValue());
                } else {
                    System.out.println("Please fill in all information");
                    Alert alert= new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill in all information to sign up!");
                    alert.show();
                }
            }
        });
        button_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "LogIn.fxml", "Log in!", null,null);
            }
        });


    }


}
