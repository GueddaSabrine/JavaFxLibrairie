package org.openjfx.javafxmavenarchetypes.model;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the org.openjfx.javafxmavenarchetypes.model package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.openjfx.javafxmavenarchetypes.model
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Bibliotheque }
     */
    public Bibliotheque createBibliotheque() {
        return new Bibliotheque();
    }

    /**
     * Create an instance of {@link Bibliotheque.Livre }
     */
    public Bibliotheque.Livre createBibliothequeLivre() {
        return new Bibliotheque.Livre();
    }

    /**
     * Create an instance of {@link Bibliotheque.Livre.Auteur }
     */
    public Bibliotheque.Livre.Auteur createBibliothequeLivreAuteur() {
        return new Bibliotheque.Livre.Auteur();
    }

}
