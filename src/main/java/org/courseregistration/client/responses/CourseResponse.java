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

	/**
	 * get course
	 * @return course
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

	/**
	 * get links
	 * @return List<Link>
	 */
	@JsonIgnore
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
	 * get content
	 * @return
	 */
	public List<CourseResponse> getContent() {
		return content;
	}

	/**
	 * set content
	 * @param content
	 */
	public void setContent(List<CourseResponse> content) {
		this.content = content;
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

}
