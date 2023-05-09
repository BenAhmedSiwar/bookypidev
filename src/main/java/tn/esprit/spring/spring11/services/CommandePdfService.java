package tn.esprit.spring.spring11.services;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import tn.esprit.spring.spring11.entities.Commande;
import tn.esprit.spring.spring11.interfaces.ICommandePdfService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class CommandePdfService implements ICommandePdfService {
    @Override
    public ByteArrayInputStream generatePdf(List<Commande> commandes) throws IOException, DocumentException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);

        document.open();

        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);

        Paragraph title = new Paragraph("Liste des commandes", font);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(20);
        document.add(title);

        com.itextpdf.text.List list = new com.itextpdf.text.List();
        for (Commande commande : commandes) {
            ListItem item = new ListItem();
            item.add(new Paragraph("Commande n°" + commande.getIdCommande(), font));
            item.add(new Paragraph("Client : " + commande.getUser().getUsername() , font));
            item.add(new Paragraph("Montant : " + commande.getTotal() + " DT", font));
            item.add(new Paragraph("Date de commande : " + commande.getDateCreation(), font));
            list.add(item);
        }
        document.add(list);

        document.close();

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(outputStream.toByteArray());
        return byteArrayInputStream;
    }
}
