package tech.chillo.sa.service;

import org.springframework.stereotype.Service;
import tech.chillo.sa.entites.Marque;
import tech.chillo.sa.repository.MarqueRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MarqueService {
    private MarqueRepository marqueRepository;

    public MarqueService (MarqueRepository marquerepository){
        this.marqueRepository = marquerepository;
    }

    public List<Marque> findAll(){
        return this.marqueRepository.findAll();
    }
    public Optional<Marque> findById(int id){
        return this.marqueRepository.findById(id);
    }
    public void Creer(Marque marque){
        this.marqueRepository.save(marque);
    }
}
