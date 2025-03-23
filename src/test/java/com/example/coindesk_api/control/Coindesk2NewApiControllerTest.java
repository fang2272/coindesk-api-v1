package com.example.coindesk_api.control;


import org.junit.jupiter.api.BeforeEach;


import static org.mockito.Mockito.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


import com.example.coindesk_api.service.CoindeskService;
import com.example.coindesk_api.vo.new_dto.CoinDeskNewResponse;
import com.example.coindesk_api.vo.new_dto.NewTime;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get; 
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;  
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;  

@Slf4j
@ExtendWith(MockitoExtension.class)
class Coindesk2NewApiControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CoindeskService service;

    @InjectMocks
    private Coindesk2NewApiController controller;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void getCoindeskData_ShouldReturnValidResponse() throws Exception {
    	String dateTimeStr01 = "1990/01/01 00:00:00";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        ZonedDateTime zonedDateTime01 = ZonedDateTime.of(
            java.time.LocalDateTime.parse(dateTimeStr01, formatter), 
//            ZoneId.of("Asia/Taipei") // 指定時區
            ZoneId.of("UTC") // 指定時區
            
        );
        ZonedDateTime zonedDateTimeISO = zonedDateTime01.withZoneSameInstant(ZoneId.of("UTC"));
        ZonedDateTime zonedDateTimeUK = zonedDateTime01.withZoneSameInstant(ZoneId.of("Europe/London"));
        
        // Arrange
        CoinDeskNewResponse mockResponse = new CoinDeskNewResponse();
        mockResponse.setTime(NewTime.builder().updated(zonedDateTime01)
        		.updatedISO(zonedDateTimeISO)
        		.updateduk(zonedDateTimeUK)
        		.build());  
        when(service.newLogic()).thenReturn(mockResponse);

        // Act & Assert        
        ResultActions data = mockMvc.perform(get("/coindesk_new")
                .contentType(MediaType.APPLICATION_JSON))      ;
        String content =  data.andReturn().getResponse().getContentAsString();
        log.info("------Response-------");
        log.info(content);
        log.info("-------------------");
        data.andExpect(status().isOk()) 
             .andExpect(jsonPath("$.time.updated").value("1990/01/01 00:00:00"))
             .andExpect(jsonPath("$.time.updatedISO").value("1990/01/01 00:00:00"))
             .andExpect(jsonPath("$.time.updateduk").value("1990/01/01 00:00:00"));
        verify(service, times(1)).newLogic();
    }

}
