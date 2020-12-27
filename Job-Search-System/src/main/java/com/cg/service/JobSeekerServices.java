package com.cg.service;

import java.util.List;

import com.cg.entity.AppliedJobs;
import com.cg.entity.FavoriteJobs;
import com.cg.entity.Job;
import com.cg.entity.JobSeeker;
import com.cg.entity.Message;
import com.cg.exception.JobSeekerException;

public interface JobSeekerServices {
	
	public JobSeeker registerJobSeeker(JobSeeker jobSeeker) throws JobSeekerException, Exception;
	public List<Job> getJobsByLocation(String l1) throws JobSeekerException,Exception;
	public Job getJobById(Integer jobId) throws JobSeekerException, Exception;
	public AppliedJobs applyAJob(AppliedJobsDO appliedJobsDO) throws JobSeekerException,Exception;
	public FavoriteJobs addToFavJob(FavoriteJobsDO favoriteJobsDO) throws JobSeekerException,Exception;
	public Integer deleteAppliedJob(Integer appliedJobId) throws JobSeekerException;
	public List<FavoriteJobs> getAllFavJobs(Integer JobSeekerId) throws JobSeekerException;
	public List<AppliedJobs> getAllAppliedJobs(Integer appliedJobId) throws JobSeekerException;
	public Integer deleteFavoriteJob(Integer favoriteJobId) throws JobSeekerException;
	public AppliedJobs addToAppliedJobs(AddToApplyDO addToApplyDO) throws JobSeekerException;
	//public Message sendMessageToEmployer(MessageDO messageDO) throws JobSeekerException;
	


}
