package tn.esprit.spring.spring11.interfaces;

import com.itextpdf.text.DocumentException;
import tn.esprit.spring.spring11.entities.Commande;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

public interface ICommandePdfService {
    public ByteArrayInputStream generatePdf(List<Commande> commandes) throws DocumentException, IOException;

}
