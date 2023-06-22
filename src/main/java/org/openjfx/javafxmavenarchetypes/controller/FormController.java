package org.openjfx.javafxmavenarchetypes.controller;
import be.quodlibet.boxable.Cell;
import be.quodlibet.boxable.utils.ImageUtils;
import javafx.application.Platform;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.openjfx.javafxmavenarchetypes.model.Bibliotheque;
import org.openjfx.javafxmavenarchetypes.model.XMLhandler;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import be.quodlibet.boxable.*;
import java.util.ResourceBundle;

import static org.apache.pdfbox.pdmodel.font.PDType1Font.*;
//import static org.apache.pdfbox.pdmodel.font.Standard14Fonts.FontName.COURIER;
//import static org.apache.pdfbox.pdmodel.font.Standard14Fonts.FontName.HELVETICA_BOLD;

/**
 * @Version 1.5
 * @author Aimée Marion Sabrine
 */

/**
 * Classe principale avec une implementation.
 * @author Aimée Marion Sabrine
 */

/**
 *
 * @param <DatabaseConnection>
 */
public class FormController<DatabaseConnection> {

    /*
    * Déclarations des attributs de la classe FormController.
    * */
    private Connection connectDB;
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

    @FXML
    public CheckBox checkbox;

    /**
     * Déclarations des différentes colonnes du tableau ainsi que le tableau lui-même.
     */
    @FXML
    public TableColumn <Bibliotheque.Livre, String> colAuteur;
    @FXML
    public TableColumn <Bibliotheque.Livre, String> colPresentation;
    @FXML
    public TableColumn <Bibliotheque.Livre, Integer > colParution;
    @FXML
    public TableColumn <Bibliotheque.Livre, Integer> colColonne;
    @FXML
    public TableColumn <Bibliotheque.Livre, Integer>colRangee;
    @FXML
    public TableColumn <Bibliotheque.Livre, String> colTitre;


    //Tableview
    @FXML
    public TableView <Bibliotheque.Livre> tableau;

    /**
     * Création d'une bibliothèque.
     */
    public Bibliotheque bibliotheque = new Bibliotheque();

    public XMLhandler xmlfile = new XMLhandler();

    /**
     * Déclaration des différents boutons de l'application.
     */
    @FXML
    public Button btnMoins;
    @FXML
    public Button btnValider;
    @FXML
    public Button btnPlus;

    /**
     * Déclaration des différents messages d'erreurs pour la vérification des champs..
     */
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
    //Livre from the current global Bibliotheque object bibliotheque 's Livre list that's currently selected in the table view
    Bibliotheque.Livre selectedbook = null ;
    //Working Xml file (the one currently openened
    File selectedFile = null;

    /**
     *
     */
    boolean fileSaved ;

    /**
     * Cette méthode est appelée lors de l'initialisation de l'executable.
     * Elle configure l'état initial des éléments
     * graphiques tels que les boutons, les champs de texte, le calendrier.
     */
    @FXML
    public void initialize(){
        inittableau();
        btnMoins.setDisable(true);
        setDefaultTextField();
        fileSaved = true;
        calendrier.getEditor().setDisable(true);
        hideErrorMsg();
    }

    /**
     * Hide all the error message below the textfields in the form
     * Set all Visible  attribute of textviewers to false
     */
    public void hideErrorMsg(){
        msgErrorTitre.setVisible(false);
        msgErrorAuteur.setVisible(false);
        msgErrorColonne.setVisible(false);
        msgErrorRangee.setVisible(false);
        msgErrorUrl.setVisible(false);
    }

    /**
     * Bind cell of table view to getter in order to retrieve attribute from Bibliotheque class
     */
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
            int parution = cellData.getValue().getParution();
            ObservableValue<Integer> observableParution = Bindings.createIntegerBinding(() -> parution).asObject();
            return observableParution;
        });
        tableau.getColumns().setAll(colTitre,colAuteur,colPresentation,colParution,colColonne,colRangee);
        tableau.setItems(bibliotheque.getLivre());

    }

    @FXML
    /**
     * Set selectedbook to the Livre object binded to the row selected
     * Put attribute of the Livre object from the selected row into the textfield of the form
     * Unable btnMoins (minus button)
     */
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
            checkbox.setSelected(selectedbook.getDisponibilite());

            //boutoins moins active
            btnMoins.setDisable(false);
        }
    }

    /**
     * Méthode qui permet de récupérer la data entrées par l'utilisateur
     * @param event
     */
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
            int datapickerText = calendrier.getValue().getYear();
            String imageUrl = image.getText();
            boolean disponibilite = checkbox.isSelected();

            //Affichage de l'image
            Image image = new Image(imageUrl);
            imageView.setImage(image);
            if (selectedbook == null) {
                bibliotheque.addLivre(titreText, auteur1, presentationText, datapickerText, colonneText, rangeeText, imageUrl, disponibilite);
                // Mise a jour du tableau
                tableau.refresh();
                fileSaved = false;
                //AlerteAddModifyBookDone();

            } else {

                selectedbook.setTitre(titreText);
                selectedbook.setPresentation(presentationText);
                selectedbook.setParution(datapickerText);
                selectedbook.setRangee(rangeeText);
                selectedbook.setColonne(colonneText);
                selectedbook.setImage(imageUrl);

                // Mise a jour du tableau
                if (Alerte(Alert.AlertType.INFORMATION,
                        "Modification Livre",
                        "modifier  + selectedbook.getTitre()",
                        "Les modifications apportées au livre " + selectedbook.getTitre() + "vont etre validée. Cliquez sur" +
                                " OK pour continuer")) {

                    fileSaved = false;
                    Alerte(Alert.AlertType.INFORMATION,
                            "Done",
                            null,
                            "Bibliotheque mise a jour"
                            );
                }
            }
        }
    }

    /**
     * Cette fonction contient des regex pour le respect de l'écriture de chaque champ au moment de l'insertion des données
     * @return
     */
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
        if(colonne.getText().matches("[0-9]*") && Integer.parseInt(colonne.getText()) <= 12 && Integer.parseInt(colonne.getText()) >= 1){
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


    /**
     * Gère l'action de sauvegarde sous un nouveau fichier.
     *
     * @param event L'événement déclencheur de l'action.
     * @throws JAXBException Si une exception se produit lors de la sauvegarde du fichier en utilisant JAXB.
     */
    @FXML
    public void handleSaveAs(ActionEvent event) throws JAXBException {
        xmlfile.SaveAs(tableau.getScene().getWindow(), bibliotheque);
    }

    /**
     * Gère l'action de sauvegarde du fichier.
     *
     * @param event L'événement déclencheur de l'action.
     * @throws JAXBException Si une exception se produit lors de la sauvegarde du fichier en utilisant JAXB.
     */
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

    /**
     * Gère l'événement de l'ouverture d'un fichier.
     *
     * @param event L'événement de l'ouverture.
     * @throws JAXBException Si une exception JAXB se produit lors de la manipulation des fichiers XML.
     * @throws SAXException Si une exception SAX se produit lors de la validation du fichier XML par le schéma.
     */
    public void handleOpen(ActionEvent event) throws JAXBException, SAXException {
        File xsdf = new File("src/main/xsd/Biblio.xsd");

        /* ouverture du fichier xml */
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Ouvrir");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Fichier XML", "*.xml"));
        File openFile = fileChooser.showOpenDialog(tableau.getScene().getWindow());
        if (openFile != null){
            //unmarshalling ( xml -> java)
            JAXBContext jaxbContext = JAXBContext.newInstance(Bibliotheque.class);
            Unmarshaller jaxbunMarshaller = jaxbContext.createUnmarshaller();
            SchemaFactory schemafactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            //try
            Schema sch  = schemafactory.newSchema(xsdf);
            jaxbunMarshaller.setSchema(sch);
            bibliotheque= (Bibliotheque) jaxbunMarshaller.unmarshal(openFile);
            //bibliotheque.print();

            /* mise a jour du tableau d'affichage */


            fileSaved = true;
            selectedFile = openFile ;


        }

    }

    /**
     * Gère le clic à l'extérieur d'une zone spécifique.
     */
    public void handleOutsideCLick(){

       tableau.getSelectionModel().clearSelection();
       setDefaultTextField();
       selectedbook = null;
       btnMoins.setDisable(true);
       hideErrorMsg();

    }

    /**
     * Rétablit les valeurs par défaut des champs de texte.
     */
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
        checkbox.setSelected(true);
    }

    /**
     * Gère le clic sur le bouton Plus.
     */
    public void handlePlusBouton(){

        tableau.getSelectionModel().clearSelection();
        setDefaultTextField();
        btnMoins.setDisable(true);
        selectedbook = null;
        titre.requestFocus();
    }

    /**
     * Gère le clic sur le bouton Moins.
     */
    public void handleMoinsBouton(){

        if(selectedbook != null){
            if (Alerte(Alert.AlertType.CONFIRMATION,
                    "Suppression Livre",
                    "Supprimer"+selectedbook.getTitre(),
                    "Voulez vous supprimer " + selectedbook.getTitre() + "  de la liste? Cliquez sur" +
                            " OK pour supprimer")
                         ) {
                bibliotheque.getLivre().remove(selectedbook);
                fileSaved = false;
                handleOutsideCLick();
            }
        }

    }

    /**
     * Gère l'événement de sortie de l'application.
     *
     * @throws JAXBException Si une exception JAXB se produit lors de la manipulation des fichiers XML.
     */
    public void handleExit() throws JAXBException {
        String name = "no file";
        if(!fileSaved){
            if(selectedFile != null)name = selectedFile.getName();
            if(Alerte(Alert.AlertType.CONFIRMATION ,
                    "Exit",
                    "You're going to exist without saving",
                    "Toute les modifications apportées au fichier " + name + "seront perdu. Cliquez sur" +
                            " OK pour sauvegarder votre fichier")){
                handleSave(new ActionEvent());
            }

        }
        Platform.exit();
    }


    /**
     * Affiche une boîte de dialogue avec les options OK et Annuler.
     *
     * @param myType Le type d'alerte.
     * @param title Le titre de l'alerte.
     * @param headerText Le texte d'en-tête de l'alerte.
     * @param content Le contenu de l'alerte.
     * @return {@code true} si l'utilisateur a cliqué sur OK, {@code false} sinon.
     */
    public boolean Alerte(Alert.AlertType myType, String title , String headerText, String content){

        Alert alert = new Alert(myType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(content );

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            return true;
        } else {
            return false ;
        }

    }

    /**
     * Teste la génération d'un fichier PDF.
     *
     * @throws IOException Si une exception d'entrée/sortie se produit lors de la manipulation des fichiers.
     */
    @FXML
    public void testpdf() throws IOException {

       // Standard14Fonts.FontName font_name_3v= Standard14Fonts.getMappedFontName("HELVETICA_BOLD");
        //PDFont pdfFont=  new PDType1Font(font_name_3v.HELVETICA_BOLD);

        PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        PDPage page2 = new PDPage(PDRectangle.A4);
        // rect can be used to get the page width and height
        document.addPage(page);


        //ajout element
        float margin = 50;
        // starting y position is whole page height subtracted by top and bottom margin
        float yStartNewPage = page.getMediaBox().getHeight() - (2 * margin);
        float yStartNewPage2 = page2.getMediaBox().getHeight() - (2 * margin);

        // we want table across whole page width (subtracted by left and right margin ofcourse)
        float tableWidth = page.getMediaBox().getWidth() - (2 * margin);

        boolean drawContent = true;
        float yStart = yStartNewPage;
        float yStart2 = yStartNewPage2;
        float bottomMargin = 70;
        // y position is your coordinate of top left corner of the table
        float yPosition = 550;

        //Premier tableau
        BaseTable table = new BaseTable(yStart, yStartNewPage, bottomMargin, tableWidth, margin, document, page, true,
                drawContent);

        Row<PDPage> headerRow = table.createRow(15f);
        Cell<PDPage> cell = headerRow.createCell(100, "Bibliotheque collection");
        cell.setFillColor(Color.BLACK);
        cell.setTextColor(Color.WHITE);
        table.addHeaderRow(headerRow);

        // deuxieme tableau
        BaseTable tableind = new BaseTable(yStart, yStartNewPage, bottomMargin, tableWidth, margin, document, page2, true,
                drawContent);

        Row<PDPage> headerRowind = tableind.createRow(15f);
        Cell<PDPage> cellind = headerRowind.createCell(100, "Livrre non disponible");
        cellind.setFillColor(Color.GRAY);
        cellind.setTextColor(Color.WHITE);
        tableind.addHeaderRow(headerRowind);

        //creation des lignes
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
            if(!fact.getDisponibilite()){
                Row<PDPage> rowind = tableind.createRow(10f);
                cellind = rowind.createImageCell((100 / 9f), ImageUtils.readImage(imagefile));
                cellind = rowind.createCell((35 / 3.0f), fact.getTitre() );
                cellind.setFont(HELVETICA);
                cellind = rowind.createCell((35 / 3.0f), fact.getStringAuteur() );
                cellind.setFont(HELVETICA);
                cellind = rowind.createCell((100 / 3.0f) *2, fact.getPresentation() );
                cellind.setFont(HELVETICA);
            }

            imagefile.delete();
        }

        table.draw();
        document.addPage(page2);
        tableind.draw();

        //sauvegarder
        document.save("./test.pdf");
        document.close();

    }

    //public ObservableList<Bibliotheque.Livre> data = FXCollections.observableArrayList();
    /**
     * Méthode qui permet de détecter les changements.
     * Elle permet de notifier les changements survenant dans la liste.
     * Lorsque des éléments sont ajoutés, supprimés ou modifiés dans la liste,
     * les observateurs enregistrés sont notifiés des modifications.
     * Cela facilite la mise à jour de l'interface utilisateur en fonction des changements de données.
     * Retourne une collection.
     * Collection observable contenant les éléments de la liste.
     *
     * @return data
     */
    public ObservableList<Bibliotheque.Livre> data = FXCollections.observableArrayList();

    /**
     * Gère l'événement de connexion.
     *
     * @param event L'événement de connexion.
     */
    public void handleConnexion(ActionEvent event) {
        tableau.getItems().clear();
        DatabaseConnexion connectNow = new DatabaseConnexion();
        connectDB = connectNow.getConnection();
        String selectAllBook = "SELECT * FROM livre";
        try {
            ResultSet queryOutput = connectNow.selectBook(selectAllBook);
            while (queryOutput.next()){
                Bibliotheque.Livre.Auteur auteur9 = new Bibliotheque.Livre.Auteur();
                auteur9.setNom(queryOutput.getString("nom"));
                auteur9.setPrenom(queryOutput.getString("prenom"));
                bibliotheque.addLivre(queryOutput.getString("titre"),
                        auteur9,
                        queryOutput.getString("presentation"),
                        queryOutput.getInt("parution"),
                        queryOutput.getInt("colonne"),
                        queryOutput.getInt("rangee"),
                        queryOutput.getString("image"),true);
                        fileSaved = false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
