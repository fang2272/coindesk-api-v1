package com.example.coindesk_api.control;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.coindesk_api.service.CoindeskService; 
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
class CoindeskControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CoindeskService service;

    @InjectMocks
    private CoindeskController controller;
    
    @BeforeEach
    void setUp() {
    	  this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();  
    }
    

    @Test
    void testGetCoindeskData() throws Exception {
        when(service.getCoindeskData()).thenReturn("{ \"bpi\": { \"USD\": { \"rate\": \"50000.00\" } } }");

        mockMvc.perform(get("/coindesk"))
            .andExpect(status().isOk());
    }
}