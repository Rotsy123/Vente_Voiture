package tech.chillo.sa.service;

import org.springframework.stereotype.Service;
import tech.chillo.sa.entites.Carburant;
import tech.chillo.sa.repository.CarburantRepository;

@Service
public class CarburantService {
    private CarburantRepository carburantRepository;

    public CarburantService(CarburantRepository carburantRepository) {
        this.carburantRepository = carburantRepository;
    }
}
