package com.example.coindesk_api.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CoindeskService {

    private static final String COINDESK_API_URL = "https://kengp3.github.io/blog/coindesk.json";
    private RestTemplate restTemplate = new RestTemplate();
    public String getCoindeskData() { 
        return restTemplate.getForObject(COINDESK_API_URL, String.class);
    }
}