package org.openjfx.javafxmavenarchetypes.controller;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ObservableValue;
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
        Integer colonneText = Integer.parseInt(colonne.getText());
        Integer rangeeText = Integer.parseInt(rangee.getText());
        String datapickerText = String.valueOf(calendrier.getValue());

        System.out.println(datapickerText);

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

    }
}
