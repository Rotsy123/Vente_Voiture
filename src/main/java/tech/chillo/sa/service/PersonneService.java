package tech.chillo.sa.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tech.chillo.sa.entites.Personne;
import tech.chillo.sa.repository.PersonneRepository;
import tech.chillo.sa.model.StatistiqueUtilisateur;
// import tech.chillo.sa.security.TokenUtil;

import java.util.List;
import java.util.Optional;

@Service
public class PersonneService  {

    private PersonneRepository personneRepository;
    private AnnonceService annonceService;

    public PersonneService (PersonneRepository personnerepository, AnnonceService annonceService){
        this.personneRepository = personnerepository;
        this.annonceService = annonceService;
    }
    public PersonneService(){}

    public List<Personne> findAll(){
        return this.personneRepository.findAll();
    }
    public Optional<Personne> findById(int id){
        return this.personneRepository.findById(id);
    }
    public void save(Personne personne){
        this.personneRepository.save(personne);
    }

    public Personne connected(String mail, String motdepasse) throws Exception {
        Optional<Personne> personne = personneRepository.findByMailAndMotdepasse(mail, motdepasse);

        if (personne.isPresent()) {
            // String token = TokenUtil.generateToken(personne.get());
//            storeToken(token);
            return personne.get();
        }

        throw new Exception("Mot de passe incorrect");
    }


    // public Personne savePersonne(Personne personne, String motdepasse1, String motdepasse2) throws Exception {
    //     personne.setMotdepasse(motdepasse1,motdepasse2);
    //     return personneRepository.save(personne);
    // } 

    public Personne savePersonne(Personne personne) throws Exception {
        return personneRepository.save(personne);
    } 

    public StatistiqueUtilisateur getStatistiqueUtilisateur (int idpersonne) {
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

//    @Override
//    public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {
//        return this.personneRepository
//            .findByMail(username)
//            .orElseThrow(() -> new UsernameNotFoundException("aucun user correspondant"));
//    }
    
}
