package com.bibliotheque.endpoint;



import com.bibliotheque.entity.ReservationEntity;
import com.bibliotheque.gs_ws.*;
import com.bibliotheque.service.contract.ReservationEntityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import java.util.*;



@Endpoint
public class ReservationEndpoint {

    public static final String NAMESPACE_URI = "http://www.bibliotheque.com/livres-ws";

    private ReservationEntityService reservationEntityService;

    public ReservationEndpoint(){
    }

    @Autowired
    public ReservationEndpoint(ReservationEntityService reservationEntityService){
        this.reservationEntityService = reservationEntityService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllReservationRequest")
    @ResponsePayload
    public GetAllReservationResponse getAllReservation(@RequestPayload GetAllReservationRequest request) throws DatatypeConfigurationException {

        GetAllReservationResponse response = new GetAllReservationResponse();
        List<ReservationEntity> reservationEntityList = reservationEntityService.getAllReservations();
        List<ReservationType> reservationTypeList = new ArrayList<>();

        for (ReservationEntity entity : reservationEntityList) {
            ReservationType reservationType = new ReservationType();
            GregorianCalendar calendar = new GregorianCalendar();

            calendar.setTime(entity.getDateResa());
            XMLGregorianCalendar dateDemandeDeResa = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);

            reservationType.setId(entity.getId());
            reservationType.setDateResa(dateDemandeDeResa);
            reservationType.setLivreid(entity.getLivreId());
            reservationType.setNumPositionResa(entity.getNumPositionResa());
            reservationType.setStatut(entity.getStatut());
            reservationType.setMembreid(entity.getMembreid());

            reservationTypeList.add(reservationType);
        }

        response.getReservationList().addAll(reservationTypeList);

        return response;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getListReservationByLivreIdRequest")
    @ResponsePayload
    public GetListReservationByLivreIdResponse getListReservationByOuvrageId(@RequestPayload GetListReservationByLivreIdRequest request) throws DatatypeConfigurationException {
        GetListReservationByLivreIdResponse response = new GetListReservationByLivreIdResponse();
        List<ReservationEntity> reservationEntityList = reservationEntityService.getAllReservationsByLivre(request.getLivreid());

        List<ReservationType> reservationTypeList = new ArrayList<>();

        for (ReservationEntity entity : reservationEntityList) {
            ReservationType reservationType = new ReservationType();
            GregorianCalendar calendar = new GregorianCalendar();

            calendar.setTime(entity.getDateResa());
            XMLGregorianCalendar dateDemandeDeResa = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);

            reservationType.setId(entity.getId());
            reservationType.setDateResa(dateDemandeDeResa);
            reservationType.setLivreid(entity.getLivreId());
            reservationType.setNumPositionResa(entity.getNumPositionResa());
            reservationType.setStatut(entity.getStatut());
            reservationType.setMembreid(entity.getMembreid());

            reservationTypeList.add(reservationType);
        }

        response.getReservationListByLivreId().addAll(reservationTypeList);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getListReservationByCompteIdRequest")
    @ResponsePayload
    public GetListReservationByCompteIdResponse getListReservationByCompteId(@RequestPayload GetListReservationByCompteIdRequest request) throws DatatypeConfigurationException {
        GetListReservationByCompteIdResponse response = new GetListReservationByCompteIdResponse();
        List<ReservationEntity> reservationEntityList = reservationEntityService.getAllReservationByMembre(request.getMembreid());
        List<ReservationType> reservationTypeList = new ArrayList<>();

        for (ReservationEntity entity : reservationEntityList) {
            ReservationType reservationType = new ReservationType();
            GregorianCalendar calendar = new GregorianCalendar();

            calendar.setTime(entity.getDateResa());
            XMLGregorianCalendar dateResaDisponible = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);

            reservationType.setId(entity.getId());
            reservationType.setDateResa(dateResaDisponible);
            reservationType.setLivreid(entity.getLivreId());
            reservationType.setNumPositionResa(entity.getNumPositionResa());
            reservationType.setStatut(entity.getStatut());
            reservationType.setMembreid(entity.getMembreid());

            reservationTypeList.add(reservationType);
        }

        response.getReservationListByCompteId().addAll(reservationTypeList);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addReservationRequest")
    @ResponsePayload
    public AddReservationResponse addReservationResponse(@RequestPayload  AddReservationRequest request) throws DatatypeConfigurationException {
        AddReservationResponse response = new AddReservationResponse();
        ReservationType reservationType = new ReservationType();
        ReservationEntity reservationEntity = new ReservationEntity();
        ServiceStatus serviceStatus = new ServiceStatus();
        GregorianCalendar dateResa = new GregorianCalendar();
        Date today = Calendar.getInstance().getTime();
        dateResa.setTime(today);
        XMLGregorianCalendar dateResaConverted = DatatypeFactory.newInstance().newXMLGregorianCalendar(dateResa);
        reservationType.setDateResa(dateResaConverted);
        reservationEntity.setDateResa(today);

        reservationEntity.setLivreId(request.getReservationType().getLivreid());
        reservationEntity.setMembreid(request.getReservationType().getMembreid());
        reservationEntity.setStatut(request.getReservationType().getStatut());

        ReservationEntity savedReservationEntity = reservationEntityService.addReservation(reservationEntity);


        if(savedReservationEntity == null) {

            serviceStatus.setStatusCode("CONFLICT");
            serviceStatus.setMessage("Exception while adding Entity");

        } else {

            BeanUtils.copyProperties(savedReservationEntity, reservationType);
            serviceStatus.setStatusCode("SUCCESS");
            serviceStatus.setMessage("Content Added Successfully");

        }

        response.setReservationType(reservationType);
        response.setServiceStatus(serviceStatus);

        return response;
    }


}


