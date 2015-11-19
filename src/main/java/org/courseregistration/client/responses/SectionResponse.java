package org.courseregistration.client.responses;

import java.util.List;

import org.courseregistration.client.model.Link;
import org.courseregistration.client.model.Page;
import org.courseregistration.client.model.Section;

public class SectionResponse {
	private Section section;
	private List<Link> links;
	private List<SectionResponse> content;
	private Page page;

	/**
	 * get section
	 * @return section
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
	 * get content
	 * @return List<SectionResponse>
	 */
	public List<SectionResponse> getContent() {
		return content;
	}

	/**
	 * set content
	 * @param content
	 */
	public void setContent(List<SectionResponse> content) {
		this.content = content;
	}

	/**
	 * convert to string datatype
	 * @return String
	 */
	@Override
	public String toString() {
		StringBuilder toReturn = new StringBuilder("SectionResponse{");
		if (section != null) {
			toReturn.append("Section :{");
			toReturn.append(section.toString());
			toReturn.append("}");
		}
		if (content != null) {
			toReturn.append("content : [");
			for (SectionResponse response : content) {
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
