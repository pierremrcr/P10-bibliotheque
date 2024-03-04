package com.bibliotheque.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bibliotheque.entity.EmpruntEntity;
import com.bibliotheque.entity.ExemplaireEntity;
import com.bibliotheque.repository.EmpruntEntityRepository;
import com.bibliotheque.repository.ExemplaireEntityRepository;
import com.bibliotheque.repository.LivreEntityRepository;
import com.bibliotheque.service.contract.EmpruntEntityService;

@Service
@Transactional
public class EmpruntEntityServiceImpl implements EmpruntEntityService {

    @Autowired
    EmpruntEntityRepository repository;

    @Autowired
    ExemplaireEntityRepository exemplaireEntityRepository;
    
    @Autowired
    LivreEntityRepository livreEntityRepository;

    @Override
    public EmpruntEntity getEmpruntById(int id) {

        return this.repository.findById(id);
    }

    @Override
    public List<EmpruntEntity> getAllEmprunts() {
        List<EmpruntEntity> listeEmprunts = new ArrayList<>();
        this.repository.findAll().forEach(empruntEntity -> listeEmprunts.add(empruntEntity));
        return listeEmprunts;
    }

    @Override
    public boolean addEmprunt(EmpruntEntity emprunt) {

        try {
             this.repository.save(emprunt);
             return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateEmprunt(EmpruntEntity emprunt) {
        try {
            this.repository.save(emprunt);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteEmpruntById(int id) {
        try {
            this.repository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<EmpruntEntity> getAllEmpruntsWhereDateFinIsBeforeDateToday() {
        List<EmpruntEntity> listeEmprunts = new ArrayList<>();
        this.repository.findAllEmpruntWhereDateRetourIsBeforDateToday().forEach(empruntEntity -> listeEmprunts.add(empruntEntity));
        return listeEmprunts;
    }

    @Override
    public boolean updateEmpruntTermine(EmpruntEntity emprunt) {
        int exemplaireId = emprunt.getExemplaireid();
        ExemplaireEntity exemplaireEntity = this.exemplaireEntityRepository.findById(exemplaireId);
        exemplaireEntity.setDisponibilite(true);
        emprunt.setExemplaireEntity(exemplaireEntity);
        try {
            this.repository.save(emprunt);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<EmpruntEntity> getAllEmpruntsByLivreId(int livreId) {
        List<EmpruntEntity> listeEmprunts = new ArrayList<>();
        List<ExemplaireEntity> listeExemplaires = new ArrayList<>();

        this.livreEntityRepository.findAllExemplairesByLivreId(livreId).forEach(e-> listeExemplaires.add(e));

        for(ExemplaireEntity exemplaire : listeExemplaires){
            this.repository.findAllEmpruntsByLivreId(exemplaire.getId()).forEach(e-> listeEmprunts.add(e));
        }

        return listeEmprunts;


    }


}


