package org.courseregistration.client.model;


import org.codehaus.jackson.annotate.JsonIgnore;

public class Course {

    private Long id;
    private String code;
    private String name;
    private String description;
    private Integer numOfCredits;
    private String prerequisiteCourse;
    private String department;
    private String program;
    @JsonIgnore
    private Link link;

    /**
     * get Link
     * @return Link
     */
    public Link getLink() {
        return link;
    }

    /**
     * set link
     * @param link
     */
    public void setLink(Link link) {
        this.link = link;
    }

    /**
     * get ID
     * @return Long
     */
    public Long getId() {
        return id;
    }

    /**
     * set Id
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * get code
     * @return String
     */
    public String getCode() {
        return code;
    }

    /**
     * set code
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * get name
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * set name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get description
     * @return String
     */
    public String getDescription() {
        return description;
    }

    /**
     * set description
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * gets number of credits
     * @return Integer
     */
    public Integer getNumOfCredits() {
        return numOfCredits;
    }

    /**
     * sets number of credits
     * @param numOfCredits
     */
    public void setNumOfCredits(Integer numOfCredits) {
        this.numOfCredits = numOfCredits;
    }

    /**
     * get prerequisite course
     * @return String
     */
    public String getPrerequisiteCourse() {
        return prerequisiteCourse;
    }

    /**
     * set prerequisite course
     * @param prerequisiteCourse
     */
    public void setPrerequisiteCourse(String prerequisiteCourse) {
        this.prerequisiteCourse = prerequisiteCourse;
    }

    /**
     * get department
     * @return String
     */
    public String getDepartment() {
        return department;
    }

    /**
     * set department
     * @param department
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * get program
     * @return String
     */
    public String getProgram() {
        return program;
    }

    /**
     * set program
     * @param program
     */
    public void setProgram(String program) {
        this.program = program;
    }

    /**
     * equals
     * @param o
     * @return
     */
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

    /**
     * hashcode
     * @return
     */
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

    /**
     * to string
     * @return
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n\tCourse Details:__________________________________");
        if(code!=null)
            builder.append("\n\tCode: " + code);
        if(name!=null)
            builder.append("\n\tName: " + name);
        if(numOfCredits !=null)
            builder.append(" [ Credits: " + numOfCredits + "]");
        if(description!=null)
            builder.append("\n\t[ Course Description: " + description + "]");
        if(prerequisiteCourse!=null)
            builder.append("\n\t[ Prerequisites: " + prerequisiteCourse + "]");
        if(department!=null)
            builder.append("\n\t[ Department: " + department);
        if(program!=null)
            builder.append(", Program: " + program + "]");
        return builder.toString();
    }
}
