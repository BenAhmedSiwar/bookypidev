package tn.esprit.spring.spring11.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity


    public class Notification implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        int notifId;
        @Column(length = 1000)
        String NotifDescription;

        @Temporal(TemporalType.DATE)
        Date notifDate;
        @ManyToOne
        User transmitter;
        @ManyToOne
        User receiver ;



}
