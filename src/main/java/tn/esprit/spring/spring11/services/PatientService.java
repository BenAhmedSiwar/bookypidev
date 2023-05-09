package tn.esprit.spring.spring11.services;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.spring.spring11.entities.Pathologie;
import tn.esprit.spring.spring11.entities.Patient;
import tn.esprit.spring.spring11.interfaces.IPatientService;
import tn.esprit.spring.spring11.repositories.PathRepo;
import tn.esprit.spring.spring11.repositories.PatientRepo;

/*@Service
@AllArgsConstructor
@Slf4j*/
/*public class PatientService implements IPatientService {
   /* @Override
    public Patient ajouterPatientEtAffecterAPathologie(Patient p, String codePath) {
        PatientRepo patientRepo;
        Pathologie pathologie= PathRepo.findByCodePath(codePath);
        p.getPathologies().add(pathologie);
        return  patientRepo.save(p);

    }
}*/

