package org.openjfx.javafxmavenarchetypes.controller;
import be.quodlibet.boxable.Cell;
import be.quodlibet.boxable.utils.ImageUtils;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.print.*;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;
import javafx.stage.FileChooser;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
//import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
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
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import be.quodlibet.boxable.*;
import be.quodlibet.boxable.line.LineStyle;

import static org.apache.pdfbox.pdmodel.font.PDType1Font.*;
//import static org.apache.pdfbox.pdmodel.font.Standard14Fonts.FontName.COURIER;
//import static org.apache.pdfbox.pdmodel.font.Standard14Fonts.FontName.HELVETICA_BOLD;


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

    //error text

    @FXML
    public Text msgErrorTitre;
    @FXML
    public Text msgErrorAuteur ;
    @FXML
    public Text msgErrorColonne ;
    @FXML
    public Text msgErrorRangee ;
    @FXML
    public Text msgErrorUrl ;

    //mmh ...
    Bibliotheque.Livre selectedbook = null ;
    File selectedFile = null;

    boolean fileSaved ;
    @FXML
    public void initialize(){

        inittableau();
        btnMoins.setDisable(true);
        setDefaultTextField();
        fileSaved = true;
        calendrier.getEditor().setDisable(true);
        hideErrorMsg();
    }

    public void hideErrorMsg(){

        msgErrorTitre.setVisible(false);
        msgErrorAuteur.setVisible(false);
        msgErrorColonne.setVisible(false);
        msgErrorRangee.setVisible(false);
        msgErrorUrl.setVisible(false);
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


        if(checkData()) {

            hideErrorMsg();
            //Recuperer les données entrees dans le texte fields.
            Bibliotheque.Livre.Auteur auteur1 = new Bibliotheque.Livre.Auteur() ;
            String auteurTexte = auteur.getText();
            String[] PrenomNom = auteurTexte.split(" ", 2);
            auteur1.setPrenom(PrenomNom[0].substring(0,1).toUpperCase() + PrenomNom[0].substring(1));
            auteur1.setNom(PrenomNom[1].substring(0,1).toUpperCase() + PrenomNom[1].substring(1));
            String presentationText = presentation.getText();
            String titreText = titre.getText();
            int colonneText = Integer.parseInt(colonne.getText());
            int rangeeText = Integer.parseInt(rangee.getText());
            String datapickerText = String.valueOf(calendrier.getValue().getYear());
            String imageUrl = image.getText();

            //Affichage de l'image
            Image image = new Image(imageUrl);
            imageView.setImage(image);
            if (selectedbook == null) {
                bibliotheque.addLivre(titreText, auteur1, presentationText, datapickerText, colonneText, rangeeText, imageUrl);
                // Mise a jour du tableau

                ObservableList<Bibliotheque.Livre> listD = getListData();
                tableau.setItems(listD);
                tableau.refresh();
                fileSaved = false;
                AlerteAddModifyBookDone();

            } else {

                selectedbook.setTitre(titreText);
                selectedbook.setPresentation(presentationText);
                selectedbook.setParution(datapickerText);
                selectedbook.setRangee(rangeeText);
                selectedbook.setColonne(colonneText);
                selectedbook.setImage(imageUrl);


                // Mise a jour du tableau
                if (AlerteModifyBook()) {
                    ObservableList<Bibliotheque.Livre> listD = getListData();
                    tableau.setItems(listD);
                    tableau.refresh();
                    fileSaved = false;
                    AlerteAddModifyBookDone();
                }
            }
        }



    }

    public boolean checkData(){
        boolean ti , aut, col , rg , img;
        if(titre.getText().matches("[A-Za-z0-9 _]*")){
            ti = true;
        }
        else{

            ti = false;
            msgErrorTitre.setVisible(true);

        }
        if(auteur.getText().matches("[A-Za-z]*\s[A-Za-z]*")){
            aut = true;
        }
        else{

            aut = false;
            msgErrorAuteur.setVisible(true);

        }
        if(colonne.getText().matches("[0-9]*") && Integer.parseInt(rangee.getText()) <= 12 && Integer.parseInt(rangee.getText()) >= 1){
            col = true;
        }
        else{

            col = false;
            msgErrorColonne.setVisible(true);

        }
        if(rangee.getText().matches("[1-7]")){
            rg = true;
        }
        else{

            rg = false;
            msgErrorRangee.setVisible(true);


        }
        img = true;
        try{
            new Image(image.getText());

        }
        catch(Exception e){

           img = false ;
            System.out.println( e.getMessage());
            msgErrorUrl.setVisible(true);


        }

        return ti && aut && col && rg && img ;
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
            fileSaved = true;

        }

    }

    public void handleOutsideCLick(){

       tableau.getSelectionModel().clearSelection();
       setDefaultTextField();
       selectedbook = null;
       btnMoins.setDisable(true);
       hideErrorMsg();

    }

    public void setDefaultTextField(){

        titre.setText("Titre");
        auteur.setText("Prenom Nom");
        presentation.setText("Un court resume");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse("01-01-2000", formatter);
        calendrier.setValue(localDate);
        colonne.setText("1");
        rangee.setText("1");
        image.setText("https://birkhauser.com/product-not-found.png");
    }

    public void handlePlusBouton(){

        tableau.getSelectionModel().clearSelection();
        setDefaultTextField();
        btnMoins.setDisable(true);
        selectedbook = null;
        titre.requestFocus();
    }

    public void handleMoinsBouton(){

        if(selectedbook != null){
            if (AlerteSuppBook()) {
                bibliotheque.getLivre().remove(selectedbook);
                ObservableList<Bibliotheque.Livre> listD = getListData();
                tableau.setItems(listD);
                tableau.refresh();
                fileSaved = false;
            }
        }

    }

    public void handleExit() throws JAXBException {

        if(!fileSaved){
            if(AlerteSauvegarde()){
                handleSave(new ActionEvent());
            }

        }
        Platform.exit();
    }

    public boolean AlerteSauvegarde(){
        String name = "no file";
        if(selectedFile != null){name = selectedFile.getName(); }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("You're going to exist without saving");
        alert.setContentText("Toute les modifications apportées au fichier " + name + "seront perdu. Cliquez sur" +
                " OK pour sauvegarder votre fichier" );

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            return true;
        } else {
            return false ;
        }
    }

    public void AlerteAddModifyBookDone(){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Done");
        alert.setHeaderText(null);
        alert.setContentText("Bibliotheque mise a jour");

        alert.showAndWait();
    }

    public boolean AlerteModifyBook(){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Modification Livre");
        alert.setHeaderText("Voulez vous modifier " + selectedbook.getTitre());
        alert.setContentText("Les modifications apportées au livre " + selectedbook.getTitre() + "vont etre validée. Cliquez sur" +
                " OK pour continuer" );

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            return true;
        } else {
            return false ;
        }

    }

    public boolean AlerteSuppBook(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Suppression Livre");
        alert.setHeaderText("Voulez vous supprimer " + selectedbook.getTitre());
        alert.setContentText("Voulez vous supprimer " + selectedbook.getTitre() + "  de la liste? Cliquez sur" +
                " OK pour supprimer" );

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            return true;
        } else {
            return false ;
        }

    }
    @FXML
    public void testpdf() throws IOException {

       // Standard14Fonts.FontName font_name_3v= Standard14Fonts.getMappedFontName("HELVETICA_BOLD");
        //PDFont pdfFont=  new PDType1Font(font_name_3v.HELVETICA_BOLD);

        PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        // PDRectangle.LETTER and others are also possible
        PDRectangle rect = page.getMediaBox();
        // rect can be used to get the page width and height
        document.addPage(page);

        //ajout element
        float margin = 50;
        // starting y position is whole page height subtracted by top and bottom margin
        float yStartNewPage = page.getMediaBox().getHeight() - (2 * margin);
        // we want table across whole page width (subtracted by left and right margin ofcourse)
        float tableWidth = page.getMediaBox().getWidth() - (2 * margin);

        boolean drawContent = true;
        float yStart = yStartNewPage;
        float bottomMargin = 70;
        // y position is your coordinate of top left corner of the table
        float yPosition = 550;
        //PDPageContentStream cos = new PDPageContentStream(document, page);
        BaseTable table = new BaseTable(yStart, yStartNewPage, bottomMargin, tableWidth, margin, document, page, true,
                drawContent);
//Create Header row
        Row<PDPage> headerRow = table.createRow(15f);
        Cell<PDPage> cell = headerRow.createCell(100, "Bibliotheque collection");
        cell.setFillColor(Color.BLACK);
        cell.setTextColor(Color.WHITE);

        table.addHeaderRow(headerRow);
        List<Bibliotheque.Livre> facts = bibliotheque.getLivre();
        for (Bibliotheque.Livre fact : facts) {
            Row<PDPage> row = table.createRow(10f);
            InputStream in = new URL(fact.getImage()).openStream();
            Files.copy(in, Path.of("./imagetemp"));
            File imagefile = new File("./imagetemp");
            cell = row.createImageCell((100 / 9f), ImageUtils.readImage(imagefile));
            cell = row.createCell((35 / 3.0f), fact.getTitre() );
            cell.setFont(HELVETICA);
            cell = row.createCell((35 / 3.0f), fact.getStringAuteur() );
            cell.setFont(HELVETICA);
            cell = row.createCell((100 / 3.0f) *2, fact.getPresentation() );
            cell.setFont(HELVETICA);

            imagefile.delete();
        }
        table.draw();



        //sauvegarder
        document.save("./test.pdf");
        document.close();

    }

}
