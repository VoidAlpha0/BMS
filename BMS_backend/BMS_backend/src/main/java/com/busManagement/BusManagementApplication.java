package com.busManagement;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.busManagement.entity.Admin;
import com.busManagement.serviceImpl.AdminServiceImpl;

@SpringBootApplication
public class BusManagementApplication {
	
	@Autowired
	AdminServiceImpl adminService;
	


	public static void main(String[] args) {
		SpringApplication.run(BusManagementApplication.class, args);
		System.out.println("Started!!!!");
	}

}
