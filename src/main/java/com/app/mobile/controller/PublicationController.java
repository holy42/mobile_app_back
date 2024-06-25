package com.app.mobile.controller;

import com.app.mobile.dto.PublicationDto;
import com.app.mobile.dto.UserPubDto;
import com.app.mobile.model.Publication;
import com.app.mobile.service.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/publication")
public class PublicationController {

    @Autowired
    private PublicationService publicationService;

    // ----- Publications recherchées par la description
    @GetMapping("/recherche/{search}")
    public ResponseEntity<List<Map<String, Object>>> getPubSearch(@PathVariable(value = "search") String search){
        return ResponseEntity.ok(publicationService.getPubRecherche(search));
    }

    @GetMapping("/recherche_entre_deux_dates/{start}/{end}")
    public ResponseEntity<List<Map<String, Object>>> getPubSearchTwoDates(@PathVariable(value = "start") Date start, @PathVariable(value = "end") Date end){
        return ResponseEntity.ok(publicationService.getPubRechercheEntreDeuxDates(start, end));
    }

    // ----- Publications de l'user idUser -----
    @GetMapping("/par_user/{idUser}")
    public ResponseEntity<List<Map<String, Object>>> getPubParUser(@PathVariable(value = "idUser") int id){
        return ResponseEntity.ok(publicationService.getPubUser(id));
    }

    // ----- Publications des amis de l'user idUser -----
    @GetMapping("/par_amis_user/{idUser}")
    public ResponseEntity<List<Map<String, Object>>> getPubAmisParUser(@PathVariable(value = "idUser") int id){
        return ResponseEntity.ok(publicationService.getPubAmisUser(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Publication>> getAllPub(){
        return ResponseEntity.ok(publicationService.getAllPub());
    }

    @PostMapping("/insert")
    public ResponseEntity<String> add(@RequestBody PublicationDto publicationDto){
        publicationService.insertPub(publicationDto);
        return ResponseEntity.ok("Publication ajouté");
    }

    @PutMapping("/update")
    public ResponseEntity<String> edit(@RequestBody Publication publication) {
        publicationService.updatePub(publication);
        return ResponseEntity.ok("Publication modifiée");
    }

    @PutMapping("/updateStatut/{idPub}")
    public ResponseEntity<String> editStatut(@PathVariable(value = "idPub") int idPub) {
        publicationService.updateStatut(idPub);
        return ResponseEntity.ok("Statut Update");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") int id) {
        publicationService.deletePub(id);
        return ResponseEntity.ok("Publication supprimée");
    }
}
