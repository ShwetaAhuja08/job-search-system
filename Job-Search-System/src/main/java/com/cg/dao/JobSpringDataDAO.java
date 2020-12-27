package com.cg.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cg.entity.Job;

@Repository

public interface JobSpringDataDAO extends JpaRepository<Job,Integer>{

}
