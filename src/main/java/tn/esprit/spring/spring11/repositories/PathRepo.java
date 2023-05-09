package tn.esprit.spring.spring11.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.spring11.entities.Abonnement;
import tn.esprit.spring.spring11.entities.Pathologie;

@Repository
public interface PathRepo extends JpaRepository<Pathologie,Long> {

    static Pathologie findByCodePath(String codePath) {

        return null;
    }
     //default void calculerNombreActesParPathologie(){

    //}
}
