package com.cg.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cg.entity.Message;

/***
 * This MessageDAO class represents the repository that extends JPA repository
 * @author admin
 *
 */
@Repository
public interface MessageDAO extends JpaRepository<Message,Integer>{

}
