package com.example.bla;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewBooks extends UserController implements Initializable {

    @FXML
    private ListView<String> books;

    @FXML
    private Button exit;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
 DBUtils.ViewBooks(books);

        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "HomePageReader.fxml","Log In!", username,"reader");

            }
        });

    }

}
