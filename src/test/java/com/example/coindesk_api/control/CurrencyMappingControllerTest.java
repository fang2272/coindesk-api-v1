package com.example.coindesk_api.control;
 
import org.junit.jupiter.api.BeforeEach;

import com.example.coindesk_api.service.CurrencyMappingService;
import com.example.coindesk_api.vo.CurrencyMapping;
import com.example.coindesk_api.vo.site24x7.CurrencyMappingDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
 

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock; 
import org.mockito.junit.jupiter.MockitoExtension; 
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
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
//        when(service.getAllCurrencies()).thenReturn(Arrays.asList(
//            new CurrencyMapping(1L, "USD", "美元" , "&#36;"  ,"57,756.298" ,"United States Dollar", new BigDecimal("57756.2984")),
//            new CurrencyMapping(2L, "EUR", "歐元" , "&euro;" ,"52,243.287" ,"Euro", new BigDecimal("52243.2865"))
//        ));
        when(service.getAllCurrenciesV2()).thenReturn(Arrays.asList(
                new CurrencyMappingDTO(1L, "USD", "美元" , "&#36;"  ,"57,756.298" ,"United States Dollar", new BigDecimal("57756.2984")),
                new CurrencyMappingDTO(2L, "EUR", "歐元" , "&euro;" ,"52,243.287" ,"Euro", new BigDecimal("52243.2865"))
            ));

        mockMvc.perform(get("/currencies"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    void testAddCurrency() throws Exception {
//        CurrencyMapping currency = new CurrencyMapping(null, "GBP", "英鎊" ,"&pound;" ,"43,984.02" ,"British Pound Sterling", new BigDecimal("43984.0203"));
        CurrencyMappingDTO currency = new CurrencyMappingDTO(null, "GBP", "英鎊" ,"&pound;" ,"43,984.02" ,"British Pound Sterling", new BigDecimal("43984.0203"));
        
        when(service.addCurrencyV2(any())).thenReturn(currency);

//        mockMvc.perform(post("/currencies")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(currency)))
//            .andExpect(status().isOk())
//            .andExpect(jsonPath("$.currencyCode").value("GBP"));
        
        mockMvc.perform(post("/currencies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(currency)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value("GBP"));
        
    }
    @Test
    void testUpdateCurrency() throws Exception {
        String code = "USD";
        CurrencyMapping currency = new CurrencyMapping(1L, "USD", "美元", "&#36;", "57,756.298", "United States Dollar", new BigDecimal("57756.2984"));

        when(service.updateCurrency(eq(code), any(CurrencyMapping.class))).thenReturn(currency);

        mockMvc.perform(put("/currencies/{code}", code)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(currency)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.currencyCode").value("USD"))
            .andExpect(jsonPath("$.currencyName").value("美元"));
    }

    @Test
    void testDeleteCurrency() throws Exception {
        String code = "USD";

        doNothing().when(service).deleteCurrency(code);

        mockMvc.perform(delete("/currencies/{code}", code))
            .andExpect(status().isOk())
            .andExpect(content().string("Deleted successfully"));
    }
}