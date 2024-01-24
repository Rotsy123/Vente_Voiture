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
    @Query("SELECT v FROM Voiture v INNER JOIN Annonce a ON v.id = a.voiture.id join bouquet on bouquet.id = a.bouquet.id WHERE a.personne.id = :idpersonne order by bouquet.pourcentage_commission desc")
    List<Voiture> findVoituresByPersonneId(@Param("idpersonne") int idpersonne);

    //TOKONY MBOLA ALAINA MIARAKA @ STATUT ANY
    @Query("SELECT a FROM Annonce a join bouquet on bouquet.id = a.bouquet.id WHERE a.personne.id = :idpersonne order by bouquet.pourcentage_commission desc")
    List<Annonce> findByPersonneId(@Param("idpersonne") int idpersonne);

    @Query("SELECT a FROM Annonce a join bouquet on bouquet.id = a.bouquet.id order by bouquet.pourcentage_commission desc")
    List<Annonce> GetAllAnnonceOrderByBouquet();

    @Query("SELECT a FROM Annonce a where a.datepublication = :datePublication and a.personne.id = :idpersonne")
    Optional<Annonce> getAnnoncesByDatePublicationAndIdpersonne(@Param("datePublication") LocalDateTime datePublication,@Param("idpersonne") int idpersonne);

    List<Annonce> findByEtat(int etat);

    @Query("SELECT COUNT(a.id) FROM Annonce a WHERE a.etat = :etat AND a.personne.id != :idpersonne")
    long countByEtatAndPersonneNotEqual(@Param("etat") int etat,@Param("idpersonne") int idpersonne);

    List<Annonce> findByEtatAndPersonne_IdNot(int etat, int idpersonne);

    @Transactional
    @Modifying
    @Query("UPDATE Annonce SET etat = :nouvelEtat WHERE id = :id")
    void updateEtat(@Param("id") int id, @Param("nouvelEtat") int nouvelEtat);

    @Query("SELECT COUNT(a.id) FROM Annonce a WHERE a.etat = :etat AND a.personne.id = :idpersonne")
    int countByEtatAndPersonne(@Param("etat") int etat,@Param("idpersonne") int idpersonne);

    // List<Annonce> findByPersonneId(int personneId);
    @Query("SELECT a.bouquet.pourcentage_commission * d.prix / 100 FROM Annonce a JOIN DetailsVoiture d ON a.voiture.id = d.voiture.id JOIN Bouquet b ON b.id = a.bouquet.id WHERE a.id = :idannonce")
    double prixCommission(@Param("idannonce") int idannonce);

    int countByPersonneId(int idpersonne);

    int countByPersonneIdAndEtat(int idpersonne, int etat);

    @Query("SELECT a FROM Annonce a WHERE MONTH(a.datepublication) = :mois AND YEAR(a.datepublication) = :annee and etat = 10")
    List<Annonce> findAnnonceByMonthAndYearAndEtat(@Param("mois") int mois, @Param("annee") int annee);

}

