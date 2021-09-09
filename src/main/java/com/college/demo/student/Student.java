package com.college.demo.student;

import java.time.LocalDate;
import java.time.Period;


public class Student {
   
	private Long id;
	private String name;
	private String email;
	private LocalDate dob;
	private Integer age;
	
	public Student() {
		super();
	}
	
	/*
	 * public Student(String name, String email, LocalDate dob, Integer age) {
	 * super(); this.name = name; this.email = email; this.dob = dob; this.age =
	 * age; }
	 */

	public Student(Long id, String name, String email, LocalDate dob) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.dob = dob;
	}
	
	public Long getId() {
		return id;
	}


	public Integer getAge() {
		return Period.between(dob, LocalDate.now()).getYears();
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDob() {
		return dob;
	}
	
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", email=" + email + ", dob=" + dob + ", age=" + age + "]";
	}


	
}
