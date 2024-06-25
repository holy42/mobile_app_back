package com.app.mobile.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Groupe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int groupe;

    private  String nomGroupe;
    private int createur;

    @OneToMany(mappedBy = "groupe", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Message> message;

    @ManyToMany
    @JoinTable(
            name = "membre",
            joinColumns = @JoinColumn(name = "idGroupe"),
            inverseJoinColumns = @JoinColumn(name = "idUser")
    )
    private Set<User> userMembre;

    public Groupe(int groupe, String nomGroupe, int createur) {
        this.groupe = groupe;
        this.nomGroupe = nomGroupe;
        this.createur = createur;
    }
}
