package com.example.coindesk_api.vo.site24x7;
  
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
import com.fasterxml.jackson.annotation.JsonProperty; 

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CoinDeskResponse {
	private Time time;
	private String disclaimer;
	private String chartName;
	
	@JsonProperty("bpi") 
	private Map<String,CurrencyMappingDTO>bpi ;
}
