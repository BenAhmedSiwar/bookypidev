package tn.esprit.spring.spring11.interfaces;

import tn.esprit.spring.spring11.entities.Livre;

import java.util.List;

public interface ILivreService {
    Livre addLivreAndAssignToUser(Livre livre, Long idUser);
    public List<Livre> getAllLivres();
}
