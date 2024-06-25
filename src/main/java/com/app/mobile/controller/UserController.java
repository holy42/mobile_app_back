package com.app.mobile.controller;

import com.app.mobile.model.User;
import com.app.mobile.dto.UserDto;
import com.app.mobile.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/recherche/{search}")
    public ResponseEntity<List<User>> getSearch(@PathVariable(value = "search") String search){
        return ResponseEntity.ok(userService.getUserSearch(search));
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUser(){
        return ResponseEntity.ok(userService.getAllUser());
    }

    @PostMapping("/insert")
    public ResponseEntity<String> add(@RequestBody UserDto user){
        userService.insertUser(user);
        return ResponseEntity.ok("Ajout user succès");
    }

    @PutMapping("/update")
    public ResponseEntity<String> edit(@RequestBody User user){
         userService.updateUser(user);
         return ResponseEntity.ok("User modifié avec succès");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") int id){
        userService.deleteUser(id);
        return ResponseEntity.ok("Utilisateur supprimé");
    }
}
