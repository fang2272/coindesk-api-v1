package com.example.coindesk_api.service;
 

import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.coindesk_api.repository.CurrencyMappingRepository;
import com.example.coindesk_api.vo.CurrencyMapping;
import com.example.coindesk_api.vo.new_dto.CoinDeskNewResponse;
import com.example.coindesk_api.vo.new_dto.NewTime;
import com.example.coindesk_api.vo.site24x7.CoinDeskResponse;
import com.example.coindesk_api.vo.site24x7.CurrencyMappingDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.BeanUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j; 

@Slf4j
@Service
@RequiredArgsConstructor
public class CoindeskService {

    private static final String COINDESK_API_URL = "https://kengp3.github.io/blog/coindesk.json";
    private RestTemplate restTemplate = new RestTemplate();
    
    private final CurrencyMappingRepository repository;
    
    public String getCoindeskData() { 
        return restTemplate.getForObject(COINDESK_API_URL, String.class);
    }
    
    /***
     * 進行資料轉換
     * **/
    public CoinDeskResponse convertFromJsonBody(String content) {
    	if(StringUtils.isAllBlank(content)) {
    		return null;
    	}
    	ObjectMapper mapper = new ObjectMapper();
    	mapper.registerModule(new JavaTimeModule());
    	mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    	CoinDeskResponse result = null;
		try {
			result = mapper.readValue(content, CoinDeskResponse.class);
		} catch (JsonProcessingException e) {
			log.error("json parser error");
		}
    	return result;
    }
    public CoinDeskNewResponse newLogic() {
    	// 呼叫coindesk的API
    	String srcContent = getCoindeskData();
    	
    	// 進行資料轉換
    	CoinDeskResponse sourceCurrencyData = convertFromJsonBody(srcContent);
    	
    	CoinDeskNewResponse result = new CoinDeskNewResponse();
        BeanUtils.copyProperties(sourceCurrencyData, result); 
		result.setTime(NewTime.builder()
				       .updated(sourceCurrencyData.getTime().getUpdated())
				       .updatedISO(sourceCurrencyData.getTime().getUpdatedISO())
				       .updateduk(sourceCurrencyData.getTime().getUpdateduk())
				       .build());
		
		//B. 幣別相關資訊（幣別，幣別中文名稱，以及匯率）。
		addDesc(result.getBpi());
        return result ; 
    }
    /***
     * B. 幣別相關資訊（幣別，幣別中文名稱，以及匯率）。
     * 
     * **/
    void addDesc(final Map<String,CurrencyMappingDTO>bpi){
		bpi.entrySet().forEach(unit -> {
			Optional<CurrencyMapping> option = repository.findByCurrencyCode(unit.getKey());

			if (option.isPresent() && option.get() != null) {
				unit.getValue().setCurrencyName(option.get().getCurrencyName());
			}

		});
    }
    
}