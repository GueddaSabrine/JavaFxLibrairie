package org.openjfx.javafxmavenarchetypes.controller;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
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
import java.util.Optional;


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
    File selectedFile = null;

    boolean fileSaved ;
    @FXML
    public void initialize(){

        inittableau();
        btnMoins.setDisable(true);
        setDefaultTextField();
        fileSaved = false;
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

       selectedbook = tableau.getSelectionModel().getSelectedItem();
        if(selectedbook != null){
            titre.setText(selectedbook.getTitre());
            auteur.setText(selectedbook.getStringAuteur());
            presentation.setText(selectedbook.getPresentation());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate localDate = LocalDate.parse("01-01-" + selectedbook.getParution(), formatter);
            calendrier.setValue(localDate);
            colonne.setText(Integer.toString(selectedbook.getColonne()));
            rangee.setText(Integer.toString(selectedbook.getRangee()));
            image.setText(selectedbook.getImage());

            //boutoins moins active

            btnMoins.setDisable(false);
        }
    }
    @FXML
    public void handleNewBook(ActionEvent event){

       //Recuperer les données entrees dans le texte fields.
        Bibliotheque.Livre.Auteur auteur1 = new Bibliotheque.Livre.Auteur() ;
        String auteurTexte = auteur.getText();
        String[] PrenomNom = auteurTexte.split(" ", 2);
        if(PrenomNom.length == 2){
            auteur1.setPrenom(PrenomNom[0]);
            auteur1.setNom(PrenomNom[1]);
        }
        else{
            auteur1.setPrenom("");
            auteur1.setNom(PrenomNom[0]);
        }
        String presentationText = presentation.getText();
        String titreText = titre.getText();
        int colonneText = Integer.parseInt(colonne.getText());
        int rangeeText = Integer.parseInt(rangee.getText());
        String datapickerText = String.valueOf(calendrier.getValue().getYear());
        String imageUrl = image.getText();

        //Affichage de l'image
        Image image = new Image(imageUrl);
        imageView.setImage(image);

        if(selectedbook == null) {
            bibliotheque.addLivre(titreText, auteur1, presentationText, datapickerText, colonneText, rangeeText, imageUrl);
        }
        else {

                selectedbook.setTitre(titreText);
                selectedbook.setPresentation(presentationText);
                selectedbook.setParution(datapickerText);
                selectedbook.setRangee(rangeeText);
                selectedbook.setColonne(colonneText);
                selectedbook.setImage(imageUrl);

        }
        // Mise a jour du tableau
        ObservableList<Bibliotheque.Livre> listD = getListData();
        tableau.setItems(listD);
        tableau.refresh();



    }
    @FXML
    public void handleSaveAs(ActionEvent event) throws JAXBException {



        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Enregistrer");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Fichier XML", "*.xml"));
        selectedFile = fileChooser.showSaveDialog(tableau.getScene().getWindow());
        if (selectedFile != null){
            JAXBContext jaxbContext = JAXBContext.newInstance(Bibliotheque.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            System.out.println("ok");
            jaxbMarshaller.marshal(bibliotheque, selectedFile);
            fileSaved = true;

        }

    }

    public void handleSave(ActionEvent event) throws JAXBException {

        if (selectedFile != null){
            JAXBContext jaxbContext = JAXBContext.newInstance(Bibliotheque.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            System.out.println("ok");
            jaxbMarshaller.marshal(bibliotheque, selectedFile);
            fileSaved = true;

        }
        else{

            handleSaveAs(event);
        }
    }

    public void handleOpen(ActionEvent event) throws JAXBException, SAXException {
        File xsdf = new File("src/main/xsd/Biblio.xsd");

        /* ouverture du fichier xml */
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Ouvrir");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Fichier XML", "*.xml"));
        selectedFile = fileChooser.showOpenDialog(tableau.getScene().getWindow());
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
            fileSaved = true;

        }

    }

    public void handleOutsideCLick(){

       tableau.getSelectionModel().clearSelection();
       setDefaultTextField();
       btnMoins.setDisable(true);

    }

    public void setDefaultTextField(){

        titre.setText("Titre");
        auteur.setText("Prenom Nom");
        presentation.setText("Un court resume");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse("01-01-2000", formatter);
        calendrier.setValue(localDate);
        colonne.setText("000");
        rangee.setText("000");
        image.setText("https://birkhauser.com/product-not-found.png");
    }

    public void handlePlusBouton(){

        tableau.getSelectionModel().clearSelection();
        setDefaultTextField();
        btnMoins.setDisable(true);
        titre.requestFocus();
    }

    public void handleMoinsBouton(){

        if(selectedbook != null){

            bibliotheque.getLivre().remove(selectedbook);
            ObservableList<Bibliotheque.Livre> listD = getListData();
            tableau.setItems(listD);
            tableau.refresh();
            fileSaved = false;
        }

    }

    public void handleExit(){

        if(!fileSaved){


        }
    }

    public boolean AlerteSAuvegarde(){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            // ... user chose OK
        } else {
            // ... user chose CANCEL or closed the dialog
        }
        return true;
    }

    public void AlerteAddModifyBookDone(){

    }

    public boolean AlerteModifyBook(){
        return true;

    }

    public boolean AlerteSuppBook(){
        return true;

    }

}
