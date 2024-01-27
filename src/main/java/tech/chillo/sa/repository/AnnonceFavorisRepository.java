package tech.chillo.sa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tech.chillo.sa.entites.Annonce;
import tech.chillo.sa.entites.AnnonceFavoris;

import java.util.List;

public interface AnnonceFavorisRepository extends JpaRepository<AnnonceFavoris, Integer> {
//    @Query("SELECT af FROM AnnonceFavoris af join annonce a on a.id = af.annonce.id join bouquet on bouquet.id = a.bouquet.id WHERE af.personne.id = :idpersonne order by bouquet.pourcentage_commission desc")
//    List<AnnonceFavoris> findByPersonneId(@Param("idpersonne") int idpersonne);
}
