package tn.esprit.spring.spring11.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.spring11.entities.Pathologie;
import tn.esprit.spring.spring11.entities.Patient;

@Repository
public interface PatientRepo extends JpaRepository<Patient,Long> {
}
