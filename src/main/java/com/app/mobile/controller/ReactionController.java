package com.app.mobile.controller;

import com.app.mobile.dto.ReactionDto;
import com.app.mobile.model.Reaction;
import com.app.mobile.service.ReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reaction")
public class ReactionController {

    @Autowired
    private ReactionService reactionService;

    // ----- La liste des users qui ont réagi à une publication idPub -----
    @GetMapping("/par_publication/{idPub}")
    public ResponseEntity<List<Map<String, Object>>> getPubReagi(@PathVariable(value = "idPub") int idPub){
        return ResponseEntity.ok(reactionService.getListUserReagi(idPub));
    }

    // ----- La liste des users qui ont commenté à une publication idPub -----
    @GetMapping("/commentaire_par_publication/{idPub}")
    public ResponseEntity<List<Map<String, Object>>> getPubCommentaire(@PathVariable(value = "idPub") int idPub){
        return ResponseEntity.ok(reactionService.getListUserCommentaire(idPub));
    }

    // ----- Notification users qui ont réagi à des publications de l'user idUser ----
    @GetMapping("/notification_reagi/{idUser}")
    public ResponseEntity<List<Map<String, Object>>> getNotifReagi(@PathVariable(value = "idUser") int idUser){
        return ResponseEntity.ok(reactionService.getListNotifReagi(idUser));
    }

    // ----- Notification des users qui ont commenté à des publications de l'user idUser ----
    @GetMapping("/notification_commentaire/{idUser}")
    public ResponseEntity<List<Map<String, Object>>> getNotifCommentaire(@PathVariable(value = "idUser") int idUser){
        return ResponseEntity.ok(reactionService.getListNotifCommentaire(idUser));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Reaction>> getAllReaction(){ return ResponseEntity.ok(reactionService.getAllReaction()); }

    @PostMapping("/insert")
    public ResponseEntity<String> add(@RequestBody ReactionDto reaction){
        reactionService.insertCommentaire(reaction);
        return ResponseEntity.ok("Réaction ajoutée");
    }

    @PutMapping("/update")
    public ResponseEntity<String> edit(@RequestBody Reaction reaction){
        reactionService.updateReaction(reaction);
        return ResponseEntity.ok("Raéction modifiée");
    }

    @PutMapping("/updateStatut/{id}")
    public ResponseEntity<String> editStatut(@PathVariable(value = "id") int id){
        reactionService.updateStatut(id);
        return ResponseEntity.ok("Statut réaction modifié");
    }

    @PutMapping("/updateReagi/{id}")
    public ResponseEntity<String> editReagi(@PathVariable(value = "id") int id){
        reactionService.updateReagi(id);
        return ResponseEntity.ok("Réaction ajoutée");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") int id){
        reactionService.deleteReaction(id);
        return ResponseEntity.ok("Réaction supprimée");
    }
}
