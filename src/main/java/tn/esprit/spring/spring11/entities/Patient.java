package tn.esprit.spring.spring11.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Patient implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idPatient;
    @Enumerated(EnumType.STRING)
    TypePieceIdentite typePieceIdentite;
    String identifiantPieceIdentite;
    Date dateEmission;
    String nomP;
    String prenomP;

    @ManyToMany(cascade = CascadeType.ALL)
    public Set<Pathologie> Pathologies;


}
