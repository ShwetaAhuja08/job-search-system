package com.cg.controller;

//import java.net.http.HttpRequest;
import java.util.List;
import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


import com.cg.entity.Employer;
import com.cg.entity.Job;
import com.cg.entity.JobSeeker;
import com.cg.exception.EmployerException;
import com.cg.exception.JobSeekerException;
import com.cg.service.EmployerService;


@RestController
@RequestMapping("/jss/employers")
public class EmployerController {
	@Autowired
	//@Qualifier
	private EmployerService employerService;
	
	//http://localhost:8081/jss/employers/
		//add product	
		@PostMapping("/")
		public String registerEmployer(@RequestBody Employer employer) throws Exception {
			try {
				Employer status= employerService.registerEmployer(employer);
				if(!status.equals(null)) {
					System.out.println("employer:"+employer.getOrganizationName()+" added to database");
					return "employer:"+employer.getOrganizationName()+" added to database";
				}else {
					System.out.println("Unable to register employer");
					return "Unable to register employer to database";
				}
				
			}catch(EmployerException e) {
				System.out.println(e.getMessage());
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
			}
		}
		
		//http://localhost:8081/jss/employers/postAJob/1
		//add job	
		@PostMapping("/postAJob/{id}")
		public String postAJob(@RequestBody Job job, @PathVariable Integer id) throws Exception {
			try {
				Job status= employerService.postAjob(job,id);
				if(!status.equals(null)) {
					System.out.println("job:"+job.getTitle()+" added to database");
					return "job:"+job.getTitle()+" added to database";
				}else {
					System.out.println("Unable to post a job");
					return "Unable to post job in the database";
				}
				
			}catch(EmployerException e) {
				System.out.println(e.getMessage());
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
			}
		}
		
		//get job by Id
		//http://localhost:8081/jss/employers/3
		@GetMapping("/{jobId}")
		public ResponseEntity<Job> getJobById(@PathVariable Integer jobId){
			try {
				Job job= employerService.getJobById(jobId);
				return new ResponseEntity<>(job,HttpStatus.OK);
			}catch(EmployerException e) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
			}
		}
		
		//http://localhost:8081/jss/employers/delete/id
		//delete job
		@DeleteMapping("/delete/{id}")
		public String deleteJob(@PathVariable Integer id) throws PersistenceException, Exception {
			try {
				Integer status= employerService.deleteJob(id);
				if(!status.equals(null)) {
					return "job: "+id+" deleted from database";
				}else {

					return "Unable to delete product from database";
				}

			}catch(EmployerException e) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
			}
		}
		
		//get all jobs
		//http://localhost:8081/jss/employers/getAllJobs/2
		@GetMapping("/getAllJobs/{id}")
		public ResponseEntity<List<Job>> getAllJob(@PathVariable Integer id) throws PersistenceException, Exception{
			try {
				List<Job> jobList = employerService.getAllJobs(id);
				return new ResponseEntity<>(jobList,HttpStatus.OK);
			}catch(EmployerException e) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
			}
		}
		
		//get all jobseekers by skill
		//http://localhost:8081/jss/employers/skill/Java
		@GetMapping("/skill/{skill}")
		public ResponseEntity<List<JobSeeker>> getJobSeekersBySkill(@PathVariable("skill") String skill) throws Exception{
			try {
				List<JobSeeker> jobSeekerList= employerService.searchJobSeekersBySkillSets(skill);
				return new ResponseEntity<>(jobSeekerList,HttpStatus.OK);
			}catch(EmployerException e) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
			}
		}
		
		//http://localhost:8081/jss/employers/editAJob
		//update job
		@PutMapping("/editAJob")
		public ResponseEntity<Job> updateJob(@RequestBody Job job) {
			try {
				Job updatedJob= employerService.editAJob(job);
				return new ResponseEntity<>(updatedJob,HttpStatus.OK);

			}catch(EmployerException e) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
			}
		}
}