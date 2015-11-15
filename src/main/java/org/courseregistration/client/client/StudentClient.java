package org.courseregistration.client.client;

import org.courseregistration.client.resources.StudentResource;
import org.courseregistration.client.responses.StudentResponse;
import org.jboss.resteasy.client.ProxyFactory;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

import javax.ws.rs.core.Response;

public class StudentClient {

	public static void getStudent() {
		//ResteasyClient restEasyClient = new ResteasyClientBuilder().build();
		StudentResource studentResource = ProxyFactory.create(StudentResource.class,
				"http://localhost:8888/api.courseregistration/");
		StudentResponse student = studentResource.getStudent(100025);

		System.out.println(student.toString());
	}

	public static void getAllStudents () {
		StudentResource studentResource = ProxyFactory.create(StudentResource.class,
				"http://localhost:8888/api.courseregistration/");
		StudentResponse student = studentResource.getAllStudents();

		System.out.println(student.toString());
	}

	public static void setStudent() {
		StudentResource studentResource = ProxyFactory.create(StudentResource.class,
				"http://localhost:8888/api.courseregistration/");

		StudentResponse student = studentResource.setStudent(12345);

		System.out.println("Student Added");

	}
	// post multiple students
	public static void setAllStudents() {

	}
	// delete single student
	public static void deleteStudent() {
		StudentResource studentResource = ProxyFactory.create(StudentResource.class,
				"http://localhost:8888/api.courseregistration/");
		StudentResponse student = studentResource.deleteStudent(100025);

		System.out.println(student.toString());
	}
	// delete all students
	public static void deleteAllStudents() {

	}
	// update student details
	public static void updateStudent() {

	}
	// enroll to a section
	public static void enrollSection() {

	}
	// delete a section
	public static void deleteSection() {

	}

}
