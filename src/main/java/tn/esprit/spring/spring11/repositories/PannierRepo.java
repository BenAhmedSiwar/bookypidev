package tn.esprit.spring.spring11.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.spring11.entities.Livre;
import tn.esprit.spring.spring11.entities.Pannier;

@Repository
public interface PannierRepo extends JpaRepository<Pannier,Long> {
}
