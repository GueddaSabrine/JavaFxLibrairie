module org.openjfx.javafxmavenarchetypes {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml.bind;


    opens org.openjfx.javafxmavenarchetypes to javafx.fxml;
//    exports org.openjfx.javafxmavenarchetypes;
    exports org.openjfx.javafxmavenarchetypes.controller;
    opens org.openjfx.javafxmavenarchetypes.controller to javafx.fxml;
//    exports org.openjfx.javafxmavenarchetypes.view;
//    opens org.openjfx.javafxmavenarchetypes.view to javafx.fxml;
    exports org.openjfx.javafxmavenarchetypes;
//    exports org.openjfx.javafxmavenarchetypes.vue;
//    opens org.openjfx.javafxmavenarchetypes.vue to javafx.fxml;
//    exports org.openjfx.javafxmavenarchetypes.controller;
//    opens org.openjfx.javafxmavenarchetypes.controller to javafx.fxml;
//    exports org.openjfx.javafxmavenarchetypes;
}