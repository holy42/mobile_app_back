package com.app.mobile.repository;

import com.app.mobile.model.Amis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AmisRepository extends JpaRepository<Amis, Integer> {
    @Query(value = "SELECT u.id_user as idUser, a1.id_amis as idAmis, u.img_user as imgUser, u.pseudo as pseudo, a1.date_ajout as dateAjout " +
            "FROM User u " +
            "JOIN Amis a1 ON u.id_user = a1.id_user2 " +
            "JOIN Amis a2 ON u.id_user = a2.id_user2 " +
            "WHERE a1.id_user1 = :userId1 AND a2.id_user1 = :userId2 AND a1.valider = true AND a2.valider = true "+
            "GROUP BY u.id_user ORDER BY GREATEST(a1.date_ajout, a2.date_ajout) DESC", nativeQuery = true)
    List<Map<String, Object>> findAmisEnCommun(@Param("userId1") int userId1, @Param("userId2") int userId2);

    @Query(value = "SELECT u.id_user as idUser, a.id_amis as idAmis, u.img_user as imgUser, u.pseudo as pseudo, a.date_ajout as dateAjout " +
            "FROM User u JOIN Amis a ON u.id_user = a.id_user2 " +
            "WHERE a.id_user1 = :userId AND a.valider = true", nativeQuery = true)
    List<Map<String, Object>> findAmisUser(@Param("userId") int userId);

    @Query(value = "SELECT u.id_user as idUser, a.id_amis as idAmis, u.img_user as imgUser, u.pseudo as pseudo, a.date_ajout as dateAjout " +
            "FROM User u JOIN Amis a ON u.id_user = a.id_user2 " +
            "WHERE a.id_user1 = :userId AND a.valider = false", nativeQuery = true)
    List<Map<String, Object>> findDemandeAmis(@Param("userId") int userId);

    @Query(value = "SELECT u.id_user as idUser, a.id_amis as idAmis, u.img_user as imgUser, u.pseudo as pseudo, a.date_ajout as dateAjout " +
            "FROM User u JOIN Amis a ON u.id_user = a.id_user1 " +
            "WHERE a.id_user2 = :userId AND a.valider = true AND a.statut_amis = false ORDER BY a.date_ajout DESC", nativeQuery = true)
    List<Map<String, Object>> findNewAmisNotif(@Param("userId") int userId);
}
