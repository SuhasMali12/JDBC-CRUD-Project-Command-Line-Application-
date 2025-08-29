package com.qsp;

import java.util.Scanner;

import com.qsp.controller.Driver;

public class Main {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		Driver driver = new Driver();
		int choice = 0;

		while (true) {

			System.out.println(
					"1. Save Student\n 2. Update Student\n 3. Delete Student\n 4. Get Data By Id\n 5.Get All Data\n 6. Exit\n");
			System.out.print("Select choice : ");
			choice = scanner.nextInt();

			switch (choice) {

			case 1: {
				System.out.println("Enter Id : ");
				int id = scanner.nextInt();
				System.out.println("Enter Name : ");
				String name = scanner.next();
				System.out.println("Enter MobNo : ");
				Long mobno = scanner.nextLong();
				System.out.println("Enter Email : ");
				String email = scanner.next();
				System.out.println("Enter Address : ");
				String address = scanner.next();

				driver.saveStudent(id, name, mobno, email, address);

			}
				break;
			case 2: {
				System.out.print("Enter Id : ");
				int id = scanner.nextInt();
				driver.updateStudent(id);
			}
				break;
			case 3: {
				System.out.print("Enter Id : ");
				int id = scanner.nextInt();
				driver.deleteStudent(id);
			}
				break;
			case 4: {
				System.out.println("Enter Id : ");
				int sid = scanner.nextInt();
				driver.getDataById(sid);

			}
				break;
			case 5: {
				driver.getAllData();
			}
				break;
			case 6: {
				System.out.println("Thank You...!");
				System.exit(0);
			}
				break;
			default:
				System.out.println("Invalid Choice!");
			}

		}

	}

}