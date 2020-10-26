package com.bibliotheque.service;

import com.bibliotheque.repository.repository.LivreRepository;
import com.bibliotheque.repository.repository.ReservationRepository;
import livres.wsdl.ReservationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private LivreRepository livreRepository;

    public String addReservation(ReservationType reservationType){
       return this.reservationRepository.addReservation(reservationType);
    }

    public List<ReservationType> getReservationsByCompteId(int membreId){
        return this.reservationRepository.getListeReservationsByMembre(membreId);
    }

    public List<ReservationType> getAllReservationsByCompte(int membreId){
        return this.reservationRepository.getListeReservationsByMembre(membreId);
    }

    public List<ReservationType> getReservationsByLivre(int livreId){
        return this.reservationRepository.getListReservationsByLivre(livreId);
    }

    public List<ReservationType> reservationTypeListEnCours(List<ReservationType> listeReservations) {

        List<ReservationType> reservationTypeList = new ArrayList<>();

        for (ReservationType reservationType : listeReservations){
            if (!reservationType.getStatut().equals("Annuler")){
                reservationTypeList.add(reservationType);
            }
        }

        return reservationTypeList;
    }
}
