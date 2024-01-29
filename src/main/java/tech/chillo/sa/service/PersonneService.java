package tech.chillo.sa.service;

import org.springframework.beans.factory.annotation.Autowired;
import tech.chillo.sa.entites.Personne;
import tech.chillo.sa.repository.PersonneRepository;
import tech.chillo.sa.model.StatistiqueUtilisateur;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class PersonneService {

    @Autowired
    private PersonneRepository personneRepository;
    private AnnonceService annonceService;
//    private final BCryptPasswordEncoder passwordEncoder;

    public PersonneService(PersonneRepository personnerepository, AnnonceService annonceService) {
        this.personneRepository = personnerepository;
        this.annonceService = annonceService;
//        this.passwordEncoder = passwordEncoder;
    }


    public List<Personne> findAll() {
        return this.personneRepository.findAll();
    }

    public Optional<Personne> findById(int id) {
        return this.personneRepository.findById(id);
    }

    public Personne findByMail(String id) {
        return this.personneRepository.findByMail(id)
                .orElseThrow(() -> new NoSuchElementException("Personne introuvable"));
    }

    public Personne save(Personne personne) {
//        String mdp = this.passwordEncoder.encode(personne.getMotdepasse());
//        System.out.println(mdp + "   ----------------------   " + personne.getMotdepasse());
//        personne.setMotdepasse(mdp);
        return this.personneRepository.save(personne);
    }

   

    // public Personne savePersonne(Personne personne, String motdepasse1, String
    // motdepasse2) throws Exception {
    // personne.setMotdepasse(motdepasse1,motdepasse2);
    // return personneRepository.save(personne);
    // }

    public Personne savePersonne(Personne personne) throws Exception {
        return personneRepository.save(personne);
    }

    public StatistiqueUtilisateur getStatistiqueUtilisateur(int idpersonne) {
        StatistiqueUtilisateur statistiqueUtilisateur = new StatistiqueUtilisateur();
        Optional<Personne> personne = findById(idpersonne);

        if (personne.isPresent()) {
            Personne pers = personne.get();
            statistiqueUtilisateur.setPersonne(pers);
            statistiqueUtilisateur.setNombre_annonce(annonceService.getNombreAnnoncePersonne(idpersonne));
            statistiqueUtilisateur.setNombre_annonce_vendu(annonceService.getNombreAnnonceVenduPersonne(idpersonne));
        }
        return statistiqueUtilisateur;
    }


}
