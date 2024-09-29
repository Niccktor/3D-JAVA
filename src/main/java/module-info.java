module com.example.enseajava3dtp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.enseajava3dtp to javafx.fxml;
    exports com.example.enseajava3dtp;
}