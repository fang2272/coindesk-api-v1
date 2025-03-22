package com.example.coindesk_api.service;

 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.coindesk_api.repository.CurrencyMappingRepository;
import com.example.coindesk_api.vo.CurrencyMapping;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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
            new CurrencyMapping(1L, "USD", "美元"),
            new CurrencyMapping(2L, "EUR", "歐元")
        ));

        assertEquals(2, service.getAllCurrencies().size());
    }

    @Test
    void testGetCurrencyByCode() {
        CurrencyMapping usd = new CurrencyMapping(1L, "USD", "美元");
        when(repository.findByCurrencyCode("USD")).thenReturn(Optional.of(usd));

        Optional<CurrencyMapping> result = service.getCurrencyByCode("USD");
        assertTrue(result.isPresent());
        assertEquals("美元", result.get().getCurrencyName());
    }

    @Test
    void testAddCurrency() {
        CurrencyMapping jpy = new CurrencyMapping(null, "JPY", "日圓");
        when(repository.save(any())).thenReturn(jpy);

        CurrencyMapping result = service.addCurrency(jpy);
        assertNotNull(result);
        assertEquals("JPY", result.getCurrencyCode());
    }

    @Test
    void testUpdateCurrency() {
        CurrencyMapping usd = new CurrencyMapping(1L, "USD", "美元");
        when(repository.findByCurrencyCode("USD")).thenReturn(Optional.of(usd));
        when(repository.save(any())).thenReturn(usd);

        CurrencyMapping updated = new CurrencyMapping(null, "USD", "美金");
        CurrencyMapping result = service.updateCurrency("USD", updated);

        assertEquals("美金", result.getCurrencyName());
    }

    @Test
    void testDeleteCurrency() {
        CurrencyMapping usd = new CurrencyMapping(1L, "USD", "美元");
        when(repository.findByCurrencyCode("USD")).thenReturn(Optional.of(usd));

        service.deleteCurrency("USD");
        verify(repository, times(1)).delete(usd);
    }
}