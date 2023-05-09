package tn.esprit.spring.spring11.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.spring.spring11.entities.Commande;
import tn.esprit.spring.spring11.entities.User;
import tn.esprit.spring.spring11.interfaces.IUserService;
import tn.esprit.spring.spring11.repositories.UserRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@AllArgsConstructor
@Service
@Slf4j
public class UserService implements IUserService {
    @PersistenceContext
    private EntityManager entityManager;

    UserRepo userRepo;
    @Override

    public User ajouterUser(User user) {
        return userRepo.save(user);

    }


}
