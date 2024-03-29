package com.bibliotheque.service.contract;

import com.bibliotheque.entity.EmpruntEntity;
import com.bibliotheque.entity.MembreEntity;

import java.util.List;

public interface EmpruntEntityService {

    public EmpruntEntity getEmpruntById(int id);
    public List<EmpruntEntity> getAllEmprunts();
    public boolean addEmprunt(EmpruntEntity emprunt);
    public boolean updateEmprunt(EmpruntEntity emprunt);
    public boolean deleteEmpruntById(int id);
    public List<EmpruntEntity> getAllEmpruntsWhereDateFinIsBeforeDateToday();
    boolean updateEmpruntTermine(EmpruntEntity emprunt);
    List<EmpruntEntity> getAllEmpruntsByLivreId(int livreId);
}
