module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.graphics;
    requires javafx.graphicsEmpty;

    exports org.example.GUI;
    exports org.example.GUI.components;
    exports org.example.Data;
    exports org.example.Logic;
}