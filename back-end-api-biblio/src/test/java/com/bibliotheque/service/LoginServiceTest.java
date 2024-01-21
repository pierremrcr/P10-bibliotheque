package com.bibliotheque.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bibliotheque.entity.MembreEntity;
import com.bibliotheque.repository.LoginRepository;
import com.bibliotheque.service.impl.LoginServiceImpl;

@ExtendWith(MockitoExtension.class)
public class LoginServiceTest {

	private LoginRepository repository = Mockito.mock(LoginRepository.class);

	@InjectMocks
	private LoginServiceImpl loginService;

	@Test
	public void testGetMembreByEmailandAdresse() {
		// Créez un objet MembreEntity factice pour simuler le résultat de la recherche
		MembreEntity fakeMembre = new MembreEntity();
		fakeMembre.setAdresseMail("test@example.com");
		fakeMembre.setMotDePasse("password");

		// Définissez le comportement simulé du repository
		when(repository.findByAdresseMailAndMotDePasse("test@example.com","password")).thenReturn(fakeMembre);

		// Appelez la méthode du service que vous souhaitez tester
		MembreEntity result = this.loginService.getMembreByEmailandAdresse("test@example.com", "password");

		// Vérifiez si le résultat est le même que l'objet factice
		assertEquals(fakeMembre, result);
	}
}
