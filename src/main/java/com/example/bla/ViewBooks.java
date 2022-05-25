package com.example.bla;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ViewBooks extends UserController implements Initializable {

    @FXML
    private TableView<ModelTable> books;

    @FXML
    private TableColumn<ModelTable,String> bookName_col;

    @FXML
    private TableColumn<ModelTable, Integer> bookPrice_col;

    @FXML
    private TableColumn<ModelTable,String> username_col;
    @FXML
    private Button exit;

    ObservableList<ModelTable> oblist= FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            Connection con = DBConnector.getConnection();
            ResultSet resultSet = con.createStatement().executeQuery("select * from writer_table");

            while(resultSet.next()){
                oblist.add(new ModelTable(resultSet.getString("bookName"),resultSet.getInt("bookPrice"),resultSet.getString("username")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        bookName_col.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        bookPrice_col.setCellValueFactory(new PropertyValueFactory<>("bookPrice"));
        username_col.setCellValueFactory(new PropertyValueFactory<>("Username"));

        books.setItems(oblist);

        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "HomePageReader.fxml","Log In!", username,"reader");

            }
        });

    }

}
