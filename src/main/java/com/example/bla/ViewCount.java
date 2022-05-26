package com.example.bla;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ViewCount extends UserController implements Initializable {

    @FXML
    private TableView<ModelTable1> books;

    @FXML
    private TableColumn<ModelTable1,String> bookName_col;

    @FXML
    private TableColumn<ModelTable, Integer> bookView_col;


    @FXML
    private Button exit;

    ObservableList<ModelTable1> oblist= FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            Connection con = DBConnector.getConnection();
            ResultSet resultSet = con.createStatement().executeQuery("select * from writer_table");

            while(resultSet.next()){
                oblist.add(new ModelTable1(resultSet.getString("bookName"),resultSet.getInt("bookView")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        bookName_col.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        bookView_col.setCellValueFactory(new PropertyValueFactory<>("bookPrice"));


        books.setItems(oblist);

        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "HomePageWriter.fxml","Log In!", username,"reader");

            }
        });

    }






}
