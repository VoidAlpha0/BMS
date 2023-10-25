package com.busManagement.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.busManagement.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
	@Query("SELECT a FROM User a WHERE a.uname = :uname")
	User findByuname(@Param("uname") String uname);

	
}