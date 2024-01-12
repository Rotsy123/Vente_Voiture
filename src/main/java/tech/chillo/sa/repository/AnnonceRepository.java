package tech.chillo.sa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tech.chillo.sa.entites.Voiture;
import tech.chillo.sa.entites.Annonce;

import java.util.List;

public interface AnnonceRepository extends JpaRepository<Annonce, Integer> {
    @Query("SELECT v FROM Voiture v INNER JOIN Annonce a ON v.id = a.voiture.id join bouquet on bouquet.id = a.bouquet.id WHERE a.personne.id = :idpersonne order by bouquet.pourcentage_commission desc")
    List<Voiture> findVoituresByPersonneId(@Param("idpersonne") int idpersonne);

    //TOKONY MBOLA ALAINA MIARAKA @ STATUT ANY
    @Query("SELECT a FROM Annonce a join bouquet on bouquet.id = a.bouquet.id WHERE a.personne.id = :idpersonne order by bouquet.pourcentage_commission desc")
    List<Annonce> findByPersonneId(@Param("idpersonne") int idpersonne);

    @Query("SELECT a FROM Annonce a join bouquet on bouquet.id = a.bouquet.id order by bouquet.pourcentage_commission desc")
    List<Annonce> GetAllAnnonceOrderByBouquet();
}
