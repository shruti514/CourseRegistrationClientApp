package org.courseregistration.client.responses;

import java.util.List;

import org.courseregistration.client.student.Link;
import org.courseregistration.client.student.Page;
import org.courseregistration.client.student.Professor;

public class ProfessorResponse {
	private Professor professor;
	private List<Link> links;
	private List<Professor> professors;
	private Page page;

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}
}
