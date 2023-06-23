package org.openjfx.javafxmavenarchetypes.controller;

import javafx.application.Platform;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openjfx.javafxmavenarchetypes.HelloApplication;
import org.openjfx.javafxmavenarchetypes.model.Bibliotheque;
import org.testfx.framework.junit5.ApplicationExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.openjfx.javafxmavenarchetypes.fixture.LivreFixture.leLivre;

@ExtendWith(ApplicationExtension.class)
class FormControllerTest extends FormController {

    private Bibliotheque.Livre livre;

    private FormController formController;

//    @BeforeEach
//    void setUp() {
//        livre = leLivre().build();
//    }

    @BeforeEach
    public void setup() {
        // Cr�ation de l'instance du contr�leur
        formController = new FormController();
        // El�ments FXML
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
//         Pr�paration des donn�es de test
        formController.titre.setText("Livre 1");
        formController.auteur.setText("Auteur 1");
        formController.presentation.setText("Pr�sentation 1");
        formController.colonne.setText("1");
        formController.rangee.setText("1");
        formController.calendrier.setValue(LocalDate.now());
        formController.image.setText("image.png");

        // Ex�cution de la m�thode � tester
//        Platform.runLater(() -> formController.handleNewBook(null));

        // V�rification des r�sultats
        Assertions.assertEquals("testTitre", livre.getTitre());
        Assertions.assertEquals("re",livre.getPresentation());
        Assertions.assertEquals(true, livre.getDisponibilite());
        Assertions.assertEquals(2012, livre.getParution());
        Assertions.assertEquals("auteur", livre.getAuteur());

//        assertEquals(1, formController.tableau.getItems().size());
//        assertEquals("Livre 1", formController.tableau.getItems().get(0).getTitre());

    }
}