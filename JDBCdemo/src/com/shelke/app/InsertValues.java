package com.shelke.app;

import java.sql.*;

import org.mariadb.jdbc.internal.com.send.authentication.gssapi.WindowsNativeSspiAuthentication;

import java.awt.Window;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.Console;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class InsertValues {
	static String dbURL="jdbc:mariadb://localhost:3306/demodb";
	static String dbUser="root";
	static String dbPassword="123";
	private static int rollno;
	
	private int insertStudent(int rollNo,String name, String emailid) {
		String sqlQuery="insert into student (Rollno,name,emailid)  values('"+rollNo+"','"+name+"','"+emailid+"')";
		Connection con =null;
		Statement st;	
		try {
			DriverManager.registerDriver(new org.mariadb.jdbc.Driver());
			con = DriverManager.getConnection(dbURL, dbUser, dbPassword);
			st=con.createStatement();
			int r=st.executeUpdate(sqlQuery);
			if(r==1) {
				System.out.println("successful");
			} 
			else {
				System.out.println("database insertion failure");
			} 
			con.close();
			return 1;
		
		} 
		catch (SQLException e) {			
			e.printStackTrace();
			return 0;
		}
		
	}
	private void getStudents() {
		Student student = new Student();
		String sqlQuery="select * from student";    
		Connection con =null;
		Statement st;
		try {
			DriverManager.registerDriver(new org.mariadb.jdbc.Driver());
			con = DriverManager.getConnection(dbURL, dbUser, dbPassword);
			st=con.createStatement();
			System.out.println("Rollno"+"\t"+"name"+"\t\t"+"Emailid");
			ResultSet resultSet =st.executeQuery(sqlQuery);
			while(resultSet.next()) {
				student.setRollno(resultSet.getInt("Rollno"));
				student.setName(resultSet.getString("name"));
				student.setEmailid(resultSet.getString("emailid"));
				
				System.out.println(student.getRollno()+"\t"+student.getName()+"\t\t"+student.getEmailid());
			
			}
			con.close();
			
		
		} 
		catch (SQLException e) {			
			e.printStackTrace();
			
		}
		
	  
		
		
	}
	private void updateStudent() {
			Student student = new Student();
			Connection con =null;
			Statement st;	
			int rollno=0;
			System.out.println("Enter the Rollno");
	try {
			rollno = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			try {
				DriverManager.registerDriver(new org.mariadb.jdbc.Driver());
				con = DriverManager.getConnection(dbURL, dbUser, dbPassword);
				
				st=con.createStatement() ;
				String sqlQuery="select * from student where Rollno ="+rollno;
				ResultSet resultSet =st.executeQuery(sqlQuery);
				if(resultSet.next()) {
					student.setRollno(resultSet.getInt("Rollno"));
					student.setName(resultSet.getString("name"));
					
					System.out.println("Enter new Emailid");
					try {
							student.setEmailid(new BufferedReader(new InputStreamReader(System.in)).readLine());
						} 	catch (IOException e) {
						// TODO Auto-generated catch block
							e.printStackTrace();
						}				
					
					
					String sqlupdate="update Student set emailid='"+student.getEmailid()+"' where Rollno="+student.getRollno();
					
					//System.out.println(sqlQuery);
					int r=st.executeUpdate(sqlupdate);
					if(r==1) {
						System.out.println("successfully updated");
					}else {
						System.out.println("update failure");
					}
				
				}else {
					System.out.println("No data found"); 
				}

				
						
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			  
			
		
		
	}

	public static void main(String[] args) {
		 String choice="";
		 String userInputContent="";
		 InsertValues insertobject= new InsertValues();
		 Student student= new Student();
		int flag=0 ;
		while (true) {
			 System.out.println("*******************");
			 System.out.println("Enter option .");
			 System.out.println("R. Roll no");
			 System.out.println("N.Name");
			 System.out.println("E.EmailId");
			 System.out.println("D. display");
			 System.out.println("U. Update");
			 System.out.println("Q. Exit");
			
			 System.out.println("*******************");
			 
			 try {
	                choice = new BufferedReader(new InputStreamReader(System.in)).readLine();
	            } catch (Exception e) {
	                System.out.println(e);
	            }
	            switch (choice.toUpperCase()) {
             
		          case "R":
	                    System.out.println("User Entry/Update");
	                    System.out.println("Enter RollNo: ");
	                    try {
	                    	 student.setRollno(Integer.parseInt((new BufferedReader(new InputStreamReader(System.in))).readLine()));
	                    	
	                    } 
	                    
	                   
	                    catch (IOException e) {
	                        System.out.println(e);
	                    }
	                   
	                     break;
	                     
		          case "N":
		        	  System.out.println("Enter Name:");
		        	  try {
		        		  student.setName(new BufferedReader(new InputStreamReader(System.in)).readLine());
		        		  
		        	  }
		        	  
		        	  catch (IOException e) {
	                      System.out.println(e);
	                  }
	                   break;
	                   
		          case "E":
			          
		        	  System.out.println("Enter Email Id: ");
		        	  try {
		        		  student.setEmailid(new BufferedReader(new InputStreamReader(System.in)).readLine());
		        		  
		      			
			      			
			      			int num=student.getEmailid().indexOf("@");	
			      			String domainName =student.getEmailid().substring(num+1);
			      	        	
			      			if(domainName.equalsIgnoreCase("gmail.com")) {
			      				flag =1;
			      				//insertobject.insertStudent(student.getRollno(), student.getName(), student.getEmailid());
			      			}
			      			else {
			      				while(!domainName.equalsIgnoreCase("gmail.com")) {
			      					System.out.println("only allowed gmail.com");
			      	        		System.out.println("Entry the emailid only with gmail domain");
			      	    			student.setEmailid(( new BufferedReader(new InputStreamReader(System.in)).readLine()));
			      	    			num=student.getEmailid().indexOf("@");	        	
			      	        		domainName =student.getEmailid().substring(num+1);
			      	        		if(domainName.equalsIgnoreCase("gmail.com")) {
			      	        			flag=1; // to avoid boiler plate codes
			      	        			//insertobject.insertStudent(student.getRollno(), student.getName(), student.getEmailid());
			      	        		}
			      				}
			      					
			      			}
			      	        if(flag==1) {
			      	        		insertobject.insertStudent(student.getRollno(), student.getName(), student.getEmailid());
			      	        }
		      	     
		        	  }		        	  
		        	  catch (Exception e) {
	                      System.out.println(e);
	                  }
		              break;
		              
		          
		          case "D":
			         
					
					/*if(student.getRollno()==0 && student.getName()==null) {
						
						 System.out.println("rollno not set");
						 System.out.println("Name not set");
						 
						
					}else {*/
							
							System.out.println("Display Data:");
							insertobject.getStudents();
							
							
							
					//}
					 break;
		          case "U":
		        	  insertobject.updateStudent();
		        	  break;
                     case "Q":
	                  System.exit(1);
                     default:
	                    break;
	                   	  
	            }
			 
		 }
    }
}
