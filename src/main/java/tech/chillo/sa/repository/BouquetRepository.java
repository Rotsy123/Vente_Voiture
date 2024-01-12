package tech.chillo.sa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.chillo.sa.entites.Bouquet;

public interface BouquetRepository extends JpaRepository<Bouquet, Integer> {
    List<Bouquet> findAll();
    Optional<Bouquet> findById(int id);
}
