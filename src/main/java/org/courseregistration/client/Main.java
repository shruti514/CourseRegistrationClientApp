package org.courseregistration.client;

import org.courseregistration.client.client.*;

public class Main {

	public static void main(String arg[]) {

		System.out.println("Client app");

		StudentClient.getStudent();
		// CxfTest_Shrutee.getStudent();

		StudentClient.getAllStudents();

		//SectionClient.getSection();
	}
}
