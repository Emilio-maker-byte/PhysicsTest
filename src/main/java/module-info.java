module com.example.physicstest {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.physicstest to javafx.fxml;
    exports com.example.physicstest;
}