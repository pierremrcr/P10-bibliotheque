package com.bibliotheque.repository.repository;

import com.bibliotheque.repository.client.ReservationClient;
import livres.wsdl.AddReservationResponse;
import livres.wsdl.GetListReservationByCompteIdResponse;
import livres.wsdl.GetListReservationByLivreIdResponse;
import livres.wsdl.ReservationType;
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


}
