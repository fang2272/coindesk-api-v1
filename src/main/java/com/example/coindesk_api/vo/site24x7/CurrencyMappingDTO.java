package com.example.coindesk_api.vo.site24x7;

import java.math.BigDecimal;
 

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter  
public class CurrencyMappingDTO {
 
	private Long id;
	 
	@Schema(example = "USD", description = "貨幣代碼")
	@JsonProperty("code")
	private String currencyCode;
	 
	@Schema(example = "美元", description = "貨幣中文名稱")
	@JsonProperty("currency_name")
	private String currencyName;
	
	@Schema(example = "&#36;", description = "貨幣HTML符號")
	@JsonProperty("symbol")
	private String symbol;
	 
	@Schema(example = "57,756.298", description = "貨幣匯率")
	@JsonProperty("rate")
	private String rate;
	
	@Schema(example = "United States Dollar", description = "貨幣英文描述")
	@JsonProperty("description")
	private String description;
	 
	@Schema(example = "57756.2984", description = "貨幣匯率浮點數")
	@JsonProperty("rate_float")
	private BigDecimal rate_float;
}