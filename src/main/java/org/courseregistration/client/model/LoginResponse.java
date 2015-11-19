package org.courseregistration.client.model;

import org.courseregistration.client.responses.ProfessorResponse;
import org.courseregistration.client.responses.StudentResponse;

public class LoginResponse {
    private String username;
    private boolean isStudent;
    private boolean isProfessor;
    private boolean isAdmin;
    private StudentResponse student;
    private ProfessorResponse professor;

    /**
     * get user name
     * @return String
     */
    public String getUsername() {
        return username;
    }

    /**
     * set user name
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * get Is student
     * @return boolean
     */
    public boolean isStudent() {
        return isStudent;
    }

    /**
     * set Is student
     * @param isStudent
     */
    public void setIsStudent(boolean isStudent) {
        this.isStudent = isStudent;
    }

    /**
     * get Is professor
     * @return boolean
     */
    public boolean isProfessor() {
        return isProfessor;
    }

    /**
     * set Is professor
     * @param isProfessor
     */
    public void setIsProfessor(boolean isProfessor) {
        this.isProfessor = isProfessor;
    }

    /**
     * get Is admin
     * @return boolean
     */
    public boolean isAdmin() {
        return isAdmin;
    }

    /**
     * set Is admin
     * @param isAdmin
     */
    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    /**
     * get student
     * @return StudentResponse
     */
    public StudentResponse getStudent() {
        return student;
    }

    /**
     * set student
     * @param student
     */
    public void setStudent(StudentResponse student) {
        this.student = student;
    }

    /**
     * get professor
     * @return ProfessorResponse
     */
    public ProfessorResponse getProfessor() {
        return professor;
    }

    /**
     * set professor
     * @param professor
     */
    public void setProfessor(ProfessorResponse professor) {
        this.professor = professor;
    }

    /**
     * to string
     * @return String
     */
    @Override
    public String toString() {
        return "LoginResponse{" +
                "username='" + username + '\'' +
                ", isStudent=" + isStudent +
                ", isProfessor=" + isProfessor +
                ", isAdmin=" + isAdmin +
                ", student=" + (student != null ? student.toString() : "{}") +
                ", professor=" + (professor != null ? professor.toString() : "{}") +
                '}';
    }
}
