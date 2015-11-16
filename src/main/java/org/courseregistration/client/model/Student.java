package org.courseregistration.client.model;


import org.courseregistration.client.auth.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Student extends User {
    private Long id;
    private String firstName;
    private String username;

    private String middleName;
    private String lastName;
    private String emailId;
    private String phoneNumber;

    private Date dateOfBirth;
    private Address address;
    private String admissionType;
    private String previousDegree;

    private List<Section> sections = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAdmissionType() {
        return admissionType;
    }

    public void setAdmissionType(String admissionType) {
        this.admissionType = admissionType;
    }

    public String getPreviousDegree() {
        return previousDegree;
    }

    public void setPreviousDegree(String previousDegree) {
        this.previousDegree = previousDegree;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public void addSection(Section section) {
        this.sections.add(section);
    }

    public void dropSection(Section section) {
        if (this.sections.contains(section)) {
            this.sections.remove(section);
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
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

    @Override
    public String toString() {
        // TODO Auto-generated method stub

        String userDetails = super.toString();
        StringBuilder builder = new StringBuilder();
        builder.append("\n\tStudent:__________________________________");
        builder.append(userDetails);
        builder.append("\n\tName :\t" + firstName + " " + middleName + " "
                + lastName);
        builder.append("\n\t[ Email: " + emailId);
        builder.append(", Phone: " + phoneNumber);
        builder.append(", Date of birth :" + dateOfBirth + "] ");
        builder.append(address.toString());
        builder.append("\n\t[ Admission Type: " + admissionType);
        builder.append(", Previous Degree: " + previousDegree + "]");

        return builder.toString();
    }
}
