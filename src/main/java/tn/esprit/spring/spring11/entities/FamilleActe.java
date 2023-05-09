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
public class FamilleActe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idFa;
    String codeFa;
    String libelle;
    String description;

    @OneToMany(mappedBy = "familleActe")
    @JsonIgnore
    List<Acte> acteList;

}
