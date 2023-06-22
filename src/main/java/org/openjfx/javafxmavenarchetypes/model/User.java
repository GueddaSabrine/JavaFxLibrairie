package org.openjfx.javafxmavenarchetypes.model;

import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class User {

    protected String username;
    protected String password;
    protected boolean profile; //0 == gerant 1 == userlambda


    public void Userlogin(){

        username = Dialog(
                "username",
                "Connexion",
                "Please enter your username",
                "username : ");

        if(true){

        }
        else{
            createNewUser();
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

    public void createNewUser(){

    }

}
