package tn.esprit.spring.spring11.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.spring.spring11.entities.Commande;
import tn.esprit.spring.spring11.services.CommandeService;
import tn.esprit.spring.spring11.services.PDFService;

import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController


public class PDFController {
    @Autowired
    PDFService pdfService;
    @Autowired
    CommandeService commandeService;
    public PDFController(PDFService pdfService)
    {  this.pdfService = pdfService;
    }


    @GetMapping("/pdfcommande/generate")
    public void generatePDF(HttpServletResponse response) throws Exception {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf.." + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Commande> commandes = commandeService.getallCmds();
        pdfService.export(response, commandes);
    }



}
