package Controller;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openjfx.javafxmavenarchetypes.controller.FormController;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FormControllerTest {
    private FormController formController;

    @BeforeAll
    public static void initializeJavaFX() {
        // Initialiser le toolkit JavaFX
        new JFXPanel();
        // Ex�cuter la m�thode Platform.runLater() pour ex�cuter le code JavaFX
        Platform.runLater(() -> {
        });
    }

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
    public void testHandleNewBook() {
        // Pr�paration des donn�es de test
        formController.titre.setText("Livre 1");
        formController.auteur.setText("Auteur 1");
        formController.presentation.setText("Pr�sentation 1");
        formController.colonne.setText("1");
        formController.rangee.setText("1");
        formController.calendrier.setValue(LocalDate.now());
        formController.image.setText("image.png");

        // Ex�cution de la m�thode � tester
        formController.handleNewBook(null);

        // V�rification des r�sultats
        assertNotNull(formController.tableau.getItems());
        assertEquals(1, formController.tableau.getItems().size());
        assertEquals("Livre 1", formController.tableau.getItems().get(0).getTitre());
    }
}


