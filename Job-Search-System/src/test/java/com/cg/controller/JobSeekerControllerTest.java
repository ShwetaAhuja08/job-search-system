package com.cg.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.HttpClientErrorException;

import com.cg.JobSearchSystem.JobSearchSystemApplication;
import com.cg.dao.JobSeekerSpringDataDao;
import com.cg.entity.Job;
import com.cg.entity.JobSeeker;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = JobSearchSystemApplication.class)
@AutoConfigureMockMvc 
@AutoConfigureTestDatabase(replace=Replace.NONE)

public class JobSeekerControllerTest {
	
	@Autowired
    private MockMvc mvc;
	@Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private JobSeekerSpringDataDao jobSeekerSpringDatadao;
    
//    @AfterEach
//    public void resetDb() {
//    	jobSeekerSpringDatadao.deleteAll();
//    }

    @Test
    public void whenValidInput_thenRegisterJobSeeker() throws IOException, Exception {
    	JobSeeker neelam = new JobSeeker("Neelam");
    	JobSeeker priyanka = new JobSeeker("Priyanka");
    	
    	mvc.perform(post("/jss/jobSeekers/").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(neelam)));
    	mvc.perform(post("/jss/jobSeekers/").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(priyanka)));
    	
    	List<JobSeeker> found = jobSeekerSpringDatadao.findAll();
        assertThat(found).extracting(JobSeeker::getJobSeeker_Name).containsOnly("Neelam","Priyanka");
    	
    	    	
    }
    @Test
	public void givenPostAJob_whenGetJobById_thenStatus200() throws Exception {
		String id = "72";
		mvc.perform(get("/jss/employers/{id}",id).accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("id").exists());

	}
	
	@Test
    public void testDeleteJob() {
         int id = 1;
         Job job = restTemplate.getForObject("/jss/employers/delete/"+id, Job.class);
         restTemplate.delete("/jss/employers/delete/"+id);
         try {
              job = restTemplate.getForObject("/jss/employers/delete/"+id, Job.class);
         } catch (final HttpClientErrorException e) {
              assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
         }
    }

}
