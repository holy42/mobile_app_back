package com.app.mobile.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ReactionDto {
    private int idPub;
    private int idUser;
    private String commentaire;
    private Date dateReaction;
}
