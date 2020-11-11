package com.bibliotheque.repository.repository;

import com.bibliotheque.repository.client.ReservationClient;
import livres.wsdl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReservationRepository {

    @Autowired
    private ReservationClient reservationClient;

    public String addReservation(ReservationType reservation){
        AddReservationResponse response = this.reservationClient.addReservation(reservation);
        return response.getServiceStatus().getStatusCode();
    }

    public List<ReservationType> getListeReservationsByMembre(int membreid){
        GetListReservationByCompteIdResponse response = this.reservationClient.getReservationByMembreId(membreid);
        return response.getReservationListByCompteId();
    }

    public List<ReservationType> getListReservationsByLivre(int livreId){
        GetListReservationByLivreIdResponse response = this.reservationClient.getReservationByLivreId(livreId);
        return response.getReservationListByLivreId();
    }


    public List<ReservationType> getAllReservations() {
        GetAllReservationResponse response = this.reservationClient.getAllReservations();
        return response.getReservationList();
    }

    public String deleteReservation(Integer reservationId) {
        DeleteReservationResponse response = this.reservationClient.deleteReservation(reservationId);
        return response.getServiceStatus().getStatusCode();
    }

    public String updateReservation(ReservationType reservation) {
        UpdateReservationResponse response = this.reservationClient.updateReservation(reservation);
        return response.getServiceStatus().getStatusCode();
    }


}
