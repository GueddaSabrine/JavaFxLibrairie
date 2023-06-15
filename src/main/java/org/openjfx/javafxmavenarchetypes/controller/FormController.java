package org.openjfx.javafxmavenarchetypes.controller;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.openjfx.javafxmavenarchetypes.model.Bibliotheque;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import javax.xml.bind.Unmarshaller;

import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


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
        ObservableList<Bibliotheque.Livre> listData = FXCollections.observableArrayList(bibliotheque.getLivre());
        return listData;
    }

    //mmh ...
    Bibliotheque.Livre selectedbook = null ;
    @FXML
    public void initialize(){

        inittableau();
        btnMoins.setDisable(true);
    }

    public void inittableau(){

        colTitre.setCellValueFactory(cellData -> {
            String titre = cellData.getValue().titreProperty();
            ObservableValue<String> observableTitre = Bindings.createStringBinding(() -> titre);
            return observableTitre;
        });
        colPresentation.setCellValueFactory(cellData -> {
            String presentation = cellData.getValue().getPresentation();
            ObservableValue<String> observablePresentation = Bindings.createStringBinding(() -> presentation);
            return observablePresentation;
        });
        colAuteur.setCellValueFactory(cellData -> {
            String auteur = cellData.getValue().getStringAuteur();
            ObservableValue<String> observableAuteur = Bindings.createStringBinding(() -> auteur);
            return observableAuteur;
        });
        colRangee.setCellValueFactory(cellData -> {
            int rangee = cellData.getValue().rangeeProperty();
            ObservableValue<Integer> observableRangee = Bindings.createIntegerBinding(() -> rangee).asObject();
            return observableRangee;
        });
        colColonne.setCellValueFactory(cellData -> {
            int colonne = cellData.getValue().getColonne();
            ObservableValue<Integer> observableColonne = Bindings.createIntegerBinding(() -> colonne).asObject();
            return observableColonne;
        });
        colParution.setCellValueFactory(cellData -> {
            String parution = cellData.getValue().getParution();
            ObservableValue<String> observableParution = Bindings.createStringBinding(() -> parution);
            return observableParution;
        });
        tableau.getColumns().setAll(colTitre,colAuteur,colPresentation,colParution,colColonne,colRangee);

    }

    @FXML
    public void handleSelectionTableView(MouseEvent event){

       System.out.println(event.getTarget().getClass().toString());
       selectedbook = tableau.getSelectionModel().getSelectedItem();
       titre.setText(selectedbook.getTitre());
       auteur.setText(selectedbook.getStringAuteur());
       presentation.setText(selectedbook.getPresentation());
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
       LocalDate localDate = LocalDate.parse("01-01-"+selectedbook.getParution(), formatter);
       calendrier.setValue(localDate);
       colonne.setText(Integer.toString(selectedbook.getColonne()));
       rangee.setText(Integer.toString(selectedbook.getRangee()));
       image.setText(selectedbook.getImage());

       //boutoins moins active

        btnMoins.setDisable(false);

    }
    @FXML
    public void handleNewBook(ActionEvent event){

       //Recuperer les données entrees dans le texte fields.
        Bibliotheque.Livre.Auteur auteur1 = new Bibliotheque.Livre.Auteur() ;
        String auteurTexte = auteur.getText();
        String presentationText = presentation.getText();
        String titreText = titre.getText();
        int colonneText = Integer.parseInt(colonne.getText());
        int rangeeText = Integer.parseInt(rangee.getText());
        String datapickerText = String.valueOf(calendrier.getValue());

        //Affichage de l'image
        String imageUrl = image.getText();
        System.out.println(imageUrl);
        Image image = new Image(imageUrl);
        imageView.setImage(image);

        if(selectedbook == null) {
            bibliotheque.addLivre(titreText, auteur1, presentationText, datapickerText, colonneText, rangeeText);
        }
        else {


        }
        // Mise a jour du tableau
        ObservableList<Bibliotheque.Livre> listD = getListData();
        tableau.setItems(listD);




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

    public void handleOpen(ActionEvent event) throws JAXBException, SAXException {
        File xsdf = new File("src/main/xsd/Biblio.xsd");

        /* ouverture du fichier xml */
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Ouvrir");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Fichier XML", "*.xml"));
        File selectedFile = fileChooser.showOpenDialog(tableau.getScene().getWindow());
        if (selectedFile != null){
            //unmarshalling ( xml -> java)
            JAXBContext jaxbContext = JAXBContext.newInstance(Bibliotheque.class);
            Unmarshaller jaxbunMarshaller = jaxbContext.createUnmarshaller();
            SchemaFactory schemafactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            //try
            Schema sch  = schemafactory.newSchema(xsdf);
            jaxbunMarshaller.setSchema(sch);
            bibliotheque= (Bibliotheque) jaxbunMarshaller.unmarshal(selectedFile);
            bibliotheque.print();

            /* mise a jour du tableau d'affichage */

            ObservableList<Bibliotheque.Livre> listD = getListData();
            tableau.setItems(listD);


        }

    }

}
