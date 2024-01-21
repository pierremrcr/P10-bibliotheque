package com.bibliotheque.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import com.bibliotheque.service.LivreService;

@WebMvcTest(LivreController.class)
public class LivreControllerTest2 {

    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private LivreService livreService;

    @Test
    public void testGetAllLivres() throws Exception {
           
        mockMvc.perform(get("/livres"))
                .andExpect(status().isOk())
                .andExpect(view().name("livres"));        
    }
}
