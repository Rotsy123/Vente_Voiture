package tech.chillo.sa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tech.chillo.sa.entites.DetailsVoiture;
import tech.chillo.sa.entites.Voiture;
import java.util.List;
import java.util.Optional;

public interface VoitureRepository extends JpaRepository<Voiture, Integer> {
    List<Voiture> findAll();
    Optional<Voiture> findById(int id);
}
