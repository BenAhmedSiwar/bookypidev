package tn.esprit.spring.spring11.entities;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;


public class PDF {

    private List<Commande> commandes;

//    public PdfExporter(List<ClientAccount> clientAccountList) {
//        this.clientAccountList = clientAccountList;
//    }



    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        com.lowagie.text.Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("IdCommande", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("total", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("adresse", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("datecreation", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("username", font));
        table.addCell(cell);

    }


    private void writeTableData(PdfPTable table) {
        for (Commande commande : commandes) {
            table.addCell(String.valueOf(commande.getIdCommande()));
            table.addCell(String.valueOf(commande.getTotal()));
            table.addCell(commande.getAdresse());
            table.addCell(String.valueOf(commande.getDateCreation()));
            table.addCell(String.valueOf(commande.getUser().getUsername()));

        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException  {


        com.lowagie.text.Document document = new com.lowagie.text.Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        com.lowagie.text.Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("List of Users", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }


}
