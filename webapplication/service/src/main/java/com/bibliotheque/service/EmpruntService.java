package com.bibliotheque.service;

import com.bibliotheque.repository.repository.EmpruntRepository;
import com.bibliotheque.repository.repository.ExemplaireRepository;
import com.bibliotheque.repository.repository.LivreRepository;
import livres.wsdl.EmpruntType;
import livres.wsdl.ExemplaireType;
import livres.wsdl.LivreType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class EmpruntService {

    @Autowired
    private EmpruntRepository repository;

    @Autowired
    private LivreRepository livreRepository;

    @Autowired
    private ExemplaireRepository exemplaireRepository;

    @Autowired
    private EmpruntRepository empruntRepository;

    public EmpruntType getEmpruntById(int id) {

        return this.repository.getEmpruntById(id);
    }

    public List<ExemplaireType> getAllExemplairesEmpruntes(List<EmpruntType> listeEmprunts) {

        List<ExemplaireType> listeExemplaires = new ArrayList<>();

        for (EmpruntType empruntType : listeEmprunts) {
            ExemplaireType exemplaireType = exemplaireRepository.exemplaireById(empruntType.getExemplaireid());
            listeExemplaires.add(exemplaireType);
        }

        return listeExemplaires;

    }

    public List<LivreType> getAllLivresEmpruntes(List<ExemplaireType> listeExemplaires) {

        List<LivreType> listeLivres = new ArrayList<>();
        List<EmpruntType> listeEmprunts = new ArrayList<>();

        for (ExemplaireType exemplaireType : listeExemplaires) {
            LivreType livreType = livreRepository.livreById(exemplaireType.getLivreid());
            listeLivres.add(livreType);

        }

        return listeLivres;
    }

    public List<EmpruntType> getAllEmprunts() {

        return this.repository.getAllEmprunts();
    }

    public List<EmpruntType> getAllEmpruntsWhereDateFinIsBeforeDateToday() {

        return this.repository.getAllEmpruntsWhereDateFinIsBeforeDateToday();
    }


    public String updateEmprunt(EmpruntType empruntType) {

        return this.empruntRepository.updateEmprunt(empruntType);

    }

    public String updateRelanceEmprunt(EmpruntType empruntType) {
        return this.empruntRepository.updateRelanceEmprunt(empruntType);
    }

    public String updateEmpruntTermine(EmpruntType empruntType) {
        return this.empruntRepository.updateEmpruntTermine(empruntType);
    }

    public String addEmprunt(EmpruntType empruntType) {

        return this.empruntRepository.addEmprunt(empruntType);
    }

    public List<EmpruntType> getAllEmpruntsByLivreId(int livreId){
        return this.repository.getAllEmpruntsByLivreId(livreId);
    }

    public List<Long> joursRestantsEmprunt(List<EmpruntType> empruntTypeList) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        List<Long> jourRestantEmprunt = new ArrayList<>();

        Date toDay = new Date();

        for (EmpruntType empruntType : empruntTypeList) {

            String dateFinEmprunt = dateFormat.format(dateFormat.parse(empruntType.getDateFin().toString()));
            Date dateFinEmpruntConverted = dateFormat.parse(dateFinEmprunt);

            long UNE_HEURE = 60 * 60 * 1000L;
            long numberDaysBeforeReturn =  (dateFinEmpruntConverted.getTime() - toDay.getTime() + UNE_HEURE) / (UNE_HEURE * 24);
            jourRestantEmprunt.add(numberDaysBeforeReturn);
        }

        Collections.sort(jourRestantEmprunt);

        return jourRestantEmprunt;
    }


    public List<EmpruntType> trieEmpruntsParDateDeFin(List<EmpruntType> empruntTypeList, List<Long> jourRestantEmprunt) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<EmpruntType> trieEmpruntParDateDeFin = new ArrayList<>();

        Date toDay = new Date();

        for (Long jourRestant : jourRestantEmprunt) {
            for (EmpruntType empruntType : empruntTypeList) {
                String dateFinEmpruntStr = dateFormat.format(dateFormat.parse(empruntType.getDateFin().toString()));
                Date dateFinEmprunt = dateFormat.parse(dateFinEmpruntStr);

                long UNE_HEURE = 60 * 60 * 1000L;
                long numberDaysBeforeReturn =  (dateFinEmprunt.getTime() - toDay.getTime() + UNE_HEURE) / (UNE_HEURE * 24);

                if (jourRestant == numberDaysBeforeReturn) {
                    trieEmpruntParDateDeFin.add(empruntType);
                }
            }
        }

        return trieEmpruntParDateDeFin;
    }

}
