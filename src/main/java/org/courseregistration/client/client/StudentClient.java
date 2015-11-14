package org.courseregistration.client.client;

import org.courseregistration.client.resources.StudentResource;
import org.courseregistration.client.responses.StudentResponse;
import org.jboss.resteasy.client.ProxyFactory;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

public class StudentClient {

	public static void getStudent() {

		ResteasyClient restEasyClient = new ResteasyClientBuilder().build();

		StudentResource client = ProxyFactory.create(StudentResource.class,
				"http://localhost:8888/api.courseregistration/");
		StudentResponse student = client.getStudent(100025);

		System.out.println(student.toString());
	}

}
