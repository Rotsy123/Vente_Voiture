package tech.chillo.sa.service;

import org.springframework.stereotype.Service;
import tech.chillo.sa.repository.MarqueRepository;

@Service
public class MarqueService {
    private MarqueRepository marqueRepository;

    public MarqueService (MarqueRepository marquerepository){
        this.marqueRepository = marquerepository;
    }
}
