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
public class Reaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idReaction;

    @ManyToOne
    @JoinColumn(name = "idPub", nullable = false)
    private Publication pub;

    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false)
    private User userReaction;

    private boolean reagi;

    @Column(columnDefinition = "TEXT")
    private String commentaire;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dateReaction")
    private Date dateReaction;

    private boolean statutReaction;
}
