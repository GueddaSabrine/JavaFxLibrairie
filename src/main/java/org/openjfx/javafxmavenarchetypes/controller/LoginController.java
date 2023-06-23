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
 *
 */
public class LoginController {


    public TextField textFieldNom;
    public TextField textFieldLogin;
    public TextField textFieldPassword;
    public TextField textFieldPrenom;
    public CheckBox checkboxRole;
    public Label labelNom;
    public Label labelRole;
    public Label labelPassword;
    public Label labelLogin;
    public Label labelPrenom;

    public User usr = new User();
    public Button BttnNew;
    DatabaseConnexion db = new DatabaseConnexion();
    public Text question;
    public Button BttnValider;

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

    private void updatepwd(String pwd, String username) throws IOException, SQLException {

        changeScene();

    }

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

