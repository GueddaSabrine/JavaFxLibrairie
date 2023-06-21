package org.openjfx.javafxmavenarchetypes.fixture;

import org.openjfx.javafxmavenarchetypes.model.Bibliotheque;

public class LivreFixture {
    private String titre = "TitreTest";
    private Bibliotheque.Livre.Auteur auteur = new Bibliotheque.Livre.Auteur("Musso", "Guillaume");
    private int id = 0;
    private String presentation = "Presentation fictive pour test";
    private String parution = "date de parution";
    private int colonne = '0';
    private int rangee = '0';
    private String image = "url image";

    public static LivreFixture leLivre() {
        return new LivreFixture();
    }


//    public LivreFixture avecTitre(String titre) {
//        this.titre = titre;
//        return this;
//    }


    public Bibliotheque.Livre build() {
        return Bibliotheque.Livre
                .builder()
                .titre(titre)
                .auteur(auteur)
                .image(image)
                .rangee(rangee)
                .presentation(presentation)
                .parution(parution)
                .build();
    }
}
