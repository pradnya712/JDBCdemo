package com.pradnya.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;

public class InsertValues {
	String dbURL="jdbc:mariadb://localhost:3306/demodb";
	String dbUser="root";
	String dbPassword="123";
	

	public static void main(String[] args){
		
		InsertValues insertobject=new InsertValues();
		Student student = new Student();
		
		try {
			System.out.println("Enter Student Name");
			student.setName((new BufferedReader(new InputStreamReader(System.in))).readLine());
			System.out.println("Enter Student emailid");
			student.setEmailid((new BufferedReader(new InputStreamReader(System.in))).readLine());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		String sqlQuery="insert into student values('"+student.getName()+"','"+student.getEmailid()+"')";
		Connection con =null;
		Statement st;
		
		try {
			DriverManager.registerDriver(new org.mariadb.jdbc.Driver());
			con = DriverManager.getConnection(insertobject.dbURL, insertobject.dbUser, insertobject.dbPassword);
			st=con.createStatement();
			int r=st.executeUpdate(sqlQuery);
			con.close();
			if(r==1) {
			System.out.println("insert successful");
				
			}
			else {
				System.out.println("insert failure");
			}
		} 
		catch (SQLException e) {
			
			e.printStackTrace();
		}
	
	}

}
    