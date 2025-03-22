package com.example.coindesk_api.vo.new_dto;

import java.time.ZonedDateTime;

import javax.validation.constraints.NotBlank;

import com.example.coindesk_api.vo.site24x7.deserializer.CustomDateDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data; 
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewTime { 
	
	  
	  @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	  private ZonedDateTime  updated;
	   
	  @JsonFormat(timezone = "ISO" ,pattern = "yyyy/MM/dd HH:mm:ss")
	  private ZonedDateTime updatedISO;
	   
	  @JsonFormat(timezone = "UK" , pattern = "yyyy/MM/dd HH:mm:ss")
	  private ZonedDateTime  updateduk;
}
