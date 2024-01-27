package tech.chillo.sa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import tech.chillo.sa.entites.Voiture;
import tech.chillo.sa.entites.Annonce;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface AnnonceRepository extends JpaRepository<Annonce, Integer> {
    @Query(nativeQuery = true,
            value = "SELECT a.id, a.idvoiture, a.idpersonne, a.datepublication, h.bouquet,a.etat,a.datevalidation" +
                    " FROM historique h\n" +
                    " JOIN annonce a ON h.annonce = a.id\n" +
                    " JOIN (\n" +
                    "    SELECT annonce, MAX(datedebut) as max_datedebut\n" +
                    "    FROM historique\n" +
                    "    GROUP BY annonce\n" +
                    ") h_max ON h.annonce = h_max.annonce AND h.datedebut = h_max.max_datedebut\n" +
                    "Join personne on a.idpersonne = personne.id\n" +
                    "join bouquet on bouquet.id = h.idbouquet;")
    List<Object[]> findAllAnnoncesWithLatestBouquet();

    @Query(nativeQuery = true,
            value = "SELECT a.id, a.idvoiture, a.idpersonne, a.datepublication, h.bouquet,a.etat,a.datevalidation" +
                    " FROM historique h\n" +
                    " JOIN annonce a ON h.annonce = a.id\n" +
                    " JOIN (\n" +
                    "    SELECT annonce, MAX(datedebut) as max_datedebut\n" +
                    "    FROM historique\n" +
                    "    GROUP BY annonce\n" +
                    ") h_max ON h.annonce = h_max.annonce AND h.datedebut = h_max.max_datedebut\n" +
                    "Join personne on a.idpersonne = personne.id\n" +
                    "join bouquet on bouquet.id = h.bouquet" +
                    " WHERE a.idpersonne = :idpersonne " +
                    " order by bouquet.pourcentage_commission desc;")
    List<Object[]> findByPersonneId(@Param("idpersonne") int idpersonne);

    @Query(nativeQuery = true,
            value = "SELECT a.id, a.idvoiture, a.idpersonne, a.datepublication, h.bouquet,a.etat,a.datevalidation" +
                    " FROM historique h\n" +
                    " JOIN annonce a ON h.annonce = a.id\n" +
                    " JOIN (\n" +
                    "    SELECT annonce, MAX(datedebut) as max_datedebut\n" +
                    "    FROM historique\n" +
                    "    GROUP BY annonce\n" +
                    ") h_max ON h.annonce = h_max.annonce AND h.datedebut = h_max.max_datedebut\n" +
                    "Join personne on a.idpersonne = personne.id\n" +
                    "join bouquet on bouquet.id = h.bouquet" +
                    " order by bouquet.pourcentage_commission desc;")
    List<Object[]> GetAllAnnonceOrderByBouquet();

    @Query(nativeQuery = true,
            value = "SELECT a.id, a.idvoiture, a.idpersonne, a.datepublication, h.bouquet,a.etat,a.datevalidation \n"+
                    " FROM historique \n" +
                    " JOIN annonce a ON h.annonce = a.id \n"+
                    " JOIN (\n" +
                    " SELECT annonce, MAX(datedebut) as max_datedebut \n" +
                    " FROM historique \n" +
                    " GROUP BY annonce \n" +
                    " ) h_max ON h.annonce = h_max.annonce AND h.datedebut = h_max.max_datedebut \n" +
                    " Join personne on a.idpersonne = personne.id \n" +
                    " join bouquet on bouquet.id = h.bouquet where a.datepublication = :datePublication and a.idpersonne = :idpersonne")
    Optional<Object[]> getAnnoncesByDatePublicationAndIdpersonne(@Param("datePublication") LocalDateTime datePublication,@Param("idpersonne") int idpersonne);

    List<Annonce> findByEtat(int etat);

    @Query("SELECT COUNT(a.id) FROM Annonce a WHERE a.etat = :etat AND a.personne.id != :idpersonne")
    long countByEtatAndPersonneNotEqual(@Param("etat") int etat,@Param("idpersonne") int idpersonne);

    List<Annonce> findByEtatAndPersonne_IdNot(int etat, int idpersonne);

    @Transactional
    @Modifying
    @Query("UPDATE Annonce SET etat = :nouvelEtat WHERE id = :id")
    void updateEtat(@Param("id") int id, @Param("nouvelEtat") int nouvelEtat);


    @Transactional
    @Modifying
    @Query("UPDATE Annonce SET datevalidation = current_timestamp WHERE id = :id")
    void Validation(@Param("id") int id);

    @Query("SELECT COUNT(a.id) FROM Annonce a WHERE a.etat = :etat AND a.personne.id = :idpersonne")
    int countByEtatAndPersonne(@Param("etat") int etat,@Param("idpersonne") int idpersonne);

    // List<Annonce> findByPersonneId(int personneId);
    @Query(nativeQuery = true,
            value = "    SELECT\n" +
            "    SUM(b.pourcentage_commission * EXTRACT(EPOCH FROM (h.datefin - h.datedebut)) / (60 * 60 * 24)) AS difference_in_days\n" +
            "    FROM\n" +
            "    historique h\n" +
            "    JOIN\n" +
            "    bouquet b ON h.bouquet = b.id\n" +
            "    WHERE h.annonce = :idannonce")
    double prixCommission(@Param("idannonce") int idannonce);

    int countByPersonneId(int idpersonne);

    int countByPersonneIdAndEtat(int idpersonne, int etat);

    @Query("SELECT a FROM Annonce a WHERE MONTH(a.datepublication) = :mois AND YEAR(a.datepublication) = :annee and etat = 10")
    List<Annonce> findAnnonceByMonthAndYearAndEtat(@Param("mois") int mois, @Param("annee") int annee);

}

