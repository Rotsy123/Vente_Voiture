package tech.chillo.sa.service;

import tech.chillo.sa.entites.Historique;
import tech.chillo.sa.repository.HistoriqueRepository;

public class HistoriqueService {
    private HistoriqueRepository historiqueRepository;
    public HistoriqueService(HistoriqueRepository hr){
        this.historiqueRepository = hr;
    }

    public void ChangerBouquet(Historique historique){
        this.historiqueRepository.save(historique);
    }
}
