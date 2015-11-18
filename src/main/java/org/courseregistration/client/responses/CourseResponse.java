package org.courseregistration.client.responses;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.courseregistration.client.model.Course;
import org.courseregistration.client.model.Link;
import org.courseregistration.client.model.Page;

public class CourseResponse {
	private Course course;
	private List<Link> links;
	private List<CourseResponse> content;
	private Page page;

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@JsonIgnore
	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	public List<CourseResponse> getContent() {
		return content;
	}

	public void setContent(List<CourseResponse> content) {
		this.content = content;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

}
