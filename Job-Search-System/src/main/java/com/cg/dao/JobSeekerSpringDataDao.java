package com.cg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.cg.entity.Job;
import com.cg.entity.JobSeeker;

/***
 * This JobSeekerSpringDataDAO class represents the repository that extends JPA repository
 * @author admin
 *
 */

@Repository
public interface JobSeekerSpringDataDao extends JpaRepository<JobSeeker, Integer>{

	@Query("From Job j where LOWER(j.location) = LOWER(:l1)")
	List<Job> getJobsByLocation(@Param("l1") String l1);
}
