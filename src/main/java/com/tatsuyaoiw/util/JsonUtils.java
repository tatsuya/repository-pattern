package com.tatsuyaoiw.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;

public class JsonUtils {

	private static final ObjectMapper MAPPER = new ObjectMapper();

	static {
		MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
	}

	public static String serialize(Object value) {
		try {
			return MAPPER.writeValueAsString(value);
		} catch (JsonProcessingException e) {
			throw new IllegalArgumentException("Failed to serialize object to json", e);
		}
	}

	public static <T> T deserialize(String json, Class<T> clazz) {
		try {
			return MAPPER.readValue(json, clazz);
		} catch (IOException e) {
			throw new IllegalArgumentException("Failed to parse json: " + json + " to object: " + clazz.getName(), e);
		}
	}

}
