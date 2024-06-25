package com.app.mobile.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPubDto {
    private int idUser;
    private int idPub;
    private String pseudo;
    private byte[] imgUser;
    private String texte;
    private byte[] fichier;
    private Date datePub;
}
