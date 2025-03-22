package com.example.coindesk_api.service;
 

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*; 
@ExtendWith(MockitoExtension.class)
class CoindeskServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private CoindeskService service;

    @Test
    void testGetCoindeskData() {
        String mockResponse = "{ \"bpi\": { \"USD\": { \"rate\": \"50000.00\" } } }";
        when(restTemplate.getForObject(anyString(), eq(String.class))).thenReturn(mockResponse);
        ReflectionTestUtils.setField(service, "restTemplate", restTemplate);        
        String result = service.getCoindeskData();
        assertNotNull(result);
        assertTrue(result.contains("50000.00"));
    }
}