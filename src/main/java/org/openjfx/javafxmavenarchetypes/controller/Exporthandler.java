package org.openjfx.javafxmavenarchetypes.controller;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import be.quodlibet.boxable.BaseTable;
import be.quodlibet.boxable.Cell;
import be.quodlibet.boxable.Row;
import be.quodlibet.boxable.utils.ImageUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.openjfx.javafxmavenarchetypes.model.Bibliotheque;

import static org.apache.pdfbox.pdmodel.font.PDType1Font.HELVETICA;

/**
 * Classe permettant d'exporter des documents Word et PDF.
 */
public class Exporthandler {

    /**
     * Exporte un document Word vide.
     *
     * @throws Exception si une erreur se produit lors de l'exportation.
     */
    public void exportWord() throws Exception {
        //Blank Document
        XWPFDocument document = new XWPFDocument();

        //Write the Document in file system
        FileOutputStream out = new FileOutputStream(new File("createdocument.docx"));
        document.write(out);
        out.close();
        System.out.println("createdocument.docx written successully");
    }


    /**
     * Exporte un document Word vide.
     *
     * @param bibliotheque
     * @throws IOException si une erreur se produit lors de l'exportation.
     */
    public void cretepdf(Bibliotheque bibliotheque) throws IOException {
        // Standard14Fonts.FontName font_name_3v= Standard14Fonts.getMappedFontName("HELVETICA_BOLD");
        //PDFont pdfFont=  new PDType1Font(font_name_3v.HELVETICA_BOLD);
        PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        PDPage page2 = new PDPage(PDRectangle.A4);
        // rect can be used to get the page width and height
        document.addPage(page);

        //ajout element
        float margin = 50;
        // starting y position is whole page height subtracted by top and bottom margin
        float yStartNewPage = page.getMediaBox().getHeight() - (2 * margin);
        float yStartNewPage2 = page2.getMediaBox().getHeight() - (2 * margin);

        // we want table across whole page width (subtracted by left and right margin ofcourse)
        float tableWidth = page.getMediaBox().getWidth() - (2 * margin);

        boolean drawContent = true;
        float yStart = yStartNewPage;
        float yStart2 = yStartNewPage2;
        float bottomMargin = 70;
        // y position is your coordinate of top left corner of the table
        float yPosition = 550;

        //Premier tableau
        BaseTable table = new BaseTable(yStart, yStartNewPage, bottomMargin, tableWidth, margin, document, page, true,
                drawContent);

        Row<PDPage> headerRow = table.createRow(15f);
        Cell<PDPage> cell = headerRow.createCell(100, "Bibliotheque collection");
        cell.setFillColor(Color.BLACK);
        cell.setTextColor(Color.WHITE);
        table.addHeaderRow(headerRow);

        // deuxieme tableau
        BaseTable tableind = new BaseTable(yStart, yStartNewPage, bottomMargin, tableWidth, margin, document, page2, true,
                drawContent);

        Row<PDPage> headerRowind = tableind.createRow(15f);
        Cell<PDPage> cellind = headerRowind.createCell(100, "Livrre non disponible");
        cellind.setFillColor(Color.GRAY);
        cellind.setTextColor(Color.WHITE);
        tableind.addHeaderRow(headerRowind);

        //creation des lignes
        List<Bibliotheque.Livre> facts = bibliotheque.getLivre();
        for (Bibliotheque.Livre fact : facts) {
            Row<PDPage> row = table.createRow(10f);
            InputStream in = new URL(fact.getImage()).openStream();
            Files.copy(in, Path.of("./imagetemp"));
            File imagefile = new File("./imagetemp");
            cell = row.createImageCell((100 / 9f), ImageUtils.readImage(imagefile));
            cell = row.createCell((35 / 3.0f), fact.getTitre());
            cell.setFont(HELVETICA);
            cell = row.createCell((35 / 3.0f), fact.getStringAuteur());
            cell.setFont(HELVETICA);
            cell = row.createCell((100 / 3.0f) * 2, fact.getPresentation());
            cell.setFont(HELVETICA);
            if (!fact.getDisponibilite()) {
                Row<PDPage> rowind = tableind.createRow(10f);
                cellind = rowind.createImageCell((100 / 9f), ImageUtils.readImage(imagefile));
                cellind = rowind.createCell((35 / 3.0f), fact.getTitre());
                cellind.setFont(HELVETICA);
                cellind = rowind.createCell((35 / 3.0f), fact.getStringAuteur());
                cellind.setFont(HELVETICA);
                cellind = rowind.createCell((100 / 3.0f) * 2, fact.getPresentation());
                cellind.setFont(HELVETICA);
            }

            imagefile.delete();
        }

        table.draw();
        document.addPage(page2);
        tableind.draw();

        //sauvegarder
        document.save("./test.pdf");
        document.close();

    }
}
