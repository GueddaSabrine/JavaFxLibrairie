package org.openjfx.javafxmavenarchetypes.controller;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;
import org.openjfx.javafxmavenarchetypes.HelloApplication;
import org.openjfx.javafxmavenarchetypes.model.User;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Optional;

/**
 * Contrôleur responsable de la gestion de la connexion et de la création d'utilisateurs.
 */
public class LoginController {

    /**
     * Champ de texte pour le nom.
     */
    public TextField textFieldNom;
    public TextField textFieldLogin;
    public TextField textFieldPassword;
    public TextField textFieldPrenom;
    public CheckBox checkboxRole;
    /**
     * Étiquette pour le nom.
     */
    public Label labelNom;
    public Label labelRole;
    public Label labelPassword;
    public Label labelLogin;
    public Label labelPrenom;


    /**
     * Instance de l'utilisateur actuellement connecté.
     */
    public User usr = new User();
    public Button BttnNew;
    /**
     * Connexion à la base de données.
     */
    DatabaseConnexion db = new DatabaseConnexion();
    public Text question;
    public Button BttnValider;

    /**
     * Méthode d'initialisation appelée lors du chargement du contrôleur.
     * Initialise l'interface graphique et les gestionnaires d'événements.
     */
    public void initialize() {

        textFieldNom.setVisible(false);
        textFieldPassword.setVisible(false);
        textFieldPrenom.setVisible(false);
        checkboxRole.setVisible(false);
        labelNom.setVisible(false);
        labelPassword.setVisible(false);
        labelRole.setVisible(false);
        labelPrenom.setVisible(false);
        question.setText("Entrer votre username");
        BttnValider.setOnAction(actionEvent -> {
            try {
                checkUsername();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        BttnNew.setOnAction(actionEvent -> {

            askNewUser();
        });
        db.getConnection();


    }

    /**
     * Affiche l'interface pour la création d'un nouvel utilisateur.
     */
    private void askNewUser() {

        textFieldNom.setVisible(true);
        textFieldPassword.setVisible(true);
        textFieldPrenom.setVisible(true);
        checkboxRole.setVisible(true);
        labelNom.setVisible(true);
        labelPassword.setVisible(true);
        labelRole.setVisible(true);
        labelPrenom.setVisible(true);
        question.setText("Nouvelle Utilisateur");
        BttnValider.setText("Cr User");
        BttnValider.setOnAction(actionEvent -> {
            try {
                newUser();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

    }

    /**
     * Crée un nouvel utilisateur en insérant les informations dans la base de données.
     *
     * @throws IOException  En cas d'erreur d'entrée/sortie lors de la création de l'utilisateur.
     * @throws SQLException En cas d'erreur SQL lors de la création de l'utilisateur.
     */
    private void newUser() throws IOException, SQLException {
        String req = "INSERT INTO `user`(`nom`, `prenom`, `mdp`, `role`, `login`) VALUES (?,?,?,?,?)";
        Pair<Object, Integer> arg[] = new Pair[]{new Pair<>(textFieldNom.getText(), Types.VARCHAR),
                new Pair<>(textFieldPrenom.getText(), Types.VARCHAR),
                new Pair<>(textFieldPassword.getText(), Types.VARCHAR),
                new Pair<>(checkboxRole.isSelected(), Types.BOOLEAN),
                new Pair<>(textFieldLogin.getText(), Types.VARCHAR)
        };
        db.insert(req, arg);
        usr.setUsername(textFieldLogin.getText());
        usr.setProfile(checkboxRole.isSelected());
        usr.setNom(textFieldNom.getText());
        usr.setPrenom(textFieldPrenom.getText());
        usr.setPassword(textFieldPassword.getText());
        changeScene();
    }

    /**
     * Vérifie si le nom d'utilisateur existe dans la base de données.
     *
     * @throws SQLException En cas d'erreur SQL lors de la vérification du nom d'utilisateur.
     */
    public void checkUsername() throws SQLException {

        String query = "SELECT * FROM user WHERE login ='" + textFieldLogin.getText() + "'";
        ResultSet queryOutput = db.selectBook(query);
        if (queryOutput.isBeforeFirst()) {

            setPwdView();
            usr.setUsername(textFieldLogin.getText());
        } else {
            question.setText("username inconnu");
        }
    }

    /**
     * Affiche l'interface pour entrer le mot de passe de l'utilisateur.
     */
    private void setPwdView() {

        question.setText("Entrer votre pwd");
        BttnValider.setOnAction(actionEvent -> {
            try {
                checkPwd();
            } catch (SQLException | IOException e) {
                throw new RuntimeException(e);
            }
        });
        textFieldPassword.setVisible(true);
        labelPassword.setVisible(true);
    }

    /**
     * Vérifie si le mot de passe est correct pour l'utilisateur actuel.
     *
     * @throws SQLException En cas d'erreur SQL lors de la vérification du mot de passe.
     * @throws IOException  En cas d'erreur d'entrée/sortie lors de la vérification du mot de passe.
     */
    private void checkPwd() throws SQLException, IOException {

        String query = "SELECT * FROM user WHERE login ='" + usr.getUsername() + "' AND mdp ='" + textFieldPassword.getText() + "'";
        ResultSet queryOutput = db.selectBook(query);
        if (queryOutput.isBeforeFirst()) { //utilisateur trouver et pwd correcte
            while (queryOutput.next()) {

                usr.setNom(queryOutput.getString("nom"));
                usr.setPassword(queryOutput.getString("mdp"));
                usr.setPrenom(queryOutput.getString("prenom"));
                usr.setProfile(queryOutput.getBoolean("role"));

            }
            changeScene();

        } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("ERRONED PWD");
            alert.setHeaderText("Votre mot de passe est incorrecte");
            alert.setContentText("Voulez vous définir ce mot de passe comme nouveau mot de passe ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                updatepwd(textFieldPassword.getText(), usr.getUsername());
            }


        }

    }

    /**
     * Met à jour le mot de passe de l'utilisateur dans la base de données.
     *
     * @param pwd      Le nouveau mot de passe.
     * @param username Le nom d'utilisateur.
     * @throws IOException  En cas d'erreur d'entrée/sortie lors de la mise à jour du mot de passe.
     * @throws SQLException En cas d'erreur SQL lors de la mise à jour du mot de passe.
     */
    private void updatepwd(String pwd, String username) throws IOException, SQLException {
        changeScene();
    }

    /**
     * Change de scène vers la vue "Biblio.fxml" et ferme la connexion à la base de données.
     *
     * @throws IOException  En cas d'erreur d'entrée/sortie lors du changement de scène.
     * @throws SQLException En cas d'erreur SQL lors de la fermeture de la connexion à la base de données.
     */
    private void changeScene() throws IOException, SQLException {
        db.closeConnection();
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("Biblio.fxml"));

        Stage stage = (Stage) BttnNew.getScene().getWindow();
        try {

            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.setUserData(usr);

            stage.show();
        } catch (IOException e) {
            System.err.println(String.format("Error: %s", e.getMessage()));
        }
    }
}

