package tech.chillo.sa.service;

import org.springframework.stereotype.Service;
import tech.chillo.sa.entites.Personne;
import tech.chillo.sa.repository.PersonneRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PersonneService {

    private PersonneRepository personneRepository;

    public PersonneService (PersonneRepository personnerepository){
        this.personneRepository = personnerepository;
    }

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
            return personne.get();
        }
    
        throw new Exception("Mot de passe incorrect");
    }

    public Personne savePersonne(Personne personne, String motdepasse1, String motdepasse2) throws Exception {
        personne.setMotdepasse(motdepasse1,motdepasse2);
        return personneRepository.save(personne);
    } 
    
}
