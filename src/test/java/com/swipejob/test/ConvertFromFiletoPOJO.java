package com.swipejob.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swipejobs.file.jobs.Job;
import com.swipejobs.file.workers.Worker;

public class ConvertFromFiletoPOJO {
	
	public static void main(String[] args) throws IOException{
		
		
		byte[] jsonData = Files.readAllBytes(Paths.get("workers2.txt"));
		byte[] jobJsonData = Files.readAllBytes(Paths.get("jobs.txt"));
		List<Job> jobsDrivingLicenseFlagList = null;
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		Worker[] workersList = objectMapper.readValue(jsonData, Worker[].class);
		Job[] jobsList = objectMapper.readValue(jobJsonData, Job[].class);
		
		int i = 4;

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
	
	}
	
	public static boolean checkIfContains(List<String> workersCert, List<String> requiredCertsForJob) {
		
		if(workersCert.containsAll(requiredCertsForJob)) {
			return true;
		}
		
		return false;
	}

}
