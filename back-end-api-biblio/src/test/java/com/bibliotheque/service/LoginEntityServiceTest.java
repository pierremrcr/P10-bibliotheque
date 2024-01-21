package com.bibliotheque.service;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bibliotheque.BibliothequeApplication;
import com.bibliotheque.entity.MembreEntity;
import com.bibliotheque.service.contract.LoginService;
import com.bibliotheque.service.contract.MembreEntityService;

@SpringBootTest(classes = BibliothequeApplication.class)
@RunWith(SpringRunner.class)
public class LoginEntityServiceTest {
	

	@Autowired
	private LoginService loginService;
	
	@Autowired
	private MembreEntityService membreService;
	
	
	@Test
    public void getMembreByEmailandAdresseTest() {
  
        String email = "test@example.com";
        String motDePasse = "passtest";
        MembreEntity membreEntity = new MembreEntity(1,email,motDePasse); 
        
        this.membreService.addMembre(membreEntity);
        
        MembreEntity resultat = this.loginService.getMembreByEmailandAdresse(email,motDePasse);

        assertEquals("test@example.com",resultat.getAdresseMail());
    }
   }


