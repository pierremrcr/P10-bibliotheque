package com.bibliotheque.service.contract;

import com.bibliotheque.entity.LivreEntity;
import com.bibliotheque.entity.MembreEntity;
import com.bibliotheque.entity.ReservationEntity;

import java.util.List;
import java.util.Optional;

public interface ReservationEntityService {

    ReservationEntity getReservationById(int id);
    List<ReservationEntity> getAllReservations();
    ReservationEntity addReservation(ReservationEntity reservation);
    ReservationEntity updateReservation(ReservationEntity reservation);
    void deleteReservation(int id);
    List<ReservationEntity> getAllReservationsByLivre(int livreId);
    List<ReservationEntity> getAllReservationByMembre(int membreId);



}
