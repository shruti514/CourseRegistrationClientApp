package org.courseregistration.client.client;

import java.util.List;

import org.courseregistration.client.model.Content;
import org.courseregistration.client.model.Course;
import org.courseregistration.client.model.Section;
import org.courseregistration.client.resources.CourseResource;
import org.courseregistration.client.resources.SectionResource;
import org.courseregistration.client.responses.CourseResponse;
import org.courseregistration.client.responses.SectionResponse;
import org.jboss.resteasy.client.ProxyFactory;

public class CourseClient {

    private static CourseResource resource = ProxyFactory.create(CourseResource.class,
            "http://localhost:8888/api.courseregistration/");

    public static void getCourseById() {

        Course response = resource.getCourse(100003);
        System.out.println(response.toString());

    }

    public static void getAllCourse() {
        CourseResponse response = resource.getAllCourses();
        List<Content> contents = response.getContent();
        for(Content content:contents)
        {
            System.out.println(content.getCourse().toString());
        }
    }

    public static void getCoursesPaged()
    {
        CourseResponse response = resource.getCoursesPaginated(2, 2);
        List<Content> contents = response.getContent();
        for(Content content:contents)
        {
            System.out.println(content.getCourse().toString());
        }
    }

    public static void deleteCourseById()
    {
        CourseResponse response = resource.deleteById(100003);
        System.out.println(response);
    }
}
