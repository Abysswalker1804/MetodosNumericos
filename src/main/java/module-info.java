module org.example.metodosnumericos1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.metodosnumericos1 to javafx.fxml;
    exports org.example.metodosnumericos1;
}