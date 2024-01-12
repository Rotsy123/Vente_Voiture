package tech.chillo.sa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import tech.chillo.sa.repository.NotificationRepository;
import tech.chillo.sa.entites.Notification;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public Optional<Notification> getAllMessageOrAnnonce(String type)  {
        return notificationRepository.getNotificationByType(type);
    }

    public Optional<Notification> getAllNotificationsNonLue(int etat)  {
        return notificationRepository.getNotificationByEtat(etat);
    }

    public long getNombreNotificationsNonLue()  {
        return notificationRepository.countByEtat(1);
    }

    public void save(Notification notification)  {
        notificationRepository.save(notification);
    }

    // Ajoutez d'autres méthodes en fonction de vos besoins
}
