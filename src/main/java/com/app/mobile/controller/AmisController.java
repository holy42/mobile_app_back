package com.app.mobile.controller;

import com.app.mobile.dto.AmisDto;
import com.app.mobile.model.Amis;
import com.app.mobile.service.AmisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/amis")
public class AmisController {
    @Autowired
    private AmisService amisService;

    // ----- La liste des amis de l'user idUser -----
    @GetMapping("/par_user/{idUser}")
    public ResponseEntity<List<Map<String, Object>>> getAmisParUser(@PathVariable(value = "idUser") int idUser) {
        return ResponseEntity.ok(amisService.getAmisUser(idUser));
    }

    // ----- Amis en commun entre les users idUser1 et idUser2 -----
    @GetMapping("/en_commun/{idUser1}/{idUser2}")
    public ResponseEntity<List<Map<String, Object>>> getAmisCommun(@PathVariable(value = "idUser1") int idUser1, @PathVariable(value = "idUser2") int idUser2) {
        return ResponseEntity.ok(amisService.getAmisCommun(idUser1, idUser2));
    }

    // ----- La liste des demandes d'amis de l'user idUser -----
    @GetMapping("/demande/{idUser}")
    public ResponseEntity<List<Map<String, Object>>> getAmisDemande(@PathVariable(value = "idUser") int idUser) {
        return ResponseEntity.ok(amisService.getAmisDemande(idUser));
    }

    // ----- La liste des nouvelles demandes validées que l'user idUser a envoyé -----
    @GetMapping("/nouveau/{idUser}")
    public ResponseEntity<List<Map<String, Object>>> getAmisNouveauNotif(@PathVariable(value = "idUser") int idUser) {
        return ResponseEntity.ok(amisService.getNotifAmisValider(idUser));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Amis>> getAllAmis() {
        return ResponseEntity.ok(amisService.getAllAmis());
    }

    @PostMapping("/insert")
    public ResponseEntity<String> add(@RequestBody AmisDto amisDto){
        amisService.insertAmis(amisDto);
        return ResponseEntity.ok("Ami enregistré");
    }

    @PutMapping("/updateValider/{id}")
    public ResponseEntity<String> editValider(@PathVariable(value = "id") int id ) {
        amisService.updateValiderAmis(id);
        return ResponseEntity.ok("Demande acceptée");
    }

    @PutMapping("/updateStatut/{id}")
    public ResponseEntity<String> editStatut(@PathVariable(value = "id") int id ) {
        amisService.updateStatutAmis(id);
        return ResponseEntity.ok("Statut modifié");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") int id){
        amisService.deleteAmis(id);
        return ResponseEntity.ok("Amis supprimé");
    }
}
