package com.app.mobile.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Amis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAmis")
    private int idAmis;

    // Le récepteur de la demande d'amis
    @ManyToOne
    @JoinColumn(name = "idUser1", nullable = false)
    private User user1;

    // L'envoyeur de la demande d'amis
    @ManyToOne
    @JoinColumn(name = "idUser2", nullable = false)
    private User user2;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dateAjout")
    private Date dateAjout;

    private Boolean valider;
    private Boolean statutAmis;
}
