package com.example.bla;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;


    public class HomePageWriter extends UserController implements Initializable {
        @FXML
        private Button button_logout;

        @FXML
        Label label_welcome;

        @FXML
        private Button addBook_button;

        @FXML
        private Button deleteBook_button;

        @FXML
        private Button viewCount_button;







        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {

            button_logout.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    DBUtils.changeScene(event, "LogIn.fxml","Log In!", null,null);

                }
            });


        addBook_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScene(actionEvent,"AddBook.fxml","Adding Book", username,"writer");
            }
        });


            deleteBook_button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    DBUtils.changeScene(actionEvent,"DeleteBook.fxml","Deleting Book", username,"writer");
                     }
            });

            viewCount_button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    DBUtils.changeScene(actionEvent,"ViewCount.fxml","Total Viewership",username, "writer");
                }
            });

    }
    public void getInformation(String username, String actor){
            label_welcome.setText("Welcome " +actor + " " + username + "!");
        }
    }


