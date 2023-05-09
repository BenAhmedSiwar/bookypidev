package tn.esprit.spring.spring11.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Commande  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idCommande;
    float total;
    String adresse;
    Date dateCreation;
    @JsonIgnore
    @ManyToOne
    User user;
    @JsonIgnore
    @OneToMany(mappedBy = "commande",cascade = CascadeType.ALL)
    List<LignePannier> lignesPannier;

    @OneToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name = "pannier_id")
    Pannier pannier;






}
