package com.swipejob.client;

import java.io.IOException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.swipejobs.file.workers.Worker;
import com.swipejobs.file.workers.WorkerDeserializer;


public class WorkerClient {
	
	private ObjectMapper objectMapper = null;
	private SimpleModule module = null;
	private Worker[] worker = null;
	
	public Worker[] getWorker() throws JsonParseException, JsonMappingException, IOException {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://test.swipejobs.com/api/workers");
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_XML);
		Response response = invocationBuilder.get();
		
		String res = response.readEntity(String.class);
		objectMapper = registerDeserializer();
		
		if(objectMapper != null) {
			worker = objectMapper.readValue(res, Worker[].class);
		}
		
		return worker;
	}
		
	
	
	public ObjectMapper registerDeserializer(){
		objectMapper = new ObjectMapper();
		module = new SimpleModule();
		module.addDeserializer(Worker[].class, new WorkerDeserializer());
		objectMapper.registerModule(module);
		return objectMapper;
	}
	
	/*public static void main(String[] args) throws Exception{
		
		WorkerClient wc = new WorkerClient();
		Worker[] worker = wc.getWorker();
		
		for (Worker worker2 : worker) {
			System.out.println("Worker name is" +worker2.getName().getFirst());
		}
		
	}*/
}
