package com.shelke.app;

public class Student {
	
	private int rollno;
	private String name;
	private String emailid;
	public Student() {
		
		super();
		
	}
	
	public Student(String name, String emailid) {
		super();
		this.name = name;
		this.emailid = emailid;
	}
	public int getRollno() {
		return rollno;
	}
	public void setRollno(int rollno) {
		this.rollno = rollno;
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


