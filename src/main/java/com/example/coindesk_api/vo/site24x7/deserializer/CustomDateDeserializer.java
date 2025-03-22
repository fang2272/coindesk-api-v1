package com.example.coindesk_api.vo.site24x7.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class CustomDateDeserializer extends JsonDeserializer<ZonedDateTime> {
	private static final DateTimeFormatter FORMATTER_UPDATED = DateTimeFormatter.ofPattern("MMM d, yyyy HH:mm:ss z",
			Locale.ENGLISH);
	private static final DateTimeFormatter FORMATTER_UPDATEDUK = DateTimeFormatter.ofPattern("MMM d, yyyy 'at' HH:mm z",
			Locale.ENGLISH);

	@Override
	public ZonedDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
		String dateString = p.getText();

		if (dateString.contains(" at ")) { // 針對 updateduk
			return ZonedDateTime.parse(dateString, FORMATTER_UPDATEDUK);
		} else { // 針對 updated
			return ZonedDateTime.parse(dateString, FORMATTER_UPDATED);
		}
	}
}