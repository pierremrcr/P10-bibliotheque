package com.bibliotheque.repository.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bibliotheque.repository.client.EmpruntClient;

import livres.wsdl.AddEmpruntResponse;
import livres.wsdl.EmpruntType;
import livres.wsdl.GetAllEmpruntResponse;
import livres.wsdl.GetAllEmpruntsByLivreIdResponse;
import livres.wsdl.GetAllEmpruntsWhereDateFinIsBeforeDateTodayResponse;
import livres.wsdl.GetEmpruntByIdResponse;
import livres.wsdl.UpdateEmpruntResponse;
import livres.wsdl.UpdateEmpruntTermineResponse;
import livres.wsdl.UpdateRelanceEmpruntResponse;

@Component
public class EmpruntRepository {


    @Autowired
    private EmpruntClient client;

    public EmpruntType getEmpruntById(int id) {
        GetEmpruntByIdResponse response = this.client.getEmpruntById(id);
        return response.getEmpruntType();
    }

    public String updateEmprunt(EmpruntType empruntType) {
        UpdateEmpruntResponse response = this.client.updateEmprunt(empruntType);
        return response.getServiceStatus().getStatusCode();
    }

    public String updateRelanceEmprunt(EmpruntType empruntType) {
        UpdateRelanceEmpruntResponse response = this.client.updateRelanceEmprunt(empruntType);
        return response.getServiceStatus().getStatusCode();
    }

    public String updateEmpruntTermine(EmpruntType empruntType) {
        UpdateEmpruntTermineResponse response = this.client.updateEmpruntTermine(empruntType);
        return response.getServiceStatus().getStatusCode();
    }


    public String addEmprunt(EmpruntType empruntType) {
        AddEmpruntResponse response = this.client.addEmprunt(empruntType);
        return response.getServiceStatus().getStatusCode();
    }

    public List<EmpruntType> getAllEmprunts() {
        GetAllEmpruntResponse response = this.client.getAllEmprunts();
        return response.getEmpruntType();
    }

    public List<EmpruntType> getAllEmpruntsWhereDateFinIsBeforeDateToday() {
        GetAllEmpruntsWhereDateFinIsBeforeDateTodayResponse response = this.client.getAllEmpruntsWhereDateFinIsBeforeDateToday();
        return response.getEmpruntType();
    }

    public List<EmpruntType> getAllEmpruntsByLivreId(int livreId){
        GetAllEmpruntsByLivreIdResponse response = this.client.getAllEmpruntsByLivreId(livreId);
        return response.getListeEmpruntsByLivreId();
    }



}