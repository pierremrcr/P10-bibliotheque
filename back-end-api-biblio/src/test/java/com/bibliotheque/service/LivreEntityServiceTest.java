package com.bibliotheque.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bibliotheque.BibliothequeApplication;
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

	boolean result = this.livreService.addLivre(livre);

	assertTrue(result);

	}


	@Test
	public void deleteLivreTest() {

	LivreEntity livre = new LivreEntity(1, "Kilometre zero", "Inconnu");
	
	boolean result = this.livreService.addLivre(livre);
	
	assertTrue(result);
	
	final boolean result2 = livreService.deleteLivreById(1);
	
	assertEquals(result2, true);

	}
		

}
