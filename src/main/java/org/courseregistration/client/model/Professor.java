package org.courseregistration.client.model;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.courseregistration.client.auth.User;

public class Professor extends User {
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
	@JsonIgnore
	private List<Link> link;
	private String hashedPassword;

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
		if (!officeHoursFromTime.contains("-"))
			officeHoursFromTime = "2011-01-01T" + officeHoursFromTime;
		this.officeHoursFromTime = officeHoursFromTime;
	}

	public String getOfficeHoursToTime() {
		return officeHoursToTime;
	}

	public void setOfficeHoursToTime(String officeHoursToTime) {
		if (!officeHoursToTime.contains("-"))
			officeHoursToTime = "2011-01-01T" + officeHoursToTime;
		this.officeHoursToTime = officeHoursToTime;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n\tProfessor:__________________________________");
		builder.append("\n\tName :\t" + getFirstName() + " " + getLastName());
		builder.append("\n\t[ Email: " + emailId);
		if(bio!=null)
			builder.append("\n\t[ Bio: " + bio);
		builder.append(", Phone: " + phoneNumber);
		if(dateOfBirth!=null)
			builder.append(", Date of birth :" + dateOfBirth + "] ");
		if (address != null)
			builder.append(address.toString());
		if (facultyType != null)
			builder.append("\n\t[ Faculty type: " + facultyType);
		if(yearsOfExperience!=null)
			builder.append(", Years of Experience: " + yearsOfExperience);
		if(officeHoursFromTime!=null && officeHoursToTime !=null)
			builder.append(", Office hours: from " + officeHoursFromTime
				+ " to " + officeHoursToTime + "] ");

		return builder.toString();
	}

	public List<Link> getLink() {
		return link;
	}

	public void setLink(List<Link> link) {
		this.link = link;
	}

	public String getHashedPassword() {
		return hashedPassword;
	}

	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}
}
