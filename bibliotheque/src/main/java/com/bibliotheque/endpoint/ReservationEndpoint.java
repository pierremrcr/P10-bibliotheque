package com.bibliotheque.endpoint;



import com.bibliotheque.entity.ExemplaireEntity;
import com.bibliotheque.entity.LivreEntity;
import com.bibliotheque.entity.MembreEntity;
import com.bibliotheque.entity.ReservationEntity;
import com.bibliotheque.gs_ws.*;
import com.bibliotheque.service.contract.ReservationEntityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

        for (ReservationEntity reservationEntity : reservationEntityList) {
            ReservationType reservationType = new ReservationType();
            LivreType livreType = new LivreType();
            MembreType membreType = new MembreType();
            LivreEntity livreEntity = reservationEntity.getLivreEntity();
            MembreEntity membreEntity = reservationEntity.getMembreEntity();

            for(ExemplaireEntity exemplaireEntity : livreEntity.getListeExemplaires()){
                ExemplaireType exemplaireType = new ExemplaireType();
                BeanUtils.copyProperties(exemplaireEntity, exemplaireType);
                livreType.getListeExemplaires().add(exemplaireType);

            }


            BeanUtils.copyProperties(livreEntity, livreType);
            BeanUtils.copyProperties(membreEntity, membreType);

            GregorianCalendar calendar = new GregorianCalendar();

            calendar.setTime(reservationEntity.getDateDispo());
            XMLGregorianCalendar dateDemandeDeResa = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);

            reservationType.setId(reservationEntity.getId());
            reservationType.setDateDispo(dateDemandeDeResa);
            reservationType.setLivreid(reservationEntity.getLivreId());
            reservationType.setLivreEntity(livreType);
            reservationType.setNumPositionResa(reservationEntity.getNumPositionResa());
            reservationType.setStatut(reservationEntity.getStatut());
            reservationType.setMembreid(reservationEntity.getMembreid());
            reservationType.setMembreEntity(membreType);

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

            calendar.setTime(entity.getDateDispo());
            XMLGregorianCalendar dateDemandeDeResa = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);

            reservationType.setId(entity.getId());
            reservationType.setDateDispo(dateDemandeDeResa);
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

            calendar.setTime(entity.getDateDispo());
            XMLGregorianCalendar dateResaDisponible = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);

            reservationType.setId(entity.getId());
            reservationType.setDateDispo(dateResaDisponible);
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
        reservationType.setDateDispo(request.getReservationType().getDateDispo());
        reservationType.setStatut("réservé");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateDispo = dateFormat.parse(request.getReservationType().getDateDispo().toString());
        reservationEntity.setDateDispo(dateDispo);
        reservationEntity.setLivreId(request.getReservationType().getLivreid());
        reservationEntity.setMembreid(request.getReservationType().getMembreid());
        reservationEntity.setStatut("réservé");

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

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteReservationRequest")
    @ResponsePayload
    public DeleteReservationResponse deleteReservation(@RequestPayload DeleteReservationRequest request){
        DeleteReservationResponse response = new DeleteReservationResponse();
        ServiceStatus serviceStatus = new ServiceStatus();

        boolean flag = reservationEntityService.deleteReservation(request.getReservationId());

        if (flag == false) {
            serviceStatus.setStatusCode("FAIL");
            serviceStatus.setMessage("Exception while deleting Entity id=" + request.getReservationId());
        } else {
            serviceStatus.setStatusCode("SUCCESS");
            serviceStatus.setMessage("Content Deleted Successfully");
        }

        response.setServiceStatus(serviceStatus);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateReservationRequest")
    @ResponsePayload
    public UpdateReservationResponse updateReservation(@RequestPayload UpdateReservationRequest request){
        UpdateReservationResponse response = new UpdateReservationResponse();
        ServiceStatus serviceStatus = new ServiceStatus();

        ReservationEntity reservationEntityFromDB = reservationEntityService.getReservationById(request.getReservationType().getId());

        if(reservationEntityFromDB == null) {
            serviceStatus.setStatusCode("CONFLICT");
            serviceStatus.setMessage("Réservation = " + request.getReservationType().getId() + "not found");

        } else {

            boolean flag = reservationEntityService.updateReservation(reservationEntityFromDB);

            if (flag == false) {
                serviceStatus.setStatusCode("CONFLICT");
                serviceStatus.setMessage("Exception while updating Entity=" + request.getReservationType().getId());

            } else {
                serviceStatus.setStatusCode("SUCCESS");
                serviceStatus.setMessage("Content updated Successfully");
            }
        }

        response.setServiceStatus(serviceStatus);
        return response;


        }



    }







