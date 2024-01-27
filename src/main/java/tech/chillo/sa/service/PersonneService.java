package tech.chillo.sa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tech.chillo.sa.entites.Personne;
import tech.chillo.sa.repository.PersonneRepository;
import tech.chillo.sa.model.StatistiqueUtilisateur;
// import tech.chillo.sa.security.TokenUtil;
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

    public Personne save(Personne personne) {
//        String mdp = this.passwordEncoder.encode(personne.getMotdepasse());
//        System.out.println(mdp + "   ----------------------   " + personne.getMotdepasse());
//        personne.setMotdepasse(mdp);
        return this.personneRepository.save(personne);
    }

    public Personne connected(String mail, String motdepasse) throws Exception {
        Optional<Personne> personne = personneRepository.findByMailAndMotdepasse(mail, motdepasse);

        if (personne.isPresent()) {
            Personne user = personne.get();
//            String hashedPassword = passwordEncoder.encode(motdepasse);
//            user.setMotdepasse(hashedPassword);
            return user;
        }

        throw new Exception("Mot de passe incorrect");
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

    // @Override
    // public UserDetails loadUserByUsername (String username) throws
    // UsernameNotFoundException {
    // return this.personneRepository
    // .findByMail(username)
    // .orElseThrow(() -> new UsernameNotFoundException("aucun user
    // correspondant"));
    // }
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<Personne> personneOptional = personneRepository.findByMail(username);
//
//        return personneOptional.map(personne -> {
//            return org.springframework.security.core.userdetails.User.withUsername(personne.getMail())
//                    .password(personne.getMotdepasse())
//                    .roles("USER") // Définir les rôles de l'utilisateur, si nécessaire
//                    .build();
//        }).orElseThrow(
//                () -> new UsernameNotFoundException("Aucun utilisateur trouvé avec l'adresse e-mail: " + username));
//    }

}
