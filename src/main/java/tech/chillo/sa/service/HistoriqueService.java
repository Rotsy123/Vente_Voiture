package tech.chillo.sa.service;

import org.springframework.stereotype.Service;
import tech.chillo.sa.entites.Annonce;
import tech.chillo.sa.entites.Bouquet;
import tech.chillo.sa.entites.Historique;
import tech.chillo.sa.repository.AnnonceRepository;
import tech.chillo.sa.repository.BouquetRepository;
import tech.chillo.sa.repository.HistoriqueRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class HistoriqueService {
    private HistoriqueRepository historiqueRepository;
    private AnnonceRepository annonceRepository;
    private BouquetRepository bouquetRepository;

    public HistoriqueService(HistoriqueRepository hr, AnnonceRepository ar, BouquetRepository br){
        this.historiqueRepository = hr;
        this.annonceRepository = ar;
        this.bouquetRepository = br;
    }

    public void ChangerBouquet(Historique historique){
        this.historiqueRepository.save(historique);
    }
    public void NouveauBouquet(int idannonce, int idbouquet, LocalDateTime datedebut, LocalDateTime datefin){
        Optional<Annonce> optionalannonce= this.annonceRepository.findById(idannonce);
        Optional<Bouquet> optionalbouquet = this.bouquetRepository.findById(idbouquet);
        Bouquet bouquet =  optionalbouquet.orElse(null);
        Annonce annonce = optionalannonce.orElse(null);
        Historique historique = new Historique(annonce, bouquet, datedebut, datefin);
    }
    public void DeleteHistorique(int idannonce){
        List<Historique> historique = this.historiqueRepository.findByAnnonce(idannonce);
        for(int i=0; i<historique.size(); i++){
            this.historiqueRepository.delete(historique.get(i));
        }
    }
}
