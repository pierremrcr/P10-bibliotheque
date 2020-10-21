package com.bibliotheque.repository.client;

import livres.wsdl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class ReservationClient extends WebServiceGatewaySupport {

    private static final Logger log = LoggerFactory.getLogger(ReservationClient.class);

    public AddReservationResponse addReservation(ReservationType reservation){
        AddReservationRequest request = new AddReservationRequest();
        request.setReservationType(reservation);
        return (AddReservationResponse) getWebServiceTemplate().marshalSendAndReceive(request);
    }

    public GetListReservationByCompteIdResponse getReservationByMembreId(int membreId){
        GetListReservationByCompteIdRequest request = new GetListReservationByCompteIdRequest();
        request.setMembreid(membreId);
        return (GetListReservationByCompteIdResponse) getWebServiceTemplate().marshalSendAndReceive(request);
    }
}
