package tn.esprit.spring.spring11.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.spring.spring11.entities.Pathologie;
import tn.esprit.spring.spring11.entities.User;
import tn.esprit.spring.spring11.interfaces.IPathologieService;
import tn.esprit.spring.spring11.interfaces.IUserService;

@RestController
@RequestMapping("/User")
@AllArgsConstructor
public class UserController {
    IUserService iUserService;
    @PostMapping("/addUser")
    public User ajouterUser(@RequestBody User user)
    {
        return iUserService.ajouterUser(user);
    }

}
