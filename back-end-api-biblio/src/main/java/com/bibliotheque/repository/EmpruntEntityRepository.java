package com.bibliotheque.repository;

import com.bibliotheque.entity.EmpruntEntity;
import com.bibliotheque.entity.MembreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpruntEntityRepository extends JpaRepository<EmpruntEntity, Integer> {

    List<EmpruntEntity> findAll();

    EmpruntEntity findById(int id);

    EmpruntEntity save(EmpruntEntity emprunt);

    @Query("SELECT emprunt FROM EmpruntEntity emprunt WHERE emprunt.date_fin < CURRENT_DATE AND emprunt.relance = false AND emprunt.termine = false")
    List<EmpruntEntity> findAllEmpruntWhereDateRetourIsBeforDateToday();
    
    @Query("SELECT emprunt FROM EmpruntEntity emprunt WHERE exemplaireid = :x")
    List<EmpruntEntity> findAllEmpruntsByLivreId(@Param("x") Integer exemplaireid);

}

