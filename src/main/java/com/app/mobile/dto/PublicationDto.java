package com.app.mobile.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PublicationDto {
    private int idUser;
    private String texte;
    private byte[] fichier;
    private Date datePub;
}
