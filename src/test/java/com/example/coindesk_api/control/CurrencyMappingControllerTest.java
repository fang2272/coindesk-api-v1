package com.example.coindesk_api.control;
 
import org.junit.jupiter.api.BeforeEach;

import com.example.coindesk_api.service.CurrencyMappingService;
import com.example.coindesk_api.vo.CurrencyMapping;
import com.fasterxml.jackson.databind.ObjectMapper;
 

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock; 
import org.mockito.junit.jupiter.MockitoExtension; 
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays; 

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
 
@ExtendWith(MockitoExtension.class)
class CurrencyMappingControllerTest {
 
    private MockMvc mockMvc;
    private ObjectMapper objectMapper ;

    @Mock
    private CurrencyMappingService service;

    @InjectMocks
    private CurrencyMappingController controller; 

    @BeforeEach
    void setUp() {
    	  this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build(); 
    	  this.objectMapper = new ObjectMapper();
    }

    @Test
    void testGetAllCurrencies() throws Exception {
        when(service.getAllCurrencies()).thenReturn(Arrays.asList(
            new CurrencyMapping(1L, "USD", "美元"),
            new CurrencyMapping(2L, "EUR", "歐元")
        ));

        mockMvc.perform(get("/currencies"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    void testAddCurrency() throws Exception {
        CurrencyMapping currency = new CurrencyMapping(null, "JPY", "日圓");
        when(service.addCurrency(any())).thenReturn(currency);

        mockMvc.perform(post("/currencies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(currency)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.currencyCode").value("JPY"));
    }
}