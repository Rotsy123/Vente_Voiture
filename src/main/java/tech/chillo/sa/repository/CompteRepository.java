package tech.chillo.sa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.chillo.sa.entites.Compte;

@Repository
public interface CompteRepository extends JpaRepository<Compte, Integer> {

    Compte findByPersonne_Id(int idPersonne);

    Compte findByPersonne_Mail(String mail);
}
