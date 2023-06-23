package org.openjfx.javafxmavenarchetypes.controller;


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

/**
 * Cette classe est un contrôleur pour la vue "About.fxml" qui affiche des informations sur les personnes Aimee, Sabrine et Marion.
 */
public class AboutController {

    public Button BttDone;
    public ImageView imageViewAimee;
    public ImageView imageViewSabrine;
    public ImageView imageViewMarion;
    public Label labelAimee;
    public Label labelSabrine;
    public Label labelMarion;


    /**
     * Méthode d'initialisation appelée après que la vue "About.fxml" a été chargée.
     *
     * @throws FileNotFoundException si le fichier d'image "ref.png" n'est pas trouvé
     */
    public void initialize() throws FileNotFoundException {

        Image image = new Image(new FileInputStream("src/main/resources/res/ref.png"));
        imageViewAimee.setImage(image);
        imageViewSabrine.setImage(image);
        imageViewMarion.setImage(image);

        labelAimee.setText("Aimee");
        labelSabrine.setText("Sabrine");
        labelMarion.setText("Marion");

    }

    /**
     * Gère l'action lorsque le bouton "Done" est cliqué. Charge la vue "Biblio.fxml" et la définit comme la racine de la scène courante.
     *
     * @throws FileNotFoundException si une erreur se produit lors du chargement du fichier FXML
     */
    public void bttDonehandle() throws IOException {
        Parent pane = FXMLLoader.load(
                HelloApplication.class.getResource("Biblio.fxml"));

        BttDone.getScene().setRoot(pane);

    }
}

