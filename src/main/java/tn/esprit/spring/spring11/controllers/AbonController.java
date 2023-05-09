package tn.esprit.spring.spring11.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.spring.spring11.entities.Abonnement;
import tn.esprit.spring.spring11.interfaces.IAbonService;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/abon")
@AllArgsConstructor
public class AbonController {

}
