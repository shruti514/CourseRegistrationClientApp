package org.courseregistration.client.responses;


import org.courseregistration.client.model.Content;
import org.courseregistration.client.model.Course;
import org.courseregistration.client.model.Link;

import java.util.List;

public class CourseResponse {
    private Course course;
    private List<Link> links;
    private List<Content> content;

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

    public List<Content> getContent() {
        return content;
    }

    public void setContent(List<Content> content) {
        this.content = content;
    }
}
