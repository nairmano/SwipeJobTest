package com.swipejobs.file.workers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

/**
 * @author mKabir
 */

public class WorkerDeserializer extends StdDeserializer<Worker[]>{

	private static final long serialVersionUID = 8821683092435449413L;

	protected WorkerDeserializer(Class<?> vc) {
		super(vc);
		
	}
	public WorkerDeserializer(){
		this(null);
	}
	
	@Override
	public Worker[] deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		JsonNode node = jp.getCodec().readTree(jp);
		Worker[] worker = null;
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
			ObjectReader reader = mapper.reader(new TypeReference<Worker[]>() {
			});
			worker = reader.readValue(node);
					
		return worker;
	}
	
}
