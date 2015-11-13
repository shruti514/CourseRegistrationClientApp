package org.courseregistration.client.course;


import org.courseregistration.client.student.Course;
import org.courseregistration.client.student.Link;

import java.util.List;

public class CourseResponse {
    private Course course;
    private List<Link> links;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }
}
