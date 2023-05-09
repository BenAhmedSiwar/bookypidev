package tn.esprit.spring.spring11.controllers;

import com.itextpdf.text.DocumentException;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Card;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Token;
import lombok.AllArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.spring11.entities.Commande;
import tn.esprit.spring.spring11.entities.LignePannier;
import tn.esprit.spring.spring11.entities.Livre;
import tn.esprit.spring.spring11.entities.Pannier;
import tn.esprit.spring.spring11.interfaces.ICommandePdfService;
import tn.esprit.spring.spring11.interfaces.ICommandeService;
import tn.esprit.spring.spring11.services.StripeClient;

import javax.mail.MessagingException;
import javax.persistence.EntityNotFoundException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Commande")
public class CommandeController {
    @Autowired
    ICommandePdfService iCommandePdfService;
    @Autowired
    ICommandeService iCommandeService;
    @Autowired
    StripeClient sc;
    @Value("${stripe.apikey}")
    String stripeKey;



    @PostMapping("addcommande/")
    public ResponseEntity<Commande> addCommande(@RequestParam Long idPannier, @RequestParam String adresse) throws MessagingException {
        Commande commande = iCommandeService.addCommande(idPannier, adresse);
//        sc.AddStripeCommand();
//        Customer customer = Customer.retrieve("cus_Nrh74qhhVlgwuD");
        return new ResponseEntity<>(commande, HttpStatus.CREATED);
    }

    @PostMapping("addcommandee/")
    public Commande addCommandee( @RequestBody Commande commande) throws MessagingException {

        return iCommandeService.addCommandee(commande);
    }
    @PostMapping("stripeAdd/")
    public void stripeAdd()  {
        sc.AddStripeCommand();
    }

    @GetMapping("/retrieveAllCommande/{idCommande}")
    public List<Commande> retrieveAllCommande(@PathVariable Long idCommande) {
        List<Commande> commande = iCommandeService.retrieveAllCommande(idCommande);
        return commande;
    }

    @PutMapping("UpdateAdresseCommande/{idCommande}/adresse")
    public Commande UpdateAdresseCommande(@PathVariable Long idCommande, @RequestParam String adresse) {
        // LignePannier lignePannier = iLignePannierService.updateQuantite(idLignePannier, nouvelleQuantite);

        return iCommandeService.UpdateAdresseCommande(idCommande, adresse);
    }

    @DeleteMapping("supprimerCommande/{idCommande}")
    public ResponseEntity<String> supprimerLignePannier(@PathVariable Long idCommande) {
        try {
            iCommandeService.supprimerCommande(idCommande);
            return ResponseEntity.ok("Ligne de panier supprimée avec succès.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur est survenue lors de la suppression de la ligne de panier.");
        }
    }

    @GetMapping("/getAllCommandesByUser/{userId}/commandes")
    public ResponseEntity<List<Commande>> getCommandesByUserId(@PathVariable Long idUser) {
        List<Commande> commandes = iCommandeService.getCommandeByIdUser(idUser);
        if (commandes.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(commandes);
        }

    }

    @GetMapping("/GetCmd-by-user/{userId}")//hethi tekhdem
    public List<Commande> getCmdbyidUser(@PathVariable("userId") Long userId) {
        return iCommandeService.getCmdbyidUser(userId);
    }

    @GetMapping(value = "/commandes/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> generateCommandePdf() throws IOException, DocumentException {
        List<Commande> commandes = iCommandeService.getallCmds();
        ByteArrayInputStream pdfStream = iCommandePdfService.generatePdf(commandes);
        byte[] pdfBytes = IOUtils.toByteArray(pdfStream);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.builder("inline").filename("commandes.pdf").build());
        headers.setCacheControl(CacheControl.noCache());
        ResponseEntity<byte[]> response = new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        return response;
    }

    @GetMapping("/count-total-greater-than-100")
    public ResponseEntity<Long> countCommandesTotalGreaterThan() {
        Long count = iCommandeService.getNumberOfCommandesWithTotalGreaterThan100();
        return ResponseEntity.ok(count);

    }
    @GetMapping("/getAllCommande")
    public List<Commande> getAllCommande() {
        return iCommandeService.getallCmds();
    }

    @PostMapping("/AddCard")
    public void AddPaymentMethod(
                                   @RequestParam String card_number,
                                   @RequestParam String exp_month,
                                   @RequestParam String exp_year,
                                   @RequestParam String cvc) throws StripeException {
        Stripe.apiKey = stripeKey;
        Map<String, Object> retrieveParams = new HashMap<String, Object>();
        List<String> expandList = new ArrayList<>();
        expandList.add("sources");
        retrieveParams.put("expand", expandList);
        Customer customer = Customer.retrieve("cus_Nrh74qhhVlgwuD", retrieveParams, null);
        Map<String, Object> cardParam = new HashMap<String, Object>(); //add card details
        cardParam.put("number", card_number);
        cardParam.put("exp_month", exp_month);
        cardParam.put("exp_year", exp_year);
        cardParam.put("cvc", cvc);
        //cardParam.put("funding", "debit");

        Map<String, Object> tokenParam = new HashMap<String, Object>();
        tokenParam.put("card", cardParam);

        Token token = Token.create(tokenParam); // create a token

        Map<String, Object> source = new HashMap<String, Object>();
        source.put("source", token.getId()); //add token as source

        Card card = (Card) customer.getSources().create(source); // add the customer details to which card is need to link
        card.setFunding("debit");

        String cardDetails = card.toJson();
        System.out.println("Card Details : " + cardDetails);
    }
}
