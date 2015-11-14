package org.courseregistration.client.responses;

import java.util.List;

import org.courseregistration.client.model.Link;
import org.courseregistration.client.model.Student;

public class StudentResponse {
    private Student student;
    private List<Student> content;
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

    public List<Student> getAllStudents () {
        return content;
    }

    public void setAllStudents () {
        this.content = content;
    }

    public void deleteStudent() {

    }


    @Override
    public String toString() {
        return "StudentResponse{" +
                "student=" + student.toString() +
                ", links=" + links.toString() +
                '}';
    }
}
