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

    }

    @Override
    public String toString() {
        StringBuilder toReturn = new StringBuilder("StudentResponse{");
        if(student != null){
            toReturn.append("student :{");
            toReturn.append(student.toString());
            toReturn.append("}");
        }
        if(content!=null){
            toReturn.append("content : [");
            for(StudentResponse response : content){
                toReturn.append("{");
                toReturn.append(response.toString());
                toReturn.append("}");
            }
            toReturn.append("]");
        }
        if(links!=null){
            toReturn.append("links : [");
            for(Link link : links){
                toReturn.append(link.toString());
            }
            toReturn.append("]");
        }
        if(page !=null){
            toReturn.append(page.toString());
        }
        toReturn.append("}");
        return toReturn.toString();
    }
}

