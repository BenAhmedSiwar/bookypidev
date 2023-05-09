package tn.esprit.spring.spring11.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.spring.spring11.interfaces.IUserService;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User  implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idUser;
    String username;
    String mail;
    String Pass;
    @JsonIgnore
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    Pannier pannier;

    @JsonIgnore
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    List<Commande> commandes;

}
