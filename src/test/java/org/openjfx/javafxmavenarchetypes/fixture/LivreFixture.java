package org.openjfx.javafxmavenarchetypes.fixture;

import org.openjfx.javafxmavenarchetypes.model.Bibliotheque;

/**
 * Cette classe est une fixture pour créer des instances de la classe Livre avec des valeurs préconfigurées pour les tests.
 */
public class LivreFixture {
    private String titre = "TitreTest";
    private Bibliotheque.Livre.Auteur auteur = new Bibliotheque.Livre.Auteur("Musso", "Guillaume");
    private String presentation = "Presentation fictive pour test";
    private int parution = 2012;
    private int colonne = 5;
    private int rangee = 3;
    private String image = "url image";
    private Boolean disponible = true;

    /**
     * Retourne une instance de LivreFixture.
     * @return une nouvelle instance de LivreFixture
     */
    public static LivreFixture leLivre() {
        return new LivreFixture();
    }

    /**
     * Construit et retourne un objet Livre avec les valeurs préconfigurées.
     *
     * @return un objet Livre configuré avec les valeurs préconfigurées
     */
    public Bibliotheque.Livre build() {
        return Bibliotheque.Livre
                .builder()
                .titre(titre)
                .auteur(auteur)
                .image(image)
                .rangee(rangee)
                .presentation(presentation)
                .parution(parution)
                .colonne(colonne)
                .rangee(rangee)
                .disponible(disponible)
                .build();
    }
}
