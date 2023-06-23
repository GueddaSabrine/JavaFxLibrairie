package org.openjfx.javafxmavenarchetypes.model;

import javafx.scene.control.TextInputDialog;
import org.openjfx.javafxmavenarchetypes.controller.DatabaseConnexion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class User {

    protected String username;
    protected String password;
    protected boolean profile;

    protected String nom;

    protected String prenom;


    public void Userlogin() throws SQLException {

        DatabaseConnexion db = new DatabaseConnexion();
        db.getConnection();
        username = Dialog(
                "username",
                "Connexion",
                "Please enter your username",
                "username : ");

        String query = "SELECT * FROM user WHERE login ='" +username+ "'";

        ResultSet queryOutput = db.selectBook(query);

        if(!queryOutput.isBeforeFirst()){

            password = Dialog(
                    "password",
                    "Connexion",
                    "Please enter your password",
                    "pwd : ");
            query = "SELECT * FROM user WHERE login ='" +username+ "' AND mdp ='" + password +"'" ;
            queryOutput = db.selectBook(query);
            if(queryOutput.isBeforeFirst())modifyPassword(db);
        }
        else{
            createNewUser(db);
        }

    }
    private String Dialog(String def , String title, String header, String content){
        TextInputDialog dialog = new TextInputDialog(def);
        dialog.setTitle(title);
        dialog.setHeaderText(header);
        dialog.setContentText(content);

// Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            return result.get();
        }
        return null;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setProfile(boolean profile) {
        this.profile = profile;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getPrenom() {
        return prenom;
    }

    public boolean isProfile() {
        return profile;
    }
}
