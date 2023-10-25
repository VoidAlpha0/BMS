package com.busManagement.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Location {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false)
	private String terminal;
	@Column(nullable = false)
	private String city;
	@Column(nullable = false)
	private String state;
	
	
	
	public Location() {
		
	}
	public long getId() {
		return id;
	}
	public Location(String terminal, String city, String state) {
		super();
		this.terminal = terminal;
		this.city = city;
		this.state = state;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTerminal() {
		return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}}
