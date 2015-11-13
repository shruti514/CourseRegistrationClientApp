package org.courseregistration.client.student;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;

public class Professor  extends BaseEntity{
    @JsonProperty("firstname")
    private String firstName;
    private String username;
    @JsonProperty("lastname")
    private String lastName;
    @JsonProperty("email")
    private String emailId;
    private String phoneNumber;
    private Date dateOfBirth;
    private Address address;
    private String facultyType;
    private Integer yearsOfExperience;
    private Date officeHoursFromTime;
    private Date officeHoursToTime;

    private Link link;

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFacultyType() {
        return facultyType;
    }

    public void setFacultyType(String facultyType) {
        this.facultyType = facultyType;
    }

    public Integer getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(Integer yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public Date getOfficeHoursFromTime() {
        return officeHoursFromTime;
    }

    public void setOfficeHoursFromTime(Date officeHoursFromTime) {
        this.officeHoursFromTime = officeHoursFromTime;
    }

    public Date getOfficeHoursToTime() {
        return officeHoursToTime;
    }

    public void setOfficeHoursToTime(Date officeHoursToTime) {
        this.officeHoursToTime = officeHoursToTime;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    public String toString() {
        // TODO Auto-generated method stub

        StringBuilder builder = new StringBuilder();
        builder.append("\n\tProfessor:__________________________________");
        builder.append("\n\tName :\t" + firstName + " "
            + lastName);
        builder.append("\n\t[ Email: " + emailId);
        builder.append(", Phone: " + phoneNumber);
        builder.append(", Date of birth :" + dateOfBirth + "] ");
        builder.append(address.toString());
        builder.append("\n\t[ Faculty type: " + facultyType);
        builder.append(", Years of Experience: " + yearsOfExperience);
        builder.append(", Office hours: from " + officeHoursFromTime + " to "
            + officeHoursToTime + "] ");

        return builder.toString();
    }

}
