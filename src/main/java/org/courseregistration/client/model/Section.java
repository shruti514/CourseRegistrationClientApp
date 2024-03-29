package org.courseregistration.client.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

public class Section {
	private Long id;
	private Professor professor;
	private Course course;
	private String semester;
	private String classStartTime;
	private String classEndTime;
	private String dayOfWeek;
	private Date startDate;
	private Date endDate;
	private String roomNumber;
	private Integer totalCapacity;
	private Integer numberOfEnrolledStudents;
	private Integer waitListCapacity;
	private String modeOfInstruction;
	private Integer price;
    @JsonProperty("students")
	private List<Student> student;
	@JsonIgnore
	private List<Link> links;

	public Professor getProfessor() {
		return professor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getClassStartTime() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Date parsedDate = sdf.parse(classStartTime);
		SimpleDateFormat sdf2 = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		return sdf2.format(parsedDate);
	}

	@JsonIgnore
	public String getClassStartTimeForView() {
		return classStartTime;
	}

	public void setClassStartTime(String classStartTime) {
		this.classStartTime = classStartTime;
	}

	public String getClassEndTime() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Date parsedDate = sdf.parse(classEndTime);
		SimpleDateFormat sdf2 = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		return sdf2.format(parsedDate);
	}

	@JsonIgnore
	public String getClassEndTimeForView() {
		return classEndTime;
	}

	public void setClassEndTime(String classEndTime) {
		this.classEndTime = classEndTime;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Integer getTotalCapacity() {
		return totalCapacity;
	}

	public void setTotalCapacity(Integer totalCapacity) {
		this.totalCapacity = totalCapacity;
	}

	public Integer getWaitListCapacity() {
		return waitListCapacity;
	}

	public void setWaitListCapacity(Integer waitListCapacity) {
		this.waitListCapacity = waitListCapacity;
	}

	public String getModeOfInstruction() {
		return modeOfInstruction;
	}

	public void setModeOfInstruction(String modeOfInstruction) {
		this.modeOfInstruction = modeOfInstruction;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Section))
			return false;

		Section section = (Section) o;

		if (!getId().equals(section.getId()))
			return false;
		if (!professor.equals(section.professor))
			return false;
		if (!course.equals(section.course))
			return false;
		if (!semester.equals(section.semester))
			return false;
		if (!classStartTime.equals(section.classStartTime))
			return false;
		if (!classEndTime.equals(section.classEndTime))
			return false;
		if (!dayOfWeek.equals(section.dayOfWeek))
			return false;
		if (!startDate.equals(section.startDate))
			return false;
		if (!endDate.equals(section.endDate))
			return false;
		if (!roomNumber.equals(section.roomNumber))
			return false;
		if (!totalCapacity.equals(section.totalCapacity))
			return false;
		if (!waitListCapacity.equals(section.waitListCapacity))
			return false;
		return modeOfInstruction.equals(section.modeOfInstruction);

	}

	@Override
	public int hashCode() {
		int result = getId().hashCode();
		result = 31 * result + professor.hashCode();
		result = 31 * result + course.hashCode();
		result = 31 * result + semester.hashCode();
		result = 31 * result + classStartTime.hashCode();
		result = 31 * result + classEndTime.hashCode();
		result = 31 * result + dayOfWeek.hashCode();
		result = 31 * result + startDate.hashCode();
		result = 31 * result + endDate.hashCode();
		result = 31 * result + roomNumber.hashCode();
		result = 31 * result + totalCapacity.hashCode();
		result = 31 * result + waitListCapacity.hashCode();
		result = 31 * result + modeOfInstruction.hashCode();
		result = 31 * result + price.hashCode();
		return result;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub

		StringBuilder builder = new StringBuilder();
		builder.append("\n\tSemester: " + semester);
		builder.append(course.toString());
		builder.append("\n\t[ Class timings From " + classStartTime + " To "
				+ classEndTime + "] ");
		builder.append("on " + dayOfWeek + " in a week");
		builder.append("\n\t[ Class Schedule: " + startDate);
		builder.append(" to " + endDate + "]");
		builder.append(" Room: " + roomNumber);
		builder.append("\n\t[ Capacity: total-" + totalCapacity);
		builder.append(", Wait list-" + waitListCapacity + "]");
		if(modeOfInstruction!=null)
			builder.append("\n\tMode of Instruction :\t" + modeOfInstruction);

		builder.append("\n\tSection price in USD: \t" + price);

		builder.append(professor.toString());
		builder.append("\n");

		return builder.toString();
	}

	@JsonIgnore
	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	@JsonIgnore
	public Integer getNumberOfEnrolledStudents() {
		return student.size();
	}

	public void setNumberOfEnrolledStudents(Integer numberOfEnrolledStudents) {
		this.numberOfEnrolledStudents = numberOfEnrolledStudents;
	}

	//@JsonIgnore
	public List<Student> getStudent() {
		return student;
	}

	public void setStudent(List<Student> student) {
		this.student = student;
	}

}
