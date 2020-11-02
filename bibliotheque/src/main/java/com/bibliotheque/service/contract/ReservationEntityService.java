package com.bibliotheque.service.contract;

import com.bibliotheque.entity.ReservationEntity;

import java.util.List;

public interface ReservationEntityService {

    ReservationEntity getReservationById(int id);
    List<ReservationEntity> getAllReservations();
    ReservationEntity addReservation(ReservationEntity reservation);
    boolean updateReservation(ReservationEntity reservation);
    boolean deleteReservation(int id);
    List<ReservationEntity> getAllReservationsByLivre(int livreId);
    List<ReservationEntity> getAllReservationByMembre(int membreId);



}
