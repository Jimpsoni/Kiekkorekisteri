module com.example.harkkatyo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.harkkatyo to javafx.fxml;
    exports com.example.harkkatyo;
}