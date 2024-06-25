package com.app.mobile.repository;

import com.app.mobile.dto.UserPubDto;
import com.app.mobile.model.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Integer> {
    @Query(value = "SELECT u.id_user as idUser, p.id_pub as idPub, u.pseudo as pseudo, u.img_user as imgUser, p.texte as texte, p.fichier as fichier, p.date_pub as datePub " +
            "FROM Publication p JOIN User u ON p.id_user = u.id_user WHERE u.id_user = :idUser ORDER BY p.date_pub DESC", nativeQuery = true)
    List<Map<String, Object>> findUserPublications(@Param("idUser") int idUser);

    @Query(value = "SELECT u.id_user as idUser, p.id_pub as idPub, u.pseudo as pseudo, u.img_user as imgUser, p.texte as texte, p.fichier as fichier, p.date_pub as datePub " +
            "FROM Publication p JOIN User u ON p.id_user = u.id_user WHERE p.texte LIKE %:search% ORDER BY p.date_pub DESC", nativeQuery = true)
    List<Map<String, Object>> findSearchPublications(@Param("search") String search);

    @Query(value = "SELECT u.id_user as idUser, p.id_pub as idPub, u.pseudo as pseudo, u.img_user as imgUser, p.texte as texte, p.fichier as fichier, p.date_pub as datePub " +
            "FROM Publication p JOIN User u ON p.id_user = u.id_user WHERE p.date_pub BETWEEN :start AND :end ORDER BY p.date_pub DESC", nativeQuery = true)
    List<Map<String, Object>> findSearchPublicationsBetweenTwoDates(@Param("start") Date start, @Param("end") Date end);

    @Query(value = "SELECT f.id_user as idUser, p.id_pub as idPub, f.pseudo as pseudo, f.img_user as imgUser, p.texte as texte, p.fichier as fichier, p.date_pub as datePub " +
            "FROM Publication p JOIN user f ON p.id_user = f.id_user JOIN Amis a ON a.id_user2 = f.id_user " +
            "WHERE a.id_user1 = :userId AND a.valider = true " +
            "ORDER BY p.date_pub DESC", nativeQuery = true)
    List<Map<String, Object>> findAmisPublications(@Param("userId") int userId);
}
