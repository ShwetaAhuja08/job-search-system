package com.cg.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.PersistenceException;

import com.cg.entity.Employer;
import com.cg.entity.Job;
import com.cg.entity.JobSeeker;
import com.cg.entity.Message;
import com.cg.exception.EmployerException;

public interface EmployerService {
	public Employer registerEmployer(Employer employerObject) throws EmployerException, Exception;

	public Job postAjob(Job job, Integer id) throws EmployerException,Exception;
	
	public Job getJobById(Integer jobId) throws EmployerException;
	public Integer deleteJob(Integer job_id)throws PersistenceException, Exception;
	public List<Job> getAllJobs(Integer id)throws PersistenceException, Exception;
	public List<JobSeeker> searchJobSeekersBySkillSets(String skill)throws PersistenceException, Exception;
	public Employer findById(Integer employer_id) throws EmployerException, Exception;
	public List<Message> viewMessageByJobId(Integer job_id)throws PersistenceException, Exception;
	public List<Message> getAllMessage()throws PersistenceException, Exception;
	
	public Job editAJob(Job job) throws EmployerException;
	
    public Employer getEmployerByName(String name);

        
	
	
}
