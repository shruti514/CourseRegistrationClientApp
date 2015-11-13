package org.courseregistration.client.student;

import java.util.List;

public class StudentResponse {
    private Student student;
    private List<Link> links;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    @Override
    public String toString() {
        return "StudentResponse{" +
                "student=" + student.toString() +
                ", links=" + links.toString() +
                '}';
    }
}
