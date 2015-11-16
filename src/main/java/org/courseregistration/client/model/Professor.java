package org.courseregistration.client.model;

import org.courseregistration.client.auth.User;

import java.util.Date;

public class Professor extends User{
	private Long id;
	private String firstName;
	private String username;
	private String lastName;
	private String middleName;
	private String emailId;
    private String bio;
	private String phoneNumber;
	private Date dateOfBirth;
	private Address address;
	private String facultyType;
	private Integer yearsOfExperience;
	private String officeHoursFromTime;
	private String officeHoursToTime;

	private Link link;

	public Link getLink() {
		return link;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String toString() {
		// TODO Auto-generated method stub

		StringBuilder builder = new StringBuilder();
		builder.append("\n\tProfessor:__________________________________");
		builder.append("\n\tName :\t" + getFirstName() + " " + getLastName());
		builder.append("\n\t[ Email: " + emailId);
		builder.append(", Phone: " + phoneNumber);
		builder.append(", Date of birth :" + dateOfBirth + "] ");
		builder.append(address.toString());
		builder.append("\n\t[ Faculty type: " + facultyType);
		builder.append(", Years of Experience: " + yearsOfExperience);
		builder.append(", Office hours: from " + getOfficeHoursFromTime() + " to "
				+ getOfficeHoursToTime() + "] ");

		return builder.toString();
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getOfficeHoursFromTime() {
		return officeHoursFromTime;
	}

	public void setOfficeHoursFromTime(String officeHoursFromTime) {
		this.officeHoursFromTime = officeHoursFromTime;
	}

	public String getOfficeHoursToTime() {
		return officeHoursToTime;
	}

	public void setOfficeHoursToTime(String officeHoursToTime) {
		this.officeHoursToTime = officeHoursToTime;
	}

}
