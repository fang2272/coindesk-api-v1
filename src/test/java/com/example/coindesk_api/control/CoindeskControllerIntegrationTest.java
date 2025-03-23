package com.example.coindesk_api.control; 
import org.junit.jupiter.api.Test; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions; 

import lombok.extern.slf4j.Slf4j; 
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
class CoindeskControllerIntegrationTest {

	@Autowired
    private MockMvc mockMvc;
    

    @Test
    void testGetCoindeskData() throws Exception {
        
         
        
        ResultActions data = mockMvc.perform(get("/coindesk")
                .contentType(MediaType.APPLICATION_JSON))  ;
        String content =  data.andReturn().getResponse().getContentAsString();
        log.info("------Response-------");
        log.info(content);
        log.info("-------------------");
        data.andExpect(status().isOk());
    }
}