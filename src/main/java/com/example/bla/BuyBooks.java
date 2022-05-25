package com.example.bla;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import static javax.swing.UIManager.getString;


public class BuyBooks extends UserController implements Initializable {

    @FXML
    private TextField tf_bookName;

    @FXML
    private Button buy_button;

    @FXML
    private Button cancel_button;





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        cancel_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScene(actionEvent,"HomePageReader.fxml","Welcome!",username,"reader");
            }
        });

        buy_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.BuyBooks(tf_bookName.getText());
            }
        });



    }
}
