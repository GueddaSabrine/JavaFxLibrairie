package org.openjfx.javafxmavenarchetypes.model;

import javafx.scene.control.TextInputDialog;
import org.openjfx.javafxmavenarchetypes.controller.DatabaseConnexion;

import java.sql.ResultSet;
import java.sql.SQLException;
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
    protected boolean profile;

    protected String nom;

    protected String prenom;





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
