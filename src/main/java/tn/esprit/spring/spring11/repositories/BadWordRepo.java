package tn.esprit.spring.spring11.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.spring11.entities.BadWord;

public interface BadWordRepo extends JpaRepository<BadWord, Long> {
}
