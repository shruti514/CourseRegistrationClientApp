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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isStudent() {
        return isStudent;
    }

    public void setIsStudent(boolean isStudent) {
        this.isStudent = isStudent;
    }

    public boolean isProfessor() {
        return isProfessor;
    }

    public void setIsProfessor(boolean isProfessor) {
        this.isProfessor = isProfessor;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public StudentResponse getStudent() {
        return student;
    }

    public void setStudent(StudentResponse student) {
        this.student = student;
    }

    public ProfessorResponse getProfessor() {
        return professor;
    }

    public void setProfessor(ProfessorResponse professor) {
        this.professor = professor;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "username='" + username + '\'' +
                ", isStudent=" + isStudent +
                ", isProfessor=" + isProfessor +
                ", isAdmin=" + isAdmin +
                ", student=" + (student!=null?student.toString():"{}") +
                ", professor=" + (professor!=null?professor.toString():"{}") +
                '}';
    }
}
