package com.example.coindesk_api.vo.site24x7;

import java.time.ZonedDateTime;

import javax.validation.constraints.NotBlank;

import com.example.coindesk_api.vo.site24x7.deserializer.CustomDateDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.AllArgsConstructor;
import lombok.Data; 
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Time {
	  @NotBlank(message = "updated is mandatory")
	  @JsonDeserialize(using = CustomDateDeserializer.class)
	  private ZonedDateTime  updated;
	  
	  @NotBlank(message = "updatedISO is mandatory")
	  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
	  private ZonedDateTime updatedISO;
	  
	  @NotBlank(message = "updateduk is mandatory")
	  @JsonDeserialize(using = CustomDateDeserializer.class)
	  private ZonedDateTime  updateduk;
}
