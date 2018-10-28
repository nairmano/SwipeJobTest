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
import com.swipejobs.file.jobs.Job;
import com.swipejobs.file.jobs.JobDeserializer;
import com.swipejobs.file.workers.Worker;
import com.swipejobs.file.workers.WorkerDeserializer;

public class JobClient {


	
	private ObjectMapper objectMapper = null;
	private SimpleModule module = null;
	private Job[] job = null;
	
	public Job[] getJobs() throws JsonParseException, JsonMappingException, IOException {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://test.swipejobs.com/api/jobs");
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_XML);
		Response response = invocationBuilder.get();
		
		String res = response.readEntity(String.class);
		objectMapper = registerDeserializer();
		
		if(objectMapper != null) {
			job = objectMapper.readValue(res, Job[].class);
		}
		
		return job;
	}
		
	
	
	public ObjectMapper registerDeserializer(){
		objectMapper = new ObjectMapper();
		module = new SimpleModule();
		module.addDeserializer(Job[].class, new JobDeserializer());
		objectMapper.registerModule(module);
		return objectMapper;
	}
	
	public static void main(String[] args) throws Exception{
		
		JobClient jc = new JobClient();
		Job[] job = jc.getJobs();
		 for (Job job2 : job) {
			 System.out.println("Job name is" +job2.getJobTitle());
		}
		
	}

}
