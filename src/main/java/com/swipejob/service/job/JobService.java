package com.swipejob.service.job;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swipejob.client.JobClient;
import com.swipejob.client.WorkerClient;
import com.swipejobs.file.jobs.Job;
import com.swipejobs.file.workers.Worker;

@Path("/jobs")
public class JobService {
	
	
	  @Path("{workerId}")
	  @GET
	  @Produces("application/json")
	  public Response getJobs(@PathParam("workerId") int workerId) throws Exception {
		  	WorkerClient workerClient = new WorkerClient();
		  	JobClient jobClient = new JobClient();
		  	
		  	Worker[] workersList = workerClient.getWorker();
		  	Job[] jobsList = jobClient.getJobs();

		  	List<Job> jobsDrivingLicenseFlagList = null;
			
			ObjectMapper objectMapper = new ObjectMapper();
			
			int i = workerId;

			Worker workerFilteredOnId = Arrays.stream(workersList)
					.filter(x -> x.getUserId() == i)
					.findAny()
					.orElse(null);
			
			if(workerFilteredOnId != null) {
				System.out.println("Worker found is " + workerFilteredOnId.getName().getFirst()
						+"and her email is "+workerFilteredOnId.getEmail());
				jobsDrivingLicenseFlagList = Arrays.stream(jobsList)
						.filter(x -> x.getDriverLicenseRequired() == workerFilteredOnId.getHasDriversLicense())
						.collect(Collectors.toList());
				
				jobsDrivingLicenseFlagList.stream()
					.forEach(s -> System.out.println("Title is "+s.getJobTitle() +" and id is "
							+s.getJobId()));
			}
			
			List<String> workersCertificates = workerFilteredOnId.getCertificates();
			workersCertificates.removeAll(Collections.singleton(null));
			
			HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
			int count = 0;
			List<Job> finalJobList = new ArrayList<Job>();
			for (Job job : jobsDrivingLicenseFlagList) {
				
				List<String> requireCertificates = job.getRequiredCertificates();
				
				if(checkIfContains(workersCertificates, requireCertificates)) {
					count ++;
					finalJobList.add(job);
					if(count == 3) break;
					else {
						System.out.println("Could not find any job for the worker as criteria didnt meet for required certification");
					}
				}
				
			}
			
			finalJobList.stream()
			 .forEach(s -> System.out.println("Job Title is"+s.getJobTitle()));
		
		  GenericEntity<List<Job>> entity = new GenericEntity<List<Job>>(finalJobList) {};
		  
		  return Response.ok(entity).build();
		//return Response.status(200).entity(finalJobList).build();
	  }

	  public static boolean checkIfContains(List<String> workersCert, List<String> requiredCertsForJob) {
			
			if(workersCert.containsAll(requiredCertsForJob)) {
				return true;
			}
			
			return false;
		}
	  
}
