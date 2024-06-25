package com.app.mobile.service;

import com.app.mobile.dto.UserDto;
import com.app.mobile.model.User;
import com.app.mobile.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getUserSearch(String recherche) {
        return userRepository.findByPseudoLike(recherche);
    }

    @Override
    public User insertUser(UserDto userDTO) {
        User user = new User();
        user.setContact(userDTO.getContact());
        user.setEmail(userDTO.getEmail());
        user.setMdp(userDTO.getMdp());
        user.setPseudo(userDTO.getPseudo());
        user.setStatutUser(true);
        user.setImgUser(null);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User user) {
        return userRepository.findById(user.getIdUser())
                .map(u-> {
                    u.setPseudo(user.getPseudo());
                    u.setEmail(user.getEmail());
                    u.setContact(user.getContact());
                    u.setMdp(user.getMdp());
                    u.setStatutUser(user.isStatutUser());
                    u.setImgUser(user.getImgUser());
                    return userRepository.save(u);
                }).orElseThrow(()-> new RuntimeException("Utilisateur non trouvé"));
    }

    @Override
    public String deleteUser(int id) {
        if (id == 0){
            throw new RuntimeException("l'idPub "+ id + " n'existe pas");
        }
        userRepository.deleteById(id);
        return "Utilisateur supprimé";
    }
}
