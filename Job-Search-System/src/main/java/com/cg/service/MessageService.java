package com.cg.service;

import com.cg.entity.Message;
import com.cg.exception.MessageException;

public interface MessageService {
	
	public Message sendMessageToEmployer(MessageDO messageDO) throws MessageException;
	
}
