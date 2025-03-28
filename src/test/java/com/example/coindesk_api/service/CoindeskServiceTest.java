package com.example.coindesk_api.service;
 

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import com.example.coindesk_api.repository.CurrencyMappingRepository;
import com.example.coindesk_api.vo.CurrencyMapping;
import com.example.coindesk_api.vo.new_dto.CoinDeskNewResponse;
import com.example.coindesk_api.vo.site24x7.CoinDeskResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.BufferedReader;
import java.io.IOException; 
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors; 


@ExtendWith(MockitoExtension.class)
class CoindeskServiceTest {
    @Mock
    private CurrencyMappingRepository repository;
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

	@Test
	public void testConvertFromJsonBody() throws IOException {
		try (InputStreamReader isr = new InputStreamReader(CoindeskService.class.getResourceAsStream("/coindesk.json"),
				StandardCharsets.UTF_8);) {
			String json = new BufferedReader(isr).lines().collect(Collectors.joining("\n"));
			
			CoinDeskResponse result = service.convertFromJsonBody(json);
			assertNotNull(result);
			assertTrue("just for test".equals(result.getDisclaimer()));
			assertTrue("Bitcoin".equals(result.getChartName()));
			assertNotNull(result.getTime());
			assertNotNull(result.getTime().getUpdated());
			assertNotNull(result.getTime().getUpdatedISO());
			assertNotNull(result.getTime().getUpdateduk());
			assertNotNull(result.getBpi());
			assertNotNull(result.getBpi().get("USD"));
			assertNotNull(result.getBpi().get("GBP"));
			assertNotNull(result.getBpi().get("EUR"));
			
		}
	}

	@Test
	public void testNewLogic() throws IOException {
//		try (InputStreamReader isr = new InputStreamReader(CoindeskService.class.getResourceAsStream("/coindesk.json"),
//				StandardCharsets.UTF_8);) {
//			String mockResponse = new BufferedReader(isr).lines().collect(Collectors.joining("\n"));
			
 
		    CurrencyMapping currency01 =  mock(CurrencyMapping .class);
		    CurrencyMapping currency02 =  mock(CurrencyMapping .class);
		    CurrencyMapping currency03 =  mock(CurrencyMapping .class);
		    
		    		
	    	when(repository.findByCurrencyCode("USD")).thenReturn(Optional.of(currency01));
	    	when(repository.findByCurrencyCode("GBP")).thenReturn(Optional.of(currency02));
	    	when(repository.findByCurrencyCode("EUR")).thenReturn(Optional.of(currency03)); 
	    	
	    	when(currency01.getCurrencyName()).thenReturn("美元");
	    	when(currency02.getCurrencyName()).thenReturn("歐元");
	    	when(currency03.getCurrencyName()).thenReturn("英鎊");
	    	
			CoinDeskNewResponse result = service.newLogic();
			assertNotNull(result);
			assertTrue("just for test".equals(result.getDisclaimer()));
			assertTrue("Bitcoin".equals(result.getChartName()));
			assertNotNull(result.getTime());
			assertNotNull(result.getTime().getUpdated());
			assertNotNull(result.getTime().getUpdatedISO());
			assertNotNull(result.getTime().getUpdateduk());
			assertNotNull(result.getBpi());
			assertNotNull(result.getBpi().get("USD"));
			assertNotNull(result.getBpi().get("GBP"));
			assertNotNull(result.getBpi().get("EUR"));
			
//		} 
	}
}