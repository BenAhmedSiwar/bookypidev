package tn.esprit.spring.spring11.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.spring11.entities.LignePannier;
import tn.esprit.spring.spring11.interfaces.ILignePannierService;
import tn.esprit.spring.spring11.interfaces.ILivreService;
import tn.esprit.spring.spring11.interfaces.IPannierService;
import tn.esprit.spring.spring11.interfaces.IUserService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/LignePannier")
@CrossOrigin(origins ="*")
@AllArgsConstructor
public class LignePannierController {
   /* IUserService iUserService;
    IPannierService iPannierService;
    ILivreService iLivreService;
    @PostMapping("/addLignePannieravecinfolivre/{idUser}")
   public LignePannier ajouterLignePannierAvecInfosLivre(Long idLivre, @PathVariable Long idPannier, int quantite, float prix, String nomLivre) {

*/
    IUserService  iUserService;
    ILivreService  iLivreService;
    IPannierService   iPannierService;
    ILignePannierService iLignePannierService;

    @PostMapping("/addLignePannieravecinfolivre/{idLivre}/{idPannier}/{quantite}")
    public LignePannier ajouterLignePannierAvecInfosLivre (@PathVariable ("idLivre") Long idLivre,@PathVariable ("idPannier") Long idPannier,@PathVariable ("quantite") int quantite)
    {
        return iLignePannierService.ajouterLignePannierAvecInfosLivre(idLivre,idPannier,quantite);
    }

    @GetMapping("/retrieveAllLignePannierAssignedToPannier/{idPannier}")
    public List<LignePannier> retrieveAllLignePannierAssignedToPannier(@PathVariable Long idPannier) {
        List<LignePannier> lignesPannier = iLignePannierService.retrieveAllLignePannierAssignedToPannier(idPannier);
        return lignesPannier;
    }
    @PutMapping("UpdateQuantité/{idLignePannier}/quantite")
    public LignePannier updateQuantite(@PathVariable Long idLignePannier, @RequestParam int nouvelleQuantite){
       // LignePannier lignePannier = iLignePannierService.updateQuantite(idLignePannier, nouvelleQuantite);

        return iLignePannierService.updateQuantite(idLignePannier, nouvelleQuantite);
    }
    @DeleteMapping("SupprimerLignePannier/{idLignePannier}")
    public ResponseEntity<String> supprimerLignePannier(@PathVariable Long idLignePannier) {
        try {
            iLignePannierService.supprimerLignePannier(idLignePannier);
            return ResponseEntity.ok("Ligne de panier supprimée avec succès.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur est survenue lors de la suppression de la ligne de panier.");
        }
    }


}
