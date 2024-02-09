package tech.chillo.sa.service;


import org.springframework.stereotype.Service;
import tech.chillo.sa.entites.Photos;
import tech.chillo.sa.repository.MarqueRepository;
import tech.chillo.sa.repository.PhotosRepository;

@Service
public class PhotosService {
    PhotosRepository photosrepository;

    public PhotosService (PhotosRepository photosrepository){
        this.photosrepository = photosrepository;
    }

    public void Insert(Photos photos){
        this.photosrepository.save(photos);
    }

}
