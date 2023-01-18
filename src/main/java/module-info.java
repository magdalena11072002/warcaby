module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.graphics;
    requires javafx.graphicsEmpty;

    exports org.example.gui;
    exports org.example.gui.components;
    exports org.example.data;
    exports org.example.logic;
}