package org.openjfx.javafxmavenarchetypes.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.util.Pair;
import org.openjfx.javafxmavenarchetypes.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.Types;

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

    public void initialize(){

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

        textFieldNom.setVisible(false);
        textFieldPassword.setVisible(false);
        textFieldPrenom.setVisible(false);
        checkboxRole.setVisible(false);
        labelNom.setVisible(false);
        labelPassword.setVisible(false);
        labelRole.setVisible(false);
        labelPrenom.setVisible(false);
        question.setText("Nouvelle Utilisateur");
        BttnValider.setOnAction(actionEvent -> {
            newUser();
        });

    }

    private void newUser() {
        String req = "INSERT INTO `user`(`nom`, `prenom`, `mdp`, `role`, `login`) VALUES (?,?,?,?,?)";
        Pair<Object, SQLType> arg[] = new Pair[]{new Pair<>(textFieldNom.getText(), Types.VARCHAR),
                                        new Pair<>(textFieldPrenom.getText(), Types.VARCHAR),
                                        new Pair<>(textFieldPassword.getText(), Types.VARCHAR),
                                        new Pair<>(checkboxRole.isSelected(), Types.BOOLEAN),
                                        new Pair<>(textFieldLogin.getText(), Types.VARCHAR)
                                                 } ;
        db.insert(req,arg);b
    }

    public void checkUsername() throws SQLException {

        String query = "SELECT * FROM user WHERE login ='" +textFieldLogin.getText()+ "'";
        ResultSet queryOutput = db.selectBook(query);
        if(!queryOutput.isBeforeFirst()){

            setPwdView();
            usr.setUsername(textFieldLogin.getText());
        }
        else{
            question.setText("username inconnu");
        }
    }

    private void setPwdView() {

        question.setText("Entrer votre pwd");
        BttnValider.setOnAction(actionEvent -> {
            try {
                checkPwd();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        textFieldPassword.setVisible(true);
        labelPassword.setVisible(true);
    }

    private void checkPwd() throws SQLException {

        String query = "SELECT * FROM user WHERE login ='" +usr.getUsername()+ "' AND mdp ='" + textFieldPassword.getText() +"'" ;
        ResultSet queryOutput = db.selectBook(query);
        if(!queryOutput.isBeforeFirst()){ //utilisateur trouver

            usr.setNom(queryOutput.getString("nom"));
            usr.setPassword(queryOutput.getString("mdp"));
            usr.setPrenom(queryOutput.getString("prenom"));
            usr.setProfile(queryOutput.getBoolean("role"));
            changeScene();

        }
        else{


        }

    }

    private void changeScene() {
    }
}

