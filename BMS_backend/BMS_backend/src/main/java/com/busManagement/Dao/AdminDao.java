package com.busManagement.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.busManagement.entity.Admin;

@Repository
public interface AdminDao extends JpaRepository<Admin, Integer> {
//	Admin findByadminUName(String adminUName);
	@Query("SELECT a FROM Admin a WHERE a.adminUName = :adminUName")
	Admin findByAdminUName(@Param("adminUName") String adminUName);

}