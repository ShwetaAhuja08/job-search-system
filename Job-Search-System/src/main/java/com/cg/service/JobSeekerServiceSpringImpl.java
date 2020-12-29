package com.cg.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cg.dao.AppliedJobsDAO;
import com.cg.dao.EmployerSpringDataDAO;
import com.cg.dao.FavoriteJobsDAO;
import com.cg.dao.JobSeekerSpringDataDao;
import com.cg.dao.JobSpringDataDAO;
import com.cg.dao.MessageDAO;
import com.cg.entity.AppliedJobs;
import com.cg.entity.FavoriteJobs;
import com.cg.entity.Job;
import com.cg.entity.JobSeeker;
import com.cg.exception.JobSeekerException;

/***
 * 
 * @author admin
 *
 */
@Primary
@Service(value="jobSeekerServiceSpring")
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
	@Autowired
	private MessageDAO messageDAO;
	@Autowired
	private EmployerSpringDataDAO employerSpringDataDAO; 

	/***
	 * Add job Seeker to database
	 * @param jobSeeker
	 * @return jobSeeker
	 */	
	@Override
	public JobSeeker registerJobSeeker(JobSeeker jobSeeker) throws JobSeekerException{
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

	/***
	 * Search job by Location
	 * @param l1
	 * @return jobList
	 */
	@Override
	public List<Job> getJobsByLocation(String l1) throws JobSeekerException{
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
	/***
	 * Find Job By Id
	 * @param jobId
	 * @return Job
	 */
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

	/***
	 * Apply for a job
	 * @param appliedJobsDO
	 * @return appliedJobs
	 */
	@Override 
	public AppliedJobs applyAJob(AppliedJobsDO appliedJobsDO) throws JobSeekerException{ 
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

	/***
	 * Add to favorite jobs
	 * @param favoriteJobsDO
	 * @return favoriteJobs
	 */
	@Override
	public FavoriteJobs addToFavJob(FavoriteJobsDO favoriteJobsDO) throws JobSeekerException{
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

	/***
	 * Get Favorite Jobs by jobseeker Id
	 * @param jobSeekerId
	 * @return jobList
	 */
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

	/***
	 * Get all applied jobs by that job seeker
	 * @param jobSeekerId
	 * @return jobList
	 */
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

	/***
	 * Delete applied job by id
	 * @param id
	 * @return appliedJobId
	 */
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

	/***
	 * Delete favorite job by id
	 * @param id
	 * @return favoriteJobId
	 */
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

	/***
	 * Add to applied jobs from favorite jobs
	 * @param addToApplyDO
	 * @return appliedJobs
	 */
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
}