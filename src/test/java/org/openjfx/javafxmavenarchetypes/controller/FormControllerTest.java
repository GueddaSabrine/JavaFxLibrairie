package org.openjfx.javafxmavenarchetypes.controller;

import javafx.application.Platform;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openjfx.javafxmavenarchetypes.HelloApplication;
import org.testfx.framework.junit5.ApplicationExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class FormControllerTest extends HelloApplication {

    private FormController formController;

    @BeforeEach
    public void setup() {
        // Création de l'instance du contrôleur
        formController = new FormController();
        // Eléments FXML
        formController.titre = new TextField();
        formController.auteur = new TextField();
        formController.presentation = new TextField();
        formController.colonne = new TextField();
        formController.rangee = new TextField();
        formController.calendrier = new DatePicker();
        formController.image = new TextField();
        formController.imageView = new ImageView();
        formController.tableau = new TableView<>();
    }

    @Test
    void handleNewBook() {
        // Préparation des données de test
        formController.titre.setText("Livre 1");
        formController.auteur.setText("Auteur 1");
        formController.presentation.setText("Présentation 1");
        formController.colonne.setText("1");
        formController.rangee.setText("1");
        formController.calendrier.setValue(LocalDate.now());
        formController.image.setText("image.png");

        // Exécution de la méthode à tester
        Platform.runLater(() -> formController.handleNewBook(null));

        // Vérification des résultats
        assertNotNull(formController.tableau.getItems());
        assertEquals(1, formController.tableau.getItems().size());
        assertEquals("Livre 1", formController.tableau.getItems().titre());


    }
}