package com.cg.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.cg.entity.Employer;
import com.cg.entity.JobSeeker;

/***
 * This EmployerSpringDataDAO class represents the repository that extends JPA repository
 * @author admin
 *
 */

@Repository
public interface EmployerSpringDataDAO extends JpaRepository<Employer,Integer>{

	public Employer findByOrganizationName(String name);
	
	@Query("From JobSeeker j where LOWER(j.skillSet) = LOWER(:skill)")
	List<JobSeeker> getJobSeekersBySkill(@Param("skill") String skill);

}
