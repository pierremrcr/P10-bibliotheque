package com.bibliotheque.service.impl;

import com.bibliotheque.entity.ReservationEntity;
import com.bibliotheque.repository.LivreEntityRepository;
import com.bibliotheque.repository.ReservationEntityRepository;
import com.bibliotheque.service.contract.ReservationEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class ReservationEntityServiceImpl implements ReservationEntityService {

    @Autowired
    ReservationEntityRepository repository;

    @Autowired
    LivreEntityRepository livreEntityRepository;

    @Override
    public ReservationEntity getReservationById(int id) {
        return this.repository.findById(id);
    }

    @Override
    public List<ReservationEntity> getAllReservations() {
        List<ReservationEntity> reservationEntityList = new ArrayList<>();
        this.repository.findAll().forEach(reservationEntity -> reservationEntityList.add(reservationEntity));
        return reservationEntityList;
    }

    @Override
    public ReservationEntity addReservation(ReservationEntity reservation) {
        List<ReservationEntity> reservationEntityList = getAllReservationsByLivre(reservation.getLivreId());
        List<Integer> positionResaList = new ArrayList<>();

        for (ReservationEntity reservationEntity : reservationEntityList){
            positionResaList.add(reservationEntity.getNumPositionResa());
            Collections.sort(positionResaList);
        }

        if(positionResaList.size() > 0){
            reservation.setNumPositionResa(positionResaList.get(positionResaList.size()-1)+1);

        } else {
            reservation.setNumPositionResa(1);
        }

        return this.repository.save(reservation);
    }

    @Override
    public ReservationEntity updateReservation(ReservationEntity reservation) {
        try {
            return this.repository.save(reservation);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteReservation(int id) {
        try {
            ReservationEntity reservationEntity = this.repository.findById(id);
            List<ReservationEntity> listeReservations = getAllReservationsByLivre(reservationEntity.getLivreId());

            for (ReservationEntity reservation : listeReservations) {
                if (reservationEntity.getNumPositionResa() < reservation.getNumPositionResa() && reservationEntity.getNumPositionResa() != 0) {
                    reservation.setNumPositionResa(reservation.getNumPositionResa() - 1);
                    updateReservation(reservation);
                }
            }
            this.repository.deleteById(id);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<ReservationEntity> getAllReservationsByLivre(int livreId) {
        List<ReservationEntity> reservationEntityList = new ArrayList<>();
        for(ReservationEntity entity : this.repository.findAll()){
            if (entity.getLivreId() == livreId){
                reservationEntityList.add(entity);
            }
        }
        return reservationEntityList;
    }

    @Override
    public List<ReservationEntity> getAllReservationByMembre(int membreId) {
        List<ReservationEntity> reservationEntityList = new ArrayList<>();
        for(ReservationEntity entity : this.repository.findAll()){
            if(entity.getMembreid() == membreId){
                reservationEntityList.add(entity);
            }
        }
        return reservationEntityList;
    }


}
