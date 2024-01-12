
package tech.chillo.sa.service;

import org.springframework.stereotype.Service;
import tech.chillo.sa.entites.Transmission;
import tech.chillo.sa.repository.TransmissionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TransmissionService {
    private TransmissionRepository TransmissionRepository;

    public TransmissionService (TransmissionRepository Transmissionrepository){
        this.TransmissionRepository = Transmissionrepository;
    }

    public List<Transmission> findAll(){
        return this.TransmissionRepository.findAll();
    }
    public Optional<Transmission> findById(int id){
        return this.TransmissionRepository.findById(id);
    }
    public void Creer(Transmission Transmission){
        this.TransmissionRepository.save(Transmission);
    }
}
