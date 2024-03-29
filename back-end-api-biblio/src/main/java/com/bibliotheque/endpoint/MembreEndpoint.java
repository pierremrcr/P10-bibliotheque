package com.bibliotheque.endpoint;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.bibliotheque.entity.EmpruntEntity;
import com.bibliotheque.entity.ExemplaireEntity;
import com.bibliotheque.entity.LivreEntity;
import com.bibliotheque.entity.MembreEntity;
import com.bibliotheque.entity.ReservationEntity;
import com.bibliotheque.gs_ws.AddMembreRequest;
import com.bibliotheque.gs_ws.AddMembreResponse;
import com.bibliotheque.gs_ws.DeleteMembreRequest;
import com.bibliotheque.gs_ws.DeleteMembreResponse;
import com.bibliotheque.gs_ws.EmpruntType;
import com.bibliotheque.gs_ws.ExemplaireType;
import com.bibliotheque.gs_ws.GetAllMembresRequest;
import com.bibliotheque.gs_ws.GetAllMembresResponse;
import com.bibliotheque.gs_ws.GetMembreByIdRequest;
import com.bibliotheque.gs_ws.GetMembreByIdResponse;
import com.bibliotheque.gs_ws.LivreType;
import com.bibliotheque.gs_ws.MembreType;
import com.bibliotheque.gs_ws.ReservationType;
import com.bibliotheque.gs_ws.ServiceStatus;
import com.bibliotheque.gs_ws.UpdateMembreRequest;
import com.bibliotheque.gs_ws.UpdateMembreResponse;
import com.bibliotheque.service.contract.MembreEntityService;

@Endpoint
public class MembreEndpoint {

    public static final String NAMESPACE_URI = "http://www.bibliotheque.com/livres-ws";

    private MembreEntityService membreEntityService;

    public MembreEndpoint(){

    }

    @Autowired
    public MembreEndpoint(MembreEntityService membreEntityService) {

        this.membreEntityService = membreEntityService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getMembreByIdRequest")
    @ResponsePayload
    public GetMembreByIdResponse getMembreById(@RequestPayload GetMembreByIdRequest request)  throws DatatypeConfigurationException {
        GetMembreByIdResponse response = new GetMembreByIdResponse();
        MembreEntity membreEntity = membreEntityService.getMembreById(request.getId());
        MembreType membreType = new MembreType();

        for(EmpruntEntity empruntEntity : membreEntity.getListeEmprunts()){
            EmpruntType empruntType = new EmpruntType();
            ExemplaireType exemplaireType = new ExemplaireType();
            LivreType livreType = new LivreType();
            GregorianCalendar dateDebut = new GregorianCalendar();
            GregorianCalendar dateFin = new GregorianCalendar();
            dateDebut.setTime(empruntEntity.getDate_debut());
            dateFin.setTime(empruntEntity.getDate_fin());
            XMLGregorianCalendar dateConvertedDebut = DatatypeFactory.newInstance().newXMLGregorianCalendar(dateDebut);
            XMLGregorianCalendar dateConvertedFin = DatatypeFactory.newInstance().newXMLGregorianCalendar(dateFin);

            empruntType.setDateDebut(dateConvertedDebut);
            empruntType.setDateFin(dateConvertedFin);
            BeanUtils.copyProperties(empruntEntity, empruntType);
            membreType.getListeEmprunts().add(empruntType);

            ExemplaireEntity exemplaireEntity = empruntEntity.getExemplaireEntity();
            LivreEntity livreEntity = empruntEntity.getExemplaireEntity().getLivre();


            BeanUtils.copyProperties(exemplaireEntity, exemplaireType);
            BeanUtils.copyProperties(livreEntity, livreType);

            empruntType.setExemplaireEntity(exemplaireType);
            exemplaireType.setLivre(livreType);

        }
        
        for(ReservationEntity reservationEntity : membreEntity.getListeReservations()){
            ReservationType reservationType = new ReservationType();
            LivreType livreType = new LivreType();
            GregorianCalendar dateResa = new GregorianCalendar();
            dateResa.setTime(reservationEntity.getDateDispo());
            XMLGregorianCalendar dateResaConverted = DatatypeFactory.newInstance().newXMLGregorianCalendar(dateResa);
            reservationType.setDateDispo(dateResaConverted);
            BeanUtils.copyProperties(reservationEntity, reservationType);

            membreType.getListeReservation().add(reservationType);

            LivreEntity livreEntity = reservationEntity.getLivreEntity();
            BeanUtils.copyProperties(livreEntity, livreType);
            reservationType.setLivreEntity(livreType);


        }

        BeanUtils.copyProperties(membreEntity, membreType);
        response.setMembreType(membreType);
        return response;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllMembresRequest")
    @ResponsePayload
    public GetAllMembresResponse getAllMembres(@RequestPayload GetAllMembresRequest request) throws DatatypeConfigurationException {
        GetAllMembresResponse response = new GetAllMembresResponse();
        List<MembreType> membreTypeList = new ArrayList<MembreType>();
        List<MembreEntity> membreEntityList = membreEntityService.getAllMembres();
        for (MembreEntity entity : membreEntityList) {
            MembreType membreType = new MembreType();

            for(EmpruntEntity empruntEntity : entity.getListeEmprunts()){
                EmpruntType empruntType = new EmpruntType();

                GregorianCalendar dateDebut = new GregorianCalendar();
                GregorianCalendar dateFin = new GregorianCalendar();
                dateDebut.setTime(empruntEntity.getDate_debut());
                dateFin.setTime(empruntEntity.getDate_fin());
                XMLGregorianCalendar dateConvertedDebut = DatatypeFactory.newInstance().newXMLGregorianCalendar(dateDebut);
                XMLGregorianCalendar dateConvertedFin = DatatypeFactory.newInstance().newXMLGregorianCalendar(dateFin);

                empruntType.setDateDebut(dateConvertedDebut);
                empruntType.setDateFin(dateConvertedFin);

                BeanUtils.copyProperties(empruntEntity, empruntType);
                membreType.getListeEmprunts().add(empruntType);

            }

            BeanUtils.copyProperties(entity, membreType);
            membreTypeList.add(membreType);
        }
        response.getMembreType().addAll(membreTypeList);

        return response;

    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addMembreRequest")
    @ResponsePayload
    public AddMembreResponse addMembre(@RequestPayload AddMembreRequest request) {
        AddMembreResponse response = new AddMembreResponse();
        MembreType newMembreType = new MembreType();
        ServiceStatus serviceStatus = new ServiceStatus();

        MembreEntity newMembreEntity = new MembreEntity();

        BeanUtils.copyProperties(request.getMembreType(),newMembreEntity);
        
        boolean flag = membreEntityService.addMembre(newMembreEntity);

        if(flag == false) {
            serviceStatus.setStatusCode("CONFLICT");
            serviceStatus.setMessage("Exception while updating Entity=" + request.getMembreType().getNom());
        } else {
            serviceStatus.setStatusCode("SUCCESS");
            serviceStatus.setMessage("Content updated Successfully");
        }

        response.setMembreType(newMembreType);
        response.setServiceStatus(serviceStatus);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateMembreRequest")
    @ResponsePayload
    public UpdateMembreResponse updateMembre(@RequestPayload UpdateMembreRequest request) {
        UpdateMembreResponse response = new UpdateMembreResponse();
        ServiceStatus serviceStatus = new ServiceStatus();

        // 1. Find if livre available
        MembreEntity membreFromDB = membreEntityService.getMembreById(request.getMembreType().getId());

        if(membreFromDB == null) {
            serviceStatus.setStatusCode("NOT FOUND");
            serviceStatus.setMessage("Membre = " + request.getMembreType().getNom() + " not found");
        } else {

            // 2. Get updated livre information from the request
            membreFromDB.setNom(request.getMembreType().getNom());
            membreFromDB.setPrenom(request.getMembreType().getPrenom());
            membreFromDB.setAdresseMail(request.getMembreType().getAdresseMail());
            membreFromDB.setMotDePasse((request.getMembreType().getMotDePasse()));
            membreFromDB.setTelephone(request.getMembreType().getTelephone());
            membreFromDB.setAdresse(request.getMembreType().getAdresse());
            membreFromDB.setCodePostal(request.getMembreType().getCodePostal());
            membreFromDB.setVille(request.getMembreType().getVille());

            // 3. update the livre in database
            BeanUtils.copyProperties(membreFromDB, request.getMembreType());
            boolean flag = membreEntityService.updateMembre(membreFromDB);

            if(flag == false) {
                serviceStatus.setStatusCode("CONFLICT");
                serviceStatus.setMessage("Exception while updating Entity=" + request.getMembreType().getNom());
            }else {
                serviceStatus.setStatusCode("SUCCESS");
                serviceStatus.setMessage("Content updated Successfully");
            }

        }

        response.setServiceStatus(serviceStatus);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteMembreRequest")
    @ResponsePayload
    public DeleteMembreResponse deleteMembre(@RequestPayload DeleteMembreRequest request) {
        DeleteMembreResponse response = new DeleteMembreResponse();
        ServiceStatus serviceStatus = new ServiceStatus();

        boolean flag = membreEntityService.deleteMembreById(request.getId());

        if (flag == false) {
            serviceStatus.setStatusCode("FAIL");
            serviceStatus.setMessage("Exception while deletint Entity id=" + request.getId());
        } else {
            serviceStatus.setStatusCode("SUCCESS");
            serviceStatus.setMessage("Content Deleted Successfully");
        }

        response.setServiceStatus(serviceStatus);
        return response;
    }


}
