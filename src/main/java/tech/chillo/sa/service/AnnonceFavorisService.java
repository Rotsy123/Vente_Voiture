package tech.chillo.sa.service;

import tech.chillo.sa.entites.Annonce;
import tech.chillo.sa.entites.AnnonceFavoris;
import tech.chillo.sa.entites.Personne;
import tech.chillo.sa.repository.AnnonceFavorisRepository;
import tech.chillo.sa.repository.AnnonceRepository;
import tech.chillo.sa.repository.PersonneRepository;

import java.util.List;

public class AnnonceFavorisService {
    private AnnonceFavorisRepository annonceFavorisRepository;
    private AnnonceRepository annonceRepository;
    private PersonneRepository personneRepository;
    public AnnonceFavorisService(AnnonceRepository ar, PersonneRepository pr,AnnonceFavorisRepository afp){
        this.annonceFavorisRepository = afp;
        this.annonceRepository = ar;
        this.personneRepository = pr;
    }

    public void createAnnonceFavoris(Annonce annonce, Personne personne){
        this.annonceRepository.save(annonce);
        this.personneRepository.save(personne);
        AnnonceFavoris anf = new AnnonceFavoris(personne, annonce);
        this.annonceFavorisRepository.save(anf);

    }
//    public List<AnnonceFavoris> GetFavorisByPersonne(int idpersonne){
//        return this.annonceFavorisRepository.findByPersonneId(idpersonne);
//    }
}
