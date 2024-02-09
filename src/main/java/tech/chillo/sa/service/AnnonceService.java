package tech.chillo.sa.service;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.chillo.sa.controller.AnnonceCreationRequest;
import tech.chillo.sa.entites.*;
import tech.chillo.sa.repository.*;
import tech.chillo.sa.model.StatistiqueComission;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;



@Service
public class AnnonceService {
    private AnnonceRepository annoncerepository;
    private VoitureRepository voitureRepository;
    private DetailsVoitureRepository detailsVoitureRepository;
    private MarqueRepository marqueRepository;
    private ModeleRepository modeleRepository;
    private PersonneRepository personneRepository;
    private BouquetRepository bouquetRepository;
    private HistoriqueRepository historiqueRepository;
    @Autowired
    private PhotosRepository photosRepository;

    public AnnonceService(HistoriqueRepository historiqueRepository, BouquetRepository bouquetRepository,AnnonceRepository annoncerepository, VoitureRepository vp, DetailsVoitureRepository dvp, MarqueRepository mr, ModeleRepository mdr, PersonneRepository personneRepository, PhotosRepository psr){
        this.annoncerepository = annoncerepository;
        this.voitureRepository = vp;
        this.detailsVoitureRepository = dvp;
        this.marqueRepository = mr;
        this.modeleRepository = mdr;
        this.personneRepository = personneRepository;
        this.bouquetRepository = bouquetRepository;
        this.historiqueRepository = historiqueRepository;
        this.photosRepository = psr;
    }
    public void DeleteAnnonce(int id){
        List<Historique> historiques = this.historiqueRepository.findByAnnonce(id);
        for(int i=0; i<historiques.size(); i++){
            this.historiqueRepository.delete(historiques.get(i));
        }
        Optional <Annonce> optionalannonce = this.annoncerepository.findById(id);
        Annonce annonce = optionalannonce.orElse(null);
        this.annoncerepository.delete(annonce);
    }
    public Bouquet getBouquetById(int id) {
        Optional<Bouquet> optionalBouquet = bouquetRepository.findById(id);
        return optionalBouquet.orElse(null);
    }

    public Voiture getVoitureById(int id) {
        Optional<Voiture> optionalVoiture = voitureRepository.findById(id);
        return optionalVoiture.orElse(null);
    }
    public Personne getPersonneById(int id) {
        Optional<Personne> optionalPersonne = personneRepository.findById(id);
        return optionalPersonne.orElse(null);
    }
//    public List<Annonce> GetByIdPersonne(int idpersonne){
//        return  this.annoncerepository.findLatestDistinctByPersonneId(idpersonne);
//    }

    public List<Annonce> GetAnnonce(){
        List<Annonce> annonces = new ArrayList<>();

        List<Object[]> results = annoncerepository.findAllAnnoncesWithLatestBouquet();
        for (Object[] result : results) {
            Annonce annonce = new Annonce();
            annonce.setId((int) result[0]);
            annonce.setVoiture(this.getVoitureById((int) result[1]));
            annonce.setPersonne(this.getPersonneById((int) result[2]));
            annonce.setBouquet(this.getBouquetById((int) result[4]));
            annonce.setDatepublication((Timestamp) result[3]);
            annonce.setEtat((int) result[5]);
            annonce.setDatevalidation((Timestamp ) result[6]);
            Photos photos = this.photosRepository.getByIdvoiture(annonce.getVoiture().getId()+"");
            annonce.getVoiture().setPhotos(photos.getPhotos());
            annonces.add(annonce);
        }

        return annonces;

    }
    public List<Annonce> GetAllOfPersonne(int idpersonne){
        List<Annonce> annonces = new ArrayList<>();

        List<Object[]> results = annoncerepository.findByPersonneId(idpersonne);
        for (Object[] result : results) {
            Annonce annonce = new Annonce();
            annonce.setId((int) result[0]);
            annonce.setVoiture(this.getVoitureById((int) result[1]));
            annonce.setPersonne(this.getPersonneById((int) result[2]));
            annonce.setBouquet(this.getBouquetById((int) result[4]));
            annonce.setDatepublication((Timestamp) result[3]);
            annonce.setEtat((int) result[5]);
            annonce.setDatevalidation((Timestamp ) result[6]);
            Photos photos = this.photosRepository.getByIdvoiture(annonce.getVoiture().getId()+"");
            annonce.getVoiture().setPhotos(photos.getPhotos());
            annonces.add(annonce);
        }

        return annonces;
    }
    public List<Annonce> GetAllNonValiderOrderByBouquet(){
        List<Annonce> annonces = new ArrayList<>();

        List<Object[]> results = annoncerepository.GetAllAnnonceNonValiderOrderByBouquet();
        for (Object[] result : results) {
            Annonce annonce = new Annonce();
            annonce.setId((int) result[0]);
            annonce.setVoiture(this.getVoitureById((int) result[1]));
            annonce.setPersonne(this.getPersonneById((int) result[2]));
            annonce.setBouquet(this.getBouquetById((int) result[4]));
            annonce.setDatepublication((Timestamp) result[3]);
            annonce.setEtat((int) result[5]);
            annonce.setDatevalidation((Timestamp ) result[6]);
            Photos photos = this.photosRepository.getByIdvoiture(annonce.getVoiture().getId()+"");
            annonce.getVoiture().setPhotos(photos.getPhotos());
            annonces.add(annonce);
        }

        return annonces;
    }
    public List<Annonce> GetAllValiderOrderByBouquet(){
        List<Annonce> annonces = new ArrayList<>();

        List<Object[]> results = annoncerepository.GetAllAnnonceValiderOrderByBouquet();
        for (Object[] result : results) {
            Annonce annonce = new Annonce();
            annonce.setId((int) result[0]);
            annonce.setVoiture(this.getVoitureById((int) result[1]));
            annonce.setPersonne(this.getPersonneById((int) result[2]));
            annonce.setBouquet(this.getBouquetById((int) result[4]));
            annonce.setDatepublication((Timestamp) result[3]);
            annonce.setEtat((int) result[5]);
            annonce.setDatevalidation((Timestamp ) result[6]);
            System.out.println(annonce.getVoiture().getId()+" io mintsy");
            Photos photos = this.photosRepository.getByIdvoiture(annonce.getVoiture().getId()+"");
            System.out.println(photos.getPhotos().length+" io le photos jiaby");
            annonce.getVoiture().setPhotos(photos.getPhotos());
            annonces.add(annonce);
        }

        return annonces;
    }
    @Transactional
    public Annonce createsaveAnnonceWithDetails(AnnonceCreationRequest request) {
        Voiture voiture = request.getVoiture();
        DetailsVoiture detailsVoiture = request.getDetailsVoiture();
        Annonce annonce = request.getAnnonce();
        voiture.setDetailsVoiture(detailsVoiture);
        this.voitureRepository.save(voiture);
        System.out.println(voiture.getMarque().getNom()+"NOM MARQUE"+ request.getPhotos().length);
        Photos photosall = new Photos(request.getPhotos(), voiture.getId()+"");
        this.photosRepository.save(photosall);

//        for(int i=0; i<request.getPhotos().length; i++){
//            System.out.println(request.getPhotos()[i]+"   IO LE SARY");
//            System.out.println(photosall.getPhotos()+"    "+photosall.getVoiture().getId()+" iooooooooooooooo");
//        }
        detailsVoiture.setVoiture(voiture);
        this.detailsVoitureRepository.save(detailsVoiture);
        annonce.setVoiture(voiture);

        annonce.setPersonne(request.getAnnonce().getPersonne());
        return this.annoncerepository.save(annonce);

    }

    public List<Annonce> getAnnonceByEtat(int etat) {
        List<Annonce> annconces = this.annoncerepository.findByEtat(etat);
        return annconces;
    }

    public List<Annonce> getAnnonceNonLue() {
        List<Annonce> annconces = this.annoncerepository.findByEtat(0);
        return annconces;
    }

    public long getNombreAnnonceNonLue(int idpersonne) {
        return annoncerepository.countByEtatAndPersonneNotEqual(0, idpersonne);
    }

    public List<Annonce> getAnnoncesByEtatAndPersonneNotEqual(int idpersonne) {
        int etat = 5;
        return annoncerepository.findByEtatAndPersonne_IdNot(etat, idpersonne);
    }

    @Transactional
    public void updateEtatAnnonce(int id) {
        annoncerepository.updateEtat(id, 10);
    }

    @Transactional
    public void Validation(int id){
        annoncerepository.Validation(id);
    }

    public int getNombreAnnoncePersonne(int idpersonne) {
        return annoncerepository.countByPersonneId(idpersonne);
    }

    public int getNombreAnnonceVenduPersonne(int idpersonne) {
        return annoncerepository.countByPersonneIdAndEtat(idpersonne,10);
    }

    public List<StatistiqueComission> getStatistiqueComission(int annee){
        List<StatistiqueComission> statistiqueComissions = new ArrayList<StatistiqueComission>();
        for(int i=1; i< 13; i++) {
            StatistiqueComission statistiqueComission = new StatistiqueComission();
            statistiqueComission.setAnnee(annee);
            statistiqueComission.setMois(i);
            statistiqueComission.setPrix(getComission(i,annee));
            statistiqueComissions.add(statistiqueComission);
        }
        return statistiqueComissions;
    }

    public double getComission (int mois, int annee) {
        double rep =0;
        List<Annonce> list =  annoncerepository.findAnnonceByMonthAndYearAndEtat(mois,annee);
        for(int i=0; i<list.size(); i++) {
            rep = rep+ annoncerepository.prixCommission(list.get(i).getId());
        }
        return rep;

    }
}
