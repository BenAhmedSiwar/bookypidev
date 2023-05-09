package tn.esprit.spring.spring11.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.spring11.entities.Livre;
import tn.esprit.spring.spring11.entities.User;
import tn.esprit.spring.spring11.interfaces.ILivreService;
import tn.esprit.spring.spring11.repositories.LivreRepo;
import tn.esprit.spring.spring11.repositories.UserRepo;

import java.util.List;

@AllArgsConstructor
@Service
@Slf4j
public class LivreService implements ILivreService {
    LivreRepo livreRepo;
    UserRepo userRepo;
    @Override
    public Livre addLivreAndAssignToUser(Livre livre, Long idUser) {
    User user=userRepo.findById(idUser).orElse(null);
    livre.setIdUser(user);
    livreRepo.save(livre);
        return livre;
    }

    @Override
    @Autowired
    public List<Livre> getAllLivres() {
        return livreRepo.findAll();
    }
}
