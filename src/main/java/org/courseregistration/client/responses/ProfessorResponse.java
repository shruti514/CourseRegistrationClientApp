package org.courseregistration.client.responses;


import org.courseregistration.client.student.Link;
import org.courseregistration.client.student.Professor;

import java.util.List;

public class ProfessorResponse {
    private Professor professor;
    private List<Link> links;

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }
}
