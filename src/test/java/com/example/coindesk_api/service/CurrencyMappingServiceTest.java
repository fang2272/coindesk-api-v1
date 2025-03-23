package com.example.coindesk_api.service;

 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;

import com.example.coindesk_api.repository.CurrencyMappingRepository;
import com.example.coindesk_api.vo.CurrencyMapping;
import com.example.coindesk_api.vo.site24x7.CurrencyMappingDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CurrencyMappingServiceTest {

    @Mock
    private CurrencyMappingRepository repository;

    @InjectMocks
    private CurrencyMappingService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCurrencies() {
        when(repository.findAll()).thenReturn(Arrays.asList(
        		 new CurrencyMapping(1L, "USD", "美元" , "&#36;"  ,"57,756.298" ,"United States Dollar", new BigDecimal("57756.2984")),
                 new CurrencyMapping(2L, "EUR", "歐元" , "&euro;" ,"52,243.287" ,"Euro", new BigDecimal("52243.2865"))
        ));

        assertEquals(2, service.getAllCurrencies().size());
    }
    @Test
    void testGetAllCurrenciesV2() {
        List<CurrencyMapping> mockData = new ArrayList<>();
        CurrencyMapping currency = new CurrencyMapping();
        currency.setCurrencyCode("USD");
        currency.setCurrencyName("US Dollar");
        mockData.add(currency);

        when(repository.findAll()).thenReturn(mockData);

        List<CurrencyMappingDTO> result = service.getAllCurrenciesV2();

        assertEquals(1, result.size());
        assertEquals("USD", result.get(0).getCurrencyCode());
        verify(repository, times(1)).findAll();
    }


    @Test
    void testGetCurrencyByCode() {
        CurrencyMapping usd =  new CurrencyMapping(1L, "USD", "美元" , "&#36;"  ,"57,756.298" ,"United States Dollar", new BigDecimal("57756.2984"));
        when(repository.findByCurrencyCode("USD")).thenReturn(Optional.of(usd));

        Optional<CurrencyMapping> result = service.getCurrencyByCode("USD");
        assertTrue(result.isPresent());
        assertEquals("美元", result.get().getCurrencyName());
    }

    @Test
    void testAddCurrency() {
        CurrencyMapping jpy = new CurrencyMapping(null, "GBP", "英鎊" ,"&pound;" ,"43,984.02" ,"British Pound Sterling", new BigDecimal("43984.0203"));
        when(repository.save(any())).thenReturn(jpy);

        CurrencyMapping result = service.addCurrency(jpy);
        assertNotNull(result);
        assertEquals("GBP", result.getCurrencyCode());
    }
    @Test
    void testAddCurrencyV2() {
        CurrencyMappingDTO dto = new CurrencyMappingDTO();
        dto.setCurrencyCode("USD");
        dto.setCurrencyName("US Dollar");

        CurrencyMapping mockCurrency = new CurrencyMapping();
        BeanUtils.copyProperties(dto, mockCurrency);
        mockCurrency.setId(1L);

        when(repository.save(any(CurrencyMapping.class))).thenReturn(mockCurrency);

        CurrencyMappingDTO result = service.addCurrencyV2(dto);

        assertNotNull(result.getId());
        assertEquals("USD", result.getCurrencyCode());
        verify(repository, times(1)).save(any(CurrencyMapping.class));
    }

    @Test
    void testUpdateCurrency() {
        CurrencyMapping usd =  new CurrencyMapping(1L, "USD", "美元" , "&#36;"  ,"57,756.298" ,"United States Dollar", new BigDecimal("57756.2984"));
        when(repository.findByCurrencyCode("USD")).thenReturn(Optional.of(usd));
        when(repository.save(any())).thenReturn(usd);

        CurrencyMapping updated =  new CurrencyMapping(null, "USD", "美金" , "&#36;"  ,"57,756.298" ,"United States Dollar", new BigDecimal("57756.2984"));
        CurrencyMapping result = service.updateCurrency("USD", updated);

        assertEquals("美金", result.getCurrencyName());
    }

    @Test
    void testDeleteCurrency() {
        CurrencyMapping usd =  new CurrencyMapping(1L, "USD", "美元" , "&#36;"  ,"57,756.298" ,"United States Dollar", new BigDecimal("57756.2984"));
        when(repository.findByCurrencyCode("USD")).thenReturn(Optional.of(usd));

        service.deleteCurrency("USD");
        verify(repository, times(1)).delete(usd);
    }
}