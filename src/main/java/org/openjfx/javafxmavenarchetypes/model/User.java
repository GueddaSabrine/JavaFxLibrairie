package org.openjfx.javafxmavenarchetypes.model;

import javafx.scene.control.TextInputDialog;
import org.openjfx.javafxmavenarchetypes.controller.DatabaseConnexion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class User {

    protected String username;
    protected String password;
    protected boolean profile; //0 == gerant 1 == userlambda


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

    public void createNewUser(DatabaseConnexion db){


    }

    public void modifyPassword(DatabaseConnexion db){

    }

}
