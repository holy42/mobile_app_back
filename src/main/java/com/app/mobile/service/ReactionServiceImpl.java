package com.app.mobile.service;

import com.app.mobile.dto.ReactionDto;
import com.app.mobile.model.Publication;
import com.app.mobile.model.Reaction;
import com.app.mobile.model.User;
import com.app.mobile.repository.PublicationRepository;
import com.app.mobile.repository.ReactionRepository;
import com.app.mobile.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReactionServiceImpl implements ReactionService {
    @Autowired
    private ReactionRepository reactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PublicationRepository publicationRepository;

    @Override
    public List<Map<String, Object>> getListUserReagi(int id) {
        if(id <= 0){
            throw new RuntimeException("L'idPub "+id+" n'existe pas");
        }
        return reactionRepository.findUserWhoReacted(id);
    }

    @Override
    public List<Map<String, Object>> getListUserCommentaire(int id) {
        if(id <= 0){
            throw new RuntimeException("L'idPub "+id+" n'existe pas");
        }
        return reactionRepository.findUserWhoCommented(id);
    }

    @Override
    public List<Map<String, Object>> getListNotifReagi(int id) {
        if(id <= 0){
            throw new RuntimeException("L'idUser "+id+" n'existe pas");
        }
        return reactionRepository.findNewReagi(id);
    }

    @Override
    public List<Map<String, Object>> getListNotifCommentaire(int id) {
        if(id <= 0){
            throw new RuntimeException("L'idUser "+id+" n'existe pas");
        }
        return reactionRepository.findNewCommentaire(id);
    }

    @Override
    public Reaction insertCommentaire(ReactionDto reactionDto) {
        User user = userRepository.findById(reactionDto.getIdUser()).orElseThrow(()-> new RuntimeException("Utilisateur introuvable"));
        Publication publication = publicationRepository.findById(reactionDto.getIdPub()).orElseThrow(()-> new RuntimeException("Publication non trouvée"));

        Reaction reaction = new Reaction();
        reaction.setUserReaction(user);
        reaction.setPub(publication);
        reaction.setCommentaire(reaction.getCommentaire());
        reaction.setDateReaction(reactionDto.getDateReaction());
        reaction.setStatutReaction(false);
        return reactionRepository.save(reaction);
    }

    @Override
    public List<Reaction> getAllReaction() {
        return reactionRepository.findAll();
    }

    @Override
    public Reaction updateStatut(int id) {
        Reaction reaction = reactionRepository.findById(id).orElseThrow(()-> new RuntimeException("Reaction introuvable"));
        reaction.setStatutReaction(true);
        return reactionRepository.save(reaction);
    }

    @Override
    public Reaction updateReaction(Reaction reaction) {
        return reactionRepository.findById(reaction.getIdReaction())
                .map(r-> {
                    r.setCommentaire(reaction.getCommentaire());
                    r.setDateReaction(reaction.getDateReaction());
                    return reactionRepository.save(r);
                }).orElseThrow(()-> new RuntimeException("Réaction introuvable"));
    }

    @Override
    public Reaction updateReagi(int id) {
        Reaction reaction = reactionRepository.findById(id).orElseThrow(()-> new RuntimeException("Reaction non trouvé"));
        reaction.setReagi(true);
        return reactionRepository.save(reaction);
    }

    @Override
    public String deleteReaction(int id) {
        if (id == 0){
            throw new RuntimeException("l'idPub "+ id + " n'existe pas");
        }
        reactionRepository.deleteById(id);
        return "Reaction supprimé";
    }
}
