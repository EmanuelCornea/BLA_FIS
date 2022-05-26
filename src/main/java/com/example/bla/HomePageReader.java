package com.example.bla;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;






public class HomePageReader extends UserController implements Initializable {
    @FXML
    private Button button_logout;

    @FXML
    private Button button_viewBooks;

    @FXML
    private Button button_buyBooks;

    @FXML
    private Button button_AddMoney;

    @FXML
    Label label_welcome;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        button_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "LogIn.fxml","Log In!", null,null);

            }
        });
        button_viewBooks.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "ViewBooks.fxml","Log In!", username,"reader");

            }
        });

        button_buyBooks.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "BuyBooks.fxml","Log In!", username,"reader");

            }
        });

        button_AddMoney.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScene(actionEvent,"AddMoney.fxml","Adding Money",username,"writer");
            }
        });

    }
    public void getInformation(String username, String actor){
        label_welcome.setText("Welcome " +actor + " " + username + "!");
    }
}
