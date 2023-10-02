module com.example.bankcapstone {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.bankcapstone to javafx.fxml;
    exports com.example.bankcapstone;
}