package tn.esprit.spring.spring11.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.spring11.entities.*;
import tn.esprit.spring.spring11.interfaces.ICommandeService;
import tn.esprit.spring.spring11.repositories.CommandeRepo;
import tn.esprit.spring.spring11.repositories.LignePannierRepo;
import tn.esprit.spring.spring11.repositories.PannierRepo;
import tn.esprit.spring.spring11.repositories.UserRepo;

import javax.mail.MessagingException;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CommandeService implements ICommandeService {
    @PersistenceContext
    EntityManager entityManager;

    LignePannierRepo lignePannierRepo;
    PannierRepo pannierRepo;
    UserRepo userRepo;
    NotificationService notificationService;
    @Autowired
    CommandeRepo commandeRepo;


    @Override
    public void passerCommande(Commande commande) {

    }



    @Override
    public Commande addCommande(Long idPannier, String adresse) throws MessagingException {
        Pannier pannier = pannierRepo.findById(idPannier).orElse(null);
        Long id = pannierRepo.findById(idPannier).get().getUser().getIdUser();

        User user = userRepo.findById(id).orElse(null);

        if (pannier == null) {
            throw new IllegalArgumentException("Pannier not found");
        }

        List<LignePannier> lignePanniers = pannier.getLignePanniers();
        if (lignePanniers.isEmpty()) {
            throw new IllegalArgumentException("Pannier is empty");
        }

        float total = 0;
        for (LignePannier lignePannier : lignePanniers) {
            total += lignePannier.getPrix() * lignePannier.getQuantite();
        }

        Commande commande = new Commande();
        commande.setAdresse(adresse);
        commande.setTotal(total);
        commande.setDateCreation(new Date());
        commande.setLignesPannier(lignePanniers);
        commande.setPannier(pannier);
        commande.setUser(user);
        String message = "Cher(e) client(e),\n\nvotre commande a été ajoutée avec succés on vous contacte le plus tot possible " ;

        notificationService.sendEmail(user.getMail(), "commande ajoutée", message);

        // Assign the command to the pannier
        for (LignePannier lignePannier : lignePanniers) {
            lignePannier.setCommande(commande);

        }

        return commandeRepo.save(commande);
    }

    @Override
    public Commande addCommandee(Commande commande) {
        return commandeRepo.save(commande);
    }

    @Override
    public List<Commande> retrieveAllCommande(Long idCommande) {
        List<Commande> commandes;
        if (idCommande == null) {
            commandes = commandeRepo.findAll();
        } else {
            commandes = commandeRepo.findById(idCommande)
                    .map(Collections::singletonList)
                    .orElse(Collections.emptyList());
        }
        return commandes;
    }

    @Override
    public List<Commande> getAllCommande() {
        List<Commande> commandes;
        commandes = commandeRepo.findAll();
        return commandes;
    }

    @Override
    public Commande UpdateAdresseCommande(Long idCommande, String adresse) {
        // Rechercher la LignePannier à modifier
        Commande commande = commandeRepo.findById(idCommande)
                .orElseThrow(() -> new RuntimeException("LignePannier non trouvée"));

        // Modifier la quantité
        commande.setAdresse(adresse);

        // Enregistrer les modifications
        return commandeRepo.save(commande);
    }

    @Override
    public void supprimerCommande(Long idCommande) {
        Commande commande = commandeRepo.findById(idCommande).orElse(null);
        if (commande != null) {
            commandeRepo.delete(commande);
        } else {
            throw new EntityNotFoundException("La ligne de panier avec l'ID " + idCommande + " n'existe pas.");
        }
    }


    @Override
    public List<Commande> retrieveAllCommandeByUser(Long idUser) {
        User user = userRepo.findById(idUser).orElse(null);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        return user.getCommandes();
    }

    @Override
    public List<Commande> getCommandeByIdUser(Long idUser) {
        List<Commande> commandes = commandeRepo.findAll();
        List<Commande> result = new ArrayList<>();
        for (Commande c : commandes) {
            if (c.getUser().getIdUser().equals(idUser)) {
                result.add(c);
            }
        }
        return result;
    }

    @Override
    public List<Commande> getCmdbyidUser(Long idUser) {
        String query = "SELECT c FROM Commande c WHERE c.user.idUser = :idUser";
        TypedQuery<Commande> typedQuery = entityManager.createQuery(query, Commande.class);
        typedQuery.setParameter("idUser", idUser);
        return typedQuery.getResultList();
    }

    @Override
    public List<Commande> getallCmds() {
        return commandeRepo.findAll();
    }

    @Override
    public Long getNumberOfCommandesWithTotalGreaterThan100() {
        float total = 100f;
        return commandeRepo.countByTotalGreaterThan(total);
    }


}


