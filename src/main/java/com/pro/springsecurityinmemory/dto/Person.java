package com.pro.springsecurityinmemory.dto;

public class Person {
	
	private Integer id;
	private String firstName;
	private String secondName;
	
	Person(){
		
	}
	
	public Person(Integer id, String firstName, String secondName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.secondName = secondName;
	}


	public Integer getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + firstName + ", secondName=" + secondName + "]";
	}
	
	
	
}
