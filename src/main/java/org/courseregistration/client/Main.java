package org.courseregistration.client;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.courseregistration.client.client.SectionClient;
import org.courseregistration.client.client.StudentClient;

public class Main {

	public static void main(String arg[]) throws UnsupportedEncodingException,
			NoSuchAlgorithmException {

		System.out.println("Client app");

		// StudentClient.getStudent();
		// CxfTest_Shrutee.getStudent();

		// SectionClient.getSection();

		// TestAuthResource.getResource();
		// StudentClient.getAllStudents();
		//TestAuthResource.getResource();
		//StudentClient.getAllStudents();
		//TestAuthResource.getResource();
		TestAuthResource.test();
		//StudentClient.getAllStudents();

		// StudentClient.deleteStudent();

		StudentClient.setStudent();
		SectionClient.getSection();

		// SectionClient.getAllSections();
	}
}
