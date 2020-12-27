package com.cg.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cg.dao.AppliedJobsDAO;
import com.cg.dao.EmployerSpringDataDAO;
import com.cg.dao.FavoriteJobsDAO;
import com.cg.dao.JobSeekerSpringDataDao;
import com.cg.dao.JobSpringDataDAO;
import com.cg.dao.MessageDAO;
import com.cg.entity.AppliedJobs;
import com.cg.entity.Employer;
import com.cg.entity.FavoriteJobs;
import com.cg.entity.Job;
import com.cg.entity.JobSeeker;
import com.cg.entity.Message;
import com.cg.exception.JobSeekerException;

@Primary
@Service(value="jobSeekerServiceSpring")
//@Transactional(propagation= Propagation.REQUIRES_NEW)
@Transactional(rollbackFor=JobSeekerException.class)
public class JobSeekerServiceSpringImpl implements JobSeekerServices{

	@Autowired
	private JobSeekerSpringDataDao jobSeekerSpringDataDaoImpl;
	@Autowired
	private JobSpringDataDAO jobSpringDataDaoImpl;
	@Autowired
	private AppliedJobsDAO appliedJobsDaoImpl;
	@Autowired
	private FavoriteJobsDAO favoriteJobsDaoImpl;
		
	@Override
	public JobSeeker registerJobSeeker(JobSeeker jobSeeker) throws JobSeekerException, Exception {
		try {
			JobSeeker js= 
					jobSeekerSpringDataDaoImpl.save(jobSeeker);
			System.out.println(js);
			return js;

		}catch(DataAccessException e) {
			throw new JobSeekerException(e.getMessage(),e);
		}catch(Exception e) {
			throw new JobSeekerException(e.getMessage(),e);
		}
	}

	@Override
	public List<Job> getJobsByLocation(String l1) throws JobSeekerException, Exception {
		try {
			List<Job> jobList=
					jobSeekerSpringDataDaoImpl.getJobsByLocation(l1);
			return jobList;
		}catch(DataAccessException e) {
			throw new JobSeekerException(e.getMessage(),e);
		}catch(Exception e) {
			throw new JobSeekerException(e.getMessage(),e);
		}

	}

	@Override
	public Job getJobById(Integer jobId) throws JobSeekerException {
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
			throw new JobSeekerException(e.getMessage(),e);
		}catch(Exception e) {
			throw new JobSeekerException(e.getMessage(),e);
		}
	}



	@Override 
	public AppliedJobs applyAJob(AppliedJobsDO appliedJobsDO) throws JobSeekerException, Exception { 
		try { 
			Job j= null; 
			JobSeeker js = null;
			AppliedJobs appliedJobs = new AppliedJobs();
			j =jobSpringDataDaoImpl.getOne(appliedJobsDO.getJobId()); 
			js =jobSeekerSpringDataDaoImpl.getOne(appliedJobsDO.getJobSeekerId());
			appliedJobs.setJob(j); 
			appliedJobs.setJobseeker(js); 
			appliedJobs =appliedJobsDaoImpl.save(appliedJobs); 
			return appliedJobs;
		}catch(DataAccessException e) { 
			throw new JobSeekerException(e.getMessage(),e); 
		}catch(Exception e) { 
			throw new JobSeekerException(e.getMessage(),e);

		} 
	}


	@Override
	public FavoriteJobs addToFavJob(FavoriteJobsDO favoriteJobsDO) throws JobSeekerException, Exception {
		try {
			Job j= null;
			JobSeeker js = null;
			FavoriteJobs favoriteJobs = new FavoriteJobs();
			j = jobSpringDataDaoImpl.getOne(favoriteJobsDO.getJobId());
			js = jobSeekerSpringDataDaoImpl.getOne(favoriteJobsDO.getJobSeekerId());
			favoriteJobs.setJob(j);
			favoriteJobs.setJobseeker(js);
			favoriteJobs = favoriteJobsDaoImpl.save(favoriteJobs);
			return favoriteJobs;
		}catch(DataAccessException e) {
			throw new JobSeekerException(e.getMessage(),e);
		}catch(Exception e) {
			throw new JobSeekerException(e.getMessage(),e);

		}
	}



	@Override 
	public List<FavoriteJobs> getAllFavJobs(Integer jobSeekerId) throws JobSeekerException { 
		try { 
			List<FavoriteJobs> jobList=favoriteJobsDaoImpl.findAll();
			jobList.stream().filter(j->j.getJobseeker().getJobSeeker_Id()==jobSeekerId);
			return jobList; 
		}catch(DataAccessException e) {
			throw new JobSeekerException(e.getMessage(),e);
		}catch(Exception e) { 
			throw new JobSeekerException(e.getMessage(),e); 
		} 
	}


	@Override
	public List<AppliedJobs> getAllAppliedJobs(Integer appliedJobId) throws JobSeekerException {
		try {
			List<AppliedJobs> jobList= appliedJobsDaoImpl.findAll();
			jobList.stream().filter(j->j.getAppliedJob_id()==appliedJobId);
			return jobList;
		}catch(DataAccessException e) {
			throw new JobSeekerException(e.getMessage(),e);
		}catch(Exception e) {
			throw new JobSeekerException(e.getMessage(),e);
		}
	}

	@Override
	public Integer deleteAppliedJob(Integer appliedJobId) throws JobSeekerException {
		try {
			appliedJobsDaoImpl.deleteById(appliedJobId);
			return appliedJobId;
		}catch(DataAccessException e) {
			throw new JobSeekerException(e.getMessage(),e);
		}catch(Exception e) {
			throw new JobSeekerException(e.getMessage(),e);
		}
	}

	@Override
	public Integer deleteFavoriteJob(Integer favoriteJobId) throws JobSeekerException {

		try{ 
			favoriteJobsDaoImpl.deleteById(favoriteJobId);
			return favoriteJobId;
		}catch(DataAccessException e) {
			throw new JobSeekerException(e.getMessage(),e);
		}catch(Exception e) {
			throw new JobSeekerException(e.getMessage(),e);
		}

	}

	@Override
	public AppliedJobs addToAppliedJobs(AddToApplyDO addToApplyDO) throws JobSeekerException {
		
		try { 
			FavoriteJobs favJob= favoriteJobsDaoImpl.findById(addToApplyDO.getFavJobId()).get();
			Job j= null; 
			JobSeeker js = null;
			AppliedJobs appliedJobs = new AppliedJobs();
			j =jobSpringDataDaoImpl.getOne(favJob.getJob().getId()); 
			js =jobSeekerSpringDataDaoImpl.getOne(favJob.getJobseeker().getJobSeeker_Id());
			appliedJobs.setJob(j); 
			appliedJobs.setJobseeker(js); 
			appliedJobs = appliedJobsDaoImpl.save(appliedJobs);
			favoriteJobsDaoImpl.deleteById(addToApplyDO.getFavJobId());

			return appliedJobs;
		}catch(DataAccessException e) { 
			throw new JobSeekerException(e.getMessage(),e); 
		}catch(Exception e) { 
			throw new JobSeekerException(e.getMessage(),e);

		} 
	}

//	@Override
//	public Message sendMessageToEmployer(MessageDO messageDO) throws JobSeekerException {
//		try { 
//			Job j= null; 
//			JobSeeker js = null;
//			Employer e = null;
//			Message message = new Message();
//			j =jobSpringDataDaoImpl.findById(messageDO.getJobId()).get();
//			js =jobSeekerSpringDataDaoImpl.findById(messageDO.getJobseekerId()).get();
//			e = employerSpringDataDao.findById(messageDO.getEmployerId()).get();
//			message.setJob(j);
//			message.setJobseeker(js);
//			message.setEmployer(e);
//			message = messageDAO.save(message);
//			return message;
//		}catch(DataAccessException e) { 
//			throw new JobSeekerException(e.getMessage(),e); 
//		}catch(Exception e) { 
//			throw new JobSeekerException(e.getMessage(),e);
//
//		} 
		
		
	}



