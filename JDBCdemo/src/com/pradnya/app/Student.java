package com.pradnya.app;

public class Student {

	private String name;
	private String emailid;
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Student(String name, String emailid) {
		super();
		this.name = name;
		this.emailid = emailid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	
	
}
