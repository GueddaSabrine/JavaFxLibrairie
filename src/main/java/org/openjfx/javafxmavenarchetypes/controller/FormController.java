package org.openjfx.javafxmavenarchetypes.controller;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.openjfx.javafxmavenarchetypes.model.Bibliotheque;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import javax.xml.bind.Unmarshaller;

import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;


public class FormController  {

    // TextField
    @FXML
    public TextField titre;
    @FXML
    public TextField auteur;
    @FXML
    public TextField presentation;
    @FXML
    public TextField colonne;
    @FXML
    public TextField rangee;
    @FXML
    public DatePicker calendrier;
    @FXML
    public TextField image;
    @FXML
    public ImageView imageView;

    //TableColumn
    @FXML
    public TableColumn <Bibliotheque.Livre, String> colAuteur;
    @FXML
    public TableColumn <Bibliotheque.Livre, String> colPresentation;
    @FXML
    public TableColumn <Bibliotheque.Livre, String > colParution;
    @FXML
    public TableColumn <Bibliotheque.Livre, Integer> colColonne;
    @FXML
    public TableColumn <Bibliotheque.Livre, Integer>colRangee;
    @FXML
    public TableColumn <Bibliotheque.Livre, String> colTitre;

    //Tableview
    @FXML
    public TableView <Bibliotheque.Livre> tableau;

    //Bibliotheque

    public Bibliotheque bibliotheque = new Bibliotheque();

    //Bouton

    @FXML
    public Button btnMoins;
    @FXML
    public Button btnValider;
    @FXML
    public Button btnPlus;
    public ObservableList<Bibliotheque.Livre> getListData() {
        ObservableList<Bibliotheque.Livre> listData = FXCollections.observableArrayList();
        return listData;
    }

    @FXML
    public void handleNewBook(ActionEvent event){
        Bibliotheque.Livre.Auteur auteur1 = new Bibliotheque.Livre.Auteur() ;
        String presentationText = presentation.getText();
        String titreText = titre.getText();
        Integer colonneText = Integer.parseInt(colonne.getText());
        Integer rangeeText = Integer.parseInt(rangee.getText());
        String datapickerText = String.valueOf(calendrier.getValue());

        // Affichage des données dans le tableau nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        ObservableList<Bibliotheque.Livre> listD = getListData();

        colPresentation.setCellValueFactory(cellData -> cellData.getValue().getPresentation());
        colTitre.setCellValueFactory(cellData -> cellData.getValue().titreProperty());
        colAuteur.setCellValueFactory(cellData -> cellData.getValue().getPresentation());
        colRangee.setCellValueFactory(cellData -> {
            IntegerProperty rangee = cellData.getValue().rangeeProperty();
            ObservableValue<Integer> observableRangee = Bindings.createIntegerBinding(() -> rangee.get()).asObject();
            return observableRangee;
        });
        colColonne.setCellValueFactory(cellData -> {
            IntegerProperty colonne = cellData.getValue().getColonne();
            ObservableValue<Integer> observableColonne = Bindings.createIntegerBinding(() -> colonne.get()).asObject();
            return observableColonne;
        });
        colParution.setCellValueFactory(cellData -> cellData.getValue().getParution());

        tableau.getColumns().setAll(colTitre,colAuteur,colPresentation,colParution,colColonne,colRangee);

        Bibliotheque.Livre livrre = new Bibliotheque.Livre(titreText,auteur1,presentationText,datapickerText,colonneText,rangeeText);
        tableau.getItems().add(livrre);
        bibliotheque.addLivre(titreText,auteur1,presentationText,datapickerText,colonneText,rangeeText);

        String imageUrl = image.getText();
        System.out.println(imageUrl);
        Image image = new Image(imageUrl);
        imageView.setImage(image);
    }

    @FXML
    public void handleSaveAs(ActionEvent event) throws JAXBException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Enregistrer");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Fichier XML", "*.xml"));
        File selectedFile = fileChooser.showSaveDialog(tableau.getScene().getWindow());
        if (selectedFile != null){
            JAXBContext jaxbContext = JAXBContext.newInstance(Bibliotheque.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            System.out.println("ok");
            jaxbMarshaller.marshal(bibliotheque, selectedFile);
        }
    }

    public void handleOpen(ActionEvent event) throws JAXBException {
        /* ouverture du fichier xml */
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Ouvrir");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Fichier XML", "*.xml"));
        File selectedFile = fileChooser.showOpenDialog(tableau.getScene().getWindow());
        if (selectedFile != null){
            //unmarshalling ( xml -> java)
            JAXBContext jaxbContext = JAXBContext.newInstance(Bibliotheque.class);
            Unmarshaller jaxbunMarshaller = jaxbContext.createUnmarshaller();
            bibliotheque= (Bibliotheque) jaxbunMarshaller.unmarshal(selectedFile);

            /* mise a jour du tableau d'affichage */

            bibliotheque.getLivre().forEach(l->tableau.getItems().add(l));

        }
    }
}
