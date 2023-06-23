package org.openjfx.javafxmavenarchetypes.fixture;

import org.openjfx.javafxmavenarchetypes.model.Bibliotheque;

/**
 * Donnée préconfiguré
 */
public class LivreFixture {
    private String titre = "TitreTest";
    private Bibliotheque.Livre.Auteur auteur = new Bibliotheque.Livre.Auteur("Musso", "Guillaume");
    private String presentation = "Presentation fictive pour test";
    private int parution = 2012;
    private int colonne = '0';
    private int rangee = '0';
    private String image = "url image";
    private Boolean disponible = true;

    public static LivreFixture leLivre() {
        return new LivreFixture();
    }

    public Bibliotheque.Livre build() {
        return Bibliotheque.Livre
                .builder()
                .titre(titre)
                .auteur(auteur)
                .image(image)
                .rangee(rangee)
                .presentation(presentation)
                .parution(parution)
                .disponible(disponible)
                .build();
    }
}
