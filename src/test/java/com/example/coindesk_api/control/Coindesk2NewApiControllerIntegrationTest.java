package com.example.coindesk_api.control;

import lombok.extern.slf4j.Slf4j;
 
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get; 
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;  
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;  

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
class Coindesk2NewApiControllerIntegrationTest {

	@Autowired
    private MockMvc mockMvc;


    @Test
    void getCoindeskData_ShouldReturnValidResponse() throws Exception {   
        // Act & Assert        
        ResultActions data = mockMvc.perform(get("/coindesk_new")
                .contentType(MediaType.APPLICATION_JSON))      ;
        String content =  data.andReturn().getResponse().getContentAsString();
        log.info("------Response-------");
        log.info(content);
        log.info("-------------------");
        data.andExpect(status().isOk()) 
             .andExpect(jsonPath("$.time.updated").value("2024/09/02 07:07:20"))
             .andExpect(jsonPath("$.time.updatedISO").value("2024/09/02 07:07:20"))
             .andExpect(jsonPath("$.time.updateduk").value("2024/09/01 22:07:00"));
    
    }

}
