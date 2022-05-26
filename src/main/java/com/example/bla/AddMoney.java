package com.example.bla;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;


public class AddMoney extends UserController implements Initializable {
    @FXML
    private Button confirm_button;

    @FXML
    private  Button add_button;

    @FXML
    private TextField tf_amount;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        confirm_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScene(actionEvent,"HomePageReader.fxml","Welcome!",username,"reader");
            }
        });

        add_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.AddMoney(Integer.parseInt(tf_amount.getText().toString()));
            }
        });

    }




}
