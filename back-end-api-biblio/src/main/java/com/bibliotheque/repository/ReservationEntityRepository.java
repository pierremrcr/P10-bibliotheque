package com.bibliotheque.repository;

import com.bibliotheque.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationEntityRepository extends JpaRepository<ReservationEntity,Integer> {

    List<ReservationEntity> findAll();
    ReservationEntity findById(int id);
    ReservationEntity save(ReservationEntity reservationEntity);
    ReservationEntity deleteById(int id);

}
