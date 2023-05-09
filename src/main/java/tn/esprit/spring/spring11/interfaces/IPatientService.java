package tn.esprit.spring.spring11.interfaces;

import tn.esprit.spring.spring11.entities.Patient;

public interface IPatientService {
    public Patient ajouterPatientEtAffecterAPathologie(Patient p, String codePath);
}
