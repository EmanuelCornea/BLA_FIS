module com.example.bla {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.bla to javafx.fxml;
    exports com.example.bla;
}