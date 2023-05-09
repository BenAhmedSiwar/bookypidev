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
public class Acte implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idActe;
    String codeActe;
    int cotationActe;
    float prixUnitaireActe;
    String designiationActe;


    @ManyToOne
    @JsonIgnore
    FamilleActe familleActe ;


    @ManyToMany (mappedBy = "acteList")

    List<Pathologie> pathologieList;

}
