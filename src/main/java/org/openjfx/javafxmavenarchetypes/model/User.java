package org.openjfx.javafxmavenarchetypes.model;

import javafx.scene.control.TextInputDialog;

import java.util.Optional;

/**
 * Représente un utilisateur.
 */
public class User {

    /**
     * Nom d'utilisateur de l'utilisateur.
     */
    protected String username;
    /**
     * Mot de passe de l'utilisateur.
     */
    protected String password;
    /**
     * Profil de l'utilisateur. (0 pour gérant, 1 pour utilisateur lambda)
     */
    protected boolean profile; //0 == gerant 1 == userlambda

    /**
     * Effectue la connexion de l'utilisateur.
     */
    public void Userlogin() {
        username = Dialog(
                "username",
                "Connexion",
                "Please enter your username",
                "username : ");

        if (true) {

        } else {
            createNewUser();
        }
    }

    /**
     *
     * Affiche une boîte de dialogue avec une question et retourne la réponse de l'utilisateur.
     *
     * @param def
     * @param title
     * @param header
     * @param content
     * @return
     */
    private String Dialog(String def, String title, String header, String content) {
        TextInputDialog dialog = new TextInputDialog(def);
        dialog.setTitle(title);
        dialog.setHeaderText(header);
        dialog.setContentText(content);

        /**
         * Traditional way to get the response value.
         */
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            return result.get();
        }
        return null;
    }

    /**
     * Creation d'un nouvelle utilistauer
     */
    public void createNewUser() {
    }

}
