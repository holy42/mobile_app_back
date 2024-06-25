package com.app.mobile.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUser")
    private int idUser;

    @Column(length = 50)
    private String pseudo;

    @Column(length = 50)
    private String email;

    @Column(length = 20)
    private String contact;

    @Column(length = 50)
    private String mdp;

    @Column(name = "statutUser")
    private boolean statutUser;

    @Column(name = "imgUser", columnDefinition = "LONGBLOB")
    private byte[] imgUser;

    // Le récepteur de la demande d'amis
    @OneToMany(mappedBy = "user1", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Amis> user1 = new HashSet<>();

    // L'envoyeur de la demande d'amis
    @OneToMany(mappedBy = "user2", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Amis> user2 = new HashSet<>();

    @OneToMany(mappedBy = "userPub", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Publication> publication;

    @OneToMany(mappedBy = "userReaction", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Reaction> userReaction;

    @OneToMany(mappedBy = "userMessage", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Message> userMessage;

    @ManyToMany(mappedBy = "userMembre", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Groupe> groupes;

    public User() {
    }

    public User(int idUser, String pseudo, String email, String contact, String mdp, boolean statutUser, byte[] imgUser) {
        this.idUser = idUser;
        this.pseudo = pseudo;
        this.email = email;
        this.contact = contact;
        this.mdp = mdp;
        this.statutUser = statutUser;
        this.imgUser = imgUser;
    }

}
