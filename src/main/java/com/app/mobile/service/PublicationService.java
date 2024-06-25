package com.app.mobile.service;

import com.app.mobile.dto.PublicationDto;
import com.app.mobile.dto.UserPubDto;
import com.app.mobile.model.Publication;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface PublicationService {
    public Publication insertPub(PublicationDto publicationDto);
    public List<Publication> getAllPub();
    public Publication updatePub(Publication publication);
    public Publication updateStatut(int id);
    public String deletePub(int id);
    public List<Map<String, Object>> getPubUser(int idUser);
    public List<Map<String, Object>> getPubAmisUser(int idUser);
    public List<Map<String, Object>> getPubRecherche(String search);
    public List<Map<String, Object>> getPubRechercheEntreDeuxDates(Date start, Date end);
}
