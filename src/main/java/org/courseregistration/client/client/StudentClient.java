package org.courseregistration.client.client;

import org.courseregistration.client.HttpClientFactory;
import org.courseregistration.client.auth.UserContext;
import org.courseregistration.client.resources.StudentResource;
import org.courseregistration.client.responses.StudentResponse;
import org.jboss.resteasy.client.ProxyFactory;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.ws.rs.core.Response;
import java.util.Scanner;

public class StudentClient {

    private StudentResource studentResource = null;
    private ResteasyWebTarget target = null;

    Scanner reader = new Scanner(System.in);

    public void getConnection(UserContext userContext) throws ServerException{
        if(userContext != null) {
            target = HttpClientFactory.getWebTarget(userContext.getUsername(), userContext.getPassword());
        } else {
            target = HttpClientFactory.getWebTargetForAnonymousUser();
        }
        studentResource = target.proxy(StudentResource.class);
    }

    public void closeConnection() {
        try {
            if(!target.getResteasyClient().isClosed())
                target.getResteasyClient().close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //1. See Profile
    public StudentResponse getStudent(Long id) throws ServerException {
        Response response = studentResource.getStudent(id);
         if(response.getStatus() == 200) {
             return response.readEntity(StudentResponse.class);
         }

        throwNewException(response);
                return null;
    }

    // 2. Update Profile
    public StudentResponse updateStudent(int id) throws ServerException {
        Response response = studentResource.updateStudent(id);
        if(response.getStatus() == 200) {
            return response.readEntity(StudentResponse.class);
        }

        throwNewException(response);
                return null;
    }

    //3. Delete Profile
    public String deleteStudent(Long id) throws ServerException {
        Response response = studentResource.deleteStudent(id);
        if(response.getStatus() == 200) {
            return response.readEntity(String.class);
        }

        throwNewException(response);
        return null;

    }

    //4. Search for a course
    public StudentResponse getCourseDetails(int id) throws ServerException {
        Response response = studentResource.getCourseDetails(id);
        if(response.getStatus() == 200) {
            return response.readEntity(StudentResponse.class);
        }

        throwNewException(response);
        return null;
    }

    //5. Search for Professor
    public StudentResponse getProfessorDetails(int id) throws ServerException {
        Response response = studentResource.getProfessorDetails(id);
        if(response.getStatus() == 200) {
            return response.readEntity(StudentResponse.class);
        }

        throwNewException(response);
        return null;
    }


    // 6. List all Sections for the logged in student
    public StudentResponse getAllSections(Long id) throws ServerException {
        Response response = studentResource.getAllSections(id);
        if(response.getStatus() == 200) {
            return response.readEntity(StudentResponse.class);
        }

        throwNewException(response);
        return null;
    }

    // 7. Enroll to a section
    public StudentResponse enrollSection(int id, int section_id) throws ServerException {
        Response response = studentResource.enrollSection(id, section_id);
        if(response.getStatus() == 200) {
            return response.readEntity(StudentResponse.class);
        }

        throwNewException(response);
        return null;
    }

     // 8. Drop a Section
    public StudentResponse deleteSection(int id, int section_id) throws ServerException {
        Response response = studentResource.deleteSection(id, section_id);
        if(response.getStatus() == 200) {
            return response.readEntity(StudentResponse.class);
        }

        throwNewException(response);
        return null;
    }

    private void throwNewException(Response response) throws ServerException {
        String errorResponse = response.readEntity(String.class);
        target.getResteasyClient().close();
        System.out.println("Error:" + response.getStatus() + errorResponse);
        throw new ServerException(errorResponse);
    }

}
