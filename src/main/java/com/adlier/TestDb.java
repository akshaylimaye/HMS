package com.adlier;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestDb {
	public void connect(String url) {
		try {
			//Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			//Connection connection = DriverManager.getConnection(url);
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/HMSdb","root","abcd@123");
			System.out.println("Connected");
		} catch (Exception e) {
			System.out.println("error"+ e);
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		TestDb connServer = new TestDb();
		//String url = "jdbc:sqlserver://akshayLimaye;user=sa;password=abc@1234;database=HostpitalManagementDb";
		String url = "jdbc:mysql://localhost:3306;user=sa;password=abc@1234;database=HMSdb";
		connServer.connect(url);

	}
}
