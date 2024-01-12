package tech.chillo.sa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.chillo.sa.entites.Modele;

import java.util.List;
import java.util.Optional;

public interface ModeleRepository extends JpaRepository<Modele, Integer> {
    List<Modele> findAll();
    Optional<Modele> findById(int id);
}
