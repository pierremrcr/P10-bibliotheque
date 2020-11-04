package com.bibliotheque.service.impl;

import com.bibliotheque.entity.ReservationEntity;
import com.bibliotheque.repository.LivreEntityRepository;
import com.bibliotheque.repository.ReservationEntityRepository;
import com.bibliotheque.service.contract.ReservationEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    public boolean updateReservation(ReservationEntity reservation) {
        try {
            List<ReservationEntity> reservationEntityList = getAllReservationsByLivre(reservation.getLivreId());
            for(ReservationEntity reservationEntity : reservationEntityList){
                if(reservation.getNumPositionResa() < reservationEntity.getNumPositionResa()){
                    reservationEntity.setNumPositionResa(reservationEntity.getNumPositionResa()-1);
                    this.repository.save(reservationEntity);
                }
            }
            reservation.setNumPositionResa(0);
            if(delaiExpired(reservation)) {
                reservation.setStatut("annulé");
            }
            else {
                reservation.setStatut("terminé");
            }
             this.repository.save(reservation);
             return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
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

    private boolean delaiExpired(ReservationEntity reservation) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date today = Calendar.getInstance().getTime();
        Date dateDispo = dateFormat.parse(reservation.getDateDispo().toString());
        if (dateDispo.before(addDays(today, 2))) {
            return false;
        } else {
            return true;
        }
    }

    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }


}
