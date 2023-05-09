package tn.esprit.spring.spring11.services;


import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.spring11.entities.Commande;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class PDFService {

    public void export(HttpServletResponse response, List<Commande> commandes) throws Exception, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);

        Paragraph paragraph = new Paragraph("Liste des Commandes", fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(paragraph);

        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph.setSize(12);

        for (Commande commande : commandes) {
            Paragraph clientParagraph = new Paragraph("idcommande: " + commande.getIdCommande() + ", total: " + commande.getTotal()+", date:"+ commande.getDateCreation(), fontParagraph);
            document.add(clientParagraph);
        }

        document.close();
    }
}

