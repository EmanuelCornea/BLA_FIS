module com.example.bla {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.bla to javafx.fxml;
    exports com.example.bla;
}