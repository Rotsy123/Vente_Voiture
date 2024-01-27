package tech.chillo.sa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.chillo.sa.entites.Personne;

import java.util.List;
import java.util.Optional;

public interface PersonneRepository extends JpaRepository<Personne, Integer> {
    List<Personne> findAll();

    Optional<Personne> findById(int id);

    Optional<Personne> findByMail(String mail);

    Optional<Personne> findByMailAndMotdepasse(String mail, String motdepasse);
}
