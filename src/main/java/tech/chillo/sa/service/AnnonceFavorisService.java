package tech.chillo.sa.service;

import org.springframework.stereotype.Service;
import tech.chillo.sa.entites.Annonce;
import tech.chillo.sa.entites.AnnonceFavoris;
import tech.chillo.sa.entites.Personne;
import tech.chillo.sa.repository.AnnonceFavorisRepository;
import tech.chillo.sa.repository.AnnonceRepository;
import tech.chillo.sa.repository.PersonneRepository;

import java.util.List;
@Service
public class AnnonceFavorisService {
    private AnnonceFavorisRepository annonceFavorisRepository;
    private AnnonceRepository annonceRepository;
    private PersonneRepository personneRepository;
    public AnnonceFavorisService(AnnonceRepository ar, PersonneRepository pr,AnnonceFavorisRepository afp){
        this.annonceFavorisRepository = afp;
        this.annonceRepository = ar;
        this.personneRepository = pr;
    }

    public void createAnnonceFavoris(int annonce, int personne){
        Annonce annonces = this.annonceRepository.findById(annonce).orElse(null);
        Personne personnes = this.personneRepository.findById(personne).orElse(null);
        this.annonceRepository.save(annonces);
        this.personneRepository.save(personnes);
        AnnonceFavoris anf = new AnnonceFavoris(personnes, annonces);
        this.annonceFavorisRepository.save(anf);
    }
    public List<AnnonceFavoris> GetFavorisByPersonne(int idpersonne){
        return this.annonceFavorisRepository.findByPersonneId(idpersonne);
    }
}
