package tech.chillo.sa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
 import tech.chillo.sa.entites.Marque;

import java.util.List;
import java.util.Optional;

public interface MarqueRepository extends JpaRepository<Marque, Integer> {
    List<Marque> findAll();
    Optional<Marque> findById(int id);
}
