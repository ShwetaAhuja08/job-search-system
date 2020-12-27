package com.cg.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.PersistenceException;

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
import com.cg.entity.Message;
import com.cg.exception.EmployerException;
import com.cg.exception.JobSeekerException;

@Primary
@Service
@Transactional(rollbackFor= EmployerException.class)
public class EmployerServiceSpringDataImpl implements EmployerService{
	@Autowired
	private EmployerSpringDataDAO employerSpringDataDaoImpl;
	@Autowired
	private JobSpringDataDAO jobSpringDataDaoImpl;
	@Autowired
	private JobSeekerSpringDataDao jobSeekerSpringDataDaoImpl;

	@Override
	public Employer registerEmployer(Employer employerObject) throws EmployerException, Exception {
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

	@Override
	public Job postAjob(Job job, Integer id) throws EmployerException, Exception {

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

	@Override
	public Integer deleteJob(Integer jobId) throws PersistenceException, Exception {
		try {
			jobSpringDataDaoImpl.deleteById(jobId);
			return jobId;
		}catch(DataAccessException e) {
			throw new EmployerException(e.getMessage(),e);
		}catch(Exception e) {
			throw new EmployerException(e.getMessage(),e);
		}

	}

	@Override
	public List<Job> getAllJobs(Integer id) throws PersistenceException, Exception {
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


	@Override
	public Employer findById(Integer employer_id) throws EmployerException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Message> viewMessageByJobId(Integer job_id) throws PersistenceException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Message> getAllMessage() throws PersistenceException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<JobSeeker> searchJobSeekersBySkillSets(String skill) throws PersistenceException, Exception {
		try {
			List<JobSeeker> jobSeekerList= employerSpringDataDaoImpl.getJobSeekersBySkill(skill);
			return jobSeekerList;
		}catch(DataAccessException e) {
			throw new JobSeekerException(e.getMessage(),e);
		}catch(Exception e) {
			throw new JobSeekerException(e.getMessage(),e);
		}


	}

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

	@Override
	public Employer getEmployerByName(String name){
		return employerSpringDataDaoImpl.findByOrganizationName(name);
	}

}
