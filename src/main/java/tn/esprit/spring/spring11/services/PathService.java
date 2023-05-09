package tn.esprit.spring.spring11.services;

import org.springframework.stereotype.Service;
import tn.esprit.spring.spring11.entities.Pathologie;
import tn.esprit.spring.spring11.interfaces.IPathologieService;
import tn.esprit.spring.spring11.repositories.PathRepo;

@Service
public class PathService implements IPathologieService  {
    PathRepo  pathRepo;
    @Override
    public Pathologie ajouterPathologie(Pathologie path) {

        return pathRepo.save(path);
    }

    @Override
    public void calculerNombreActesParPathologie() {

    }
}
