package org.openjfx.javafxmavenarchetypes.controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class FormController implements Initializable {

    // TextField
    @FXML
    private TextField titre;
    @FXML
    private TextField auteur;
    @FXML
    private TextField presentation;
    @FXML
    private TextField parution;
    @FXML
    private TextField colonne;
    @FXML
    private TextField rangee;

    //TableColumn
    @FXML
    private TableColumn colAuteur;
    @FXML
    private TableColumn colPresentation;
    @FXML
    private TableColumn colParution;
    @FXML
    private TableColumn colColonne;
    @FXML
    private TableColumn colRangee;
    @FXML
    private TableColumn colTitre;

    //Tableview
    @FXML
    private TableView tableau;

    //Bouton

    @FXML
    private Button btnMoins;
    @FXML
    private Button btnValider;
    @FXML
    private Button btnPlus;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
