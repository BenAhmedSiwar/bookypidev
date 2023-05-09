package tn.esprit.spring.spring11.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.spring11.entities.Abonnement;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public interface AbonRepo extends JpaRepository<Abonnement,Long> {

}
