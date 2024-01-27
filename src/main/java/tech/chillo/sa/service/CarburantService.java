package tech.chillo.sa.service;

import org.springframework.stereotype.Service;
import tech.chillo.sa.entites.Carburant;
import tech.chillo.sa.repository.CarburantRepository;

import java.util.List;

@Service
public class CarburantService {
    private CarburantRepository carburantRepository;

    public CarburantService(CarburantRepository carburantRepository) {
        this.carburantRepository = carburantRepository;
    }

    public void create(Carburant carburant){this.carburantRepository.save(carburant);}
    public List<Carburant> GetAll(){return this.carburantRepository.findAll();}
}
