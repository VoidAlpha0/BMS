package com.busManagement.utils;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data @NoArgsConstructor @AllArgsConstructor
public class AdminAuth {

	private Integer adminId;
	private String password;
	public Integer getAdminId() {
		return adminId;
	}
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public AdminAuth() {
		
	}
	private String adminUName;
	public String getAdminUName() {
		return adminUName;
	}
	public void setAdminUName(String adminUName) {
		this.adminUName = adminUName;
	}

}