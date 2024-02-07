module com.example.laborator_1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.laborator_1 to javafx.fxml;
    exports com.example.laborator_1;
}