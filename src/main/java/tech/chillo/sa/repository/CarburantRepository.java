package tech.chillo.sa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.chillo.sa.entites.Carburant;

import java.util.List;
import java.util.Optional;

public interface CarburantRepository extends JpaRepository<Carburant, Integer> {
    List<Carburant> findAll();
    Optional<Carburant> findById(int id);
}
