package com.app.mobile.service;

import com.app.mobile.dto.AmisDto;
import com.app.mobile.model.Amis;
import com.app.mobile.model.User;
import com.app.mobile.repository.AmisRepository;
import com.app.mobile.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AmisServiceImpl implements AmisService{
    @Autowired
    private AmisRepository amisRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Map<String, Object>> getAmisCommun(int id1, int id2) {
        if(id1 <= 0 || id2 <= 0){
            throw new RuntimeException("Les idUser "+id1+" et "+id2+" n'existent pas");
        }
        return amisRepository.findAmisEnCommun(id1, id2);
    }

    @Override
    public List<Map<String, Object>> getAmisUser(int id) {
        if(id <= 0 ){
            throw new RuntimeException("L' idUser "+id+" n'existe pas");
        }
        return amisRepository.findAmisUser(id);
    }

    @Override
    public List<Map<String, Object>> getAmisDemande(int id) {
        if(id <= 0 ){
            throw new RuntimeException("L' idUser "+id+" n'existe pas");
        }
        return amisRepository.findDemandeAmis(id);
    }

    @Override
    public List<Map<String, Object>> getNotifAmisValider(int id) {
        if(id <= 0 ){
            throw new RuntimeException("L' idUser "+id+" n'existe pas");
        }
        return amisRepository.findNewAmisNotif(id);
    }

    @Override
    public Amis insertAmis(AmisDto amisDto) {
        User user1 = userRepository.findById(amisDto.getIdUser1()).orElseThrow(()-> new RuntimeException("User non trouvé"));
        User user2 = userRepository.findById(amisDto.getIdUser2()).orElseThrow(()-> new RuntimeException("User non trouvé"));

        Amis amis = new Amis();
        amis.setUser1(user1);
        amis.setUser2(user2);
        amis.setDateAjout(amisDto.getDateAjout());
        amis.setValider(false);
        amis.setStatutAmis(false);
        return amisRepository.save(amis);
    }

    @Override
    public List<Amis> getAllAmis() {
        return amisRepository.findAll();
    }

    @Override
    public Amis updateValiderAmis(int id) {
        //User user1 = userRepository.findById(idUser).orElseThrow(()-> new IllegalArgumentException("User non trouvé"));
        //User user2 = userRepository.findById(idAmis).orElseThrow(()-> new IllegalArgumentException("User non trouvé"));

        Amis amis = amisRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("User non trouvé"));
        amis.setValider(true);
        return amisRepository.save(amis);
    }

    @Override
    public Amis updateStatutAmis(int id) {
        //User user1 = userRepository.findById(idUser).orElseThrow(()-> new IllegalArgumentException("User non trouvé"));
        //User user2 = userRepository.findById(idAmis).orElseThrow(()-> new IllegalArgumentException("User non trouvé"));

        Amis amis = amisRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("User non trouvé"));
        amis.setStatutAmis(true);
        return amisRepository.save(amis);
    }

    @Override
    public String deleteAmis(int id) {
        if (id == 0){
            throw new RuntimeException("l'idPub "+ id + " n'existe pas");
        }
        amisRepository.deleteById(id);
        return "Amis supprimé";
    }
}
