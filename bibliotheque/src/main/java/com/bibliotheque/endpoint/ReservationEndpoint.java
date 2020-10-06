package com.bibliotheque.endpoint;

import com.bibliotheque.entity.ReservationEntity;
import com.bibliotheque.gs_ws.*;
import com.bibliotheque.service.contract.ReservationEntityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

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

            calendar.setTime(entity.getDateResaDisponible());
            XMLGregorianCalendar dateDemandeDeResa = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);

            reservationType.setId(entity.getId());
            reservationType.setDateResaDisponible(dateDemandeDeResa);
            reservationType.setLivreid(entity.getLivreId());
            reservationType.setNumPositionResa(entity.getNumPositionResa());
            reservationType.setStatut(entity.getStatut());
            reservationType.setMembreid(entity.getMembreid());

            reservationTypeList.add(reservationType);
        }

        response.getReservationList().addAll(reservationTypeList);

        return response;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getListReservationByOuvrageIdRequest")
    @ResponsePayload
    public GetListReservationByLivreIdResponse getListReservationByOuvrageId(@RequestPayload GetListReservationByLivreIdRequest request) throws DatatypeConfigurationException {
        GetListReservationByLivreIdResponse response = new GetListReservationByLivreIdResponse();
        List<ReservationEntity> reservationEntityList = reservationEntityService.getAllReservationsByLivre(request.getLivreid());

        List<ReservationType> reservationTypeList = new ArrayList<>();

        for (ReservationEntity entity : reservationEntityList) {
            ReservationType reservationType = new ReservationType();
            GregorianCalendar calendar = new GregorianCalendar();

            calendar.setTime(entity.getDateResaDisponible());
            XMLGregorianCalendar dateDemandeDeResa = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);

            reservationType.setId(entity.getId());
            reservationType.setDateResaDisponible(dateDemandeDeResa);
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

            calendar.setTime(entity.getDateResaDisponible());
            XMLGregorianCalendar dateResaDisponible = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);

            reservationType.setId(entity.getId());
            reservationType.setDateResaDisponible(dateResaDisponible);
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
    public AddReservationResponse addReservationResponse(@RequestPayload  AddReservationRequest request) throws DatatypeConfigurationException, ParseException {
        AddReservationResponse response = new AddReservationResponse();
        ReservationType reservationType = new ReservationType();
        ReservationEntity reservationEntity = new ReservationEntity();
        ServiceStatus serviceStatus = new ServiceStatus();

        BeanUtils.copyProperties(request.getReservationType(), reservationEntity);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        reservationEntity.setDateResaDisponible(dateFormat.parse(request.getReservationType().getDateResaDisponible().toString()));

        try {
            ReservationEntity savedReservationEntity = reservationEntityService.addReservation(reservationEntity);
            BeanUtils.copyProperties(savedReservationEntity, reservationType);

            serviceStatus.setStatusCode("SUCCESS");
            serviceStatus.setMessage("Content Added Successfully");

        } catch (DataIntegrityViolationException pEX) {
            serviceStatus.setStatusCode("CONFLICT");
            serviceStatus.setMessage("Exception while adding Entity");
        } catch (Exception pEX) {
            pEX.printStackTrace();
        }

        response.setReservationType(reservationType);
        response.setServiceStatus(serviceStatus);

        return response;
    }


}
