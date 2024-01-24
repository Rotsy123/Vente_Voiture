package tech.chillo.sa.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import tech.chillo.sa.entites.Messagerie;
import java.util.List;
import java.util.Optional;

public interface MessagerieRepository extends MongoRepository<Messagerie, String> {
    // Ajoutez des méthodes personnalisées si nécessaire

    Optional<Messagerie> findById(String id);

    List<Messagerie> findByExpediteur_IdAndDestinataire_IdOrExpediteur_IdAndDestinataire_IdOrderByDateenvoieDesc(
            int expId1, int destId1,
            int expId2, int destId2
    );

    Messagerie save(Messagerie messagerie);

    long countByEtatAndDestinataire_Id(int etat, int destinataireId);

    @Transactional
    @Modifying
    @Query("UPDATE Messagerie SET etat = :nouvelEtat WHERE id = :id")
    void updateEtat(@Param("id") int id, @Param("nouvelEtat") int nouvelEtat);
}