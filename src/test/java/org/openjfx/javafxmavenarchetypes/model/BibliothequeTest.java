package org.openjfx.javafxmavenarchetypes.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BibliothequeTest extends Bibliotheque {

    private Livre livre;

//    Fixture
//    @BeforeEach
//    void setUp() {
//        livre = leLivre().build();
//    }




    @Test
    void testGetTitre() {

        livre.setTitre("testTitre");
        Assertions.assertEquals("testTitre", livre.getTitre());
    }

    @Test
    void testGetAuteur_Nok() {
        Assertions.assertNotEquals(new Livre.Auteur("Marion", "Aimée"), livre.getAuteur());
    }

    @Test
    void testGetAuteur() {

        livre.setAuteur(new Livre.Auteur("testAuteurNom", "testAuteurPrenom"));
        Assertions.assertEquals("testAuteurNom", livre.getAuteur().getNom());
        Assertions.assertEquals("testAuteurPrenom", livre.getAuteur().getPrenom());

    }

    @Test
    void testGetPresentation() {

        livre.setPresentation("testPresentation");
        Assertions.assertEquals("testDescription", livre.getPresentation());
    }

//    @Test
//    void getLivre() {
//    }
//
//    @Test
//    void addLivre() {
//    }
//
//    @Test
//    void print() {
//    }
}