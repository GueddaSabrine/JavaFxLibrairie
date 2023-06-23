 module org.openjfx.javafxmavenarchetypes {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml.bind;

    requires mysql.connector.java;
    requires java.sql;
    requires lombok;
//    requires javafx.embed.swing.JFXPanel;

    requires org.apache.pdfbox;
    requires boxable;
    requires java.desktop;
    requires poi.ooxml;



    opens org.openjfx.javafxmavenarchetypes to javafx.fxml, java.xml.bind;
//    exports org.openjfx.javafxmavenarchetypes;
    exports org.openjfx.javafxmavenarchetypes.controller;
    opens org.openjfx.javafxmavenarchetypes.controller to javafx.fxml, java.xml.bind;
    opens org.openjfx.javafxmavenarchetypes.model to javafx.fxml, java.xml.bind;
//    exports org.openjfx.javafxmavenarchetypes.view;
//    opens org.openjfx.javafxmavenarchetypes.view to javafx.fxml;
    exports org.openjfx.javafxmavenarchetypes;
     exports org.openjfx.javafxmavenarchetypes.model;
//    exports org.openjfx.javafxmavenarchetypes;
//    exports org.openjfx.javafxmavenarchetypes.view;
//    opens org.openjfx.javafxmavenarchetypes.view to java.xml.bind, javafx.fxml;
//    exports org.openjfx.javafxmavenarchetypes.vue;
//    opens org.openjfx.javafxmavenarchetypes.vue to javafx.fxml;
//    exports org.openjfx.javafxmavenarchetypes.controller;
//    opens org.openjfx.javafxmavenarchetypes.controller to javafx.fxml;
//    exports org.openjfx.javafxmavenarchetypes;
}