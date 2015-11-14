package org.courseregistration.client;

import org.courseregistration.client.client.SectionClient;
import org.courseregistration.client.client.StudentClient;

public class Main {

	public static void main(String arg[]) {

		System.out.println("Client app");

		StudentClient.getStudent();
		// CxfTest_Shrutee.getStudent();

		SectionClient.getSection();
	}
}
