package tn.esprit.spring.spring11.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Livre  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idLivre;
    String nomLivre;
    float prix;
    int quantite;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idUser")
    public User idUser;

}
