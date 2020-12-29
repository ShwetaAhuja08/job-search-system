package com.cg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.dao.EmployerSpringDataDAO;
import com.cg.dao.JobSeekerSpringDataDao;
import com.cg.dao.JobSpringDataDAO;
import com.cg.entity.Employer;
import com.cg.entity.Job;
import com.cg.entity.JobSeeker;
import com.cg.exception.EmployerException;

/***
 * 
 * @author admin
 *
 */

@Primary
@Service
@Transactional(rollbackFor= EmployerException.class)
public class EmployerServiceSpringDataImpl implements EmployerService{
	
	@Autowired
	private EmployerSpringDataDAO employerSpringDataDaoImpl;
	@Autowired
	private JobSpringDataDAO jobSpringDataDaoImpl;
	
	/***
	 * Add Employer to database
	 * @param employer
	 * @return Employer
	 */
	@Override
	public Employer registerEmployer(Employer employerObject) throws EmployerException{
		try {
			Employer e= 
					employerSpringDataDaoImpl.save(employerObject);
			System.out.println(e);
			return e;
		}catch(DataAccessException e) {
			throw new EmployerException(e.getMessage(),e);
		}catch(Exception e) {
			throw new EmployerException(e.getMessage(),e);
		}
	}
	
	/***
	 * Add job details to the database
	 * @param job
	 * @param id
	 * @return Job
	 */	
	@Override
	public Job postAjob(Job job, Integer id) throws EmployerException{
		try {
			Employer e =employerSpringDataDaoImpl.findById(id).get();
			job.setEmployer(e);
			Job j=jobSpringDataDaoImpl.save(job);
			System.out.println(j);
			return j;
		}catch(DataAccessException e) {
			throw new EmployerException(e.getMessage(),e);
		}catch(Exception e) {
			throw new EmployerException(e.getMessage(),e);
		}
	}
	
	/***
	 * Find Job by job id
	 * @param jobId
	 * @return job;
	 */
	@Override
	public Job getJobById(Integer jobId) throws EmployerException {
		try {
			Optional<Job> optional= 
					jobSpringDataDaoImpl.findById(jobId);
			if(optional.isPresent()) {
				System.out.println(optional.get());
				return optional.get();
			}else {
				return null;
			}			
		}catch(DataAccessException e) {
			throw new EmployerException(e.getMessage(),e);
		}catch(Exception e) {
			throw new EmployerException(e.getMessage(),e);
		}
	}
	
	/***
	 * Delete job by job id from job table 
	 * @param id
	 * @return Integer
	 */
	@Override
	public Integer deleteJob(Integer jobId) throws EmployerException{
		try {
			jobSpringDataDaoImpl.deleteById(jobId);
			return jobId;
		}catch(DataAccessException e) {
			throw new EmployerException(e.getMessage(),e);
		}catch(Exception e) {
			throw new EmployerException(e.getMessage(),e);
		}
	}
	
	/***
	 * Get all jobs posted by employer id
	 * @param id
	 * @return jobList;
	 */
	@Override
	public List<Job> getAllJobs(Integer id) throws EmployerException{
		try {
			List<Job> jobList = jobSpringDataDaoImpl.findAll();
			jobList.stream().filter(j->j.getEmployer().getId()==id);
			return jobList;
		}catch(DataAccessException e) {
			throw new EmployerException(e.getMessage(),e);
		}catch(Exception e) {
			throw new EmployerException(e.getMessage(),e);
		}
	}
	
	/***
	 * Find JobSeeker by skills
	 * @param skill
	 * @return jobSeekerList;
	 */
	@Override
	public List<JobSeeker> searchJobSeekersBySkillSets(String skill) throws EmployerException{
		try {
			List<JobSeeker> jobSeekerList= employerSpringDataDaoImpl.getJobSeekersBySkill(skill);
			return jobSeekerList;
		}catch(DataAccessException e) {
			throw new EmployerException(e.getMessage(),e);
		}catch(Exception e) {
			throw new EmployerException(e.getMessage(),e);
		}
	}
	
	/***
	 * Update Job 
	 * @param job
	 * @return ResponseEntity
	 */
	@Override
	public Job editAJob(Job job) throws EmployerException {
		try {
			Job j= jobSpringDataDaoImpl.save(job);
			return j;
		}catch(DataAccessException e) {
			throw new EmployerException(e.getMessage(),e);
		}catch(Exception e) {
			throw new EmployerException(e.getMessage(),e);
		}
	}
	
	/**
	 * Get employer details by organization name
	 * @param name
	 * @return Employer
	 */
	@Override
	public Employer getEmployerByName(String name){
		return employerSpringDataDaoImpl.findByOrganizationName(name);
	}
}
