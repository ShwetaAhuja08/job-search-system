package com.cg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
	
	/***
	 * Send message to the employer 
	 * @param messageDO
	 * @return Message
	 */
	@Override
	public Message sendMessageToEmployer(MessageDO messageDO) throws MessageException {
		try { 
			Job j= null; 
			JobSeeker js = null;
			Message message = new Message();
			j =jobSpringDataDaoImpl.findById(messageDO.getJobId()).get();
			js =jobSeekerSpringDataDaoImpl.findById(messageDO.getJobseekerId()).get();
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
	
	/***
	 * Send message to the JobSeeker
	 * @param messageDO
	 * @return Message
	 */
	@Override
	public Message sendMessageToJobseeker(MessageDO messageDO) throws MessageException {
		try { 
			Job j= null; 
			JobSeeker js = null;
			Message message = new Message();
			j =jobSpringDataDaoImpl.findById(messageDO.getJobId()).get();
			js =jobSeekerSpringDataDaoImpl.findById(messageDO.getJobseekerId()).get();
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
}
