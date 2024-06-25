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
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMessage;

    @Column(name = "messageTexte", columnDefinition = "TEXT")
    private String messageTexte;

    @Column(name = "messageFichier", columnDefinition = "LONGBLOB")
    private byte[] messageFichier;

    private boolean statutMessage;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateMessage;

    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false)
    private User userMessage;

    @ManyToOne
    @JoinColumn(name = "idGroupe", nullable = false)
    private Groupe groupe;

}
