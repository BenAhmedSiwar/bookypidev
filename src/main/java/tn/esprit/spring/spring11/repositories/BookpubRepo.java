package tn.esprit.spring.spring11.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.spring11.entities.BookPublication;

@Repository
public interface BookpubRepo extends JpaRepository<BookPublication, Long>, JpaSpecificationExecutor<BookPublication> {
}