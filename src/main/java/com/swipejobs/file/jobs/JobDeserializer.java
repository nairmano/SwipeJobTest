package com.swipejobs.file.jobs;

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

public class JobDeserializer extends StdDeserializer<Job[]>{

	private static final long serialVersionUID = 8821683092435449413L;

	protected JobDeserializer(Class<?> vc) {
		super(vc);
		
	}
	public JobDeserializer(){
		this(null);
	}
	
	@Override
	public Job[] deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		JsonNode node = jp.getCodec().readTree(jp);
		Job[] job = null;
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
			ObjectReader reader = mapper.reader(new TypeReference<Job[]>() {
			});
			job = reader.readValue(node);
					
		return job;
	}
	
}
