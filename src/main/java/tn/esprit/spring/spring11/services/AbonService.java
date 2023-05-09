package tn.esprit.spring.spring11.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.spring.spring11.entities.Abonnement;
import tn.esprit.spring.spring11.interfaces.IAbonService;
import tn.esprit.spring.spring11.repositories.AbonRepo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;
@Service
@AllArgsConstructor
@Slf4j


public class AbonService implements IAbonService {


}
