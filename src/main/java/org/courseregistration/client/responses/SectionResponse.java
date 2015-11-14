package org.courseregistration.client.responses;

import java.util.List;

import org.courseregistration.client.model.Link;
import org.courseregistration.client.model.Page;
import org.courseregistration.client.model.Section;

public class SectionResponse {
	private Section section;
	private List<Link> links;
	private List<Section> content;
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

	public List<Section> getContent() {
		return content;
	}

	public void setContent(List<Section> content) {
		this.content = content;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	@Override
	public String toString() {
		return "SectionResponse{" + "Section=" + section.toString()
				+ ", links=" + links.toString() + '}';
	}
}
