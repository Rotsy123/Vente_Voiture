package tech.chillo.sa.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import tech.chillo.sa.entites.Annonces;
import java.util.List;
import java.util.Optional;

public interface AnnoncesRepository extends MongoRepository<Annonces, String> {
    // Ajoutez des méthodes personnalisées si nécessaire
    List<Annonces> findAll();

    @Query("{ 'personne.id' : ?0 }")
    List<Annonces> getAnnoncesByPersonneId(int idpersonne);

    Annonces save(Annonces annonces);

    // @Query("{ 'type' : { $regex: ?0, $options: 'i' } }")
    // Optional<List<Notification>> getNotificationByType(String type);
    
    // @Query(value = "{ 'etat' : ?0 }") //etat 1: non lue 2: deja lue
    // Optional<List<Notification>> getNotificationByEtat(int etat);

    // long countByEtatAndType(int etat, String type);

    @Query("{ 'personne.id' : ?0 }")
    List<Annonces> findByPersonneId(int personneId);

}
