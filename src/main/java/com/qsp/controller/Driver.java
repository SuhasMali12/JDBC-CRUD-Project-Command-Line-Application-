package com.qsp.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Driver implements ControllerInterface {

	Scanner sc = new Scanner(System.in);

	public void saveStudent(int s_id, String s_name, long s_mobno, String s_email, String s_address) {

		try {
			Class.forName("org.postgresql.Driver");

			Connection connection = DriverManager
					.getConnection("jdbc:postgresql://localhost:5432/projectjdbc?user=postgres&password=root");

			// PreparedStatement statement = connection.prepareStatement("INSERT INTO
			// student (id,name,mobno,email,address) VALUES (?,?,?,?,?)");

			CallableStatement statement = connection.prepareCall("call insert_student_data(?,?,?,?,?)");

			statement.setInt(1, s_id);
			statement.setString(2, s_name);
			statement.setLong(3, s_mobno);
			statement.setString(4, s_email);
			statement.setString(5, s_address);

			boolean execute = statement.execute();
			System.out.println("Data Saved!");

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void updateStudent(int s_id) {

		try {

			Class.forName("org.postgresql.Driver");

			Connection connection = DriverManager
					.getConnection("jdbc:postgresql://localhost:5432/projectjdbc?user=postgres&password=root");

			// function for search id in table if exist return 1 else 0
			CallableStatement call = connection.prepareCall("select search_id(?)");

			call.setInt(1, s_id);
			ResultSet queryRes = call.executeQuery();

			queryRes.next(); // shift cursor to first row

			int temp_id = queryRes.getInt(1); // return only one column

			if (temp_id != 0) {

				System.out.println("choice what to update : ");
				System.out.println("1. Name  ");
				System.out.println("2. MobNo  ");
				System.out.println("3. Email ");
				System.out.println("4. Address ");
				System.out.print("Enter Choice : ");
				int temp = sc.nextInt();

				switch (temp) {
				case 1: {
					System.out.print("Enter Name : ");
					String sname = sc.next();
					PreparedStatement statement = connection
							.prepareStatement("UPDATE student SET name = ? WHERE id = ?");

					statement.setString(1, sname);
					statement.setInt(2, s_id);

					statement.executeUpdate();
					System.out.println("Data Updated!");
				}
					break;
				case 2: {
					System.out.print("Enter MobNo : ");
					Long smob = sc.nextLong();
					PreparedStatement statement = connection
							.prepareStatement("UPDATE student SET mobno = ? WHERE id = ?");

					statement.setLong(1, smob);
					statement.setInt(2, s_id);

					statement.executeUpdate();
					System.out.println("Data Updated!");
				}
					break;
				case 3: {
					System.out.print("Enter Email : ");
					String semail = sc.next();
					PreparedStatement statement = connection
							.prepareStatement("UPDATE student SET email = ? WHERE id = ?");

					statement.setString(1, semail);
					statement.setInt(2, s_id);

					statement.executeUpdate();
					System.out.println("Data Updated!");
				}
					break;
				case 4: {
					System.out.print("Enter Address : ");
					String saddr = sc.next();
					PreparedStatement statement = connection
							.prepareStatement("UPDATE student SET address = ? WHERE id = ?");

					statement.setString(1, saddr);
					statement.setInt(2, s_id);

					statement.executeUpdate();
					System.out.println("Data Updated!");
				}
					break;
				default:
					System.out.println("Invalid Choice!");
				}
			} else {
				System.out.println("ID Not Found In Table..!");
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void deleteStudent(int s_id) {

		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = DriverManager
					.getConnection("jdbc:postgresql://localhost:5432/projectjdbc?user=postgres&password=root");

			// function for search id in table if exist return 1 else 0
			CallableStatement call = connection.prepareCall("select search_id(?)");

			call.setInt(1, s_id);
			ResultSet queryRes = call.executeQuery();

			queryRes.next(); // shift cursor to first row

			int temp_id = queryRes.getInt(1); // return only one column

			if (temp_id != 0) {
				// if id exist
				PreparedStatement statement = connection.prepareStatement("DELETE FROM student WHERE id = ?");

				statement.setInt(1, s_id);

				int executeUpdate = statement.executeUpdate();

				System.out.println("Student record deleted!");
			} else {
				// if not exist
				System.out.println("ID Not Found In Table..!");
			}

			connection.close();

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void getDataById(int s_id) {
		Connection connection = null;

		try {

			Class.forName("org.postgresql.Driver");

			connection = DriverManager
					.getConnection("jdbc:postgresql://localhost:5432/projectjdbc?user=postgres&password=root");

			// function for search id in table if exist return 1 else 0
			CallableStatement call = connection.prepareCall("select search_id(?)");

			call.setInt(1, s_id);
			ResultSet queryRes = call.executeQuery();

			queryRes.next(); // shift cursor to first row

			int temp_id = queryRes.getInt(1); // return only one column

			if (temp_id != 0) {
				// if id exist
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM student WHERE id = ?");
				statement.setInt(1, s_id);

				ResultSet res = statement.executeQuery();

				while (res.next()) {
					System.out.println(
							"\nId : " + res.getInt(1) + "\nName : " + res.getString(2) + "\nMobNo : " + res.getLong(3)
									+ "\nEmail : " + res.getString(4) + "\nAddress : " + res.getString(5) + "\n");
				}
			} else {
				// if id not exist
				System.out.println("ID Not Found In Table..!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void getAllData() {
		Connection connection = null;

		try {
			Class.forName("org.postgresql.Driver");

			connection = DriverManager
					.getConnection("jdbc:postgresql://localhost:5432/projectjdbc?user=postgres&password=root");

			PreparedStatement statement = connection.prepareStatement("SELECT * FROM student");

			ResultSet res = statement.executeQuery();

			while (res.next()) {
				System.out.println("\nId : " + res.getInt(1) + "\nName : " + res.getString(2) + "\nMobNo : "
						+ res.getLong(3) + "\nEmail : " + res.getString(4) + "\nAddress : " + res.getString(5) + "\n");
				System.out.println("------------------------------------");
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {

		Driver driver = new Driver();

		// driver.getDataById(111);
		// driver.updateStudent(111);
//		driver.getAllData();
//		driver.deleteStudent(103);
//		driver.getAllData();
//		driver.updateStudent(101);
//		driver.getDataById(101);
		driver.saveStudent(222, "King", 7756483758l, "king@gmail.com", "Ranchi");
		driver.getDataById(222);
	}

}