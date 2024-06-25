package com.app.mobile.service;

import com.app.mobile.dto.ReactionDto;
import com.app.mobile.model.Reaction;

import java.util.List;
import java.util.Map;

public interface ReactionService {
    public Reaction insertCommentaire(ReactionDto reactionDto);
    public Reaction updateReagi(int id);
    public List<Reaction> getAllReaction();
    public Reaction updateReaction(Reaction reaction);
    public Reaction updateStatut(int id);
    public String deleteReaction(int id);
    public List<Map<String, Object>> getListUserReagi(int id);
    public List<Map<String, Object>> getListUserCommentaire(int id);
    public List<Map<String, Object>> getListNotifReagi(int id);
    public List<Map<String, Object>> getListNotifCommentaire(int id);
}
