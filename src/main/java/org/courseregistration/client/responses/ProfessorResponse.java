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

    /**
     * get professor
     * @return
     */
    public Professor getProfessor() {
        return professor;
    }

    /**
     * set professor
     * @param professor
     */
    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    /**
     * get content
     * @return List<ProfessorResponse>
     */
    public List<ProfessorResponse> getContent(){return content;}

    /**
     * set content
     * @param content
     */
    public void setContent(List<ProfessorResponse> content){this.content = content;}

    /**
     * get links
     * @return
     */
    public List<Link> getLinks() {
        return links;
    }

    /**
     * get links
     * @param links
     */
    public void setLinks(List<Link> links) {
        this.links = links;
    }

    /**
     * get page
     * @return Page
     */
    public Page getPage(){return page;}

    /**
     * set page
     * @param page
     */
    public void setPage(Page page){this.page = page;}

    /**
     * Update the current logged in professor profile
     */
    public void updateProfessor(){

    }

    /**
     * deletes the current logged in professor
     * @param professor
     */
    public void deleteProfessor(Professor professor) {

    }

    /**
     *  Add new Course
     */
    public void addCourse(){}

    /**
     *  get all sections
     */
    public void getAllSections(){}

    /**
     * Search for a student
     */
    public void getStudentDetails(){}


    /**
     * Convert to string datatype
     * @return
     */
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



















