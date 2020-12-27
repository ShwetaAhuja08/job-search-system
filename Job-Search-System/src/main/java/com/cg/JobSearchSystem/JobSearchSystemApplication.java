package com.cg.JobSearchSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.cg")
@EntityScan(basePackages = "com.cg.entity")
@EnableJpaRepositories(basePackages = "com.cg.dao")
public class JobSearchSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobSearchSystemApplication.class, args);
	}

}
