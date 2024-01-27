package tech.chillo.sa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.chillo.sa.entites.DetailsVoiture;
import tech.chillo.sa.entites.Historique;

public interface HistoriqueRepository extends JpaRepository<Historique, Integer> {
}
