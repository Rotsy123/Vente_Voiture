package tech.chillo.sa.service;

import tech.chillo.sa.entites.Bouquet;
import tech.chillo.sa.entites.Marque;
import tech.chillo.sa.repository.BouquetRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class BouquetService {
    private BouquetRepository bouquetRepository;

    public BouquetService(BouquetRepository bouquetRepository) {
        this.bouquetRepository = bouquetRepository;
    }

    public List<Bouquet> findAll() {
        return this.bouquetRepository.findAll();
    }
    public Optional<Bouquet> findById(int id){
        return this.bouquetRepository.findById(id);
    }
    public void Creer(Bouquet bouquet){
        this.bouquetRepository.save(bouquet);
    }

    public void Delete(int idbouquet){
        Optional<Bouquet> optionalBouquet = this.bouquetRepository.findById(idbouquet);
        Bouquet bouquet = optionalBouquet.orElse(null);
        this.bouquetRepository.delete(bouquet);
    }
    public void Update(int idbouquet, double prix) throws Exception {
        Optional<Bouquet> optionalBouquet = this.bouquetRepository.findById(idbouquet);
        Bouquet bouquet = optionalBouquet.orElse(null);
        bouquet.setPourcentage_commision(prix);
        this.bouquetRepository.save(bouquet);
    }

}
