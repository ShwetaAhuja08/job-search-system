package com.cg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.dao.AppliedJobsDAO;
import com.cg.dao.EmployerSpringDataDAO;
import com.cg.dao.JobSeekerSpringDataDao;
import com.cg.dao.JobSpringDataDAO;
import com.cg.dao.MessageDAO;
import com.cg.entity.Job;
import com.cg.entity.JobSeeker;
import com.cg.entity.Message;
import com.cg.exception.MessageException;

@Primary
@Service(value="messageService")
@Transactional(rollbackFor=MessageException.class)
public class MessageServiceImpl implements MessageService{


	@Autowired
	private JobSeekerSpringDataDao jobSeekerSpringDataDaoImpl;
	@Autowired
	private JobSpringDataDAO jobSpringDataDaoImpl;
	@Autowired 
	private MessageDAO messageDAO;
	
	@Override
	public Message sendMessageToEmployer(MessageDO messageDO) throws MessageException {
		try { 

			Job j= null; 
			JobSeeker js = null;
			//Employer e = null;
			Message message = new Message();
			j =jobSpringDataDaoImpl.findById(messageDO.getJobId()).get();
			js =jobSeekerSpringDataDaoImpl.findById(messageDO.getJobseekerId()).get();
			//e = employerSpringDataDao.findById(messageDO.getJobId()).get();
			//e = jobSpringDataDaoImpl.findById(messageDO.getEmployerId()).get;
			//message.setId(messageDO.getId());
			message.setDescription(messageDO.getDescription());
			message.setJobseeker(js);
			message.setJob(j);
			message.setEmployer(j.getEmployer());
			message.setDate(messageDO.getDate());

			message = messageDAO.save(message);
			return message;
		}catch(DataAccessException e) { 
			throw new MessageException(e.getMessage(),e); 
		}catch(Exception e) { 
			throw new MessageException(e.getMessage(),e);

		} 

	}




	//	@Override
	//	public Message sendMessageToEmployer(MessageDO messageDO) throws JobSeekerException {
	//		try { 
	//			Message message = new Message();
	//			message.setDescription(messageDO.getDescription());
	//			JobSeeker jobSeeker = jobSeekerSpringDataDaoImpl.findById(messageDO.getJobseekerId()).get();
	//			message.setJobseeker(jobSeeker);
	//			Job job = jobSpringDataDaoImpl.findById(messageDO.getJobId()).get();
	//			message.setJob(job);
	//			Employer employer = employerSpringDataDao.findById(messageDO.getEmployerId()).get();
	//			message.setEmployer(employer);
	//			message.setDate(messageDO.getDate());
	//		
	//			
	//			message=messageDAO.save(message);
	//			//messageDAO.flush();
	//			
	//			return message;
	//		}catch(DataAccessException e) { 
	//			e.printStackTrace();
	//			throw new JobSeekerException(e.getMessage(),e); 
	//		}catch(Exception e) { 
	//			throw new JobSeekerException(e.getMessage(),e);
	//
	//		} 
	//		
	//
	//	}

}
