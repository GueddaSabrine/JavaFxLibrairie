package org.openjfx.javafxmavenarchetypes;
import javafx.fxml.FXML;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.openjfx.javafxmavenarchetypes.model.Bibliotheque;
import org.openjfx.javafxmavenarchetypes.controller.FormController;

import java.io.IOException;

/**
 * Classe principale de l'application.
 * Gère le démarrage de l'application et l'affichage de la fenêtre principale.
 */
public class HelloApplication extends Application {

    /** private ObservableList<Bibliotheque.Livre> listData = FXCollections.observableArrayList();**/
    /**
     * Méthode de démarrage de l'application.
     * Crée la fenêtre principale et charge le fichier FXML pour l'affichage.
     *
     * @param stage L'étage sur lequel afficher la scène.
     * @throws Exception Si une erreur se produit lors du chargement du fichier FXML.
     */
    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 870, 500);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Méthode principale de l'application.
     * Lance l'application JavaFX.
     *
     * @param args Les arguments de ligne de commande (non utilisés dans cette méthode).
     */
    public static void main(String[] args) {
        launch();
    }
}