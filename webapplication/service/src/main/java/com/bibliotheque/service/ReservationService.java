package com.bibliotheque.service;

import com.bibliotheque.repository.repository.LivreRepository;
import com.bibliotheque.repository.repository.ReservationRepository;
import livres.wsdl.ReservationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
