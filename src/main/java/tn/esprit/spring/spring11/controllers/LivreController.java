package tn.esprit.spring.spring11.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.spring11.entities.Livre;
import tn.esprit.spring.spring11.interfaces.ILivreService;
import tn.esprit.spring.spring11.interfaces.IUserService;

import java.util.List;
@CrossOrigin(origins="*")
@RestController
@RequestMapping("/Livre")
@AllArgsConstructor

public class LivreController {

    IUserService iUserService;
    ILivreService iLivreService;
    @PostMapping("/addLivreAndAssignToUser/{idUser}")
    public Livre addLivreAndAssignToUser(@RequestBody Livre livre,@PathVariable ("idUser") Long idUser)
    {
        return iLivreService.addLivreAndAssignToUser(livre,idUser);
    }
    @GetMapping("/getAllLivres")
    public List<Livre> getAllLivres() {
        return iLivreService.getAllLivres();
    }
}
