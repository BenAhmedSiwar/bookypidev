package tn.esprit.spring.spring11.interfaces;

import tn.esprit.spring.spring11.entities.Commande;
import tn.esprit.spring.spring11.entities.LignePannier;

import javax.mail.MessagingException;
import java.util.List;

public interface ICommandeService {
    public void passerCommande(Commande commande);

    public Commande addCommande(Long idPannier, String adresse) throws MessagingException;

    public Commande addCommandee(Commande commande);
    List<Commande> retrieveAllCommande(Long idCommande);
    List<Commande> getAllCommande();

    //LignePannier ajouterLignePannier(Long idLivre, Long idPannier, Long idCommande, int quantite, float prix, String nomLivre);

    public Commande UpdateAdresseCommande(Long idCommande, String adresse );
    //public LignePannier ModifierQuantiteLignePannier(Long idLivre, Long idPannier, int quantite, float prix, String nomLivre);

    public void supprimerCommande(Long idCommande);

    public List<Commande> retrieveAllCommandeByUser(Long idUser);
   // public List<Commande> getCommandes(Long idUser);
   public List<Commande> getCommandeByIdUser(Long idUser);
    public List<Commande> getCmdbyidUser(Long idUser);

    public List<Commande> getallCmds();
    public Long getNumberOfCommandesWithTotalGreaterThan100();

}
