package tech.chillo.sa.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tech.chillo.sa.entites.Messagerie;
import tech.chillo.sa.entites.Photos;

import java.util.List;

@Repository
public interface PhotosRepository extends MongoRepository<Photos, String> {
    Photos getByIdvoiture(String idvoiture);
    Photos save(Photos photos);
}
