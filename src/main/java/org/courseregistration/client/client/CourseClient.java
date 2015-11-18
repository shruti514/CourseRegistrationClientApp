package org.courseregistration.client.client;

import java.util.List;
import java.util.Scanner;

import org.courseregistration.client.HttpClientFactory;
import org.courseregistration.client.auth.UserContext;
import org.courseregistration.client.model.Content;
import org.courseregistration.client.model.Course;
import org.courseregistration.client.model.Section;
import org.courseregistration.client.resources.CourseResource;
import org.courseregistration.client.resources.SectionResource;
import org.courseregistration.client.responses.CourseResponse;
import org.courseregistration.client.responses.SectionResponse;
import org.jboss.resteasy.client.ProxyFactory;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.ws.rs.core.Response;

public class CourseClient {

    private CourseResource proxy = null;
    private ResteasyWebTarget target = null;

    Scanner reader = new Scanner(System.in);

    public void getConnection(UserContext userContext) throws ServerException {
        if (userContext != null) {
            target = HttpClientFactory.getWebTarget(userContext.getUsername(),
                    userContext.getPassword());
        } else {
            target = HttpClientFactory.getWebTargetForAnonymousUser();
        }
        proxy = target.proxy(CourseResource.class);
    }

    public void closeConection() {
        try {
            if (!target.getResteasyClient().isClosed())
                target.getResteasyClient().close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public CourseResponse getCourse(int id) throws ServerException {
        Response response = proxy.getCourse(id);
        if (response.getStatus() == 200) {
            return response.readEntity(CourseResponse.class);
        }

        throwNewException(response);
        return null;
    }

    public CourseResponse getAllSections() throws ServerException {
        Response response = proxy.getAllCourses();

        if (response.getStatus() == 200) {
            return response.readEntity(CourseResponse.class);
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
