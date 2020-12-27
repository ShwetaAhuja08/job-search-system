package com.cg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cg.entity.AppliedJobs;
import com.cg.entity.FavoriteJobs;
import com.cg.entity.Job;
import com.cg.entity.JobSeeker;
import com.cg.entity.Message;
import com.cg.exception.JobSeekerException;
import com.cg.service.AddToApplyDO;
import com.cg.service.AppliedJobsDO;
import com.cg.service.FavoriteJobsDO;
import com.cg.service.JobSeekerServices;
import com.cg.service.MessageDO;
@RestController
@RequestMapping("/jss/jobSeekers")
public class JobSeekerController {


	@Autowired(required=false)
	@Qualifier(value="jobSeekerServiceSpring")
	private JobSeekerServices jobSeekerService;

	//http://localhost:8081/jss/jobSeekers/
	//add jobSeekers	
	@PostMapping("/")
	public String registerJobSeeker(@RequestBody JobSeeker jobSeeker) throws Exception {
		try {
			JobSeeker status= jobSeekerService.registerJobSeeker(jobSeeker);
			if(!status.equals(null)) {
				System.out.println("employer:"+jobSeeker.getJobSeeker_Name()+" added to database");
				return "jobSeeker:"+jobSeeker.getJobSeeker_Name()+" added to database";
			}else {
				System.out.println("Unable to register jobSeeker");
				return "Unable to register jobSeeker to database";
			}

		}catch(JobSeekerException e) {
			System.out.println(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}

	//get all products between given two prices
	//http://localhost:8081/jss/jobSeekers/Pune
	@GetMapping("/{l1}")
	public ResponseEntity<List<Job>> getJobByLocation(@PathVariable("l1") String l1) throws Exception{
		try {
			List<Job> productList= jobSeekerService.getJobsByLocation(l1);
			return new ResponseEntity<>(productList,HttpStatus.OK);
		}catch(JobSeekerException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}


	//get job by Id
	//http://localhost:8081/jss/jobSeekers/job/21
	@GetMapping("/job/{jobId}")
	public ResponseEntity<Job> getJobById(@PathVariable Integer jobId) throws Exception{
		try {
			Job job= jobSeekerService.getJobById(jobId);
			return new ResponseEntity<>(job,HttpStatus.OK);
		}catch(JobSeekerException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}

	//applyAJob
	//http://localhost:8081/jss/jobSeekers/applyAJob/
	@PostMapping("/applyAJob/")
	public String applyAJob(@RequestBody AppliedJobsDO appliedJobsDO ) throws Exception {
		try {
			AppliedJobs status= jobSeekerService.applyAJob(appliedJobsDO);
			if(!status.equals(null)) {
				System.out.println("job:"+status.getAppliedJob_id()+" added to database");
				return "appliedJob:"+status.getAppliedJob_id()+" added to database";
			}else {
				System.out.println("Unable to post a job");
				return "Unable to apply a job in the database";
			}
		}catch(JobSeekerException e) {
			System.out.println(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}

	//applyAFavoriteJob
	//http://localhost:8081/jss/jobSeekers/addToFav/
	@PostMapping("/addToFav/")
	public String addToFavJob(@RequestBody FavoriteJobsDO favoriteJobsDO ) throws Exception {
		try {
			FavoriteJobs status= jobSeekerService.addToFavJob(favoriteJobsDO);
			if(!status.equals(null)) {
				System.out.println("job:"+status.getFavJob_id()+" added to database");
				return "FavoriteJob:"+status.getFavJob_id()+" added to database";
			}else {
				System.out.println("Unable to post a job");
				return "Unable to add favorite job in the database";
			}
		}catch(JobSeekerException e) {
			System.out.println(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}



	//get all favorite jobs
	//http://localhost:8081/jss/jobSeekers/getAllFavJobs/20

	@GetMapping("/getAllFavJobs/{jobSeekerId}") 
	public ResponseEntity<List<FavoriteJobs>> getAllFavJob(@PathVariable Integer jobSeekerId) throws JobSeekerException{ 
		try { 
			List<FavoriteJobs> jobList = jobSeekerService.getAllFavJobs(jobSeekerId); 
			return new ResponseEntity<>(jobList,HttpStatus.OK); 
		}catch(JobSeekerException e) { 
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage()); 
		} 
	}

	
	//get all products
		//http://localhost:8081/jss/jobSeekers/getAppliedJobs/22
		@GetMapping("/getAppliedJobs/{appliedJobId}")
		public ResponseEntity<List<AppliedJobs>> getAllAppliedJobs(@PathVariable Integer appliedJobId){
			try {
				List<AppliedJobs> productList = jobSeekerService.getAllAppliedJobs(appliedJobId);
				return new ResponseEntity<>(productList,HttpStatus.OK);
			}catch(JobSeekerException e) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
			}
		}
		
		//http://localhost:8081/jss/jobSeekers/deleteAppliedJob/id
				//delete applied job
				@DeleteMapping("/deleteAppliedJob/{id}")
				public String deleteAppliedJob(@PathVariable Integer id) throws JobSeekerException {
					try {
						Integer status= jobSeekerService.deleteAppliedJob(id);
						if(!status.equals(null)) {
							return "job: "+id+" deleted from database";
						}else {

							return "Unable to delete product from database";
						}

					}catch(JobSeekerException e) {
						throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
					}
				}
				
				//http://localhost:8081/jss/jobSeekers/deleteFavoriteJob/id
				//delete applied job
				@DeleteMapping("/deleteFavoriteJob/{id}")
				public String deleteFavoriteJob(@PathVariable Integer id) throws JobSeekerException {
					try {
						Integer status= jobSeekerService.deleteFavoriteJob(id);
						if(!status.equals(null)) {
							return "job: "+id+" deleted from database";
						}else {

							return "Unable to delete product from database";
						}

					}catch(JobSeekerException e) {
						throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
					}
				}
				
				//addToFavoriteJob
				//http://localhost:8081/jss/jobSeekers/addToApply/
				@PostMapping("/addToApply/")
				public String addToAppliedJob(@RequestBody AddToApplyDO addToApplyDO ) throws Exception {
					try {
						AppliedJobs status= jobSeekerService.addToAppliedJobs(addToApplyDO);
						if(!status.equals(null)) {
							System.out.println("job:"+addToApplyDO.getFavJobId()+" added to Applied Job");
							return "appliedJob:"+status.getAppliedJob_id()+" added to database";
						}else {
							System.out.println("Unable to post a job");
							return "Unable to apply a job in the database";
						}
					}catch(JobSeekerException e) {
						System.out.println(e.getMessage());
						throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
					}
				}
				
//				//sendMessageToEmployer
//				//http://localhost:8081/jss/jobSeekers/sendMsgToEmployer/
//				@PostMapping("/sendMsgToEmployer/")
//				public String sendMessageToEmployer(@RequestBody MessageDO messageDO ) throws Exception {
//					try {
//						Message status= jobSeekerService.sendMessageToEmployer(messageDO);
//						if(!status.equals(null)) {
//							System.out.println("job:"+messageDO.getDescription()+" added to Applied Job");
//							return "appliedJob:"+messageDO.getDescription()+" added to database";
//						}else {
//							System.out.println("Unable to post a job");
//							return "Unable to apply a job in the database";
//						}
//					}catch(JobSeekerException e) {
//						System.out.println(e.getMessage());
//						throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
//					}
//				}


}


