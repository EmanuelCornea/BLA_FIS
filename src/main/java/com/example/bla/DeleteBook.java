package com.example.bla;

import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.bla.UserController.username;

public class DeleteBook implements Initializable {
    @FXML
    private Button deleteButton1;
    @FXML
    private TextField deleteTextField;

    @FXML
    private Button cancel_button;

    @FXML
    private Button confirm_button;




    public void initialize(URL url, ResourceBundle resourceBundle) {

        cancel_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScene(actionEvent, "HomePageWriter.fxml", "Welcome!", username, "writer");
            }
        });

        deleteButton1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {


                DBUtils.DeleteBook(event,deleteTextField.getText());
            }


        });

        confirm_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScene(actionEvent, "HomePageWriter.fxml", "Welcome!", username, "writer");
            }
        });

    }
}