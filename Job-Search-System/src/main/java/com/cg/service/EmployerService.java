package com.cg.service;

import java.util.List;

import com.cg.entity.Employer;
import com.cg.entity.Job;
import com.cg.entity.JobSeeker;
import com.cg.exception.EmployerException;

public interface EmployerService {
	public Employer registerEmployer(Employer employerObject) throws EmployerException;

	public Job postAjob(Job job, Integer id) throws EmployerException;
	
	public Job getJobById(Integer jobId) throws EmployerException;
	public Integer deleteJob(Integer job_id)throws EmployerException;
	public List<Job> getAllJobs(Integer id)throws EmployerException;
	public List<JobSeeker> searchJobSeekersBySkillSets(String skill)throws EmployerException;
	public Job editAJob(Job job) throws EmployerException;
    public Employer getEmployerByName(String name);

        
	
	
}
