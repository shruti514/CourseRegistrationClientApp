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

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public List<SectionResponse> getContent() {
		return content;
	}

	public void setContent(List<SectionResponse> content) {
		this.content = content;
	}

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
