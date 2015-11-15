package org.courseregistration.client;

import org.courseregistration.client.auth.TestAuthResource;
import org.courseregistration.client.client.SectionClient;
import org.courseregistration.client.client.StudentClient;
import org.courseregistration.client.client.*;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class Main {

	public static void main(String arg[]) throws UnsupportedEncodingException, NoSuchAlgorithmException {

		System.out.println("Client app");

		//StudentClient.getStudent();
		// CxfTest_Shrutee.getStudent();

		//SectionClient.getSection();

		//TestAuthResource.getResource();
		//StudentClient.getAllStudents();

		//StudentClient.deleteStudent();

		StudentClient.setStudent();
		//SectionClient.getSection();
	}
}
