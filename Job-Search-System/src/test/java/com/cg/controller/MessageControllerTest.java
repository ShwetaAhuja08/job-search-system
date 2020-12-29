package com.cg.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.cg.JobSearchSystem.JobSearchSystemApplication;
import com.cg.dao.MessageDAO;
import com.cg.entity.Employer;
import com.cg.entity.Message;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = JobSearchSystemApplication.class)
@AutoConfigureMockMvc 
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class MessageControllerTest {
	
	@Autowired
	private MockMvc mvc;
	@Autowired
	private MessageDAO messageDAO;
	
	@Test
	public void whenValidInput_thenSendMsgToEmployer() throws IOException, Exception {
		Message message = new Message("Applied for job");
		Message message1 = new Message("Shortlisted for job");

		mvc.perform(post("/jss/message/sendMsgToEmployer").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(message)));
		mvc.perform(post("/jss/message/sendMsgToJobSeeker").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(message1)));
		
		List<Message> found = messageDAO.findAll();
		assertThat(found).extracting(Message::getDescription).containsOnly("Applied for job","Shortlisted for job");

	}
	@Test
	public void whenValidInput_thenSendMsgToJobSeeker() throws IOException, Exception {
		Message message = new Message("Applied for job");
		Message message1 = new Message("Shortlisted for job");
		
		mvc.perform(post("/jss/message/sendMsgToJobSeeker").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(message)));
		mvc.perform(post("/jss/message/sendMsgToJobSeeker").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(message1)));
		List<Message> found = messageDAO.findAll();
		assertThat(found).extracting(Message::getDescription).containsOnly("Applied for job", "Shortlisted for job");


	}




}
