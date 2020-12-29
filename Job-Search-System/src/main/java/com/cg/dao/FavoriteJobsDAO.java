package com.cg.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cg.entity.FavoriteJobs;

/***
 * This FavoriteJobsDAO class represents the repository that extends JPA repository
 * @author admin
 *
 */

@Repository
public interface FavoriteJobsDAO extends JpaRepository<FavoriteJobs,Integer>{

}
