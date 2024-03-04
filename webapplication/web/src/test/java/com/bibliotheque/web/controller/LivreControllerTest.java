package com.bibliotheque.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import com.bibliotheque.service.LivreService;

@WebMvcTest(LivreController.class)
public class LivreControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private LivreService livreService;
    
    @Autowired
	HttpSession session;

    @Test
    public void testGetAllLivres() throws Exception {
           
        mockMvc.perform(get("/livres"))
                .andExpect(status().isOk())
                .andExpect(view().name("livres"));        
    }
    
    @Test
    public void testGetLivre() throws Exception {
    	    	  
    	 mockMvc.perform((get("/livre"))
                 .param("id", "1")
         )
    	 .andExpect(status().isOk()) // Check if the HTTP status is OK
         .andExpect(view().name("livre")); // Check if the view name is as expected
     }       
    
       
}
