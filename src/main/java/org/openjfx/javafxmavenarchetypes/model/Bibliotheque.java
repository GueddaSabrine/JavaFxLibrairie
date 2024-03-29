package org.openjfx.javafxmavenarchetypes.model;

import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import lombok.*;

import javax.xml.bind.annotation.*;


/**
 * <p>Classe Java pour anonymous complex type.
 *
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="livre" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="titre" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="auteur">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="nom" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="prenom" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="presentation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="parution" type="{http://www.w3.org/2001/XMLSchema}unsignedShort"/>
 *                   &lt;element name="colonne" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
 *                   &lt;element name="rangee" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "listlivre"
})

/**
 * Liste des livres dans la bibliothèque.
 */
@XmlRootElement(name = "bibliotheque")
public class Bibliotheque {
    /**
     * Liste des livres dans la bibliothèque.
     */
    @XmlElement(name = "livre")
    protected ObservableList<Livre> listlivre;

    /**
     * Constructeur par défaut de la bibliothèque.
     * Initialise la liste des livres.
     */
    public Bibliotheque() {

        listlivre = FXCollections.observableArrayList();
    }

    /**
     * Gets the value of the livre property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the livre property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLivre().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Bibliotheque.Livre }
     * <p>
     * Obtient la valeur de la propriété "livre".
     * Cette méthode retourne une référence à la liste en direct,
     * et non une copie. Toute modification apportée à la liste retournée
     * sera présente dans l'objet JAXB.
     */
    public ObservableList<Bibliotheque.Livre> getLivre() {
        if (listlivre == null) {
            listlivre = FXCollections.observableArrayList();
        }
        return this.listlivre;
    }
    //used by jaxb to create/write book

    /**
     * Ajoute un livre à la bibliothèque.
     *
     * @param titre  Le titre du livre.
     * @param auteur L'auteur du livre.
     * @param pre    La présentation du livre.
     * @param pick   L'année de parution du livre.
     * @param col    La colonne où le livre est rangé.
     * @param rangee La rangée où le livre est rangé.
     * @param image  L'image du livre.
     * @param disp   Indique si le livre est disponible.
     */
    public void addLivre(int id, String titre, Livre.Auteur auteur, String pre, int pick, int col, int rangee, String image, boolean disp) {

        listlivre.add(new Livre(id, titre, auteur, pre, pick, col, rangee, image, disp));

    }

    /**
     * Affiche la bibliothèque et ses livres.
     */
    public void print() {
        System.out.println(this);
        listlivre.forEach(e -> System.out.println(e.print()));


    }

    /**
     * <p>Classe Java pour anonymous complex type.
     *
     * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="titre" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="auteur">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="nom" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="prenom" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="presentation" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="parution" type="{http://www.w3.org/2001/XMLSchema}unsignedShort"/>
     *         &lt;element name="colonne" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
     *         &lt;element name="rangee" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlRootElement(name = "livre")
    @XmlType(name = "", propOrder = {
            "id",
            "titre",
            "auteur",
            "presentation",
            "parution",
            "colonne",
            "rangee",
            "image",
            "disponible"
    })

    /**
     * Représente un livre.
     */
    @EqualsAndHashCode
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    public static class Livre {
        @XmlElement(required = true)

        protected int id;
        /**
         * Représente un livre.
         */
        @XmlElement(required = true)
        protected String titre;
        /**
         * Auteur du livre.
         */
        @XmlElement(required = true)
        protected Bibliotheque.Livre.Auteur auteur;
        /**
         * Présentation du livre
         */
        @XmlElement(required = true)
        protected String presentation;
        /**
         * Année de parution du livre
         */
        @XmlSchemaType(name = "unsignedShort")
        protected int parution;
        /**
         * Colonne où le livre est rangé.
         */
        @XmlSchemaType(name = "unsignedByte")
        protected int colonne;
        /**
         * Rangée où le livre est rangé.
         */
        @XmlSchemaType(name = "unsignedByte")
        protected int rangee;
        /**
         * Image du livre.
         */
        @XmlSchemaType(name = "unsignedByte")
        protected String image;
        /**
         * Disponibilité du livre (true = non emprunté, false = emprunté).
         */
        protected boolean disponible; //true = non emprunte false = emprunte

        /**
         * Constructeur de la classe Livre.
         *
         * @param id           cle bdd
         * @param titre        Le titre du livre.
         * @param auteur       L'auteur du livre.
         * @param presentation La présentation du livre.
         * @param parution     L'année de parution du livre.
         * @param colonne      La colonne où le livre est rangé.
         * @param rangee       La rangée où le livre est rangé.
         * @param image        L'image du livre.
         * @param disp         Indique si le livre est disponible.
         */
        public Livre(int id, String titre, Bibliotheque.Livre.Auteur auteur, String presentation, Integer parution, Integer colonne, Integer rangee, String image,
                     boolean disp) {
            this.id = id;
            this.titre = titre;
            this.auteur = auteur;
            this.presentation = presentation;
            this.parution = parution;
            this.colonne = colonne;
            this.rangee = rangee;
            this.image = image;
            this.disponible = disp;

        }

        /**
         * Constructeur par défaut de la classe Livre.
         * Initialise les valeurs par défaut.
         */
        public Livre() {
            this.titre = null;
            this.auteur = null;
            this.presentation = null;
            this.parution = 2012;
            this.colonne = 0;
            this.rangee = 0;
            this.disponible = true;
        }

        /**
         * Obtient la valeur de la propri�t� titre.
         *
         * @return possible object is
         * {@link StringProperty }
         */
        public String getTitre() {
            return titre;
        }

        /**
         * @return
         */
        public String titreProperty() {
            return titre;
        }

        /**
         * D�finit la valeur de la propri�t� titre.
         *
         * @param value allowed object is
         *              {@link StringProperty }
         */
        public void setTitre(String value) {
            this.titre = value;
        }

        /**
         * Obtient la valeur de la propri�t� auteur.
         *
         * @return possible object is
         * {@link Bibliotheque.Livre.Auteur }
         */
        public Bibliotheque.Livre.Auteur getAuteur() {
            return auteur;
        }

        /**
         * Obtient une représentation sous forme de chaîne de caractères de l'auteur du livre.
         *
         * @return La représentation de l'auteur sous forme de chaîne de caractères.
         */
        public String getStringAuteur() {

            return auteur.getPrenom() + " " + auteur.getNom();
        }

        /**
         * D�finit la valeur de la propri�t� auteur.
         *
         * @param value allowed object is
         *              {@link Bibliotheque.Livre.Auteur }
         */
        public void setAuteur(Bibliotheque.Livre.Auteur value) {
            this.auteur = value;
        }

        /**
         * Obtient la valeur de la propri�t� presentation.
         *
         * @return possible object is
         * {@link StringProperty }
         */
        public String getPresentation() {
            return presentation;
        }

        /**
         * D�finit la valeur de la propri�t� presentation.
         *
         * @param value allowed object is
         *              {@link StringProperty }
         */
        public void setPresentation(String value) {
            this.presentation = value;
        }

        /**
         * Obtient la valeur de l'image.
         */
        public String getImage() {
            return image;
        }

        /**
         * @param value
         */
        public void setImage(String value) {
            this.presentation = value;
        }

        /**
         * Obtient la valeur de la propri�t� parution.
         */
        public int getParution() {
            return parution;
        }

        /**
         * D�finit la valeur de la propri�t� parution.
         */
        public void setParution(int value) {
            this.parution = value;
        }

        /**
         * Obtient la valeur de la propri�t� colonne.
         */
        public int getColonne() {
            return colonne;
        }

        /**
         * D�finit la valeur de la propri�t� colonne.
         */
        public void setColonne(int value) {
            this.colonne = value;
        }

        /**
         * Obtient la valeur de la propri�t� rangee.
         */
        public int getRangee() {
            return rangee;
        }

        /**
         * @return
         */
        public int rangeeProperty() {
            return rangee;
        }

        /**
         * D�finit la valeur de la propri�t� rangee.
         */
        public void setRangee(int value) {
            this.rangee = value;
        }

        /**
         * @return
         */
        public boolean getDisponibilite() {
            return disponible;
        }

        /**
         * @param b
         */
        public void setDisponibilite(boolean b) {
            disponible = b;
        }

        /**
         * @return
         */
        public String print() {
            return this.toString() + "\n" + this.getTitre() + "\n" + this.getAuteur().toString();
        }

        /**
         *
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "nom",
                "prenom"
        })

        public static class Auteur {

            /**
             *
             */
            @XmlElement(required = true)
            protected String nom;
            /**
             *
             */
            @XmlElement(required = true)
            protected String prenom;

            /**
             * @param nom
             * @param prenom
             */
            public Auteur(String nom, String prenom) {
                this.nom = nom;
                this.prenom = prenom;
            }

            /**
             *
             */
            public Auteur() {
                this.nom = null;
                this.prenom = null;
            }

            /**
             * Obtient la valeur de la propri�t� nom.
             *
             * @return possible object is
             * {@link String }
             */
            public String getNom() {
                return nom;
            }

            /**
             * D�finit la valeur de la propri�t� nom.
             *
             * @param value allowed object is
             *              {@link String }
             */
            public void setNom(String value) {
                this.nom = value;
            }

            /**
             * Obtient la valeur de la propri�t� prenom.
             *
             * @return possible object is
             * {@link String }
             */
            public String getPrenom() {
                return prenom;
            }

            /**
             * D�finit la valeur de la propri�t� prenom.
             *
             * @param value allowed object is
             *              {@link String }
             */
            public void setPrenom(String value) {
                this.prenom = value;
            }

        }

    }


}
