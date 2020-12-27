package com.cg.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cg.entity.AppliedJobs;

@Repository
public interface AppliedJobsDAO extends JpaRepository<AppliedJobs,Integer>{

}
