package tn.esprit.spring.spring11.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Pannier  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idPannier;



    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    User user;
    @JsonIgnore
    @OneToMany(mappedBy = "pannier", cascade = CascadeType.ALL,orphanRemoval = true)
    List<LignePannier> lignePanniers;



    @OneToOne(mappedBy = "pannier")
    Commande commande;



    /*@OneToMany(mappedBy = "pannier", cascade = CascadeType.ALL)
    Commande commande;*/

    /*@OneToOne
    @JsonIgnore
    @JoinColumn(name = "commande_id")
    Commande  commande;*/



}
