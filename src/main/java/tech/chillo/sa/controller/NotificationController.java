package tech.chillo.sa.controller;

import org.springframework.web.bind.annotation.*;
import tech.chillo.sa.entites.Notification;
import tech.chillo.sa.service.NotificationService;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "notification")
public class NotificationController {
    private NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        System.out.println("ok");
        this.notificationService = notificationService;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Notification> rechercher() {
        return this.notificationService.getAllNotifications();
    }

    @GetMapping("/type")
    public Optional<Notification> getNotificationByType(@RequestParam("type")String type){
        return this.notificationService.getAllMessageOrAnnonce(type);
    }

    @GetMapping("/etat")
    public Optional<Notification> getNotificationByType(@RequestParam("etat")int etat){
        return this.notificationService.getAllNotificationsNonLue(etat);
    }

    @GetMapping("/nombrenotificationnonlue")
    public long getNombreNotificationsNonLue(){
        return this.notificationService.getNombreNotificationsNonLue();
    }

    @PostMapping("/save")
    public long getNombreNotificationsNonLue(){
        return this.notificationService.getNombreNotificationsNonLue();
    }
}
