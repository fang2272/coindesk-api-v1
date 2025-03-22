package com.example.coindesk_api.vo.site24x7;

import java.math.BigDecimal;
 

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter  
public class CurrencyMappingDTO {
 
	private Long id;
	 
	@JsonProperty("code")
	private String currencyCode;
	 
	@JsonProperty("currency_name")
	private String currencyName;
	
	@JsonProperty("symbol")
	private String symbol;
	 
	@JsonProperty("rate")
	private String rate;
	
	@JsonProperty("description")
	private String description;
	 
	@JsonProperty("rate_float")
	private BigDecimal rate_float;
}