//
// Ce fichier a �t� g�n�r� par l'impl�mentation de r�f�rence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apport�e � ce fichier sera perdue lors de la recompilation du sch�ma source. 
// G�n�r� le : 2023.03.23 � 02:30:44 PM CET 
//


package org.openjfx.javafxmavenarchetypes.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.DatePicker;

import java.util.ArrayList;
import java.util.List;
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
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "listlivre"
})
@XmlRootElement(name = "bibliotheque")
public class

Bibliotheque {


    @XmlElement(name = "livre")
    protected List<Bibliotheque.Livre> listlivre;

    public Bibliotheque(){

        listlivre = new ArrayList<>();
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
     * 
     * 
     */
    public List<Bibliotheque.Livre> getLivre() {
        if (listlivre == null) {
            listlivre = new ArrayList<Bibliotheque.Livre>();
        }
        return this.listlivre;
    }

    /**
     * *
     * @param titre
     * @param auteur
     * @param pre
     * @param pick
     * @param col
     * @param rangee
     */
    public void addLivre(String titre, Livre.Auteur auteur, String pre , String pick , int col, int rangee){

        listlivre.add(new Livre(titre, auteur, pre, pick ,col, rangee));
        System.out.println("new book");
        this.print();


    }

    public void print(){
        System.out.println(this);
        listlivre.forEach(e->System.out.println(e.print()));


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
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlRootElement(name = "livre")
    @XmlType(name = "", propOrder = {
        "titre",
        "auteur",
        "presentation",
        "parution",
        "colonne",
        "rangee",
            "image"
    })
    public static class Livre {
        protected StringProperty titre;
        @XmlElement(name = "auteur" )
        protected Bibliotheque.Livre.Auteur auteur;
        @XmlElement(name = "presentation")
        protected StringProperty presentation;
        @XmlSchemaType(name ="unsignedShort")
        protected StringProperty parution;
        @XmlSchemaType(name = "unsignedByte")
        protected IntegerProperty colonne;
        @XmlSchemaType(name = "unsignedByte")
        protected IntegerProperty rangee;
        @XmlSchemaType(name = "unsignedByte")
        protected StringProperty image;

        public Livre(String titre, Bibliotheque.Livre.Auteur auteur,String presentation,String parution,Integer colonne, Integer rangee){
            this.titre = new SimpleStringProperty(titre);
            this.setTitre(titre);
            this.auteur =auteur;
            this.presentation = new SimpleStringProperty(presentation);
            this.parution= new SimpleStringProperty(parution);
            this.colonne= new SimpleIntegerProperty(colonne);
            this.rangee= new SimpleIntegerProperty(rangee);
        }
        public Livre(){
            this.titre= new SimpleStringProperty(null);
            this.auteur =null;
            this.presentation= new SimpleStringProperty(null);
            this.parution= new SimpleStringProperty(null);
            this.colonne= new SimpleIntegerProperty(0);
            this.rangee= new SimpleIntegerProperty(0);
        }

        /**
         * Obtient la valeur de la propri�t� titre.
         * 
         * @return
         *     possible object is
         *     {@link StringProperty }
         *     
         */
        public String getTitre() {
            return titre.get();
        }
        public StringProperty titreProperty() {
            return titre;
        }

        /**
         * D�finit la valeur de la propri�t� titre.
         * 
         * @param value
         *     allowed object is
         *     {@link StringProperty }
         *     
         */
        public void setTitre(String value) {
            this.titre.set(value);
        }

        /**
         * Obtient la valeur de la propri�t� auteur.
         * 
         * @return
         *     possible object is
         *     {@link Bibliotheque.Livre.Auteur }
         *     
         */
        public Bibliotheque.Livre.Auteur getAuteur() {
            return auteur;
        }

        /**
         * D�finit la valeur de la propri�t� auteur.
         * 
         * @param value
         *     allowed object is
         *     {@link Bibliotheque.Livre.Auteur }
         *     
         */
        public void setAuteur(Bibliotheque.Livre.Auteur value) {
            this.auteur = value;
        }

        /**
         * Obtient la valeur de la propri�t� presentation.
         * 
         * @return
         *     possible object is
         *     {@link StringProperty }
         *     
         */
        public StringProperty getPresentation() {
            return presentation;
        }

        /**
         * D�finit la valeur de la propri�t� presentation.
         * 
         * @param value
         *     allowed object is
         *     {@link StringProperty }
         *     
         */
        public void setPresentation(StringProperty value) {
            this.presentation = value;
        }
        /**
         * Obtient la valeur de l'image.
         *
         */
        public StringProperty getImage(){
            return image;
        }

        public void setImage(StringProperty value){this.presentation=value;}

        /**
         * Obtient la valeur de la propri�t� parution.
         * 
         */
        public StringProperty getParution() {
            return parution;
        }

        /**
         * D�finit la valeur de la propri�t� parution.
         * 
         */
        public void setParution(StringProperty value) {
            this.parution = value;
        }

        /**
         * Obtient la valeur de la propri�t� colonne.
         * 
         */
        public IntegerProperty getColonne() {
            return colonne;
        }

        /**
         * D�finit la valeur de la propri�t� colonne.
         * 
         */
        public void setColonne(IntegerProperty value) {
            this.colonne = value;
        }

        /**
         * Obtient la valeur de la propri�t� rangee.
         * 
         */
        public Integer getRangee() {
            return rangee.get();
        }
        public IntegerProperty rangeeProperty() {
            return rangee;
        }

        /**
         * D�finit la valeur de la propri�t� rangee.
         * 
         */
        public void setRangee(Integer value) {
            this.rangee.set(value);
        }

        public String print(){
           return this.toString() + "\n" + this.getTitre() + "\n" + this.getAuteur().toString() ;
        }

        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "nom",
            "prenom"
        })
        public static class Auteur {

            @XmlElement(required = true)
            protected String nom;
            @XmlElement(required = true)
            protected String prenom;

            public Auteur(String nom, String prenom){
                this.nom = nom;
                this.prenom = prenom;
            }

            public Auteur(){
                this.nom = null;
                this.prenom = null;
            }
            /**
             * Obtient la valeur de la propri�t� nom.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getNom() {
                return nom;
            }

            /**
             * D�finit la valeur de la propri�t� nom.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setNom(String value) {
                this.nom = value;
            }

            /**
             * Obtient la valeur de la propri�t� prenom.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPrenom() {
                return prenom;
            }

            /**
             * D�finit la valeur de la propri�t� prenom.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPrenom(String value) {
                this.prenom = value;
            }

        }

    }

}
