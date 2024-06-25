package com.app.mobile.model;

import com.app.mobile.dto.UserPubDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int idPub;

    @Column(columnDefinition = "TEXT")
    private String texte;

    @Column(columnDefinition = "LONGBLOB")
    private byte[] fichier;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "datePub")
    private Date datePub;

    private boolean statutPub;

    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false)
    private User userPub;

    @OneToMany(mappedBy = "pub", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Reaction> reaction;

    public Publication(int idPub, String texte, byte[] fichier, Date datePub, boolean statutPub, User userPub) {
        this.idPub = idPub;
        this.texte = texte;
        this.fichier = fichier;
        this.datePub = datePub;
        this.statutPub = statutPub;
        this.userPub = userPub;
    }
}
