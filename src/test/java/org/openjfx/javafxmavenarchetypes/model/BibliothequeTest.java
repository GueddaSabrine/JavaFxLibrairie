package org.openjfx.javafxmavenarchetypes.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.openjfx.javafxmavenarchetypes.fixture.LivreFixture.leLivre;

/**
 * Test unitaire de la class Bibliotheque
 */
class BibliothequeTest extends Bibliotheque {

    private Livre livre;

    //    Fixture
    @BeforeEach
    void setUp() {
        livre = leLivre().build();
    }


    @Test
    void testGetTitre() {
        livre.setTitre("testTitre");
        Assertions.assertEquals("testTitre", livre.getTitre());
    }

    @Test
    void testGetAuteur_NotOk() {
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
        Assertions.assertEquals("testPresentation", livre.getPresentation());
    }

    @Test
    void testGetParution() {
        livre.setParution(2012);
        Assertions.assertEquals(2012, livre.getParution());
    }

    @Test
    void testGetImage() {
        livre.setImage("url image");
        Assertions.assertEquals("url image", livre.getImage());
    }

    @Test
    void testGetRangee() {
        livre.setRangee(5);
        Assertions.assertEquals(5, livre.getRangee());
    }

    @Test
    void testGetColonne() {
        livre.setColonne(5);
        Assertions.assertEquals(5, livre.getColonne());
    }

    @Test
    void testGetDisponible() {
        livre.setDisponible(true);
        Assertions.assertEquals(true, livre.getDisponibilite());
    }
}