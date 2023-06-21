//
// Ce fichier a �t� g�n�r� par l'impl�mentation de r�f�rence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apport�e � ce fichier sera perdue lors de la recompilation du sch�ma source. 
// G�n�r� le : 2023.03.23 � 02:30:44 PM CET 
//


package org.openjfx.javafxmavenarchetypes.model;


import javafx.beans.property.StringProperty;

import lombok.*;

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
public class Bibliotheque {

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
    //used by jaxb to create/write book
    /**
     * *
     * @param titre
     * @param auteur
     * @param pre
     * @param pick
     * @param col
     * @param rangee
     */
    public void addLivre(String titre, Livre.Auteur auteur, String pre , String pick , int col, int rangee ,String image){

        listlivre.add(new Livre(titre, auteur, pre, pick ,col, rangee, image));

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

    @EqualsAndHashCode
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
//    @NoArgsConstructor
    public static class Livre {
        @XmlElement(required = true)

        protected String titre;
        @XmlElement(required = true)
        protected Bibliotheque.Livre.Auteur auteur;
        @XmlElement(required = true)
        protected String presentation;
        @XmlSchemaType(name ="unsignedShort")
        protected String parution;
        @XmlSchemaType(name = "unsignedByte")
        protected int colonne;
        @XmlSchemaType(name = "unsignedByte")
        protected int rangee;
        @XmlSchemaType(name = "unsignedByte")
        protected String image;

        public Livre(String titre, Bibliotheque.Livre.Auteur auteur,String presentation,String parution,Integer colonne, Integer rangee , String image){
            this.titre = titre;
            this.setTitre(titre);
            this.auteur =auteur;
            this.presentation =presentation;
            this.parution= parution;
            this.colonne= colonne;
            this.rangee= rangee;
            this.image = image;
        }
        public Livre(){
            this.titre= null;
            this.auteur =null;
            this.presentation= null;
            this.parution= null;
            this.colonne= 0;
            this.rangee=0;
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
            return titre;
        }
        public String titreProperty() {
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
            this.titre= value;
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

        public String getStringAuteur(){

            return auteur.getPrenom()+ " " + auteur.getNom();
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
        public String getPresentation() {
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
        public void setPresentation(String value) {
            this.presentation = value;
        }
        /**
         * Obtient la valeur de l'image.
         *
         */
        public String getImage(){
            return image;
        }

        public void setImage(String value){this.presentation=value;}

        /**
         * Obtient la valeur de la propri�t� parution.
         * 
         */
        public String getParution() {
            return parution;
        }

        /**
         * D�finit la valeur de la propri�t� parution.
         * 
         */
        public void setParution(String value) {
            this.parution = value;
        }

        /**
         * Obtient la valeur de la propri�t� colonne.
         * 
         */
        public int getColonne() {
            return colonne;
        }

        /**
         * D�finit la valeur de la propri�t� colonne.
         * 
         */
        public void setColonne(int value) {
            this.colonne = value;
        }

        /**
         * Obtient la valeur de la propri�t� rangee.
         * 
         */
        public int getRangee() {
            return rangee;
        }
        public int rangeeProperty() {
            return rangee;
        }

        /**
         * D�finit la valeur de la propri�t� rangee.
         * 
         */
        public void setRangee(int value) {
            this.rangee= value;
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
