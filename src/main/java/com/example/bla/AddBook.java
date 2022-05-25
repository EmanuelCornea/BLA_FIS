package com.example.bla;

import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.net.URL;
import java.util.ResourceBundle;



public class AddBook extends UserController implements Initializable {
 @FXML
 private Button addButton1;

 @FXML
 private Button cancel_button;

 @FXML
 private Button confirm_button;
 @FXML
 private TextField addTextField;
 @FXML
 private TextField addTextField1;




 public void initialize(URL url, ResourceBundle resourceBundle) {

  cancel_button.setOnAction(new EventHandler<ActionEvent>() {
   @Override
   public void handle(ActionEvent actionEvent) {
    DBUtils.changeScene(actionEvent, "HomePageWriter.fxml", "Welcome!", username, "writer");
   }
  });


  addButton1.setOnAction(new EventHandler<ActionEvent>() {
   @Override
   public void handle(ActionEvent event) {
    DBUtils.AddBook(event,addTextField.getText(), Integer.valueOf(addTextField1.getText()), username);
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