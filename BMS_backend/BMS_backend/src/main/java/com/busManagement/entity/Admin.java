package com.busManagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "admins",schema = "bus")
@Data @NoArgsConstructor @AllArgsConstructor
public class Admin {
	public Admin() {
		
	}
	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	@Id
	private int adminId;
	
	@JsonIgnore
	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(nullable = false) //not-null
	private String password;
	
	@Column(nullable = false)
	private String adminName;
	
	@Column(nullable=false)
	private String adminUName;

	public String getAdminUName() {
		return adminUName;
	}
	public void setAdminUName(String adminUName) {
		this.adminUName = adminUName;
	}
	
	
}