package com.app.mobile.service;

import com.app.mobile.dto.PublicationDto;
import com.app.mobile.dto.UserPubDto;
import com.app.mobile.model.Publication;
import com.app.mobile.model.User;
import com.app.mobile.repository.PublicationRepository;
import com.app.mobile.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class PublicationServiceImpl implements PublicationService{
    @Autowired
    private PublicationRepository publicationRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Map<String, Object>> getPubRecherche(String search) {
        return publicationRepository.findSearchPublications(search);
    }

    @Override
    public List<Map<String, Object>> getPubRechercheEntreDeuxDates(Date start, Date end) {
        return publicationRepository.findSearchPublicationsBetweenTwoDates(start, end);
    }

    @Override
    public List<Map<String, Object>> getPubUser(int idUser) {
        if(idUser <= 0){
            throw new RuntimeException("L'idUser "+idUser+" n'existe pas");
        }
        return publicationRepository.findUserPublications(idUser);
    }

    @Override
    public List<Map<String, Object>> getPubAmisUser(int idUser) {
        if(idUser <= 0){
            throw new RuntimeException("L'idUser "+idUser+" n'existe pas");
        }
        return publicationRepository.findAmisPublications(idUser);
    }

    @Override
    public String deletePub(int id) {
        publicationRepository.deleteById(id);
        return "Publication supprimée";
    }

    @Override
    public Publication updateStatut(int id) {
        Publication pub = publicationRepository.findById(id).orElseThrow(()-> new RuntimeException("Publication non trouvée"));
        pub.setStatutPub(true);
        return publicationRepository.save(pub);
    }

    @Override
    public Publication updatePub(Publication publication) {
        return publicationRepository.findById(publication.getIdPub())
                .map(p-> {
                    p.setTexte(publication.getTexte());
                    p.setFichier(publication.getFichier());
                    p.setDatePub(publication.getDatePub());
                    return publicationRepository.save(p);
                }).orElseThrow(()-> new RuntimeException("Publication introuvable"));
    }

    @Override
    public List<Publication> getAllPub() {
        return publicationRepository.findAll();
    }

    @Override
    public Publication insertPub(PublicationDto publicationDto) {
        User user = userRepository.findById(publicationDto.getIdUser()).orElseThrow(()-> new RuntimeException("Utilisateur non trouvé"));

        Publication pub = new Publication();
        pub.setUserPub(user);
        pub.setTexte(publicationDto.getTexte());
        pub.setFichier(publicationDto.getFichier());
        pub.setDatePub(publicationDto.getDatePub());
        pub.setStatutPub(false);
        return pub;
    }
}
