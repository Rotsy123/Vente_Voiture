package tech.chillo.sa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Optional;
import java.util.List;
import tech.chillo.sa.repository.PersonneRepository;
import tech.chillo.sa.repository.AnnoncesRepository;
import tech.chillo.sa.repository.AnnonceRepository;
import tech.chillo.sa.repository.VoitureRepository;
import tech.chillo.sa.repository.DetailsVoitureRepository;
import tech.chillo.sa.repository.MarqueRepository;
import tech.chillo.sa.repository.ModeleRepository;
import tech.chillo.sa.repository.CarburantRepository;
import tech.chillo.sa.repository.TransmissionRepository;
import tech.chillo.sa.entites.Annonce;
import tech.chillo.sa.entites.DetailsVoiture;
import tech.chillo.sa.entites.Personne;
import tech.chillo.sa.entites.Annonces;
import tech.chillo.sa.entites.Marque;
import tech.chillo.sa.entites.Carburant;
import tech.chillo.sa.entites.Transmission;
import tech.chillo.sa.entites.Modele;
import tech.chillo.sa.model.Voiture;
// import tech.chillo.sa.service.AnnonceService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AnnoncesService {

    private final AnnoncesRepository annoncesRepository;
    private final AnnonceRepository annonceRepository;
    private final PersonneRepository personneRepository;
    private final VoitureRepository voitureRepository;
    private final MarqueRepository marqueRepository;
    private final ModeleRepository modeleRepository;
    private final DetailsVoitureRepository detailsVoitureRepository;
    private final CarburantRepository carburantRepository;
    private final TransmissionRepository transmissionRepository;

    @Autowired
    public AnnoncesService(AnnoncesRepository annoncesRepository,PersonneRepository personneRepository,
        VoitureRepository voitureRepository,MarqueRepository marqueRepository,ModeleRepository modeleRepository,
        DetailsVoitureRepository detailsVoitureRepository,CarburantRepository carburantRepository,
        TransmissionRepository transmissionRepository,AnnonceRepository annonceRepository) {
        this.annoncesRepository = annoncesRepository;
        this.personneRepository = personneRepository;
        this.voitureRepository = voitureRepository;
        this.marqueRepository = marqueRepository;
        this.modeleRepository =modeleRepository;
        this.detailsVoitureRepository = detailsVoitureRepository;
        this.carburantRepository = carburantRepository;
        this.transmissionRepository = transmissionRepository;
        this.annonceRepository = annonceRepository;
    }

    public List<Annonces> getAllAnnoncesByIdpersonne(int idpersonne) {
        return annoncesRepository.getAnnoncesByPersonneId(idpersonne);
    }
    
    public Annonces createAnnonces(Annonce annonce) {
        Annonces annonces = new Annonces();
        // DetailsVoiture detailsVoiture = new DetailsVoiture();
        Optional<tech.chillo.sa.entites.Voiture> voiture = voitureRepository.findById(annonce.getVoiture().getId());
        Optional<tech.chillo.sa.entites.Personne> personne = personneRepository.findById(annonce.getPersonne().getId());

       if (personne.isPresent() && voiture.isPresent()) {
            tech.chillo.sa.model.Voiture v = new tech.chillo.sa.model.Voiture();
            tech.chillo.sa.model.DetailsVoiture dv = new tech.chillo.sa.model.DetailsVoiture();
            Personne pers = personne.get();
            annonces.setPersonne(pers);

            Optional<Carburant> carburant = carburantRepository.findById(voiture.get().getDetailsVoiture().getCarburant().getId());
            Optional<Transmission> transmission = transmissionRepository.findById(voiture.get().getDetailsVoiture().getTransmission().getId());
            Optional<Marque> marque = marqueRepository.findById(voiture.get().getMarque().getId());
            Optional<Modele> modele = modeleRepository.findById(voiture.get().getModele().getId());
            if (carburant.isPresent() && transmission.isPresent() && marque.isPresent() && modele.isPresent()) {
                voiture.get().setMarque(marque.get());
                voiture.get().setModele(modele.get());
                voiture.get().getDetailsVoiture().setCarburant(carburant.get());
                voiture.get().getDetailsVoiture().setTransmission(transmission.get());
                v.setDetailsVoiture(dv.getDetailsVoiture(voiture.get().getDetailsVoiture()));
                annonces.setVoiture(v.getVoiture(voiture.get()));
            }

            annonces.setDatepublication(annonce.getDateplublication());
            return annonces;
        }
        return null;
    }

    @Transactional
    public Annonces saveAnnonces(Annonce annonce) {
        Annonces annonces = createAnnonces(annonce);
        return annoncesRepository.save(annonces);
    }

    public List<Annonces> getListeAnnoncePersonneId (int personneid) {
        List<Annonce> listeannonce = annonceRepository.findByPersonneId(personneid);

        List<Annonces> listeannonces = new ArrayList<Annonces>();
        for (var annonce : listeannonce) {
            Annonces annonces = createAnnonces(annonce);
            listeannonces.add(annonces);
        }
        return listeannonces;
    }

    public List<Annonces> getListeAnnonceOrderByBouquet () {
        List<Annonce> listeannonce = annonceRepository.GetAllAnnonceOrderByBouquet();

        List<Annonces> listeannonces = new ArrayList<Annonces>();
        for (var annonce : listeannonce) {
            Annonces annonces = createAnnonces(annonce);
            listeannonces.add(annonces);
        }
        return listeannonces;
    }
    
}
