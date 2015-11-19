package org.courseregistration.client.responses;

import java.util.List;

import org.courseregistration.client.model.Link;
import org.courseregistration.client.model.Page;
import org.courseregistration.client.model.Student;

public class StudentResponse {
	private Student student;
	private List<StudentResponse> content;
	private List<Link> links;
	private Page page;

	/**
	 * get student
	 * @return
	 */
	public Student getStudent() {
		return student;
	}

	/**
	 * set student
	 * @param student
	 */
	public void setStudent(Student student) {
		this.student = student;
	}

	/**
	 * get content
	 * @return List<StudentResponse>
	 */
	// returns list of students
	public List<StudentResponse> getContent() {
		return content;
	}

	/**
	 * set content
	 * @param content
	 */
	public void setContent(List<StudentResponse> content) {
		this.content = content;
	}

	/**
	 * get links
	 * @return List<Link>
	 */
	public List<Link> getLinks() {
		return links;
	}

	/**
	 * set links
	 * @param links
	 */
	public void setLinks(List<Link> links) {
		this.links = links;
	}

	/**
	 * get page
	 * @return Page
	 */
	public Page getPage() {
		return page;
	}

	/**
	 * set page
	 * @param page
	 */
	public void setPage(Page page) {
		this.page = page;
	}


	/**
	 * delete students
	 */
	public void deleteStudent() {

	}


	/**
	 * update student
	 */
	public void updateStudent() {

	}

	/**
	 * get course details
	 */
	public void getCourseDetails() {

	}

	/**
	 * get all professors details
	 */
	public void getAllProfessorDetails() {

	}

	/**
	 * get all sections
	 */
	public void getAllSections() {

	}

	/**
	 * enroll section
	 */
	public void enrollSection() {

	}

	/**
	 * delete section
	 */
	public void deleteSection(){

	}

	/**
	 * convert to string datatype
	 * @return
	 */
	@Override
	public String toString() {
		StringBuilder toReturn = new StringBuilder("StudentResponse{");
		if (student != null) {
			toReturn.append("student :{");
			toReturn.append(student.toString());
			toReturn.append("}");
		}
		if (content != null) {
			toReturn.append("content : [");
			for (StudentResponse response : content) {
				toReturn.append("{");
				toReturn.append(response.toString());
				toReturn.append("}");
			}
			toReturn.append("]");
		}
		if (links != null) {
			toReturn.append("links : [");
			for (Link link : links) {
				toReturn.append(link.toString());
			}
			toReturn.append("]");
		}
		if (page != null) {
			toReturn.append(page.toString());
		}
		toReturn.append("}");
		return toReturn.toString();
	}

}
