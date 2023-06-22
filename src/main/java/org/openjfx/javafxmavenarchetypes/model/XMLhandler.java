package org.openjfx.javafxmavenarchetypes.model;

import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

/**
 * Classe pour gérer les opérations XML.
 */
public class XMLhandler {

    /**
     * Fichier sélectionné.
     */
    protected File selectedFile = null;

    /**
     * Indique si le fichier a été sauvegardé.
     */
    protected boolean fileSaved = true;

    /**
     * Vérifie si le fichier a été sauvegardé.
     *
     * @return true si le fichier a été sauvegardé, false sinon.
     */
    public boolean isFileSaved(){ return fileSaved;}

    /**
     * Obtient le fichier sélectionné.
     *
     * @return Le fichier sélectionné.
     */
    public File getSelectedFile(){return selectedFile;}

    /**
     * Définit l'état de sauvegarde du fichier.
     *
     * @param fileSaved true si le fichier a été sauvegardé, false sinon.
     */
    public void setFileSaved(boolean fileSaved) {
        this.fileSaved = fileSaved;
    }

    /**
     * Définit le fichier sélectionné.
     *
     * @param selectedFile Le fichier sélectionné.
     */
    public void setSelectedFile(File selectedFile) {
        this.selectedFile = selectedFile;
    }

    /**
     * Sauvegarde les données dans un nouveau fichier XML.
     *
     * @param jvfxwindow   La fenêtre JavaFX parente.
     * @param bibliotheque La bibliothèque à sauvegarder.
     * @throws JAXBException Si une exception se produit lors de la sauvegarde du fichier en utilisant JAXB.
     */
    public void SaveAs(Window jvfxwindow, Bibliotheque bibliotheque) throws JAXBException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Enregistrer");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Fichier XML", "*.xml"));
        selectedFile = fileChooser.showSaveDialog(jvfxwindow);
        if (selectedFile != null){
            JAXBContext jaxbContext = JAXBContext.newInstance(Bibliotheque.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            System.out.println("ok");
            jaxbMarshaller.marshal(bibliotheque, selectedFile);
            fileSaved = true;

        }
    }
}
