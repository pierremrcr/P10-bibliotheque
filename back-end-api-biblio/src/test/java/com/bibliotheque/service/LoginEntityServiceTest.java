package com.bibliotheque.service;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.bibliotheque.BibliothequeApplication;
import com.bibliotheque.entity.MembreEntity;
import com.bibliotheque.service.contract.LoginService;
import com.bibliotheque.service.contract.MembreEntityService;

@SpringBootTest(classes = BibliothequeApplication.class)
@ExtendWith(SpringExtension.class)
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


