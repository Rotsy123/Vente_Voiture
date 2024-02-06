package tech.chillo.sa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Optional;
import java.util.List;
import tech.chillo.sa.repository.PersonneRepository;
import tech.chillo.sa.repository.MessagerieRepository;
import tech.chillo.sa.entites.Personne;
import tech.chillo.sa.entites.Messagerie;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MessagerieService {

    private final MessagerieRepository messagerieRepository;
    private final PersonneRepository personneRepository;

    @Autowired
    public MessagerieService(MessagerieRepository messagerieRepository, PersonneRepository personneRepository) {
        this.messagerieRepository = messagerieRepository;
        this.personneRepository = personneRepository;
    }

    public Messagerie saveMessagerie(Messagerie messagerie) {
        Optional<Personne> expediteur = personneRepository.findById(messagerie.getExpediteur().getId());
        Optional<Personne> destinataire = personneRepository.findById(messagerie.getDestinataire().getId());
        if (expediteur.isPresent() && destinataire.isPresent()) {
            messagerie.setExpediteur(expediteur.get());
            messagerie.setDestinataire(destinataire.get());
            return messagerieRepository.save(messagerie);
        }
        return null;
    }

    public List<Messagerie> listMessages(int exp, int dest) {
        List<Messagerie> messages = messagerieRepository
                .findByExpediteur_IdAndDestinataire_IdOrExpediteur_IdAndDestinataire_IdOrderByDateenvoieDesc(exp, dest,
                        dest, exp);
        return messages;
    }

    public Messagerie getLastMessage(int exp, int dest) {
        List<Messagerie> messages = listMessages(exp, dest);
        Messagerie dernierMessage = messages.isEmpty() ? null : messages.get(0);
        return dernierMessage;
    }

    public long countMessageNonlue(int idpersonne) {
        return messagerieRepository.countByEtatAndDestinataire_Id(0, idpersonne);
    }

    @Transactional
    public void updateEtatMessage(int id, int nouvelEtat) {
        // Optional<Messagerie> message = messagerieRepository.findById(id);
        // if(message.isPresent())
        messagerieRepository.updateEtat(id, nouvelEtat);
    }

    public Personne getPersonne(int idpersonne) {
        Optional<Personne> personne = personneRepository.findById(idpersonne);
        if (personne.isPresent()) {
            return personne.get();
        }
        return null;
    }

}
