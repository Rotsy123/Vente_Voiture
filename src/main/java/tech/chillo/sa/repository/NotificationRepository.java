package tech.chillo.sa.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import tech.chillo.sa.entites.Notification;
import java.util.List;
import java.util.Optional;

public interface NotificationRepository extends MongoRepository<Notification, String> {
    // Ajoutez des méthodes personnalisées si nécessaire
    List<Notification> findAll();

    @Query("{ 'type' : { $regex: ?0, $options: 'i' } }")
    Optional<Notification> getNotificationByType(String type);
    
    @Query(value = "{ 'etat' : ?0 }") //etat 1: non lue 2: deja lue
    Optional<Notification> getNotificationByEtat(int etat);

    long countByEtat(int etat);

    Notification save(Notification notification);
}
