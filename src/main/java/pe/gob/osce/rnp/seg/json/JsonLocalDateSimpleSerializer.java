package com.ideatik.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class JsonLocalDateSimpleSerializer extends JsonSerializer<LocalDateTime> {

	DateTimeFormatter formatterFull = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

	DateTimeFormatter formatterOnlyDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	@Override
	public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers)
			throws IOException {
		// TODO Auto-generated method stub
		if(value.toString().length() > 10) {
			gen.writeString(formatterFull.format(value));
		}else {
			gen.writeString(formatterOnlyDate.format(value));
		}
	}

	
}
