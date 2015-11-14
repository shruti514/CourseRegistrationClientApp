package org.courseregistration.client.responses;

import java.util.List;

import org.courseregistration.client.model.Link;
import org.courseregistration.client.model.Page;
import org.courseregistration.client.model.Student;

public class StudentResponse {
    private Student student;
    private List<StudentResponse> content;
    private List<Link> links;
    private Page page;



    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<StudentResponse> getContent() {
        return content;
    }

    public void setContent(List<StudentResponse> content) {
        this.content = content;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public void deleteStudent(Student student) {
        System.out.println("Student Deleted");
    }

    @Override
    public String toString() {
        return "StudentResponse{" +
                //"student=" + student.toString() +
                ", links=" + links.toString() +
                '}';
        }
}

