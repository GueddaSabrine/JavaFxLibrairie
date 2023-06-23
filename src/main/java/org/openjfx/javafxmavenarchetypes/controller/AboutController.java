package org.openjfx.javafxmavenarchetypes.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.openjfx.javafxmavenarchetypes.HelloApplication;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class AboutController {

    public Button BttDone;
    public ImageView imageViewAimee;
    public ImageView imageViewSabrine;
    public ImageView imageViewMarion;
    public Label labelAimee;
    public Label labelSabrine;
    public Label labelMarion;

    public void initialize() throws FileNotFoundException {

        Image image = new Image(new FileInputStream("src/main/resources/res/ref.png"));
        imageViewAimee.setImage(image);
        imageViewSabrine.setImage(image);
        imageViewMarion.setImage(image);

        labelAimee.setText("Aimee");
        labelSabrine.setText("Sabrine");
        labelMarion.setText("Marion");

    }

    public void bttDonehandle() throws IOException {
        Parent pane = FXMLLoader.load(
                HelloApplication.class.getResource("Biblio.fxml"));

        BttDone.getScene().setRoot(pane);

    }
}

