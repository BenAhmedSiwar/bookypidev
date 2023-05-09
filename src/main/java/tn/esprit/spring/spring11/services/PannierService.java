package tn.esprit.spring.spring11.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.spring.spring11.entities.Pannier;
import tn.esprit.spring.spring11.entities.User;
import tn.esprit.spring.spring11.interfaces.IPannierService;
import tn.esprit.spring.spring11.repositories.PannierRepo;
import tn.esprit.spring.spring11.repositories.UserRepo;

@AllArgsConstructor
@Service
@Slf4j
public class PannierService implements IPannierService {
    PannierRepo pannierRepo;
    UserRepo userRepo;

    @Override
    public Pannier addPannierAndAssignToUser(Pannier pannier, Long idUser) {
        User user = userRepo.findById(idUser).orElse(null);
        pannier.setUser(user);
        pannierRepo.save(pannier);
        return pannier;

    }
}
