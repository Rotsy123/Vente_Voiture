package tech.chillo.sa.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import tech.chillo.sa.entites.DetailsVoiture;
import tech.chillo.sa.entites.Voiture;
import tech.chillo.sa.repository.VoitureRepository;

import java.util.List;
import java.util.Optional;

@Service
public class VoitureService {
    private VoitureRepository voiturerepository;

    public VoitureService(VoitureRepository vr){
        this.voiturerepository = vr;
    }
    public List<Voiture> GetAll(){
        return this.voiturerepository.findAll();
    }


}
