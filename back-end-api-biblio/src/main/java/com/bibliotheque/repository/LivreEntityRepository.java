package com.bibliotheque.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bibliotheque.entity.ExemplaireEntity;
import com.bibliotheque.entity.LivreEntity;

@Repository
public interface LivreEntityRepository extends JpaRepository<LivreEntity, Integer > {

   List<LivreEntity> findAll();

   LivreEntity findByTitre(String titre);

   LivreEntity findById(int id);

   LivreEntity save(LivreEntity livreEntity);

   @Query("SELECT o FROM LivreEntity o WHERE CONCAT (auteur, titre) LIKE :x")
   List<LivreEntity> findAllLivresByKeyword(@Param("x")String keyword);

   @Query("SELECT livre, exemplaire, emprunt, membre FROM LivreEntity livre, ExemplaireEntity exemplaire, EmpruntEntity emprunt, MembreEntity membre WHERE livre.id = exemplaire.livreid AND exemplaire.id = emprunt.exemplaireid AND membre.id =:id")
   List<LivreEntity> findAllLivreEmpruntesByUser(@Param("id") int id);


   @Query("SELECT exemplaire FROM ExemplaireEntity exemplaire WHERE livreid= :x")
   List<ExemplaireEntity> findAllExemplairesByLivreId(@Param("x") int livreid);

}
