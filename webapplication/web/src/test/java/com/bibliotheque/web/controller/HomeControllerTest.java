package com.bibliotheque.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class HomeControllerTest {


	    @InjectMocks
	    private HomeController homeController;

	    private MockMvc mockMvc;

	    @BeforeEach
	    void setUp() {
	        mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
	    }

	    @Test
	    void testHome() throws Exception {
	       
	        mockMvc.perform(get("/"))
	                .andExpect(status().isOk())
	                .andExpect(view().name("home"));
	    }
	    
	    @Test
	    void notAuthorizedTest() throws Exception {
	    	mockMvc.perform(get("/403"))
            .andExpect(status().isOk())
            .andExpect(view().name("error/403"));
	    	
	    }
	}


