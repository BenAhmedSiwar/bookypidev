package tn.esprit.spring.spring11.interfaces;

import tn.esprit.spring.spring11.entities.Pathologie;

public interface IPathologieService {

    public Pathologie ajouterPathologie(Pathologie path);
    public void calculerNombreActesParPathologie();
}

