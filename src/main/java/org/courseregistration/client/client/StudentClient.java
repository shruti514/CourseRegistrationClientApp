package org.courseregistration.client.client;

import org.courseregistration.client.resources.StudentResource;
import org.courseregistration.client.responses.StudentResponse;
import org.jboss.resteasy.client.ProxyFactory;

import javax.ws.rs.core.Response;

public class StudentClient {

    //1. See Profile
    public static void getStudent() {
        //ResteasyClient restEasyClient = new ResteasyClientBuilder().getDigestHeader();
        StudentResource studentResource = ProxyFactory.create(StudentResource.class,
                "http://localhost:8888/api.courseregistration/");
        Response student = studentResource.getStudent(100025);

        System.out.println(student.toString());
    }

    // 2. Update Profile
    public static void updateStudent() {
        StudentResource studentResource = ProxyFactory.create(StudentResource.class,
                "http://localhost:8888/api.courseregistration/");

        StudentResponse student = studentResource.updateStudent(100031);

    }

    //3. Delete Profile
    public static void deleteStudent() {
        StudentResource studentResource = ProxyFactory.create(StudentResource.class,
                "http://localhost:8888/api.courseregistration/");
        StudentResponse student = studentResource.deleteStudent(100025);

        System.out.println(student.toString());
    }

    //4. Search for a course
    public static void getCourseDetails() {
        StudentResource studentResource = ProxyFactory.create(StudentResource.class,
                "http://localhost:8888/api.courseregistration/");
        StudentResponse student = studentResource.getCourseDetails();

        System.out.println(student.toString());
    }

    //5. Search for Professor
    public static void getProfessorDetails() {
        StudentResource studentResource = ProxyFactory.create(StudentResource.class,
                "http://localhost:8888/api.courseregistration/");
        StudentResponse student = studentResource.getProfessorDetails(100010);

        System.out.println(student.toString());
    }


    // 6. List all Sections
    public static void getAllSections() {
        StudentResource studentResource = ProxyFactory.create(StudentResource.class,
                "http://localhost:8888/api.courseregistration/");

        StudentResponse student = studentResource.getAllSections();

    }

    // 7. Enroll to a section
    public static void enrollSection() {
        StudentResource studentResource = ProxyFactory.create(StudentResource.class,
                "http://localhost:8888/api.courseregistration/");

        StudentResponse student = studentResource.enrollSection(100031, 100020);

    }

     // 8. Drop a Section
    public static void deleteSection() {
        StudentResource studentResource = ProxyFactory.create(StudentResource.class,
                "http://localhost:8888/api.courseregistration/");

        StudentResponse student = studentResource.deleteSection(100031, 100020);

    }

}
