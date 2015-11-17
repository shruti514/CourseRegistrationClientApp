package org.courseregistration.client.responses;

import org.courseregistration.client.model.Link;
import org.courseregistration.client.model.Page;
import org.courseregistration.client.model.Professor;

import java.util.List;

public class ProfessorResponse {
    private Professor professor;
    private List<ProfessorResponse> content;
    private List<Link> links;
    private Page page;

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<ProfessorResponse> getContent(){return content;}

    public void setContent(List<ProfessorResponse> content){this.content = content;}

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public Page getPage(){return page;}
    public void setPage(Page page){this.page = page;}
    public void deleteProfessor(Professor professor) {

    }

    @Override
    public String toString(){
        StringBuilder toReturn = new StringBuilder("ProfessorResponse{");
        if (professor != null){
            toReturn.append("Student : {");
            toReturn.append(professor.toString());
        }
        if (content != null ){
            toReturn.append("content : [");
            for(ProfessorResponse response : content){
                toReturn.append("{");
                toReturn.append(response.toString());
                toReturn.append("}");
            }
            toReturn.append("]");
        }
        if (links != null){
            toReturn.append("links : [");
            for(Link link : links){
                toReturn.append(link.toString());
            }
            toReturn.append("]");
        }
        if(page != null){
            toReturn.append(page.toString());
        }

        toReturn.append("}");
        return toReturn.toString();
    }
}



















