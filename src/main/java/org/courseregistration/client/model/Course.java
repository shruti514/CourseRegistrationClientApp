package org.courseregistration.client.model;

import java.util.List;

public class Course extends BaseEntity {

    private String code;
    private String name;
    private String description;
    private Integer numOfCredits;
    private String prerequisiteCourse;
    private String department;
    private String program;

    private Link link;

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNumOfCredits() {
        return numOfCredits;
    }

    public void setNumOfCredits(Integer numOfCredits) {
        this.numOfCredits = numOfCredits;
    }

    public String getPrerequisiteCourse() {
        return prerequisiteCourse;
    }

    public void setPrerequisiteCourse(String prerequisiteCourse) {
        this.prerequisiteCourse = prerequisiteCourse;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Course))
            return false;

        Course course = (Course) o;

        if (!getId().equals(course.getId()))
            return false;
        if (!code.equals(course.code))
            return false;
        if (!name.equals(course.name))
            return false;
        if (!description.equals(course.description))
            return false;
        if (!numOfCredits.equals(course.numOfCredits))
            return false;
        if (!department.equals(course.department))
            return false;
        return program.equals(course.program);

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + code.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + numOfCredits.hashCode();
        result = 31 * result + department.hashCode();
        result = 31 * result + program.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n\tCourse Details:__________________________________");
        builder.append("\n\tCode: " + code);
        builder.append(", Name: " + name);
        builder.append(" [ Credits: " + numOfCredits + "]");
        builder.append("\n\t[ Course Description: " + description + "]");
        builder.append("\n\t[ Prerequisites: " + prerequisiteCourse + "]");
        builder.append("\n\t[ Department: " + department);
        builder.append(", Program: " + program + "]");
        return builder.toString();
    }
}
