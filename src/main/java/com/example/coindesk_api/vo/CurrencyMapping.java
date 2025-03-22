package com.example.coindesk_api.vo;

import java.math.BigDecimal;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "currency_mapping")
public class CurrencyMapping {

	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "currency_code", unique = true, nullable = false)
	private String currencyCode;

	@Column(name = "currency_name", nullable = false)
	private String currencyName;

	@Column(name = "symbol", nullable = false)
	private String symbol;
	
	@Column(name = "rate", nullable = false)
	private String rate;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(name = "rate_float", nullable = false)
	private BigDecimal rate_float;
}