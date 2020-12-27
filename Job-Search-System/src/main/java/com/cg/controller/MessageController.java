package com.cg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cg.entity.Message;
import com.cg.exception.MessageException;
import com.cg.service.MessageDO;
import com.cg.service.MessageService;

@RestController
@RequestMapping("/jss/message")
public class MessageController {
	
	@Autowired(required=false)
	@Qualifier(value="messageService")
	private MessageService messageService;

	
	//sendMessageToEmployer
	//http://localhost:8081/jss/message/sendMsgToEmployer/
	@PostMapping("/sendMsgToEmployer")
	public String sendMessageToEmployer(@RequestBody MessageDO messageDO ) throws Exception {
		try {
			Message status= messageService.sendMessageToEmployer(messageDO);
			//if(!status.equals(null)) {
				System.out.println("job:"+status.getDescription()+" added to message ");
				return "appliedJob:"+status.getDescription()+" added to database";
//			}else {
//				System.out.println("Unable to post a job");
//				return "Unable to apply a job in the database";
//			}
		}catch(MessageException e) {
			//System.out.println(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}


}
