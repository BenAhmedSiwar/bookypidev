package tn.esprit.spring.spring11.interfaces;

import tn.esprit.spring.spring11.entities.Commande;
import tn.esprit.spring.spring11.entities.LignePannier;

import java.util.List;

public interface ILignePannierService {
    public LignePannier ajouterLignePannier(Long idLivre, Long idPannier, int quantite, float prix, String nomLivre);
    //public LignePannier ajouterLignePanierAvecInfosLivre(Long idLivre, Long idPanier, int quantite);
    public LignePannier ajouterLignePannierAvecInfosLivre (Long idLivre, Long idPannier, int quantite);

    List<LignePannier> retrieveAllLignePannierAssignedToPannier(Long idPannier);

    //LignePannier ajouterLignePannier(Long idLivre, Long idPannier, Long idCommande, int quantite, float prix, String nomLivre);

    public LignePannier updateQuantite(Long idLignePannier, int nouvelleQuantite);
    //public LignePannier ModifierQuantiteLignePannier(Long idLivre, Long idPannier, int quantite, float prix, String nomLivre);

    public void supprimerLignePannier(Long idLignePannier);
}
