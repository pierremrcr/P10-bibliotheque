package com.bibliotheque.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.bibliotheque.entity.ExemplaireEntity;
import com.bibliotheque.entity.LivreEntity;
import com.bibliotheque.repository.LivreEntityRepository;
import com.bibliotheque.service.impl.LivreEntityServiceImpl;

/*
@DataJpaTest
public class LivreEntityServiceTest {

@Mock
private LivreEntityRepository repository;

@InjectMocks
private LivreEntityServiceImpl service;

@Before
public void setUp() {
MockitoAnnotations.initMocks(this);
}

@Test
public void getLivreByIdTest(){

LivreEntity livre = new LivreEntity(1, "Kilometre zero", "Inconnu");

Mockito.when(repository.findById(1)).thenReturn(livre);
LivreEntity livreFound = this.service.getLivreById(1);

assertEquals(livre.getId(), livreFound.getId());
verify(this.repository).findById(1);
}

@Test
public void getAllLivresTest(){

LivreEntity livre = new LivreEntity(1, "Kilometre zero", "Inconnu");
LivreEntity livre2 = new LivreEntity(2, "La peste", "Albert Camus");

List<LivreEntity> liste = new ArrayList<>();
liste.add(livre);
liste.add(livre2);

when(repository.findAll()).thenReturn(liste);

List<LivreEntity> result = this.service.getAllLivres();

assertEquals("Kilometre zero",result.get(0).getTitre());
assertEquals("La peste",result.get(1).getTitre());
verify(this.repository).findAll();
}

@Test
public void addLivreTest() {

LivreEntity livre = new LivreEntity(1, "Kilometre zero", "Inconnu");
when(repository.save(any(LivreEntity.class))).thenReturn(livre);

LivreEntity result = this.service.addLivre(livre);

assertEquals("Inconnu", result.getAuteur());
verify(repository, times(1)).save(any());

}


@Test
public void updateLivreTest() {

LivreEntity livre = new LivreEntity(1, "Kilometre zero", "Inconnu");
when(repository.save(any(LivreEntity.class))).thenReturn(livre);

LivreEntity result = this.service.addLivre(livre);
assertEquals("Inconnu", result.getAuteur());

livre.setAuteur("Maud Ankoua");
when(repository.save(any(LivreEntity.class))).thenReturn(livre);
LivreEntity result2 = this.service.addLivre(livre);
assertEquals("Maud Ankoua", result2.getAuteur());

}


@Test
public void deleteLivreTest() {

LivreEntity livre = new LivreEntity(1, "Kilometre zero", "Inconnu");

Mockito.when(repository.findById(1)).thenReturn(livre).thenReturn(null);

final boolean result = service.deleteLivreById(1);

verify(repository, times(1)).deleteById(1);
assertEquals(result, true);

}

@Test
public void deleteLivreAndExemplairesTest() {

LivreEntity livre = new LivreEntity(1, "Kilometre zero", "Inconnu");

List<ExemplaireEntity> listeExemplaires = new ArrayList<>();

ExemplaireEntity exemplaire1 = new ExemplaireEntity(1,true, 1);
ExemplaireEntity exemplaire2 = new ExemplaireEntity(2,false, 1);
ExemplaireEntity exemplaire3 = new ExemplaireEntity(3,false, 1);

listeExemplaires.add(exemplaire1);
listeExemplaires.add(exemplaire2);
listeExemplaires.add(exemplaire3);

livre.setListeExemplaires(listeExemplaires);

Mockito.when(repository.findById(1)).thenReturn(livre).thenReturn(null);

final boolean result = service.deleteLivre(livre);

verify(repository, times(1)).delete(livre);
assertEquals(result, true);
assertEquals(0, livre.getListeExemplaires().size());


}



}	

*/