package tech.chillo.sa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tech.chillo.sa.entites.DetailsVoiture;
import tech.chillo.sa.entites.Historique;

import java.util.List;

public interface HistoriqueRepository extends JpaRepository<Historique, Integer> {
    @Query("SELECT h FROM Historique h WHERE h.annonce.id = :idAnnonce")
    List<Historique> findByAnnonce(@Param("idAnnonce") int idAnnonce);

}
