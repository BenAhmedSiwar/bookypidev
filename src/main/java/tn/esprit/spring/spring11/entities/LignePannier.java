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
public class LignePannier  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idLignePannier;
    String nomLivre;
    int quantite;
    float prix;
    @JsonIgnore
    @ManyToOne
    Livre livre;
    @JsonIgnore
    @ManyToOne
    Pannier pannier;
    @JsonIgnore
    @ManyToOne
    Commande commande;


}
