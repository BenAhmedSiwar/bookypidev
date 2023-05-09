package tn.esprit.spring.spring11.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.spring.spring11.entities.Commande;
import tn.esprit.spring.spring11.entities.LignePannier;

public interface CommandeRepo extends JpaRepository<Commande,Long> {
    @Query("SELECT COUNT(c) FROM Commande c WHERE c.total > :total")
    Long countByTotalGreaterThan(@Param("total") float total);
}
