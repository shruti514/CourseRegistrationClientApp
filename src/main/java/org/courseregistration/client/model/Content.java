package org.courseregistration.client.model;


public class Content {

    private Section section;
    private Course course;

    /**
     * get section
     * @return Section
     */
    public Section getSection() {
        return section;
    }

    /**
     * set section
     * @param section
     */
    public void setSection(Section section) {
        this.section = section;
    }

    /**
     * get course
     * @return Course
     */
    public Course getCourse() {
        return course;
    }

    /**
     * set course
     * @param course
     */
    public void setCourse(Course course) {
        this.course = course;
    }

}
