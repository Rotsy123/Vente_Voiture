package tech.chillo.sa.service;

import org.springframework.stereotype.Service;
import tech.chillo.sa.entites.Modele;
import tech.chillo.sa.repository.ModeleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ModeleService {
    private ModeleRepository ModeleRepository;

    public ModeleService (ModeleRepository Modelerepository){
        this.ModeleRepository = Modelerepository;
    }

    public List<Modele> findAll(){
        return this.ModeleRepository.findAll();
    }
    public Optional<Modele> findById(int id){
        return this.ModeleRepository.findById(id);
    }
    public void Creer(Modele Modele){
        this.ModeleRepository.save(Modele);
    }
}
