package tn.esprit.spring.spring11.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.spring11.entities.Commande;
import tn.esprit.spring.spring11.entities.LignePannier;
import tn.esprit.spring.spring11.entities.Livre;
import tn.esprit.spring.spring11.entities.Pannier;
import tn.esprit.spring.spring11.interfaces.ILignePannierService;
import tn.esprit.spring.spring11.repositories.CommandeRepo;
import tn.esprit.spring.spring11.repositories.LignePannierRepo;
import tn.esprit.spring.spring11.repositories.LivreRepo;
import tn.esprit.spring.spring11.repositories.PannierRepo;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@Slf4j
public class LignePannierService implements ILignePannierService {
    @Autowired
    LignePannierRepo lignePannierRepo;

    @Autowired
    LivreRepo livreRepo;
    @Autowired
    CommandeRepo commandeRepo;

    @Autowired
    PannierRepo pannierRepo;

    @Override
    public LignePannier ajouterLignePannier(Long idLivre, Long idPannier, int quantite, float prix, String nomLivre) {
        Livre livre = livreRepo.findById(idLivre)
                .orElseThrow(() -> new EntityNotFoundException("Livre non trouvé avec l'ID : " + idLivre));

        Pannier pannier = pannierRepo.findById(idPannier)
                .orElseThrow(() -> new EntityNotFoundException("Panier non trouvé avec l'ID : " + idPannier));

        LignePannier lignePannier = new LignePannier();
        lignePannier.setLivre(livre);
        lignePannier.setPannier(pannier);
        lignePannier.setQuantite(quantite);
        lignePannier.setPrix(prix);
        lignePannier.setNomLivre(nomLivre); // ajout du nom du livre à la ligne de panier

        lignePannierRepo.save(lignePannier);
        return lignePannier;
    }

  /*  @Override
    public LignePannier ajouterLignePanierAvecInfosLivre(Long idLivre, Long idPanier, int quantite) {
        return null;
    }*/

    @Override
    public LignePannier ajouterLignePannierAvecInfosLivre(Long idLivre, Long idPannier, int quantite) {
        Livre livre = livreRepo.findById(idLivre)
                .orElseThrow(() -> new EntityNotFoundException("Livre non trouvé avec l'ID : " + idLivre));
        String nomDuLivre = livre.getNomLivre();
        float prixDuLivre = livre.getPrix();
        //int quantiteDuLivre = livre.getQuantite();

        // appel à la méthode ajouterLignePannier en passant les informations du livre en tant que paramètres
        return ajouterLignePannier(idLivre, idPannier, quantite, prixDuLivre, nomDuLivre);
    }

    @Override
    public List<LignePannier> retrieveAllLignePannierAssignedToPannier(Long idPannier) {
        Pannier pannier = pannierRepo.findById(idPannier)
                .orElseThrow(() -> new EntityNotFoundException("Pannier non trouvé avec l'ID : " + idPannier));

        List<LignePannier> lignePanniers = pannier.getLignePanniers();
        for (LignePannier lignePannier : lignePanniers) {
            Livre livre = lignePannier.getLivre();
            lignePannier.setNomLivre(livre.getNomLivre());
        }

        return lignePanniers;
    }

  /*  @Override
    public LignePannier ajouterLignePannier(Long idLivre, Long idPannier, Long idCommande, int quantite, float prix, String nomLivre) {
        Livre livre = livreRepo.findById(idLivre)
                .orElseThrow(() -> new EntityNotFoundException("Livre non trouvé avec l'ID : " + idLivre));

        Pannier pannier = pannierRepo.findById(idPannier)
                .orElseThrow(() -> new EntityNotFoundException("Panier non trouvé avec l'ID : " + idPannier));

        Commande commande = commandeRepo.findById(idCommande)
                .orElseThrow(() -> new EntityNotFoundException("Commande non trouvée avec l'ID : " + idCommande));

        LignePannier lignePannier = new LignePannier();
        lignePannier.setLivre(livre);
        lignePannier.setPannier(pannier);
        lignePannier.setQuantite(quantite);
        lignePannier.setPrix(prix);
        lignePannier.setNomLivre(nomLivre);
        lignePannier.setCommande(commande); // assignation de la commande à la ligne de panier

        lignePannierRepo.save(lignePannier);
        return lignePannier;
    }*/

    public LignePannier updateQuantite(Long idLignePannier, int nouvelleQuantite) {
        // Rechercher la LignePannier à modifier
        LignePannier lignePannier = lignePannierRepo.findById(idLignePannier)
                .orElseThrow(() -> new RuntimeException("LignePannier non trouvée"));

        // Modifier la quantité
        lignePannier.setQuantite(nouvelleQuantite);

        // Enregistrer les modifications
        return lignePannierRepo.save(lignePannier);
    }
    public void supprimerLignePannier(Long idLignePannier) {
        LignePannier lignePannier = lignePannierRepo.findById(idLignePannier).orElse(null);
        if (lignePannier != null) {
            lignePannierRepo.delete(lignePannier);
        } else {
            throw new EntityNotFoundException("La ligne de panier avec l'ID " + idLignePannier + " n'existe pas.");
        }

    }
}
