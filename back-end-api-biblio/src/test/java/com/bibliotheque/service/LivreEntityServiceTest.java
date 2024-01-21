package com.bibliotheque.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bibliotheque.BibliothequeApplication;
import com.bibliotheque.entity.ExemplaireEntity;
import com.bibliotheque.entity.LivreEntity;
import com.bibliotheque.service.contract.LivreEntityService;


@SpringBootTest(classes = BibliothequeApplication.class)
@RunWith(SpringRunner.class)
public class LivreEntityServiceTest {
	
	
	@Autowired
	LivreEntityService livreService;
	
	
	@Test
	public void getLivreByIdTest(){

	LivreEntity livre = new LivreEntity(1, "Kilometre zero", "Inconnu");

	this.livreService.addLivre(livre);
	
	LivreEntity livreFound = this.livreService.getLivreById(1);

	assertEquals(1, livreFound.getId());
	
	}

	@Test
	public void getAllLivresTest(){

	LivreEntity livre = new LivreEntity(1, "Kilometre zero", "Inconnu");
	LivreEntity livre2 = new LivreEntity(2, "La peste", "Albert Camus");

	this.livreService.addLivre(livre);
	this.livreService.addLivre(livre2);

	List<LivreEntity> result = this.livreService.getAllLivres();

	assertEquals("Kilometre zero",result.get(0).getTitre());
	assertEquals("La peste",result.get(1).getTitre());
	
	}

	@Test
	public void addLivreTest() {

	LivreEntity livre = new LivreEntity(1, "Kilometre zero", "Inconnu");

	LivreEntity result = this.livreService.addLivre(livre);

	assertEquals("Inconnu", result.getAuteur());

	}


	@Test
	public void updateLivreTest() {

	LivreEntity livre = new LivreEntity(1, "Kilometre zero", "Inconnu");

	LivreEntity result = this.livreService.addLivre(livre);
	assertEquals("Inconnu", result.getAuteur());

	livre.setAuteur("Maud Ankoua");
	LivreEntity result2 = this.livreService.addLivre(livre);
	assertEquals("Maud Ankoua", result2.getAuteur());

	}


	@Test
	public void deleteLivreTest() {

	LivreEntity livre = new LivreEntity(1, "Kilometre zero", "Inconnu");
	
	LivreEntity result = this.livreService.addLivre(livre);
	assertEquals("Inconnu", result.getAuteur());
	
	final boolean result2 = livreService.deleteLivreById(1);

	assertEquals(result2, true);

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
	
	livreService.addLivre(livre);
	
	LivreEntity livreFound = livreService.getLivreById(1);
	assertEquals("Inconnu", livreFound.getAuteur());
	assertEquals(3, livreFound.getListeExemplaires().size());
	
	boolean result = livreService.deleteLivre(livreFound);
	
	assertEquals(true, result);
	assertEquals(0,livreFound.getListeExemplaires().size());


	}
	
	

}
