package org.courseregistration.client;


import org.courseregistration.client.student.StudentResponse;
import org.jboss.resteasy.client.ProxyFactory;


public class TestRestEasy_shrutee {

public static void  getStudent(){
    StudentResource client = ProxyFactory.create(StudentResource.class,
            "http://localhost:8888/api.courseregistration/");
    StudentResponse student = client.getStudent(100025);

    System.out.println(student.toString());
}

}
