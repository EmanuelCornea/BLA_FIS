package com.example.bla;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import java.sql.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class DBUtils {
    public static void changeScene(ActionEvent event, String fxmlFile, String title, String username, String actor) {
        Parent root = null;

        if (username != null && actor != null) {
            try {
                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
                root = loader.load();


                UserController userController = loader.getController();
                userController.getInformation(username, actor);


            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                root = FXMLLoader.load(DBUtils.class.getResource(fxmlFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);

        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public static void signUpUser(ActionEvent event, String username, String password, String actor) {
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bla", "root", "root");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM users WHERE username = ? ");
            psCheckUserExists.setString(1, username);
            resultSet = psCheckUserExists.executeQuery();

            if (resultSet.isBeforeFirst()) {
                System.out.println("User already exists!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You cannot set this username");
                alert.show();
            } else {
                psInsert = connection.prepareStatement("INSERT INTO users (username, password, actor) VALUES (?, ?, ?)");
                psInsert.setString(1, username);
                psInsert.setString(2, password);
                psInsert.setString(3, actor);


                psInsert.executeUpdate();
                if (Objects.equals(actor, "reader")) {
                    changeScene(event, "HomePageReader.fxml", "Welcome!", username, "reader");
                } else if (Objects.equals(actor, "writer")) {
                    changeScene(event, "HomePageWriter.fxml", "Welcome!", username, "writer");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psCheckUserExists != null) {
                try {
                    psCheckUserExists.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psInsert != null) {
                try {

                    psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static void logInUser(ActionEvent event, String username, String password, String actor) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bla", "root", "root");
            preparedStatement = connection.prepareStatement("SELECT *  FROM users WHERE username = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("User not found");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are incorrect");
                alert.show();

            } else {
                while (resultSet.next()) {
                    String retrievedPassword = resultSet.getString("password");
                    String retrievedActor = resultSet.getString("actor");
                    if (retrievedPassword.equals(password)) {
                        if (retrievedActor.equals("reader")) {
                            changeScene(event, "HomePageReader.fxml", "Welcome! ", username, "reader");
                        } else if (retrievedActor.equals("writer")) {

                            changeScene(event, "HomePageWriter.fxml", "Welcome!", username, "writer");

                        }
                    } else {
                        System.out.println("Passwords did not match!");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Provided credentials are incorrect");
                        alert.show();

                    }
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    public static void AddBook(ActionEvent event, String bookName, Integer bookPrice, String username) {

        Connection connection1 = null;
        PreparedStatement psInsert = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement psCheckUserExists1 = null;
        ResultSet resultSet = null;
        try {
            connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/bla", "root", "root");
            psCheckUserExists1 = connection1.prepareStatement("SELECT *  FROM writer_table WHERE bookName = ? ");
            psCheckUserExists1.setString(1, bookName);

            resultSet = psCheckUserExists1.executeQuery();
            if (resultSet.isBeforeFirst()) {
                System.out.println("Book already exists!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You cannot set this bookName");
                alert.show();
            } else {
                psInsert = connection1.prepareStatement("INSERT INTO writer_table (bookName,bookPrice,username) VALUES (?, ?, ?)");
                psInsert.setString(1, bookName);
                psInsert.setInt(2, bookPrice);
                psInsert.setString(3, username);
                psInsert.executeUpdate();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Book added!");
                alert.show();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection1 != null) {
                try {
                    connection1.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    public static void DeleteBook(ActionEvent event, String bookName) {

        Connection connection1 = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement psCheckUserExists1 = null;
        ResultSet resultSet = null;
        PreparedStatement psDeletedBook = null;


        try {
            connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/bla", "root", "root");
            psCheckUserExists1 = connection1.prepareStatement("SELECT *  FROM writer_table WHERE bookName = ? ");
            psCheckUserExists1.setString(1, bookName);

            resultSet = psCheckUserExists1.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                System.out.println("Book does not exists!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Book deleting suspended due to error");
                alert.show();
            } else {
                psDeletedBook = connection1.prepareStatement("Delete FROM writer_table WHERE bookName= ?");
                psDeletedBook.setString(1, bookName);
                psDeletedBook.executeUpdate();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Book deleted!");
                alert.show();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection1 != null) {
                try {
                    connection1.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

    }


    public static void BuyBooks(String bookName){
        Connection connection=null;
    Connection connection1 = null;
    Connection connection2=null;
    Connection connection3=null;
    PreparedStatement preparedStatement = null;
    PreparedStatement psCheckUserExists1 = null;
    PreparedStatement psView=null;
    ResultSet resultSet2=null;
    ResultSet resultSet = null;
    ResultSet resultSet1=null;
    PreparedStatement psCheckUserExists2=null;
    PreparedStatement psBoughtBook = null;
    int totalMoney = 0;



        try {

            connection2=DriverManager.getConnection("jdbc:mysql://localhost:3306/bla", "root", "root");
            psCheckUserExists2=connection2.prepareStatement("select money from reader_table ");
            resultSet1=psCheckUserExists2.executeQuery();
            if(resultSet1.next()){
                 totalMoney=resultSet1.getInt("money");
            }
            
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bla", "root", "root");
            psCheckUserExists1 = connection.prepareStatement("SELECT *  FROM writer_table WHERE bookName = ? ");
            psCheckUserExists1.setString(1, bookName);
        resultSet = psCheckUserExists1.executeQuery();
        if (!resultSet.isBeforeFirst()) {
            System.out.println("Book does not exists!");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Book buying suspended due to error");
            alert.show();
        } else {




            while (resultSet.next()) {
                String retrievedAuthor = resultSet.getString("username");
                int retrievedPrice = resultSet.getInt("bookPrice");
                int rView=resultSet.getInt("bookView");


                if(totalMoney-retrievedPrice>=0){

                    connection1= DriverManager.getConnection("jdbc:mysql://localhost:3306/bla", "root", "root");
                psBoughtBook = connection1.prepareStatement("Insert into reader_table (bookName, author, money) values (?,?,?)");
                psBoughtBook.setString(1, bookName);
                psBoughtBook.setString(2, retrievedAuthor);
                psBoughtBook.setInt(3, totalMoney-retrievedPrice);
                psBoughtBook.executeUpdate();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Book bought!");
                alert.show();


                    connection3= DriverManager.getConnection("jdbc:mysql://localhost:3306/bla", "root", "root");
                    psView = connection3.prepareStatement("UPDATE writer_table set  bookView=? where bookName=?");

                    psView.setInt(1, rView+1);
                    psView.setString(2, bookName);
                    psView.executeUpdate();

            }else{
                    System.out.println("Insufficient money!");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Book buying suspended due to error");
                    alert.show();
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection1 != null) {
            try {
                connection1.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

}

public static void AddMoney(int money){
        Connection connection1 = null;
    PreparedStatement psInsert = null;
    PreparedStatement preparedStatement = null;
    PreparedStatement psCheckUserExists1 = null;
    ResultSet resultSet = null;
    try {
        connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/bla", "root", "root");

            psInsert = connection1.prepareStatement("INSERT INTO reader_table (money)  VALUES ( ?)");
            psInsert.setInt(1, money);
            psInsert.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Money added!");
            alert.show();


    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection1 != null) {
            try {
                connection1.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

}




}
