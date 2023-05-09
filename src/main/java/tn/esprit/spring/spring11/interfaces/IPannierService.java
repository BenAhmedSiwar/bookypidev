package tn.esprit.spring.spring11.interfaces;

import tn.esprit.spring.spring11.entities.Livre;
import tn.esprit.spring.spring11.entities.Pannier;

public interface IPannierService {
    Pannier addPannierAndAssignToUser(Pannier pannier, Long idUser);
}
