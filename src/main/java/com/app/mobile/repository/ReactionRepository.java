package com.app.mobile.repository;

import com.app.mobile.model.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction, Integer> {
    @Query(value = "SELECT u.id_user as idUser, r.id_reaction as idReaction, u.pseudo as pseudo, u.img_user as imgUser, r.date_reaction as dateReaction, r.commentaire as commentaire " +
            "FROM Reaction r JOIN user u ON r.id_user = u.id_user " +
            "WHERE r.id_pub = :pubId AND r.reagi = true", nativeQuery = true)
    List<Map<String, Object>> findUserWhoReacted(@Param("pubId") int id);

    @Query(value = "SELECT u.id_user as idUser, r.id_reaction as idReaction, u.pseudo as pseudo, u.img_user as imgUser, r.date_reaction as dateReaction, r.commentaire as commentaire " +
            "FROM Reaction r JOIN user u ON r.id_user = u.id_user " +
            "WHERE r.id_pub = :pubId AND r.commentaire IS NOT NULL", nativeQuery = true)
    List<Map<String, Object>> findUserWhoCommented(@Param("pubId") int id);

    @Query(value = "SELECT u.id_user as idUser, r.id_reaction as idReaction, u.pseudo as pseudo, u.img_user as imgUser, r.commentaire as commentaire, r.date_reaction as dateReaction " +
            "FROM Reaction r JOIN user u ON r.id_user = u.id_user JOIN publication p ON p.id_pub = r.id_pub " +
            "WHERE p.id_user = :idUser AND r.reagi = true AND r.statut_reaction = false ORDER BY r.date_reaction DESC", nativeQuery = true)
    List<Map<String, Object>> findNewReagi(@Param("idUser") int id);

    @Query(value = "SELECT u.id_user as idUser, r.id_reaction as idReaction, u.pseudo as pseudo, u.img_user as imgUser, r.commentaire as commentaire, r.date_reaction as dateReaction " +
            "FROM Reaction r JOIN user u ON r.id_user = u.id_user JOIN publication p ON p.id_pub = r.id_pub " +
            "WHERE p.id_user = :idUser AND r.commentaire IS NOT NULL AND r.statut_reaction = false ORDER BY r.date_reaction DESC", nativeQuery = true)
    List<Map<String, Object>> findNewCommentaire(@Param("idUser") int id);
}
