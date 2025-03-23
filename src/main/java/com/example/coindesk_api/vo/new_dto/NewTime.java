package com.example.coindesk_api.vo.new_dto;

import java.time.ZonedDateTime;

import javax.validation.constraints.NotBlank;

import com.example.coindesk_api.vo.site24x7.deserializer.CustomDateDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data; 
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewTime { 
	
	  @Schema(example = "1990/01/01 00:00:00", description = "更新時間")
	  @JsonFormat(timezone = "UTC" ,pattern = "yyyy/MM/dd HH:mm:ss")
	  private ZonedDateTime  updated;
	   
	  @JsonFormat(timezone = "UTC" ,pattern = "yyyy/MM/dd HH:mm:ss")
	  private ZonedDateTime updatedISO;
	   
	  @JsonFormat(timezone = "Europe/London" , pattern = "yyyy/MM/dd HH:mm:ss")
	  private ZonedDateTime  updateduk;
}
