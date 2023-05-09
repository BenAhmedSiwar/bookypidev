package tn.esprit.spring.spring11.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.spring11.entities.Livre;
import tn.esprit.spring.spring11.entities.Pannier;
import tn.esprit.spring.spring11.interfaces.ILivreService;
import tn.esprit.spring.spring11.interfaces.IPannierService;
import tn.esprit.spring.spring11.interfaces.IUserService;

import java.util.List;

@RestController
@RequestMapping("/pannier")
@AllArgsConstructor
public class PannierController {
    IUserService iUserService;
    IPannierService iPannierService;
    @PostMapping("/addPannierAndAssignToUser/{idUser}")
    public Pannier addPannierAndAssignToUser(@RequestBody Pannier pannier, @PathVariable("idUser") Long idUser)
    {
        return iPannierService.addPannierAndAssignToUser(pannier,idUser);
    }

}
