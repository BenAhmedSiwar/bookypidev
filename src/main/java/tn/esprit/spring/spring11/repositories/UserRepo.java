package tn.esprit.spring.spring11.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import tn.esprit.spring.spring11.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
}
