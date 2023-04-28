module com.example.zkouskaprihlaseni3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.zkouskaprihlaseni3 to javafx.fxml;
    exports com.example.zkouskaprihlaseni3;
}