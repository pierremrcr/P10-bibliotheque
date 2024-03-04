package com.bibliotheque.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bibliotheque.BibliothequeApplication;
import com.bibliotheque.entity.MembreEntity;
import com.bibliotheque.entity.ReservationEntity;
import com.bibliotheque.service.contract.MembreEntityService;

@SpringBootTest(classes = BibliothequeApplication.class)
@RunWith(SpringRunner.class)
public class MembreEntityServiceTest {
	
	@Autowired
	private MembreEntityService membreService;
	
	@Test
	public void getMembreByIdTest(){

	MembreEntity membre = new MembreEntity(1, "test@gmail.com", "password");

	this.membreService.addMembre(membre);
	
	MembreEntity membreFound = this.membreService.getMembreById(1);

	assertEquals(1, membreFound.getId());
	
	}
	
	@Test
	public void getAllmembresTest(){

	MembreEntity membre = new MembreEntity("Mercier", "Pierre", "test@gmail.com");
	MembreEntity membre2 = new MembreEntity("Vilou", "Pierre", "vilou@gmail.com");
	MembreEntity membre3 = new MembreEntity("DSH", "Matou", "dsh@gmail.com");

	this.membreService.addMembre(membre);
	this.membreService.addMembre(membre2);
	this.membreService.addMembre(membre3);

	List<MembreEntity> result = this.membreService.getAllMembres();

	assertEquals("Mercier",result.get(0).getNom());
	assertEquals("vilou@gmail.com",result.get(1).getAdresseMail());
	assertEquals("DSH",result.get(2).getNom());
	
	
	}

	@Test
	public void addMembreTest() {

	MembreEntity membre = new MembreEntity("DSH", "Matou", "dsh@gmail.com");

	boolean result = this.membreService.addMembre(membre);

	assertTrue(result);

	}


	@Test
	public void deletemembreTest() {

	MembreEntity membre = new MembreEntity(2, "test@gmail.com", "password");
	
	boolean result = this.membreService.addMembre(membre);
	
	assertTrue(result);
	
	final boolean result2 = membreService.deleteMembreById(1);

	assertEquals(result2, true);

	}
	
	

}
