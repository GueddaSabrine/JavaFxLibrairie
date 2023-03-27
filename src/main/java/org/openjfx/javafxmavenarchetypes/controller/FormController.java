package org.openjfx.javafxmavenarchetypes.controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.openjfx.javafxmavenarchetypes.model.Bibliotheque;


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

    //TableColumn
    @FXML
    public TableColumn <Bibliotheque.Livre.Auteur, String> colAuteur;
    @FXML
    public TableColumn <Bibliotheque.Livre, String> colPresentation;
    @FXML
    public TableColumn <Bibliotheque.Livre, DatePicker> colParution;
    @FXML
    public TableColumn <Bibliotheque.Livre, Short> colColonne;
    @FXML
    public TableColumn <Bibliotheque.Livre, Short>colRangee;
    @FXML
    public TableColumn <Bibliotheque.Livre, String> colTitre;

    //Tableview
    @FXML
    public TableView <Bibliotheque.Livre> tableau;

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
        Bibliotheque.Livre.Auteur auteur1 = new Bibliotheque.Livre.Auteur(auteur.getText(),auteur.getText()) ;
        String presentationText = presentation.getText();
        String titreText = titre.getText();
        Short colonneText = Short.parseShort(colonne.getText());
        Short rangeeText = Short.parseShort(rangee.getText());
        String datapickerText = calendrier.getAccessibleText();

        System.out.println(titreText);

        // Affichage des données dans le tableau
        /**ObservableList<Bibliotheque.Livre> listD = getListData();
        colPresentation.setCellValueFactory(new PropertyValueFactory<Bibliotheque.Livre,String>("presentationText"));
        colTitre.setCellValueFactory(new PropertyValueFactory<Bibliotheque.Livre,String>("titreText"));
        colRangee.setCellValueFactory(new PropertyValueFactory<Bibliotheque.Livre,Integer>("rangeeText"));
        colColonne.setCellValueFactory(new PropertyValueFactory<Bibliotheque.Livre,Short>("colonneText"));
        colParution.setCellValueFactory(new PropertyValueFactory<Bibliotheque.Livre,DatePicker>("datapickerText"));
        tableau.setItems(listD);**/
        Bibliotheque.Livre livrre = new Bibliotheque.Livre(titreText,auteur1,presentationText,datapickerText,colonneText,rangeeText);
        tableau.getItems().add(livrre);


    }
}
