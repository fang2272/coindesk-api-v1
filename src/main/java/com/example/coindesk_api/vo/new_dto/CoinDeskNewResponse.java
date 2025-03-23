package com.example.coindesk_api.vo.new_dto;
  
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.example.coindesk_api.vo.site24x7.CurrencyMappingDTO; 
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema; 


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CoinDeskNewResponse {
	private NewTime time;
	@Schema(example = "just for test", description = "用途")
	private String disclaimer;
	@Schema(example = "Bitcoin", description = "chart名稱")
	private String chartName;
	
	@JsonProperty("bpi") 
	private Map<String,CurrencyMappingDTO>bpi ;
}
