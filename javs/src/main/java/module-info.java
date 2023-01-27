module com.example.javs {
  requires javafx.controls;
  requires javafx.fxml;


  opens com.example.javs to javafx.fxml;
  exports com.example.javs;
}