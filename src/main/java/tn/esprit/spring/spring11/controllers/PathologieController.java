package tn.esprit.spring.spring11.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.spring.spring11.entities.Pathologie;
import tn.esprit.spring.spring11.interfaces.IPathologieService;

@RestController
@RequestMapping("/Pathologie")
@AllArgsConstructor
public class PathologieController {
    IPathologieService iPathologieService;
    @PostMapping("/addPath")
    public Pathologie ajouterPathologie(@RequestBody Pathologie path)
    {
return iPathologieService.ajouterPathologie(path);
    }

}
