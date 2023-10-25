package com.busManagement.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "passengers",schema = "bus")
@Data @NoArgsConstructor @AllArgsConstructor
public class Passenger {
	
	@Id
	@GeneratedValue
	private Integer passengerId;
	
	public Integer getPassengerId() {
		return passengerId;
	}
	public Passenger() {
		
	}
	public void setPassengerId(Integer passengerId) {
		this.passengerId = passengerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Double getLuggage() {
		return luggage;
	}
	public void setLuggage(Double luggage) {
		this.luggage = luggage;
	}
	private String name;
	private Integer age;
	private Double luggage;


}
