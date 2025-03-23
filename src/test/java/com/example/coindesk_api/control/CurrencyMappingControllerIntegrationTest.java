package com.example.coindesk_api.control;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;

import com.example.coindesk_api.vo.CurrencyMapping;
import com.example.coindesk_api.vo.site24x7.CurrencyMappingDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions; 
import java.math.BigDecimal; 


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
class CurrencyMappingControllerIntegrationTest {

	@Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper ; ; 

    @BeforeEach
    void setUp() { 
    	  this.objectMapper = new ObjectMapper();
    }

    @Test
    void testGetAllCurrencies() throws Exception { 

        ResultActions data = mockMvc.perform(get("/currencies")
        		.contentType(MediaType.APPLICATION_JSON))  ;
        String content =  data.andReturn().getResponse().getContentAsString();
        	        log.info("------Response-------");
        	        log.info(content);
        	        log.info("-------------------");
        data.andExpect(status().isOk())
          .andExpect(jsonPath("$.size()").value(4));
    }

    @Test 
    void testAddCurrency() throws Exception { 
        CurrencyMappingDTO currency = new CurrencyMappingDTO(null,"JPY", "日圓" ,"&yen;" ,"43,984.02" ,"Japan Dollars", new BigDecimal("43984.0203"));
        
          
        ResultActions data = mockMvc.perform(post("/currencies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(currency)))  ;
        String content =  data.andReturn().getResponse().getContentAsString();
        	        log.info("------Response-------");
        	        log.info(content);
        	        log.info("-------------------");
        data .andExpect(status().isOk())
        .andExpect(jsonPath("$.code").value("JPY"));
        
    }
    @Test
    void testUpdateCurrency() throws Exception {
        String code = "USD";
        CurrencyMapping currency = new CurrencyMapping(1L, "USD", "美元", "&#36;", "57,756.298", "United States Dollar", new BigDecimal("57756.2984"));

        ResultActions data =  mockMvc.perform(put("/currencies/{code}", code)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(currency)))  ;
        String content =  data.andReturn().getResponse().getContentAsString();
        	        log.info("------Response-------");
        	        log.info(content);
        	        log.info("-------------------");
        data.andExpect(status().isOk())
        .andExpect(jsonPath("$.currencyCode").value("USD"))
        .andExpect(jsonPath("$.currencyName").value("美元"));
    }

    @Test
    void testDeleteCurrency() throws Exception {
        String code = "USD"; 

        ResultActions data =  mockMvc.perform(delete("/currencies/{code}", code))
            .andExpect(status().isOk())
            .andExpect(content().string("Deleted successfully"));
                
        String content =  data.andReturn().getResponse().getContentAsString();
        	        log.info("------Response-------");
        	        log.info(content);
        	        log.info("-------------------"); 
    }
}